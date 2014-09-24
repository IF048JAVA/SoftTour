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
<!--<button class="btn btn-primary" onclick="show()">Большая модаль</button>-->

<div id="indexModal" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content" >
            <div class="panel-group" style="margin:10px;">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a id="tourResult" class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-2" href="#panel-element-f3"></a>
                    </div>
                    <div id="panel-element-f3" class="panel-collapse collapse">
                        <div class="panel-body">
                            Info about tour #1...
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

