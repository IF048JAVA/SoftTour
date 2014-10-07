<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2 style="text-align: center">Введіть мінімальну та максимальну ціну для пошуку туру та виберіть країну</h2>

<div class="row">
        <div class="col-md-3 imgfont"  style="margin-left:50px;"><img src="img/egypt_logo.png"id="egypt" onclick="searchTours('Egypt')"><br>Єгипет</div>
        <div class="col-md-1"></div>
        <div class="col-md-3 imgfont"><img src="img/greece_logo.png" id="greece" onclick="searchTours('Greece')"><br>Греція</div>
        <div class="col-md-1"></div>
        <div class="col-md-3 imgfont"><img src="img/turkey_logo.png" id = "turkey" onclick="searchTours('Turkey')"><br>Туреччина</div>
        <div class="col-md-1"></div>
</div>
<div class="row" style="margin-bottom: 15px;">
        <div class="col-md-2"></div>
        <div class="col-md-3">
            <strong>Введіть мінімальну ціну туру:</strong>
        <div class="input-group">
             <span class="input-group-addon">$</span>
             <input type="text" id="minPrice" class="form-control" placeholder="0">
        </div>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-3">
            <strong>Введіть максимальну ціну туру:</strong>
        <div class="input-group">
            <span class="input-group-addon">$</span>
            <input type="text" id="maxPrice" class="form-control" placeholder="9999">
        </div>
        </div>
        <div class="col-md-2"></div>
    <div id="resText" class="col-md-12" style="visibility: hidden;"><h4  style="text-align: center;">Результати пошуку</h4></div>
</div>
<button type="button" class="btn btn-primary" onclick="parseTour()">Primary</button>
<div id="indexResult" class="parent-group" style="visibility: hidden; background-color:rgba(255,255,255,0.4); border-radius:6px; padding:10px;  margin-bottom:15px;">
    <!--Content from tour.js!-->
</div>
<script id="indexTemplate" type="text/x-jquery-tmpl">
            <div class="panel panel-default" id="panel-favorite\${id}">
                <div class="panel-heading" data-toggle="collapse" href="#panel-element-f\${id}">
                         <span class="tabTitleFont">Країна: </span>
                         <span id="tourCountry-f\${id}" class="tabulatedTitle">\${hotel.region.country.name}</span>
                         <span class="tabTitleFont">Тривалість туру: </span>
                         <span id="tourDays-f\${id}" class="tabulatedTitle">\${days} Днів</span>
                         <span class="tabTitleFont">Вартість туру: </span>
                         <span id="tourPrice-f\${id}" class="tabulatedTitle">\${price} $</span>
                         <span class="tabTitleFont">Харчування: </span>
                         <span id="tourFood-f\${id}" class="tabulatedTitle">\${food.name}</span>
                         <span class="tabTitleFont">Дата вильоту: </span>
                         <span id="tourDepartureDate-f\${id}" class="tabulatedTitle">\${date}</span>
                         <span id="deleteButtonF\${id}" data-role="button" class="pull-right" ><i class="glyphicon glyphicon-star-empty glyphicon-pointer" onclick="uncollapse(\${id}})"></i></span>
                </div>
                <div id="panel-element-f\${id}" class="panel-collapse collapse">
                    <div class="panel-body">
                        Info about tour \${id}...
                    </div>
                </div>
            </div>
</script>