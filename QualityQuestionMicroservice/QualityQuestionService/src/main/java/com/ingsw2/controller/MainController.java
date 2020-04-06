package com.ingsw2.controller;

import com.ingsw2.model.*;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(path="/ecommerce/quality")
public class MainController {
    @Autowired
    private QualityTestRepository QualityTestRepository;
    @Autowired
    private CustomerRepository CustomerRepository;
    @Autowired
    private QuestionRepository QuestionRepository;
	
	private static JSONObject executeGet(String targetURL) throws IOException {

        HttpGet request = new HttpGet(targetURL);

        CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(request);

		HttpEntity entity = response.getEntity();

		if(entity != null){
			return new JSONObject(EntityUtils.toString(entity));
		}

        return null;

    }

    private static JSONObject executePost(String targetURL, List<NameValuePair> urlParameters) throws IOException {

        HttpPost post = new HttpPost(targetURL);

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post)){

            HttpEntity entity = response.getEntity();
			if(entity != null){
                return new JSONObject(EntityUtils.toString(entity));
            }
        }

        return null;
    }

    @PostMapping(path="/get")
    public @ResponseBody ControllerResponse getCustomerQualityTest(@RequestParam String email){

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("email", email));

        JSONObject response = null;
        try {
            response = executePost("http://"+System.getenv("customer_domain")+":"+System.getenv("customer_port")+"/ecommerce/customer/exists",urlParameters);
            if(response == null)
                return new ControllerResponse(false,"Service not disponible");
            if(!response.getString("message").equals("Exists"))
                return new ControllerResponse(false,"User not found");
            else{
                JSONObject responseSeq = executeGet("http://"+System.getenv("question_domain")+":"+System.getenv("question_port")+"/ecommerce/question/genRandomSeq?n=10");
                if(responseSeq == null)
                    return new ControllerResponse(false,"Service not disponible");
                QualityTest qualityTest = new QualityTest();
				Customer customer = CustomerRepository.findByEmail(email).orElse(null);
				if(customer == null)
					customer = new Customer(email);
                qualityTest.setCustomer(customer);
                List<Question> quests = new LinkedList<Question>();
                JSONArray arr = responseSeq.getJSONArray("data");
                for (int i = 0; i < arr.length(); i++) {
                    Question tempQuest = QuestionRepository.findByCode(arr.getString(i)).orElse(null);
					if(tempQuest == null)
						tempQuest = new Question(arr.getString(i));
                    tempQuest.setQualityTest(qualityTest);
					quests.add(tempQuest);
                }
                qualityTest.setQuestions(quests);
                QualityTestRepository.save(qualityTest);
                return new ControllerResponse<QualityTest>(true,null,qualityTest);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ControllerResponse(false,"Service not disponible");
        }
    }
}