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
             <span class="input-group-addon">UAH</span>
             <input type="text" id="minPrice" class="form-control" placeholder="0">
        </div>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-3">
            <strong>Введіть максимальну ціну туру:</strong>
        <div class="input-group">
            <span class="input-group-addon">UAH</span>
            <input type="text" id="maxPrice" class="form-control" placeholder="9999">
        </div>
        </div>
        <div class="col-md-2"></div>
    <div id="resText" class="col-md-12" style="visibility: hidden;"><h4  style="text-align: center;">Результати пошуку</h4></div>
</div>

<div id="indexResult" class="parent-group" style="visibility: hidden; background-color:rgba(255,255,255,0.4); border-radius:6px; padding:10px;">
    <!--Content from tour.js!-->
</div>
