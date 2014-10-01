<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">

    <div class="col-md-2">
        <form class="navbar-form hotel_search" role="search" onsubmit="searchByName()">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Пошук..." id="searchHotelByName">
                <div class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="searchByName()">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>
        <div class="panel-heading hotels-filter" id="filter">
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapseFilter">
                    Фільтр
                </a>
            </h4>

            <div id="collapseFilter" class="panel-collapse collapse in">
                <div class="panel-body">
                    <select id="countrySelect2" multiple="multiple" class="select2-offscreen filter-element">
                        <option></option>
                    </select>

                    <label for="rating" class="muted">Рейтинг</label>
                    <input id="rating" value="0" type="number" class="rating" min=0 max=5 step=1
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="comfort" class="muted">Комфорт</label>
                    <input id="comfort" value="0" type="number" class="rating" min=0 max=5 step=1
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="cleanliness" class="muted">Чистота</label>
                    <input id="cleanliness" value="0" type="number" class="rating" min=0 max=5 step=1
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="location" class="muted">Розташування</label>
                    <input id="location" value="0" type="number" class="rating" min=0 max=5 step=1
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="value_for_money" class="muted">Ціна/Якість</label>
                    <input id="value_for_money" value="0" type="number" class="rating" min=0 max=5 step=1
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">
                    <button id="search_hotel" type="button" class="btn btn-default btn-block filter-button"
                            onclick="searchHotels()">Пошук
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-1"></div>
    <div id="hotels-result" class="col-md-9 hotels-result">
        <div class="panel-group" id="hotelResult">

        </div>
    </div>
</div>

<script id="hotelTemplate" type="text/x-jquery-tmpl">
    <div id="result" class="panel panel-default">
        <div class="panel-heading" data-toggle="collapse">
            <h4 class="panel-title">
                <a id="hotel\${id}" data-toggle="collapse" href="#collapseHotel\${id}">
                \${name}
                </a>
            </h4>

            <div id="collapseHotel\${id}" class="panel-collapse collapse">
                <div class="panel-body">
                    <div class="row clearfix">
                        <div class="col-sm-4">
                            <div class="input-group backdown">
                                <div>
                                    <img src='\${imgUrl}' class="avatar img-circle" id="hotelImg\${id}">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <h5 >Назва Готелю</h5>
                            <h5 >Країна</h5>
                            <h5 >Регіон</h5>
                            <h5 >Рейтинг</h5>
                            <h5 >Комфорт</h5>
                            <h5 >Чистота</h5>
                            <h5 >Розташування</h5>
                            <h5>Ціна/якість</h5>
                        </div>
                        <div class="col-sm-4">
                            <h5 id="hotelName\${id}">\${name}</h5>
                            <h5 id="hotelCountry\${id}">\${region.country.name}</h5>
                            <h5 id="hotelRegion\${id}">\${region.name}</h5>
                            <h5 id="hotelRating\${id}">\${rating}</h5>
                            <h5 id="hotelComfort\${id}">\${comfort}</h5>
                            <h5 id="hotelCleanliness\${id}">\${cleanliness}</h5>
                            <h5 id="hotelLocation\${id}">\${location}</h5>
                            <h5 id="hotelValueForMoney\${id}">\${valueForMoney}</h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</script>