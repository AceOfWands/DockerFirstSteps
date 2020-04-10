package com.ingsw2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ingsw2.model.Question;
import com.ingsw2.model.QuestionRepository;
import com.ingsw2.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

@SpringBootApplication
public class QuestionGeneratorServiceApplication extends SpringBootServletInitializer {
	@Autowired
	private com.ingsw2.model.QuestionRepository QuestionRepository;

	public static void main(String[] args) {
	    SpringApplication.run(QuestionGeneratorServiceApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}

	@PostConstruct
	public void init() throws IOException {
			this.QuestionRepository.deleteAll();
			Gson gson = new Gson();
			Type type = new TypeToken<List<Question>>(){}.getType();
			File jsonFile = new File(System.getenv("quests_file"));
			Reader jsonReader = new FileReader(jsonFile);
			List<Question> quests = gson.fromJson(jsonReader, type);
			jsonReader.close();
			for(Question q : quests){
				for(Response r : q.getResponses()){
					r.setQuestion(q);
				}
			}
			if(quests != null) {
				this.QuestionRepository.saveAll(quests);
				
				/*FileWriter dumpFile = new FileWriter(System.getenv("quests_file"));
				dumpFile.write(gson.toJson(this.QuestionRepository.findAll()));
				dumpFile.close();*/
			}
	}
}
