<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

		<div class="col-md-3 col-md-3-offset"></div>
		<div class="col-md-6 form">
		
			<sf:form class="form-horizontal" method="POST" modelAttribute="user" 
				onsubmit="return registrationControl('name', 'email', 'password', 'confirmPassword', 'birthday', 'phone')">
				<fieldset>

						<!-- Form Name -->
						<legend>Реєстрація</legend>

						<!-- Name input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="name">Ім'я</label>
							<div class="col-md-5">
								<sf:input path="name" id="name" class="form-control input-md" 
									placeholder="Bід 2 до 30 символів" />
								<sf:errors path="name" cssClass="error" />
							</div>
						</div>

						<!-- Email input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="email">Email/Login</label>
							<div class="col-md-5">
								<sf:input path="email" id="email" class="form-control input-md" 
									placeholder="Bведіть email" />
								<sf:errors path="email" cssClass="error" />
							</div>
						</div>

						<!-- Password input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="password">Пароль</label>
							<div class="col-md-5">
								<sf:password path="password" id="password"  class="form-control input-md" 
									placeholder="Bід 6 до 30 символів" />
							</div>
						</div>

						<!-- Confirm password input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="confirmPassword">Підтвердьте Пароль</label>
							<div class="col-md-5">
								<sf:password path="" id="confirmPassword" class="form-control input-md"
									 placeholder="Повторіть введення паролю" />	 
							</div>
						</div>

						<!-- Birthday input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="birthday">Дата народження</label> 
							<div class="col-md-5">
								<sf:input path="birthday" id="birthday" class="form-control input-md"
									placeholder="yyyy-mm-dd"/>
							</div>
						</div>

						<!-- Sex Select -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="sex">Стать</label>
							<div class="col-md-5">
								<sf:select path="sex" id="sex" class="form-control">
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
									placeholder="+38(xxx)xxx-xx-xx"/>
								<p class="help-block">+38(xxx)xxx-xx-xx</p>
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