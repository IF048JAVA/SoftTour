<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2 style="text-align: center">Введіть мінімальну та максимальну ціну для пошуку туру та виберіть країну</h2>

<div class="row">
        <div class="col-md-3 imgfont"  style="margin-left:50px;"><img src="img/egypt_logo.png"id="egypt" onclick="parseTour('Єгипет'),clearArray()"><br>Єгипет</div>
        <div class="col-md-1"></div>
        <div class="col-md-3 imgfont"><img src="img/greece_logo.png" id="greece" onclick="parseTour('Греція'),clearArray()"><br>Греція</div>
        <div class="col-md-1"></div>
        <div class="col-md-3 imgfont"><img src="img/turkey_logo.png" id = "turkey" onclick="parseTour('Туреччина'),clearArray()"><br>Туреччина</div>
        <div class="col-md-1"></div>
</div>
<div class="row" style="margin-bottom: 15px;margin-top: 15px;">
        <div class="col-md-1"></div>
        <div class="col-md-4"style="text-align: center;">
            <strong >Введіть ваш бюджет:</strong>
        <div class="input-group">
             <span class="input-group-addon">$</span>
             <input type="text" id="indexBudget" class="form-control" placeholder="1500">
        </div></div>
        <div class="col-md-2"></div>
        <div class="col-md-4"style="text-align: center;">
            <strong >Введіть кількість подорожуючих:</strong>
        <div class="input-group">
            <span class="input-group-addon"></span>
            <input type="text" id="Travelers" class="form-control" placeholder="1">
        </div></div>
        <div class="col-md-1"></div>
</div>



<div id="indexResult" class="parent-group" style="visibility: hidden; text-align:center; background-color:rgba(255,255,255,0.4); border-radius:6px; padding:15px;  margin-bottom:15px;">

    <!--Content from template!-->
</div>
<script id="indexTemplate" type="text/x-jquery-tmpl">
            <div class="panel panel-default" id="panel-favorite\${id}">
                <div class="panel-heading" id="results\${id}">
                         <span data-toggle="collapse" href="#panel-element-f\${id}" onclick="saveHistoryRecord(\${id})">
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
                         <span id="deleteButtonF\${id}" data-role="button" class="pull-right" ><i class="glyphicon glyphicon-star-empty cursor-pointer" onclick="saveFavorites(\${id})"></i></span>
                </div>
                <div id="panel-element-f\${id}" class="panel-collapse collapse">
                    <div class="panel-body">
                        Info about tour \${id}...
                    </div>
                </div>
            </div>
</script>