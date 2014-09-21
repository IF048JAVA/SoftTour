<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

			
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#favorites" role="tab" data-toggle="tab">Favorites</a></li>
			<li><a href="#history" role="tab" data-toggle="tab">History</a></li>
			<li><a href="#myProfile" role="tab" data-toggle="tab">My Profile</a></li>				  
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
		
			<!-- Tab favorites -->
			<div class="tab-pane active" id="favorites">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="panel-group" id="panel-1">
							<div class="panel panel-default">
								<div class="panel-heading">
									 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-1" href="#panel-element-f1">Tour #1</a>
								</div>
								<div id="panel-element-f1" class="panel-collapse collapse">
									<div class="panel-body">
										Info abaut tour #1...
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-1" href="#panel-element-f2">Tour #2</a>
								</div>
								<div id="panel-element-f2" class="panel-collapse collapse">
									<div class="panel-body">
										Info abaut tour #2...
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Tab history -->
			<div class="tab-pane" id="history">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="panel-group" id="panel-2">
							<div class="panel panel-default">
								<div class="panel-heading">
									 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-2" href="#panel-element-f3">Tour #1</a>
								</div>
								<div id="panel-element-f3" class="panel-collapse collapse">
									<div class="panel-body">
										Info abaut tour #1...
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-2" href="#panel-element-f4">Tour #2</a>
								</div>
								<div id="panel-element-f4" class="panel-collapse collapse">
									<div class="panel-body">
										Info abaut tour #2...
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Tab myProfile -->
			<div class="tab-pane" id="myProfile">
				<div class="col-md-3 col-md-3-offset"></div>
				<div class="col-md-6 form">
				
					<form class="form-horizontal" action="http://localhost:8080/SoftTour/user/save"
                          onsubmit="return registrationControl('firstNameInput', 'EmailInput',
													'PasswordInput', 'ConfirmPasswordInput',
													'PhoneNumberInput')">
						
						<fieldset>
						
							<!-- Form Name -->
							<legend>Мій профіль</legend>

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
							
							<!-- Save Button -->
							<div class="form-group">
							<button class="btn btn-primary" type="submit">
								<span class="glyphicon glyphicon-ok"></span>
								Зберегти
							</button>
						</div>
							
						</fieldset>
					</form>
				</div>
			</div>		  

