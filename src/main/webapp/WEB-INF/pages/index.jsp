<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<h2 style="text-align: center">Введіть мінімальну та максимальну ціну для пошуку туру та виберіть країну</h2>

<div class="row">
        <div class="col-md-3 imgfont"  style="margin-left:50px;"><img src="img/egypt_logo.png"id="egypt" onclick="parseTour('Єгипет',1),clearArray()"><br>Єгипет</div>
        <div class="col-md-1"></div>
        <div class="col-md-3 imgfont"><img src="img/greece_logo.png" id="greece" onclick="parseTour('Греція',1),clearArray()"><br>Греція</div>
        <div class="col-md-1"></div>
        <div class="col-md-3 imgfont"><img src="img/turkey_logo.png" id = "turkey" onclick="parseTour('Туреччина',1),clearArray()"><br>Туреччина</div>
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
            <input type="text" id="TravelersAdult" class="form-control" placeholder="1">
        </div></div>
        <div class="col-md-3"style="text-align: center;">
        <strong >Введіть кількість дітей:</strong>
        <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user cursor-pointer"></i></span>
            <input type="text" id="TravelersChildren" class="form-control" placeholder="0">
        </div></div>
        <div class="col-md-1"></div>
</div>



<div id="indexResult" class="parent-group" style="visibility: hidden; text-align:center; background-color:rgba(255,255,255,0.4); border-radius:6px; padding:15px;  margin-bottom:15px;padding-bottom: 120px;">


    <!--Content from template!-->
</div>
<script id="indexTemplate" type="text/x-jquery-tmpl">
            <div class="panel panel-default" id="panel-favorite\${id}">
                <div class="panel-heading" id="results\${id}">
                         <span data-toggle="collapse" href="#panel-element-f\${id}" onclick="loadAddInfo(\${id})">
                         <span class="tabTitleFont cursor-pointer" >Країна: </span>
                         <span id="tourCountry-f\${id}" class="tabulatedTitle cursor-pointer">\${hotel.region.country.name}</span>
                         <span class="tabTitleFont cursor-pointer" >Тривалість туру: </span>
                         <span id="tourDays-f\${id}" class="tabulatedTitle cursor-pointer">\${days} Днів</span>
                         <span class="tabTitleFont cursor-pointer">Вартість туру: </span>
                         <span id="tourPrice-f\${id}" class="tabulatedTitle cursor-pointer">\${price} $</span>
                         <span class="tabTitleFont cursor-pointer">Харчування: </span>
                         <span id="tourFood-f\${id}" class="tabulatedTitle cursor-pointer">\${food}</span>
                         <span class="tabTitleFont cursor-pointer">Дата вильоту: </span>
                         <span id="tourDepartureDate-f\${id}" class="tabulatedTitle cursor-pointer">\${date}</span></span>
                          <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                         <span id="deleteButtonF\${id}" data-role="button" class="pull-right" ><i class="glyphicon glyphicon-star-empty cursor-pointer" onclick="saveFavorites(\${id})"></i></span>
                         </security:authorize>
                </div>
                    <div id="panel-element-f\${id}" class="panel-collapse collapse">
                    <div class="panel-body">
                       <div class="col-sm-4">
                        <div class="input-group backdown">
                            <div id="imgHold\${id}"></div>
                        </div>
                    </div>

                    <div class="col-sm-7">
                        <strong class="text-info pull-left hotelTitle" id="hotel\${id}">
                            \${hotel.name}&nbsp;
                        </strong>
                        <span>
                            <input id="stars" value=\${hotel.stars} type="number" class="rating" min=0 max=5 step=1 data-size="xs"
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
                        <h4 id="adultsAmount\${id}">\${adultAmount}</h4>
                        <h4 id="childrenAmount\${id}">\${childrenAmount}</h4>
                        <h4 id="hotelRaiting\${id}">-</h4>
                        <h4 id="hotelRoomType\${id}">\${roomType}</h4>
                        <h4 id="departyreCity\${id}">\${departureCity}</h4>
                    </div>



                      </div>

                </div>
            </div>
            <script type='text/javascript' src='<c:url value="js/star-rating.min.js"/>'/>
</script>
<!--<div class = "col-md-5"></div>
<div class = "col-md-1"><button type="button" class="btn btn-primary" onclick="expandParse()">Expand</button></div>
<div class = "col-md-6"></div>-->