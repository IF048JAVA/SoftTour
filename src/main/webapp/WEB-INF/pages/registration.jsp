<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<div class="col-md-3 col-md-3-offset"></div>
		<div class="col-md-6 form">

			<form class="form-horizontal" action="http://localhost:8080/SoftTour/user/save" 
				onsubmit="return registrationControl('firstNameInput', 'EmailInput', 
													'PasswordInput', 'ConfirmPasswordInput',
													'PhoneNumberInput')">
				<fieldset>

						<!-- Form Name -->
						<legend>Реєстрація</legend>

						<!-- Name input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="firstNameInput">Ім'я</label>
							<div class="col-md-5">
								<input id="firstNameInput" name="firstNameInput" type="text"
									placeholder="" class="form-control input-md">
							</div>
						</div>

						<!-- Email input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="EmailInput">Email/Login</label>
							<div class="col-md-5">
								<input id="EmailInput" name="EmailInput" type="text"
									placeholder="" class="form-control input-md">
							</div>
						</div>

						<!-- Password input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="PasswordInput">Пароль</label>
							<div class="col-md-5">
								<input id="PasswordInput" name="PasswordInput" type="password"
									placeholder="" class="form-control input-md">
							</div>
						</div>

						<!-- Confirm password input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="ConfirmPasswordInput">Підтвердьте
								Пароль</label>
							<div class="col-md-5">
								<input id="ConfirmPasswordInput" name="ConfirmPasswordInput"
									type="password" placeholder="" class="form-control input-md">
							</div>
						</div>

						<!-- Birthday input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="birthdayInput">Дата народження</label> 
							<div class="col-md-5">
								<input  id="birthdayInput" type="text" placeholder="dd/mm/yyyy" class="form-control input-md"> 
							</div>

						</div>

						<!-- Sex Select -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="sexInput">Стать</label>
							<div class="col-md-5">
								<select id="sexInput" class="form-control">
									<option>Чоловік</option>
									<option>Жінка</option>
								</select>
							</div>
						</div>


						<!-- Phone input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="PhoneNumberInput">Номер
								телефону</label>
							<div class="col-md-5">
								<input id="PhoneNumberInput" name="PhoneNumberInput" type="text"
									placeholder="" class="form-control input-md">
								<p class="help-block">+38(xxx)xxx-xx-xx</p>
							</div>
						</div>

						<!-- Save Button -->
						<div class="form-group">
							<button class="btn btn-primary" type="submit">
								<span class="glyphicon glyphicon-ok"></span>
								Реєстрація
							</button>
						</div>

				</fieldset>
			</form>
		</div>
