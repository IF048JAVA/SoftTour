<html>
<head>
<meta charset="utf-8">
<title>registration</title>

<link href="css/profileStyle.css" rel="stylesheet">
	<link href="css/datepicker.css" rel="stylesheet">
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-theme.css" rel="stylesheet">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet">
	
	<script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/bootstrap-datepicker-ua.js"></script>
</head>

<body class="body">
	<div class="container">
		<div class="col-md-6 form" id="location_registration" >

			<form class="form-horizontal">
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
								<script type="text/javascript">
									
									// When the document is ready
									$(document).ready(function () {
										$('#birthdayInput').datepicker({
											format: 'dd/mm/yyyy'
										});	
									});  
								</script>
						</div>

						<!-- Sex Select -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="SexInput">Стать</label>
							<div class="col-md-5">
								<select class="form-control">
									<option>Чоловік</option>
									<option>Жінка</option>
								</select>
							</div>
						</div>


						<!-- Phone input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="PhoneNuvmerInput">Номер
								телефону</label>
							<div class="col-md-5">
								<input id="PhoneNuvmerInput" name="PhoneNuvmerInput" type="text"
									placeholder="" class="form-control input-md">
								<p class="help-block">+38(xxx)xxx-xx-xx</p>
							</div>
						</div>

						<!-- Save Button -->
						<div class="form-group">
							<a href="#" class="btn btn-primary"><span
								class="glyphicon glyphicon-ok"></span> Зберегти</a>
						</div>

				</fieldset>
			</form>
		</div>
	</div>

</body>

</html>