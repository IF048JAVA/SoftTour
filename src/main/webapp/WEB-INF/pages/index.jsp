<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
        <div class="col-md-3 imgfont"  style="margin-left:50px;" ><img src="img/egypt_logo.png"id="egypt" onclick="searchTours('Egypt')"><br>Єгипет</div>
        <div class="col-md-1"></div>
        <div class="col-md-3 imgfont"><img src="img/greece_logo.png" id="greece" onclick="searchTours('Greece')"><br>Греція</div>
        <div class="col-md-1"></div>
        <div class="col-md-3 imgfont"><img src="img/turkey_logo.png" id = "turkey" onclick="searchTours('Turkey')"><br>Туреччина</div>
        <div class="col-md-1"></div>
</div>
<div class="row" style="margin-bottom: 15px;">
        <div class="col-md-2"></div>
        <div class="col-md-3">
        <div class="input-group">
             <span class="input-group-addon">UAH</span>
             <input type="text" id="minPrice" class="form-control" placeholder="Введіть мінімальну ціну">
        </div>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-3">
        <div class="input-group">
            <span class="input-group-addon">UAH</span>
            <input type="text" id="maxPrice" class="form-control" placeholder="Введіть максимальну ціну">
        </div>
        </div>
        <div class="col-md-2"></div>
</div>

<div id="indexResult" style="visibility: hidden; background-color:rgba(255,255,255,0.4); border-radius:6px; margin:10px;">
<div class="panel-group" style="background-color:rgba(255,255,255,0); margin: 10px;">
        <div class="panel panel-default">
            <div class="panel-heading" >
                <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-2" href="#panel-element-h1">
                    <span class="tabTitleFont">Країна:</span>
                    <span id="tourCountry-h1" class="tabulatedTitle"></span>
                    <span class="tabTitleFont">Вартість туру:</span>
                    <span id="tourPrice-h1" class="tabulatedTitle"></span>
                    <span class="tabTitleFont">Харчування:</span>
                    <span id="tourFood-h1" class="tabulatedTitle"></span>
                </a>
            </div>
            <div id="panel-element-h1" class="panel-collapse collapse">
                <div class="panel-body">
                    Info about tour #1...
                </div>
            </div>
        </div>
</div>
</div>

