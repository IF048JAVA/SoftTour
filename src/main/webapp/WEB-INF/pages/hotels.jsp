<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">

    <div class="col-md-2">
        <form class="navbar-form hotel_search" role="search">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Пошук...">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
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
                    <button id="search_hotel" type="button" class="btn btn-default btn-block filter-button" onclick="searchHotels()">Пошук</button>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-1"></div>
    <div id="hotels-result" class="col-md-9 hotels-result">
    </div>
</div>