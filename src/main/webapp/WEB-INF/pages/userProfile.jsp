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

        <div class="panel panel-default" id="panel-favorite1000">
            <div class="panel-heading" data-toggle="collapse" href="#panel-element-f1000">
                             <span class="panel-title collapsed" data-parent="#panel-1">
                                 <span class="tabTitleFont">Країна: </span>
                                 <span id="tourCountry-f1000" class="tabulatedTitle">\${tour.hotel.region.country.name}</span>
                                 <span class="tabTitleFont">Тривалість туру: </span>
                                 <span id="tourDays-f1000" class="tabulatedTitle">\${tour.days} Днів</span>
                                 <span class="tabTitleFont">Вартість туру: </span>
                                 <span id="tourPrice-f1000}" class="tabulatedTitle">\${tour.price} $</span>
                                 <span class="tabTitleFont">Харчування: </span>
                                 <span id="tourFood-f1000" class="tabulatedTitle">\${tour.food.name}</span>
                                 <span class="tabTitleFont">Дата вильоту: </span>
                                 <span id="tourDepartureDate-f1000" class="tabulatedTitle">\${tour.date}</span>
                                 <span id="deleteButtonF1000" data-role="button" class="pull-right clickable"><i class="glyphicon glyphicon-remove" onclick="delFavFunction(1000);deleteFavorite(1000)"></i></span>
                             </span>
                </div>
                    <div id="panel-element-f1000" class="panel-collapse collapse">
                        <div class="panel-body">

                            <div class="col-sm-4">
                                <div class="input-group backdown">
                                    <div>
                                        <img src='\${tour.imgUrl}' class="avatar img-circle" id="hotelImg\${id}">
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-1">
                            </div>

                            <div class="col-sm-7">
                                <strong class="text-info pull-left" id="hotel\${id}">
                                    \${tour.hotel.name}
                                </strong>
                                <span>
                                    <input id="stars" value=\${tour.hotel.stars} type="number" class="rating" min=0 max=5 step=1 data-size="xxs"
                                        data-show-clear="false" data-show-caption="false" readonly="true" width="100px">
                                </span>
                            </div>

                            <div class="col-sm-3">
                                <h5>Кількість людей</h5>
                                <h5>Дорослі</h5>
                                <h5>Діти</h5>
                                <h5>Рейтинг готелю</h5>
                                <h5>Тип номерів</h5>
                                <h5>Місто вильоту</h5>
                            </div>

                            <div class="col-sm-4">
                                <h5 id="touristsAmount\${id}">\${tour}</h5>
                                <h5 id="adultsAmount\${id}">\${tour}</h5>
                                <h5 id="childrenAmount\${id}">\${tour}</h5>
                                <h5 id="hotelRaiting\${id}">\${tour.hotel.rating}</h5>
                                <h5 id="hotelRoomType\${id}">\${tour}</h5>
                                <h5 id="departyreCity\${id}">\${tour.departureCity}</h5>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script id="favoriteTemplate" type="text/x-jquery-tmpl">
            <div class="panel panel-default" id="panel-favorite\${id}">
                <div class="panel-heading" data-toggle="collapse" href="#panel-element-f\${id}">
                     <span class="panel-title collapsed" data-parent="#panel-1">
                         <span class="tabTitleFont">Країна: </span>
                         <span id="tourCountry-f\${id}" class="tabulatedTitle">\${tour.hotel.region.country.name}</span>
                         <span class="tabTitleFont">Тривалість туру: </span>
                         <span id="tourDays-f\${id}" class="tabulatedTitle">\${tour.days} Днів</span>
                         <span class="tabTitleFont">Вартість туру: </span>
                         <span id="tourPrice-f\${id}" class="tabulatedTitle">\${tour.price} $</span>
                         <span class="tabTitleFont">Харчування: </span>
                         <span id="tourFood-f\${id}" class="tabulatedTitle">\${tour.food.name}</span>
                         <span class="tabTitleFont">Дата вильоту: </span>
                         <span id="tourDepartureDate-f\${id}" class="tabulatedTitle">\${tour.date}</span>
                         <span id="deleteButtonF\${id}" data-role="button" class="pull-right clickable"><i class="glyphicon glyphicon-remove" onclick="delFavFunction(\${id});deleteFavorite(\${id})"></i></span>
                     </span>
                </div>
                <div id="panel-element-f\${id}" class="panel-collapse collapse">
                    <div class="panel-body">

                        <div class="col-sm-4">
                            <div class="input-group backdown">
                                <div>
                                    <img src='\${tour.hotel.imgUrl}' class="avatar img-circle" id="hotelImg\${id}">
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-1">
                        </div>

                        <div class="col-sm-7">
                            <strong class="text-info pull-left" id="hotel\${id}">
                                \${tour.hotel.name}
                            </strong>
                            <span>
                                <input id="stars" value=\${tour.hotel.stars} type="number" class="rating" min=0 max=5 step=1 data-size="xxs"
                                    data-show-clear="false" data-show-caption="false" readonly="true" width="100px">
                            </span>
                        </div>

                        <div class="col-sm-3">
                            <h5>Кількість людей</h5>
                            <h5>Дорослі</h5>
                            <h5>Діти</h5>
                            <h5>Рейтинг готелю</h5>
                            <h5>Тип номерів</h5>
                            <h5>Місто вильоту</h5>
                        </div>

                        <div class="col-sm-4">
                            <h5 id="touristsAmount\${id}">\${tour}</h5>
                            <h5 id="adultsAmount\${id}">\${tour}</h5>
                            <h5 id="childrenAmount\${id}">\${tour}</h5>
                            <h5 id="hotelRaiting\${id}">\${tour.hotel.rating}</h5>
                            <h5 id="hotelRoomType\${id}">\${tour}</h5>
                            <h5 id="departyreCity\${id}">\${tour.departureCity}</h5>
                        </div>

                    </div>
                </div>
            </div>

        </script>

        <script id="historyTemplate" type="text/x-jquery-tmpl">
            <div class="panel panel-default">
                <div class="panel-heading" data-toggle="collapse" href="#panel-element-h\${id}">
                     <span class="panel-title collapsed"  data-parent="#panel-1" >
                         <span class="tabTitleFont">Країна: </span>
                         <span id="tourCountry-h\${id}" class="tabulatedTitle">\${tour.hotel.region.country.name}</span>
                         <span class="tabTitleFont">Тривалість туру: </span>
                         <span id="tourDays-h\${id}" class="tabulatedTitle">\${tour.days} Днів</span>
                         <span class="tabTitleFont">Вартість туру: </span>
                         <span id="tourPrice-h\${id}" class="tabulatedTitle">\${tour.price} </span>
                         <span class="tabTitleFont">Харчування: </span>
                         <span id="tourFood-h\${id}" class="tabulatedTitle">\${tour.food.name}</span>
                         <span class="tabTitleFont">Дата вильоту: </span>
                         <span id="tourDepartureDate-h\${id}" class="tabulatedTitle">\${tour.date}</span>
                     </span>
                </div>
                <div id="panel-element-h\${id}" class="panel-collapse collapse">
                    <div class="panel-body">

                        <div class="col-sm-4">
                            <div class="input-group backdown">
                                <div>
                                    <img src='\${tour.hotel.imgUrl}' class="avatar img-circle" id="hotelImg\${id}">
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-1">
                        </div>

                        <div class="col-sm-7">
                            <strong class="text-info pull-left" id="hotel\${id}">
                                \${tour.hotel.name}
                            </strong>
                            <span>
                                <input id="stars" value=\${tour.hotel.stars} type="number" class="rating" min=0 max=5 step=1 data-size="xxs"
                                    data-show-clear="false" data-show-caption="false" readonly="true" width="100px">
                            </span>
                        </div>

                        <div class="col-sm-3">
                            <h5>Кількість людей</h5>
                            <h5>Дорослі</h5>
                            <h5>Діти</h5>
                            <h5>Рейтинг готелю</h5>
                            <h5>Тип номерів</h5>
                            <h5>Місто вильоту</h5>
                        </div>

                        <div class="col-sm-4">
                            <h5 id="touristsAmount\${id}">\${tour}</h5>
                            <h5 id="adultsAmount\${id}">\${tour}</h5>
                            <h5 id="childrenAmount\${id}">\${tour}</h5>
                            <h5 id="hotelRaiting\${id}">\${tour.hotel.rating}</h5>
                            <h5 id="hotelRoomType\${id}">\${tour}</h5>
                            <h5 id="departyreCity\${id}">\${tour.departureCity}</h5>
                        </div>

                    </div>
                </div>
            </div>
            <script type='text/javascript' src='<c:url value="js/star-rating.min.js"/>'/>
        </script>


