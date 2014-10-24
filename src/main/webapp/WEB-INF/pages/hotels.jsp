<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div class="row" id="hotel_page">
    <div class="col-md-2">
        <!-- Search field -->
        <form class="navbar-form hotel_search" role="search" onsubmit="search()">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Пошук..." id="searchHotelByName">

                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>

        <!-- Filter -->
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

                    <label for="rating">Рейтинг</label>
                    <input id="rating" value="0" type="number" class="rating" min=0 max=5 step=0.5
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="comfort">Комфорт</label>
                    <input id="comfort" value="0" type="number" class="rating" min=0 max=5 step=0.5
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="cleanliness">Чистота</label>
                    <input id="cleanliness" value="0" type="number" class="rating" min=0 max=5 step=0.5
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="location">Розташування</label>
                    <input id="location" value="0" type="number" class="rating" min=0 max=5 step=0.5
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="value_for_money">Ціна/Якість</label>
                    <input id="value_for_money" value="0" type="number" class="rating" min=0 max=5 step=0.5
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="sort">Сортування</label>
                    <select id="sort" name="selectbasic" class="form-control">
                        <option value="rating">Рейтинг</option>
                        <option value="comfort">Комфортом</option>
                        <option value="cleanliness">Чистота</option>
                        <option value="location">Розташування</option>
                        <option value="valueForMoney">Ціна/якість</option>
                    </select>

                    <button id="search_hotel" type="button" class="btn btn-default btn-block filter-button"
                            onclick="showSearchResult()">Пошук
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-1"></div>

    <!-- Result panel -->
    <div id="hotels-result" class="col-md-9 hotels-result">
        <div class="pagin"></div>
        <div class="panel-group" id="hotelResult">

        </div>
        <div class="pagin"></div>

    </div>
</div>

<!-- Tours Modal -->
<div class="modal fade" id="toursModal" tabindex="-1" role="dialog"
     aria-labelledby="toursModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-tours">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"> Тури </h4>
            </div>
            <div class="modal-body tours-body">
                <div class="container tour-list" id="tour-list">
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="hotelTemplate.jsp"/>
