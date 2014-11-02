<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<body onload="getCountry()">
<div>
<div id="left_div" class="form">
    <form class="form-horizontal" role="form" name="form" id="form" action="/search/result">
        <div class="form-group">
            <div class="col-sm-6">
                <p>Країна:</p>
                <select class="form-control" id="country" name="country" onchange="getRegion()">

                <script id="selectCountry" type="text/x-jquery-tmpl">
                    <option id="\${itTourId}" name="optionCountry">\${name}</option>
                </script>
                </select>
            </div>
            <div class="col-sm-6">
                <p>Регіон:</p>
                <select class="form-control" id="region" name="region">
                <script id="selectRegion" type="text/x-jquery-tmpl">
                    <option id="option">\${name}</option>
                </script>
                </select>
            </div>
            <div class="col-sm-12">
            <div class="col-sm-6">
                <p>Готель:</p>

                    <label>2*</label>
                    <input type="checkbox" value="2" id="twoStar" name="twoStar">
                    <label>3*</label>
                    <input type="checkbox" value="3" id="threeStar" name="threeStar" checked>
                    <label>4*</label>
                    <input type="checkbox" value="4" id="fourStar" name="fourStar" checked>
                    <label>5*</label>
                    <input type="checkbox" value="5" id="fiveStar" name="fiveStar" checked><br>
                    <span id="checkHelp" style="color: red"></span>

            </div>
            <div class="col-sm-6">
                <p>Харчування:</p>

                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="HB" id="foodOne" name="foodOne" checked>
                        HB
                    </label>
                    <label>
                        <input type="checkbox" value="BB" id="foodTwo" name="foodTwo" checked>
                        BB
                    </label>
                    <label>
                        <input type="checkbox" value="FB" id="foodThree" name="foodThree" checked>
                        FB
                    </label>
                    <br>
                    <label>
                        <input type="checkbox" value="AI" id="foodFour" name="foodFour" checked>
                        AI
                    </label>
                    <label>
                        <input type="checkbox" value="UAI" id="foodFive" name="foodFive" checked>
                        UAI
                    </label>
                    <label>
                        <input type="checkbox" value="RO" id="foodSix" name="foodSix" checked>
                        RO
                    </label><br>
                    <span id="checkHelp2" style="color: red"></span>
                </div>
            </div>
            </div>
            <div class="col-sm-12">
            <div class="col-sm-6">
                <p>Розміщення в номері:</p>
                <div class="col-sm-6">
                    <p>Дорослі:</p>
                    <select class="form-control" id="adults" name="adults">
                        <option>1</option>
                        <option selected>2</option >
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <div class="col-sm-6">
                    <p>Діти:</p>
                    <select class="form-control" id="children" name="children">
                        <option>0</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
            </div>

            <div class="col-sm-6">
                <p>Дата вильоту:</p>
                <div class="col-sm-6">
                    <p>З:</p>
                    <input type="text" class="form-control" id="dateFrom" name="dateFrom" placeholder="11.11.14" onblur="checkDate(this)"/>
                </div>
                <div class="col-sm-6">
                    <p>По:</p>
                    <input type="text" class="form-control" id="dateTo" name="dateTo" placeholder="31.12.14" onblur="checkDate(this)"/>
                </div>
                    <span id="helpDate" class="help" style="color: red"></span>
            </div>
            </div>
            <div class="col-sm-12">
            <div class="col-sm-6">
                <p>Кількість ночей:</p>
                <div class="col-sm-6">
                    <p>Від:</p>
                    <select class="form-control" id="nightFrom" name="nightFrom">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option selected>6</option>
                        <option>7</option>
                        <option>8</option>
                        <option>9</option>
                        <option>10</option>
                        <option>11</option>
                        <option>12</option>
                        <option>13</option>
                        <option>14</option>
                        <option>15</option>
                        <option>16</option>
                        <option>17</option>
                        <option>18</option>
                        <option>19</option>
                        <option>20</option>
                        <option>21</option>
                    </select>
                </div>

                <div class="col-sm-6">
                    <p>До:</p>
                    <select class="form-control" id="nightTo" name="nightTo">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option>6</option>
                        <option>7</option>
                        <option>8</option>
                        <option>9</option>
                        <option>10</option>
                        <option>11</option>
                        <option>12</option>
                        <option>13</option>
                        <option selected>14</option>
                        <option>15</option>
                        <option>16</option>
                        <option>17</option>
                        <option>18</option>
                        <option>19</option>
                        <option>20</option>
                        <option>21</option>
                    </select>
                </div>
			</div>
            <div class="col-sm-6">
                <p>Ціна($):</p>

                    <div class="col-sm-6">
                        <p>З:</p>
                        <input type="text" class="form-control" id="priceFrom" name="priceFrom" onblur="checkPrice(this)"/>
                    </div>
                    <div class="col-sm-6">
                        <p>До:</p>
                        <input type="text" class="form-control" id="priceTo" name="priceTo" onblur="checkPrice(this)"/>
                    </div>
                    <span id="helpPrice" style="color: red"></span>

            </div>    
        </div>
        </div>
        <input type="button" class="btn btn-primary btn-lg" value="Пошук" onclick="return showResults(this.form,1)">
    </form>
</div>

<div class="col-sm-6">
    <div id="carousel-example-generic" class="carousel slide mycarousel" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="img/egypt_carousel.jpg">
                <div class="carousel-caption" >
                </div>
                <p style="font-size:18px;">Єгипет славиться багатьма фестивалями і релігійними карнавалами, відомий також як мавлід (Mawlid). Як правило, вони пов'язані з конкретними коптськими чи суфійськими святими, але часто їх святкують всі єгиптяни, незалежно від віросповідання чи релігії.</p>
            </div>
            <div class="item">
                <img src="img/greece_carousel.jpg">
                <div class="carousel-caption">
                </div>
                <p style="font-size:18px;">Серед найбільш відомих та популярних туристичних центрів Греції: історико-культурних — Афіни, Дельфи, острів Корфу, Крит.</p>
            </div>
            <div class="item">
                <img src="img/turkey_carousel.jpg">
                <div class="carousel-caption">
                </div>
                <p style="font-size:18px;">Найпопулярніші курорти в Туреччині — Сіде, Белек, Мармарис, Анталія, Кемер, Аланія, Бодрум, Фетхіє.</p>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div><!--каруселька-->
</div><!--правий блок контенту-->

</div>

    <div style="margin-top: 50px"></div>
<div class="col-md-12">

    <div id="searchResult" class="col-lg-12" style="text-align: center">

    </div>


</div>
</body>
    <!--input type="button" value="Search" onclick="showResults()"-->
    <script id="searchTemplate" type="text/x-jquery-tmpl">
        <div class="panel panel-default tourCollapseTitle" id="panel-favorite\${id}">
    <div class="panel-heading" data-toggle="collapse" href="#panel-element-f\${id}">
        <div id="results\${id}">
                         <span data-toggle="collapse" href="#panel-element-f\${id}" onclick="loadAddInfo(\${id})">
                         <span class="tabTitleFont cursor-pointer" >Країна: </span>
                         <span id="tourCountry-f\${id}" class="tabulatedTitle cursor-pointer">\${tour.hotel.region.country.name}</span>
                         <span class="tabTitleFont cursor-pointer" >Тривалість туру: </span>
                         <span id="tourDays-f\${id}" class="tabulatedTitle cursor-pointer">\${tour.days} Днів</span>
                         <span class="tabTitleFont cursor-pointer">Вартість туру: </span>
                         <span id="tourPrice-f\${id}" class="tabulatedTitle cursor-pointer">\${tour.price} $</span>
                         <span class="tabTitleFont cursor-pointer">Харчування: </span>
                         <span id="tourFood-f\${id}" class="tabulatedTitle cursor-pointer">\${tour.food}</span>
                         <span class="tabTitleFont cursor-pointer">Дата вильоту: </span>
                         <span id="tourDepartureDate-f\${id}" class="tabulatedTitle cursor-pointer">\${tour.date}</span></span>
                          <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
        <span id="deleteButtonF\${id}" data-role="button" class="pull-right" ><i class="glyphicon glyphicon-star-empty cursor-pointer" onclick="saveFavorites(\${id})"></i></span>
    </security:authorize>
                </div>
    </div>
    <div id="panel-element-f\${id}" class="panel-collapse collapse">
        <div class="panel-body">

            <div class="col-sm-4">
                <div class="input-group backdown">
                        <div id="imgHold\${id}"></div>
                    <div>
                    </div>
                </div>
            </div>

            <div class="col-sm-7">
                <strong class="text-info pull-left hotelTitle" id="hotel\${id}">
                    \${tour.hotel.name}&nbsp;
                </strong>
                        <span>
                            <input id="stars" value=\${tour.hotel.stars} type="number" class="rating" min=0 max=5 step=1
                                   data-size="xs"
                                   data-show-clear="false" data-show-caption="false" readonly="true" width="100px">
                        </span>
            </div>

            <div class="col-sm-3">
                <h4>Дорослі</h4>
                <h4>Діти</h4>
                <h4>Рейтинг готелю</h4>
                <h4>Тип номерів</h4>
                <h4>Місто вильоту</h4>
            </div>

            <div class="col-sm-3">
                <h4 id="adultsAmount\${id}">\${tour.adultAmount}</h4>
                <h4 id="childrenAmount\${id}">\${tour.childrenAmount}</h4>
                <h4 id="hotelRaiting\${id}">-</h4>
                <h4 id="hotelRoomType\${id}">\${tour.roomType}</h4>
                <h4 id="departyreCity\${id}">\${tour.departureCity}</h4>
            </div>

            <div class="col-sm-1">

                <!-- Button trigger modal -->

                <button type="button" class="btn btn-info btn-TourOrder" onclick="showTourOrderFormF(\${tour.id})">
                    <span class="glyphicon glyphicon-briefcase"></span>
                    <h4>Замовити тур!</h4>
                </button>

                <!-- Modal -->
                <div class="modal fade" id="orderModal-f\${tour.id}" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel\${tour.id}" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel\${tour.id}">Введіть дані</h4>
                            </div>
                            <div class="modal-body">

                                <form:form id="orderTourForm\${tour.id}" class="form-horizontal" action="" method="POST"
                                           data-toggle="validator" role="form">

    <fieldset>

    <!-- Name input-->
    <div class="form-group">
    <label class="col-md-4 control-label" for="name">Ім'я</label>

    <div class="col-md-5">
    <input type="text" name="name" id="name" class="form-control input-md"
    pattern="\b[A-Za-z0-9]{2,30}\b"
    placeholder="Bід 2 до 30 символів"
    data-error="Ви ввели некоректне ім'я !"
    required="required"
    />
    </div>
    </div>

    <!-- Email input-->
    <div class="form-group">
    <label class="col-md-4 control-label" for="email">Email</label>

    <div class="col-md-5">
    <input type="email" name="email" id="email"
    class="form-control input-md"
    pattern="\b(?!.{31})([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4})\b"
    placeholder="Bведіть email"
    data-error="Ви ввели некоректний email !"
    required="required"
    />

    <div class="help-block with-errors"></div>
    <errors path="email" cssClass="error"/>

    </div>
    </div>

    <!-- City input-->
    <div class="form-group">
    <label class="col-md-4 control-label" for="city">Місто</label>

    <div class="col-md-5">
    <input type="text" name="city" id="city" class="form-control input-md"
    pattern="\b[A-Za-z]{2,30}\b"
    placeholder="Місто"
    data-error="Введіть місто!"
    required="required"
    />
    </div>
    </div>

    <!-- Phone input-->
    <div class="form-group">
    <label class="col-md-4 control-label" for="phone">Номер телефону</label>

    <div class="col-md-5">
    <input type="text" name="phone" id="phone" class="form-control input-md"
    pattern="[\+]?[0-9]{4,19}\b"
    placeholder="Введіть номер телефону"
    data-error="Ви ввели некоректний номер телефону !">

    <div class="help-block with-errors"></div>
    <errors path="email" cssClass="error"/>
    </div>
    </div>

    <div class="form-group">
    <div class="col-md-5"></div>
    <div class="col-md-3">
    <button type="submit" name="submit" class="btn btn-primary">
    <span class="glyphicon glyphicon-ok"></span>
    Замовити
    </button>
    </div>
    </div>

    </fieldset>
</form:form>
                            </div>
                            <div class="modal-footer">
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <form role="form" class="form-inline">
                <button type="button" class="btn btn-info transitionInfo" data-toggle="collapse"
                        data-target="#transitInfo-All-F\${tour.id}" onclick="getAllTransitF(\${tour.id},\${id})">
                    <span class="glyphicon glyphicon-road"></span>
                    <span class="glyphicon glyphicon-plane"></span>
                    &nbsp; Як добратися з ... ?
                </button>
                <div class="form-group">
                    <select id="cityFrom-F\${id}" class="select2-offscreen cityFromSelector">
                    </select>
                </div>
            </form>
        </div>
    </div>
</div>
</script>



