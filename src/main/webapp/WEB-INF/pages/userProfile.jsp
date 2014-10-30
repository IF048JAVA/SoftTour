<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

						</div>
					</div>
				</div>
			</div>
			
			<!-- Tab history -->
			<div class="tab-pane" id="history">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="panel-group" id="panel-2">

						</div>
					</div>
				</div>
			</div>
			
			<!-- Tab myProfile -->
			<div class="tab-pane" id="myProfile">
				<div class="col-md-3 col-md-3-offset"></div>
				<div class="col-md-6 form">

					<form:form id="userProfileForm" class="form-horizontal" action="/userProfile/userToUpdate" method="POST"
                          data-toggle="validator" role="form" >
						
						<fieldset>
						
							<!-- Form Name -->
							<legend>Мій профіль</legend>

                            <!-- Name input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="name">Ім'я</label>
                                <div class="col-md-5">
                                    <input type="text" name="name"  id="name" class="form-control input-md"
                                        pattern="\b[A-Za-z0-9]{2,30}\b"
                                        placeholder="Bід 2 до 30 символів"
                                        data-error="Ви ввели некоректне ім'я !"
                                        required="required"
                                        disabled/>
                                </div>
                            </div>

                            <!-- Email input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="email">Email/Login</label>
                                <div class="col-md-5">
                                    <input type="email" name="email" id="email" class="form-control input-md"
                                        pattern="\b(?!.{31})([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4})\b"
                                        placeholder="Bведіть email"
                                        data-error="Ви ввели некоректний email !"
                                        required="required"
                                        disabled/>

                                    <div class="help-block with-errors"></div>
                                    <errors path="email" cssClass="error"/>

                                </div>
                            </div>

                            <!-- Password input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="password">Пароль</label>
                                <div class="col-md-5">
                                    <input type="password" name="password" id="password" class="form-control input-md"
                                        pattern="\b[A-Za-z0-9._%+-]{6,30}\b"
                                        placeholder="Bід 6 до 30 символів"
                                        data-error="Ви ввели некоректний пароль !"/>

                                    <div class="help-block with-errors"></div>
                                </div>
                            </div>

                            <!-- Confirm password input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="confirmPassword">Підтвердьте Пароль</label>
                                <div class="col-md-5">
                                    <input type="password" name="confirmPassword" id="confirmPassword" class="form-control input-md"
                                           placeholder="Повторіть введення паролю"
                                           data-match="#password"
                                           data-match-error="Некоректно підтверджений пароль !"/>

                                    <div class="help-block with-errors"></div>
                                </div>
                            </div>

                            <!-- Birthday input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="birthday">Дата народження</label>
                                <div class="col-md-5">
                                    <input type="text" name="birthday" id="birthday" class="form-control input-md"
                                           pattern="(((19|20)[0-9][0-9])-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]))\b"
                                           placeholder="yyyy-mm-dd"
                                           data-error="Ви ввели некоректну дату !"
                                           required="required"/>

                                    <div class="help-block with-errors"></div>
                                    <errors path="birthday" cssClass="error" />
                                </div>
                            </div>

                            <!-- Sex Select -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="sex">Стать</label>
                                <div class="col-md-5">
                                    <select name="sex" id="sex" class="form-control">
                                        <option value="MALE">Чоловік</option>
                                        <option value="FEMALE">Жінка</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Phone input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="phone">Номер телефону</label>
                                <div class="col-md-5">
                                    <input type="text" name="phone" id="phone" class="form-control input-md"
                                        pattern="[\+]?[0-9]{4,19}\b"
                                        placeholder="Введіть номер телефону"
                                        data-error="Ви ввели некоректний номер телефону !">

                                    <div class="help-block with-errors"></div>
                                    <errors path="email" cssClass="error"/>
                                </div>
                            </div>

                            <!-- Save Button -->
                            <div class="form-group">
                                <button type="submit" name="submit" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    Зберегти
                                </button>
                            </div>
							
						</fieldset>
                    </form:form>
				</div>
			</div>
        </div>

<jsp:include page="favoriteTemplate.jsp"/>
<jsp:include page="historyTemplate.jsp"/>


