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

        <script id="favoriteTemplate" type="text/x-jquery-tmpl">
            <div class="panel panel-default tourCollapseTitle" id="panel-favorite\${tour.id}">
                <div class="panel-heading" data-toggle="collapse" href="#panel-element-f\${id}">
                    <div class="panel-title collapsed" data-parent="#panel-1">
                        <table border="0">
                            <tr>
                                <td width="160">
                                    <span class="tabulatedTitle">Країна: </span>
                                    <span class="tabTitleFont">\${tour.hotel.region.country.name}</span>
                                </td>
                                <td width="230">
                                    <span class="tabulatedTitle">Тривалість туру: </span>
                                    <span class="tabTitleFont">\${tour.days} Днів</span>
                                </td>
                                <td width="200">
                                    <span class="tabulatedTitle">Вартість туру: </span>
                                    <span class="tabTitleFont">\${tour.price} $</span>
                                </td>
                                <td width="180">
                                    <span class="tabulatedTitle">Харчування: </span>
                                    <span class="tabTitleFont">\${tour.food}</span>
                                </td>
                                <td width="300">
                                    <span class="tabulatedTitle">Дата вильоту: </span>
                                    <span class="tabTitleFont">\${tour.date}&nbsp;&nbsp;&nbsp;&nbsp;\${tour.departureTime}</span>
                                </td>
                                <td width="30">
                                    <span id="deleteButtonF\${id}" data-role="button" class="pull-right clickable">
                                        <i class="glyphicon glyphicon-remove" onclick="delFavFunction(\${id});deleteFavorite(\${id})">
                                        </i>
                                    </span>
                                </td>
                            </tr>
                       </table>
                    </div>
                </div>
            <div id="panel-element-f\${id}" class="panel-collapse collapse">
                <div class="panel-body">

                    <div class="col-sm-4">
                        <div class="input-group backdown">
                            <div>
                                <img src='\${tour.hotel.imgUrl}' class="hotel-img-inTour img-circle" id="hotelImg\${id}">
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-7">
                        <strong class="text-info pull-left hotelTitle" id="hotel\${id}">
                            \${tour.hotel.name}&nbsp;
                        </strong>
                        <span>
                            <input id="stars" value=\${tour.hotel.stars} type="number" class="rating" min=0 max=5 step=1 data-size="xs"
                                data-show-clear="false" data-show-caption="false" readonly="true" width="100px">
                        </span>
                    </div>

                    <div class="col-sm-3">
                        <h4>Дорослі</h4>
                        <h4>Діти</h4>
                        <h4>Рейтинг готелю</h4>
                        <h4>Тип номерів</h4>
                        <h4>Місто вильоту</h4>
                    </div>

                    <div class="col-sm-3">
                        <h4 id="adultsAmount\${id}">\${tour.adultAmount}</h4>
                        <h4 id="childrenAmount\${id}">\${tour.childrenAmount}</h4>
                        <h4 id="hotelRaiting\${id}">\${tour.hotel.rating}</h4>
                        <h4 id="hotelRoomType\${id}">\${tour.roomType}</h4>
                        <h4 id="departyreCity\${id}">\${tour.departureCity}</h4>
                    </div>

                    <div class="col-sm-1">

                        <!-- Button trigger modal -->

                        <button type="button" class="btn btn-info btn-TourOrder" onclick="showTourOrderForm(\${tour.id})">
                            <span class="glyphicon glyphicon-briefcase"></span>
                            <h4>Замовити тур!</h4>
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="orderModal\${tour.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel\${tour.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                        <h4 class="modal-title" id="myModalLabel\${tour.id}">Введіть дані</h4>
                                    </div>
                                    <div class="modal-body">

                                        <form:form id="orderTourForm\${tour.id}" class="form-horizontal" action="" method="POST"
                                                   data-toggle="validator" role="form" >

                                            <fieldset>

                                            <!-- Name input-->
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="name">Ім'я</label>
                                                <div class="col-md-5">
                                                    <input type="text" name="name"  id="name" class="form-control input-md"
                                                    pattern="\b[A-Za-z0-9]{2,30}\b"
                                                    placeholder="Bід 2 до 30 символів"
                                                    data-error="Ви ввели некоректне ім'я !"
                                                    required="required"
                                                    />
                                                </div>
                                            </div>

                                            <!-- Email input-->
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="email">Email</label>
                                                <div class="col-md-5">
                                                    <input type="email" name="email" id="email" class="form-control input-md"
                                                    pattern="\b(?!.{31})([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4})\b"
                                                    placeholder="Bведіть email"
                                                    data-error="Ви ввели некоректний email !"
                                                    required="required"
                                                    />

                                                    <div class="help-block with-errors"></div>
                                                    <errors path="email" cssClass="error"/>

                                                </div>
                                            </div>

                                            <!-- City input-->
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="city">Місто</label>
                                                <div class="col-md-5">
                                                    <input type="text" name="city"  id="city" class="form-control input-md"
                                                    pattern="\b[A-Za-z]{2,30}\b"
                                                    placeholder="Місто"
                                                    data-error="Введіть місто!"
                                                    required="required"
                                                    />
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

                                            <div class="form-group">
                                               <div class="col-md-5"></div>
                                                <div class="col-md-3">
                                                    <button type="submit" name="submit" class="btn btn-primary">
                                                    <span class="glyphicon glyphicon-ok"></span>
                                                    Замовити
                                                    </button>
                                                </div>
                                            </div>

                                            </fieldset>
                                        </form:form>
                                    </div>
                                    <div class="modal-footer">
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <form role="form" class="form-inline">
                    <button type="button" class="btn btn-info transitionInfo" data-toggle="collapse" data-target="#transitInfo-All-H\${tour.id}" onclick="getAllTransit(\${tour.id})">
                    <span class="glyphicon glyphicon-road"></span>
                    <span class="glyphicon glyphicon-plane"></span>
                    &nbsp; Як добратися з ... ?
                    </button>
                    <div class="form-group">
                    <input type="text" class="form-control col-sm-1 cityFromAutocomplete" id="cityFrom\${tour.id}" placeholder="Місто">
                    </div>
                    </form>
                </div>
            </div>
        </div>

            <script type='text/javascript' src='<c:url value="js/star-rating.min.js"/>'/>
            <script type='text/javascript' src='<c:url value="js/bootstrap-table.min.js"/>'/>
            <script type='text/javascript' src='<c:url value="js/cityFromAutocomplete.js"/>'/>
            <script type='text/javascript' src='<c:url value="js/orderButton.js"/>'/>
        </script>

        <script id="historyTemplate" type="text/x-jquery-tmpl">
            <div class="panel panel-default tourCollapseTitle" id="panel-history\${tour.id}">
                <div class="panel-heading" data-toggle="collapse" href="#panel-element-h\${id}">
                    <div class="panel-title collapsed" data-parent="#panel-2">
                        <table border="0">
                            <tr>
                                <td width="160">
                                    <span class="tabulatedTitle">Країна: </span>
                                    <span class="tabTitleFont">\${tour.hotel.region.country.name}</span>
                                </td>
                                <td width="230">
                                    <span class="tabulatedTitle">Тривалість туру: </span>
                                    <span class="tabTitleFont">\${tour.days} Днів</span>
                                </td>
                                <td width="200">
                                    <span class="tabulatedTitle">Вартість туру: </span>
                                    <span class="tabTitleFont">\${tour.price} $</span>
                                </td>
                                <td width="180">
                                    <span class="tabulatedTitle">Харчування: </span>
                                    <span class="tabTitleFont">\${tour.food}</span>
                                </td>
                               <td width="300">
                                    <span class="tabulatedTitle">Дата вильоту: </span>
                                    <span class="tabTitleFont">\${tour.date}&nbsp;&nbsp;&nbsp;&nbsp;\${tour.departureTime}</span>
                                </td>
                                <td width="30">
                                </td>
                            </tr>
                       </table>
                    </div>
                </div>
            <div id="panel-element-h\${id}" class="panel-collapse collapse">
                <div class="panel-body">

                    <div class="col-sm-4">
                        <div class="input-group backdown">
                            <div>
                                <img src='\${tour.hotel.imgUrl}' class="hotel-img-inTour img-circle" id="hotelImg\${id}">
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-7">
                        <strong class="text-info pull-left hotelTitle" id="hotel\${id}">
                            \${tour.hotel.name}&nbsp;
                        </strong>
                        <span>
                            <input id="stars" value=\${tour.hotel.stars} type="number" class="rating" min=0 max=5 step=1 data-size="xs"
                                data-show-clear="false" data-show-caption="false" readonly="true" width="100px">
                        </span>
                    </div>

                    <div class="col-sm-3">
                        <h4>Дорослі</h4>
                        <h4>Діти</h4>
                        <h4>Рейтинг готелю</h4>
                        <h4>Тип номерів</h4>
                        <h4>Місто вильоту</h4>
                    </div>

                    <div class="col-sm-3">
                        <h4 id="adultsAmount\${id}">\${tour.adultAmount}</h4>
                        <h4 id="childrenAmount\${id}">\${tour.childrenAmount}</h4>
                        <h4 id="hotelRaiting\${id}">\${tour.hotel.rating}</h4>
                        <h4 id="hotelRoomType\${id}">\${tour.roomType}</h4>
                        <h4 id="departyreCity\${id}">\${tour.departureCity}</h4>
                    </div>

                    <div class="col-sm-1">

                        <!-- Button trigger modal -->

                        <button type="button" class="btn btn-info btn-TourOrder" onclick="showTourOrderForm(\${tour.id})">
                            <span class="glyphicon glyphicon-briefcase"></span>
                            <h4>Замовити тур!</h4>
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="orderModal\${tour.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel\${tour.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                        <h4 class="modal-title" id="myModalLabel\${tour.id}">Введіть дані</h4>
                                    </div>
                                    <div class="modal-body">

                                        <form:form id="orderTourForm\${tour.id}" class="form-horizontal" action="" method="POST"
                                                   data-toggle="validator" role="form" >

                                            <fieldset>

                                            <!-- Name input-->
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="name">Ім'я</label>
                                                <div class="col-md-5">
                                                    <input type="text" name="name"  id="name" class="form-control input-md"
                                                    pattern="\b[A-Za-z0-9]{2,30}\b"
                                                    placeholder="Bід 2 до 30 символів"
                                                    data-error="Ви ввели некоректне ім'я !"
                                                    required="required"
                                                    />
                                                </div>
                                            </div>

                                            <!-- Email input-->
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="email">Email</label>
                                                <div class="col-md-5">
                                                    <input type="email" name="email" id="email" class="form-control input-md"
                                                    pattern="\b(?!.{31})([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4})\b"
                                                    placeholder="Bведіть email"
                                                    data-error="Ви ввели некоректний email !"
                                                    required="required"
                                                    />

                                                    <div class="help-block with-errors"></div>
                                                    <errors path="email" cssClass="error"/>

                                                </div>
                                            </div>

                                            <!-- City input-->
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="city">Місто</label>
                                                <div class="col-md-5">
                                                    <input type="text" name="city"  id="city" class="form-control input-md"
                                                    pattern="\b[A-Za-z]{2,30}\b"
                                                    placeholder="Місто"
                                                    data-error="Введіть місто!"
                                                    required="required"
                                                    />
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

                                            <div class="form-group">
                                                <div class="col-md-5"></div>
                                                <div class="col-md-3">
                                                    <button type="submit" name="submit" class="btn btn-primary">
                                                    <span class="glyphicon glyphicon-ok"></span>
                                                    Замовити
                                                    </button>
                                                </div>
                                            </div>

                                            </fieldset>
                                        </form:form>
                                    </div>
                                    <div class="modal-footer">
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <form role="form" class="form-inline">
                    <button type="button" class="btn btn-info transitionInfo" data-toggle="collapse" data-target="#transitInfo-All-H\${tour.id}" onclick="getAllTransit(\${tour.id})">
                    <span class="glyphicon glyphicon-road"></span>
                    <span class="glyphicon glyphicon-plane"></span>
                    &nbsp; Як добратися з ... ?
                    </button>
                    <div class="form-group">
                    <input type="text" class="form-control col-sm-1 cityFromAutocomplete" id="cityFrom\${tour.id}" placeholder="Місто">
                    </div>
                    </form>
                </div>
            </div>
        </div>

            <script type='text/javascript' src='<c:url value="js/star-rating.min.js"/>'/>
            <script type='text/javascript' src='<c:url value="js/bootstrap-table.min.js"/>'/>
            <script type='text/javascript' src='<c:url value="js/cityFromAutocomplete.js"/>'/>
            <script type='text/javascript' src='<c:url value="js/orderButton.js"/>'/>
        </script>


