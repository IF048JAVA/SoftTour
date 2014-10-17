<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!-- Hotel template -->
<script id="hotelTemplate" type="text/x-jquery-tmpl">
<div id="result" class="panel panel-default hotel-panel">
    <div class="panel-heading" data-toggle="collapse" data-parent="#hotelResult" href="#collapseHotel\${id}">
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
                        <img src='\${imgUrl}' class="hotel-img img-circle" id="hotelImg\${id}">
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

             <!-- Button trigger modal -->
            <div class="row clearfix">
                <div class="col-md-4">
                <button class="btn btn-block hotel-btn" onclick="g60()">
                <i class="glyphicon glyphicon-globe"></i> Шукати тур
                </button>
                </div>
                <div class="col-md-4">
                        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                        <button class="btn btn-block hotel-btn" data-toggle="modal" onclick="openFeedbackWindow(\${id})">
    <i class="glyphicon glyphicon-bullhorn"></i> Залишити відгук
                        </button>
                        </security:authorize>
                </div>
                <div class="col-md-4">
                <button class="btn btn-block hotel-btn" data-toggle="modal" onclick="showComments(\${id})">
                        <i class="glyphicon glyphicon-comment"></i> Переглянути коментарі
                        </button></div>
            </div>
        </div>
    </div>
</div>

<!-- Feedback Modal -->
<div class="modal fade" id="feedbackModal\${id}" tabindex="-1" role="dialog"
     aria-labelledby="feedbackModalLabel\${id}" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="feedbackModalLabel\${id}">Ваш відгук про готель \${name}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12 stars_fb">
                        <div class="col-md-6">
                        <label for="comfort-fb\${id}" class="muted">Комфорт</label>
                            <input id="comfort-fb\${id}" value="1" type="number" class="rating" min=0 max=5 step=1
                                   data-size="xs"
                                   data-show-clear="false" data-show-caption="false">

                            <label for="cleanliness-fb\${id}" class="muted">Чистота</label>
                            <input id="cleanliness-fb\${id}" value="1" type="number" class="rating" min=0 max=5
                                   step=1
                                   data-size="xs"
                                   data-show-clear="false" data-show-caption="false"></div>
                        <div class="col-md-6">
                            <label for="location-fb\${id}" class="muted">Розташування</label>
                            <input id="location-fb\${id}" value="1" type="number" class="rating" min=0 max=5 step=1
                                   data-size="xs"
                                   data-show-clear="false" data-show-caption="false">

                            <label for="value_for_money-fb\${id}" class="muted">Ціна/Якість</label>
                            <input id="value_for_money-fb\${id}" value="1" type="number" class="rating" min=0 max=5
                                   step=1
                                   data-size="xs"
                                   data-show-clear="false" data-show-caption="false">
                        </div>
                    </div>
                    <div class="col-md-12 comment-fb">
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

<!-- Comment Modal -->
<div class="modal fade" id="commentModal\${id}" tabindex="-1" role="dialog"
     aria-labelledby="commentModalLabel\${id}" aria-hidden="true">
    <div class="modal-dialog modal-comment">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"> Відгуки про готель \${name}</h4>
            </div>
            <div class="modal-body comment-body">
                <div class="container" id="comment-list\${id}">
                </div>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript' src='<c:url value="js/star-rating.min.js"/>'>
</script>
