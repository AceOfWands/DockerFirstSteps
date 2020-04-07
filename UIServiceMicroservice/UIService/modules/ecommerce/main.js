(function(app){
	
	class EcommerceTestModule extends BobbleHead.Module{
		constructor(localDatabase){
			super('ecommerce');
			this.questions = null;
			this.currentQuestion = -1;
			this.responses = {};
		}
		init(configuration){
			super.init(configuration);
			
			this.controller('getTest', function(data, success, error){
				let email = data.get('email');
				let name = data.get('name');
				
				defaultConnector.request(new BobbleHead.ConnectorRequest('post','http://NGINX_REPLACE_CUSTOMER_DOMAIN:NGINX_REPLACE_CUSTOMER_PORT/ecommerce/customer/add',{'email': email, 'name': name}))
					.then(function(r){
						if(r.content.status == true){
							defaultConnector.request(new BobbleHead.ConnectorRequest('post','http://NGINX_REPLACE_QUALITY_DOMAIN:NGINX_REPLACE_QUALITY_PORT/ecommerce/quality/get',{'email': email}))
								.then(function(r2){
									if(r2.content.status == true){
										this.questions = r2.content.data.questions;
										this.currentQuestion = -1;
										this.responses = {};
										success();
									}else
										error();
								}.bind(this)).catch(error);
						}else
							error();
					}.bind(this)).catch(error);
			}.bind(this));
			
			this.controller('nextQuestion', function(data, success, error){
				if(this.currentQuestion == (this.questions.length - 1)){
					success(null);
				}else{
					defaultConnector.request(new BobbleHead.ConnectorRequest('get','http://NGINX_REPLACE_QUESTION_DOMAIN:NGINX_REPLACE_QUESTION_PORT/ecommerce/question/get',{'code': this.questions[this.currentQuestion+1].code}))
						.then(function(r){
							if(r.content.status == true){
								this.currentQuestion++;
								let ret = Object.assign({
									currentQuestion: this.currentQuestion+1,
									tot_questions: this.questions.length
								},r.content.data);
								success(ret);
							}else
								error();
						}.bind(this));
				}
			}.bind(this));
			
			this.controller('prevQuestion', function(data, success, error){
				this.currentQuestion--;
				defaultConnector.request(new BobbleHead.ConnectorRequest('get','app://page/back')).then(success).catch(error);
			}.bind(this));
			
			this.controller('toggleResponse', function(data, success, error){
				let id = data.get('id');
				let qid = data.get('qid');
				
				if(!this.responses[qid]) this.responses[qid] = {};
				if(!this.responses[qid][id]) this.responses[qid][id] = false;
				
				this.responses[qid][id] = !this.responses[qid][id];
				success();
			}.bind(this));
			
		}
	}
	
	let module = new EcommerceTestModule();
	app.registerModule(module);
	
})(BobbleHead.app);
	