<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2 style="text-align: center">Введіть мінімальну та максимальну ціну для пошуку туру та виберіть країну</h2>

<div class="row">
        <div class="col-md-3 imgfont"  style="margin-left:50px;"><img src="img/egypt_logo.png"id="egypt" onclick="parseTour('Єгипет',1,338),clearArray()"><br>Єгипет</div>
        <div class="col-md-1"></div>
        <div class="col-md-3 imgfont"><img src="img/greece_logo.png" id="greece" onclick="parseTour('Греція',1,372),clearArray()"><br>Греція</div>
        <div class="col-md-1"></div>
        <div class="col-md-3 imgfont"><img src="img/turkey_logo.png" id = "turkey" onclick="parseTour('Туреччина',1,318),clearArray()"><br>Туреччина</div>
        <div class="col-md-1"></div>
</div>
<div class="row" style="margin-bottom: 15px;margin-top: 15px;">
        <div class="col-md-1"></div>
        <div class="col-md-3"style="text-align: center;">
            <strong >Введіть ваш бюджет:</strong>
        <div class="input-group">
             <span class="input-group-addon">$</span>
             <input type="text" id="indexBudget" class="form-control" placeholder="1500">
        </div></div>
        <div class="col-md-1"></div>
        <div class="col-md-3"style="text-align: center;">
            <strong >Введіть кількість дорослих:</strong>
        <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user cursor-pointer"></i></span>
            <input type="text" id="TravelersAdult" class="form-control"  placeholder="1">
        </div></div>
        <div class="col-md-3"style="text-align: center;">
        <strong >Введіть кількість дітей:</strong>
        <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user cursor-pointer"></i></span>
            <input type="text" id="TravelersChildren" class="form-control" placeholder="0">
        </div></div>
        <div class="col-md-1"></div>
</div>
<!--<img src="/img/flags/Ukraine.png" class="imgC">-->


<div id="indexResult" class="parent-group" style="visibility: hidden; text-align:center; background-color:rgba(255,255,255,0.4); border-radius:6px; padding:15px;  margin-bottom:15px;padding-bottom: 120px;">


    <!--Content from template!-->
</div>
<script id="indexTemplate" type="text/x-jquery-tmpl">
            <div class="panel panel-default tourCollapseTitle" id="panel-favorite\${id}">
    <div class="panel-heading" data-toggle="collapse" href="#panel-element-f\${id}">
        <div id="results\${id}">
                         <span data-toggle="collapse" href="#panel-element-f\${id}" onclick="loadAddInfo(\${id})">
                         <span class="tabTitleFont cursor-pointer" >Країна: </span>
                         <span id="tourCountry-f\${id}" class="tabulatedTitle cursor-pointer">\${tour.hotel.region.country.name}</span>
                         <span class="tabTitleFont cursor-pointer" >Тривалість туру: </span>
                         <span id="tourDays-f\${id}" class="tabulatedTitle cursor-pointer">\${tour.days} Днів</span>
                         <span class="tabTitleFont cursor-pointer">Вартість туру: </span>
                         <span id="tourPrice-f\${id}" class="tabulatedTitle cursor-pointer">\${tour.price} $</span>
                         <span class="tabTitleFont cursor-pointer">Харчування: </span>
                         <span id="tourFood-f\${id}" class="tabulatedTitle cursor-pointer">\${tour.food}</span>
                         <span class="tabTitleFont cursor-pointer">Дата вильоту: </span>
                         <span id="tourDepartureDate-f\${id}" class="tabulatedTitle cursor-pointer">\${tour.date}</span></span>
                          <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                          <span id="deleteButtonF\${id}" data-role="button" class="pull-right" ><i class="glyphicon glyphicon-star-empty cursor-pointer" onclick="saveFavorites(\${id})"></i></span>
                          </security:authorize>
                </div>
    </div>
    <div id="panel-element-f\${id}" class="panel-collapse collapse">
        <div class="panel-body">

            <div class="col-sm-4">
                <div class="input-group backdown">
                        <div id="imgHold\${id}"></div>
                    <div>
                    </div>
                </div>
            </div>

            <div class="col-sm-7">
                <strong class="text-info pull-left hotelTitle" id="hotel\${id}">
                    \${tour.hotel.name}&nbsp;
                </strong>
                        <span>
                            <input id="stars" value=\${tour.hotel.stars} type="number" class="rating" min=0 max=5 step=1
                                   data-size="xs"
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
                <h4 id="hotelRaiting\${id}">-</h4>
                <h4 id="hotelRoomType\${id}">\${tour.roomType}</h4>
                <h4 id="departyreCity\${id}">\${tour.departureCity}</h4>
            </div>

            <div class="col-sm-1">

                <!-- Button trigger modal -->

                <button type="button" class="btn btn-info btn-TourOrder" onclick="showTourOrderFormF(\${tour.id})">
                    <span class="glyphicon glyphicon-briefcase"></span>
                    <h4>Замовити тур!</h4>
                </button>

                <!-- Modal -->
                <div class="modal fade" id="orderModal-f\${tour.id}" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel\${tour.id}" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel\${tour.id}">Введіть дані</h4>
                            </div>
                            <div class="modal-body">

                                <form:form id="orderTourForm\${tour.id}" class="form-horizontal" action="" method="POST"
                                           data-toggle="validator" role="form">

    <fieldset>

    <!-- Name input-->
    <div class="form-group">
    <label class="col-md-4 control-label" for="name">Ім'я</label>

    <div class="col-md-5">
    <input type="text" name="name" id="name" class="form-control input-md"
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
    <input type="email" name="email" id="email"
    class="form-control input-md"
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
    <input type="text" name="city" id="city" class="form-control input-md"
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
                <button type="button" class="btn btn-info transitionInfo" data-toggle="collapse"
                        data-target="#transitInfo-All-F\${tour.id}" onclick="getAllTransitF(\${tour.id},\${id})">
                    <span class="glyphicon glyphicon-road"></span>
                    <span class="glyphicon glyphicon-plane"></span>
                    &nbsp; Як добратися з ... ?
                </button>
                <div class="form-group">
                    <select id="cityFrom-F\${id}" class="select2-offscreen cityFromSelector">
                    </select>
                </div>
            </form>
        </div>
    </div>
</div>
</script>
<!--<div class = "col-md-5"></div>
<div class = "col-md-1"><button type="button" class="btn btn-primary" onclick="expandParse()">Expand</button></div>
<div class = "col-md-6"></div>-->