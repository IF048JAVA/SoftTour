<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<div class="col-md-3 col-md-3-offset"></div>
		<div class="col-md-6 form">

			<form class="form-horizontal" action="http://localhost:8080/SoftTour/user/save" 
				onsubmit="return registrationControl('name', 'email', 'password', 'confirmPassword', 'birthday', 'phone')">
				<fieldset>

						<!-- Form Name -->
						<legend>Реєстрація</legend>

						<!-- Name input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="name">Ім'я</label>
							<div class="col-md-5">
								<input type="text" name="name"  id="name" class="form-control input-md"
									placeholder="Bід 6 до 30 символів" >
							</div>
						</div>

						<!-- Email input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="email">Email/Login</label>
							<div class="col-md-5">
								<input type="text" name="email" id="email" class="form-control input-md"
									placeholder="Bід 7 до 30 символів" >
							</div>
						</div>

						<!-- Password input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="password">Пароль</label>
							<div class="col-md-5">
								<input type="password" name="password" id="password" class="form-control input-md"
									placeholder="Bід 6 до 30 символів" >
							</div>
						</div>

						<!-- Confirm password input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="confirmPassword">Підтвердьте Пароль</label>
							<div class="col-md-5">
								<input type="password" name="confirmPassword" id="confirmPassword" class="form-control input-md"
									 placeholder="Повторіть введення паролю" >
							</div>
						</div>

						<!-- Birthday input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="birthday">Дата народження</label> 
							<div class="col-md-5">
								<input type="text" name="birthday" id="birthday" class="form-control input-md"
									placeholder="dd/mm/yyyy"> 
							</div>
						</div>

						<!-- Sex Select -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="sex">Стать</label>
							<div class="col-md-5">
								<select name="sex" id="sex" class="form-control">
									<option value="male">Чоловік</option>
									<option value="female">Жінка</option>
								</select>
							</div>
						</div>

						<!-- Phone input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="phone">Номер телефону</label>
							<div class="col-md-5">
								<input type="text" name="phone" id="phone" class="form-control input-md"
									placeholder="До 20 символів">
								<p class="help-block">+38(xxx)xxx-xx-xx</p>
							</div>
						</div>

						<!-- Button of registration-->
						<div class="form-group">
							<button type="submit" class="btn btn-primary" >
								<span class="glyphicon glyphicon-ok"></span>
								Реєстрація
							</button>
						</div>

				</fieldset>
			</form>
		</div>
