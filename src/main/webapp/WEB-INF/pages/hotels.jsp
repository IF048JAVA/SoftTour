<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">

    <div class="col-md-2 hotels-filter">
        <form class="form-horizontal" role="form" id="filter">
            <form id="search_form" class="form-group" role="search">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon glyphicon glyphicon-search"></span>
                        <input name="search" id="search_hotel" type="search" class="form-control required-field"
                               placeholder="Пошук">
                    </div>
                </div>
            </form>
            <select class="form-control">
                <option selected="selected" value="338">Египет</option>
                <option value="318">Турция</option>
                <option value="16">ОАЭ</option>
                <option value="372">Греция</option>
            </select>
            <select class="form-control">
                <option selected="selected" value="0">Все регионы</option>
                <option value="5486">Дахаб</option>
                <option value="2">Макади Бей</option>
            </select>

            <form>
                <p class="muted">Загальний рейтинг</p>
                <input id="rating" value="0" type="number" class="rating" min=0 max=5 step=1 data-size="xs"
                       data-show-clear="false" data-show-caption="false">
            </form>
            <form>
                <p class="muted">Комфорт</p>
                <input id="comfort" value="0" type="number" class="rating" min=0 max=5 step=1 data-size="xs"
                       data-show-clear="false" data-show-caption="false">
            </form>
            <form>
                <p class="muted">Чистота</p>
                <input id="cleanliness" value="0" type="number" class="rating" min=0 max=5 step=1 data-size="xs"
                       data-show-clear="false" data-show-caption="false">
            </form>
            <form>
                <p class="muted">Розташування</p>
                <input id="location" value="0" type="number" class="rating" min=0 max=5 step=1 data-size="xs"
                       data-show-clear="false" data-show-caption="false">
            </form>
            <form>
                <label for="value_for_money" class="muted">Ціна/Якість</label>
                <input id="value_for_money" value="0" type="number" class="rating" min=0 max=5 step=1 data-size="xs"
                       data-show-clear="false" data-show-caption="false">
            </form>

            <button type="button" class="btn btn-default btn-block">Пошук</button>
        </form>
    </div>
    <div class="col-md-1"></div>
    <div class="col-md-9 hotels-result">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" href="#collapseHotel">
                            Hotel name
                        </a>
                    </h4>
                <div id="collapseHotel" class="panel-collapse collapse">
                    <div class="panel-body">
                        <dl class="dl-horizontal">
               <h1>Hotel info</h1>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>