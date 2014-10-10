<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <input id="rating" value="0" type="number" class="rating" min=0 max=5 step=1
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="comfort">Комфорт</label>
                    <input id="comfort" value="0" type="number" class="rating" min=0 max=5 step=1
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="cleanliness">Чистота</label>
                    <input id="cleanliness" value="0" type="number" class="rating" min=0 max=5 step=1
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="location">Розташування</label>
                    <input id="location" value="0" type="number" class="rating" min=0 max=5 step=1
                           data-size="xs"
                           data-show-clear="false" data-show-caption="false">

                    <label for="value_for_money">Ціна/Якість</label>
                    <input id="value_for_money" value="0" type="number" class="rating" min=0 max=5 step=1
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


<!-- Hotel template -->
<script id="hotelTemplate" type="text/x-jquery-tmpl">
<div id="result" class="panel panel-default">
    <div class="panel-heading"  data-toggle="collapse" data-parent="#hotelResult" href="#collapseHotel\${id}">
        <h4 class="panel-title">
            <strong class="text-info pull-left" id="hotel\${id}">
                \${name}
            </strong>
                <span class="text-info pull-right h4">
                    \${rating}
                </span>
                <span>
                 <input id="stars" value=\${stars} type="number" class="rating" min=0 max=5 step=1 data-size="xxs"
                        data-show-clear="false" data-show-caption="false" readonly="true" width="100px">
                </span>

                <span class="h5 text-muted">
                \${region.country.name}, \${region.name}
                </span>

        </h4>
    </div>
        <div id="collapseHotel\${id}" class="panel-collapse collapse">
            <div class="panel-body">
                <div class="col-sm-4">
                    <div class="input-group backdown">
                        <div>
                            <img src='\${imgUrl}' class="avatar img-circle" id="hotelImg\${id}">
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <h5>Назва Готелю</h5>
                    <h5>Країна</h5>
                    <h5>Регіон</h5>
                    <h5>Рейтинг</h5>
                    <h5>Комфорт</h5>
                    <h5>Чистота</h5>
                    <h5>Розташування</h5>
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
                <div class="row clearfix">
                    <div class="col-md-5"></div>
                    <div class="col-md-2">
                        <!-- Button trigger modal -->
                        <button class="btn btn-default btn-sm" data-toggle="modal" onclick="openModalWindow(\${id})">
                            Залишити відгук
                        </button>
                    </div>
                    <div class="col-md-5"></div>
                </div>
            </div>

    </div>
    <!-- Modal -->
    <div class="modal fade" id="myModal\${id}" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel\${id}" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span
                            class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel\${id}">Ваш відгук про готель \${name}</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12 stars_fb">
                            <div class="col-md-6"><label for="comfort" class="muted">Комфорт</label>
                                <input id="comfort-fb\${id}" value="1" type="number" class="rating" min=0 max=5 step=1
                                       data-size="xs"
                                       data-show-clear="false" data-show-caption="false">

                                <label for="cleanliness" class="muted">Чистота</label>
                                <input id="cleanliness-fb\${id}" value="1" type="number" class="rating" min=0 max=5
                                       step=1
                                       data-size="xs"
                                       data-show-clear="false" data-show-caption="false"></div>
                            <div class="col-md-6">
                                <label for="location" class="muted">Розташування</label>
                                <input id="location-fb\${id}" value="1" type="number" class="rating" min=0 max=5 step=1
                                       data-size="xs"
                                       data-show-clear="false" data-show-caption="false">

                                <label for="value_for_money" class="muted">Ціна/Якість</label>
                                <input id="value_for_money-fb\${id}" value="1" type="number" class="rating" min=0 max=5
                                       step=1
                                       data-size="xs"
                                       data-show-clear="false" data-show-caption="false">
                            </div>
                        </div>
                        <div class="col-md-12 comment">
                            <textarea class="comment_fb" id="comment\${id}" rows="5"
                            placeholder="Залиште свій коментар!" maxlength="300"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            Закрити
                        </button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal"
                                onclick="leaveFeedback(\${id})">
                            Залишити відгук
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript' src='<c:url value="js/star-rating.min.js"/>'>
</script>