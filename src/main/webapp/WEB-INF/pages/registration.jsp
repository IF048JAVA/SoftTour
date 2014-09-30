<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

		<div class="col-md-3 col-md-3-offset"></div>
		<div class="col-md-6 form">
		
			<sf:form class="form-horizontal" method="POST" modelAttribute="user" data-toggle="validator" role="form">
				<fieldset>

						<!-- Form Name -->
						<legend>Реєстрація</legend>

						<!-- Name input-->
						<div class="form-group required">
							<label class="col-md-4 control-label" for="name">Ім'я</label>
							<div class="col-md-5">
								<sf:input path="name" id="name" class="form-control input-md" 
									pattern="\b[A-Za-z0-9]{2,30}\b"
									placeholder="Bід 2 до 30 символів" 
									data-error="Ви ввели некоректне ім'я !" 
									required="required"/>
								
								<div class="help-block with-errors"></div>
								<sf:errors path="name" cssClass="error" />
							</div>
						</div>

						<!-- Email input-->
						<div class="form-group required">
							<label class="col-md-4 control-label" for="email">Email/Login</label>
							<div class="col-md-5">
								<sf:input path="email" id="email" class="form-control input-md" 
									pattern="\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}\b"
									placeholder="Bведіть email"
								 	data-error="Ви ввели некоректний email !" 
								 	required="required"/>
								 	
								<div class="help-block with-errors"></div>
								<sf:errors path="email" cssClass="error" />
							</div>
						</div>

						<!-- Password input-->
						<div class="form-group required">
							<label class="col-md-4 control-label" for="password">Пароль</label>
							<div class="col-md-5">
								<sf:password path="password" id="password" class="form-control input-md" 
									pattern="\b[A-Za-z0-9._%+-]{6,30}\b"
									placeholder="Bід 6 до 30 символів" 
									data-error="Ви ввели некоректний пароль !" 
									required="required"/>
									
								<div class="help-block with-errors"></div>	
							</div>
						</div>

						<!-- Confirm password input-->
						<div class="form-group required">
							<label class="col-md-4 control-label" for="confirmPassword">Підтвердьте Пароль</label>
							<div class="col-md-5">
								<sf:password path="" id="confirmPassword" class="form-control input-md"
									 placeholder="Повторіть введення паролю" 
									 data-match="#password" 
									 data-match-error="Некоректно підтверджений пароль !" 
									 required="required"/>	 
									 
							      <div class="help-block with-errors"></div>
							</div>
						</div>

						<!-- Birthday input-->
						<div class="form-group required">
							<label class="col-md-4 control-label" for="birthday">Дата народження</label> 
							<div class="col-md-5">
								<sf:input path="birthday" id="birthday" class="form-control input-md"
									pattern="(((19|20)[0-9][0-9])-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]))\b"
									placeholder="yyyy-mm-dd"
									data-error="Ви ввели некоректну дату !" 
									required="required"/>
									
								<div class="help-block with-errors"></div>	
								<sf:errors path="birthday" cssClass="error" />
							</div>
						</div>

						<!-- Sex Select -->
						<div class="form-group required">
							<label class="col-md-4 control-label" for="sex">Стать</label>
							<div class="col-md-5">
								<sf:select path="sex" id="sex" class="form-control"
									required="required">
									<sf:option value="MALE">Чоловік</sf:option>
									<sf:option value="FEMALE">Жінка</sf:option>
								</sf:select>
							</div>
						</div>

						<!-- Phone input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="phone">Номер телефону</label>
							<div class="col-md-5">
								<sf:input path="phone" id="phone" class="form-control input-md"
									pattern="[\+]?[0-9]{4,19}\b"
									placeholder="Введіть номер телефону"
									data-error="Ви ввели некоректний номер телефону !" />
								
								<div class="help-block with-errors"></div>
							</div>
						</div>

						<!-- Button of registration-->
						<div class="form-group">
							<sf:button type="submit" class="btn btn-primary" >
								<span class="glyphicon glyphicon-ok"></span>
								Реєстрація
							</sf:button>
						</div>

				</fieldset>
			</sf:form>
		</div>