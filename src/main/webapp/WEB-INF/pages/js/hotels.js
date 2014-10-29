var ALL_COUNTRIES = "allCountries";
var PAGE_SIZE = 10;
var contentType = "application/x-www-form-urlencoded; charset=UTF-8";

function oderTour(id){
    $('#orderModal' + id).modal('show');

}

function closeOrderModal(id){
    $('#orderModal' + id).modal('hide');
}

function showTours(hotelId) {
    $('#tour-list').empty();

    $('#toursModal').modal('show');

    var html = '<img src="img/preloader.gif" class="preloader">';

    var queryObj = {};
    queryObj.hotelId = hotelId;
    queryObj.page = 1;

    $('#tour-list').append(html);

    $.ajax({
        url: "/hotels/tours",
        type: "GET",
        data: queryObj,
        dataType: 'json',

        success: function (data) {

            var new_id = 0;

            var html = '';

            html += '<table id="toursByHotel" class="table table-hover" cellspacing="0" width="100%"><thead><tr>' +
                '<th>Країна</th>' +
                '<th>Регіон</th>' +
                '<th>Готель</th>' +
                '<th>Кількість ночей</th>' +
                '<th>Харчування</th>' +
                '<th>Дата вильоту</th>' +
                '<th>Місто вильоту</th>' +
                '<th>Вартість туру</th>' +
                '<th>Замовити тур</th></tr></thead><tbody>';

            $.each(data, function (key, value) {
                value.id = new_id;
                new_id++;

                html += '<tr><td>' + value.hotel.region.country.name + '</td>' +
                    '<td>' + value.hotel.region.name + '</td>' +
                    '<td>' + value.hotel.name + '</td>' +
                    '<td>' + value.days + '</td>' +
                    '<td>' + value.food + '</td>' +
                    '<td>' + value.date + '</td>' +
                    '<td>' + value.departureCity + '</td>' +
                    '<td>' + value.price + ' $ </td>' +
                    '<td><a class="btn btn-default" onclick="oderTour(' + value.id + ')">Замовити</a></td></tr>';

                $('#toursTemplate').tmpl(value).appendTo('#order-list');
            })

            html += '</tbody></table>';

            $("#tour-list").html(html);
            $("#toursByHotel").dataTable({
                "scrollX": true
            });
        },

        error: function () {
            alert("Error");
        }

    });

}

function showComments(id) {

    var queryObj = {};
    queryObj.hotelId = id;

    $.ajax({
        url: "/hotels/comments",
        type: "GET",
        data: queryObj,
        dataType: 'json',

        success: function (data) {

            var html = '';
            var img = '';

            var length = data.length;
            if (length == 0) {
                html += '<div class=row><div class="col-md-2 commentInfo">' +
                    '<img src="img/male.jpg" class="img-circle avatar">' +
                    '<p>Admin</p></div><div class="col-md-10 comment">' +
                    '<p>Про даний готель не залишили жодного коментаря</p></div></div>';
            }

            for (var i = 0; i < length; i++) {

                if (data[i].user.sex == "MALE") {
                    img = "img/male.jpg"
                } else {
                    img = "img/female.jpg"
                }

                if (data[i].comment != '') {
                    html += '<div class=row><div class="col-md-2 commentInfo">' +
                        '<img src="' + img + '" class="img-circle avatar">' +
                        '<p>' + data[i].user.name + '</p></div><div class="col-md-10 comment"><p>' +
                        data[i].comment + '</p></div></div>';
                }
            }
            $("#comment-list" + id).html(html);
            $('#commentModal' + id).modal('show');

        },

        error: function () {
            alert("ERROR");
        }
    });

}

$(".hotel_search").on("submit", function (e) {
    e.preventDefault();
});

function searchByName(pageNum) {

    var queryObj = {};
    queryObj.name = $("#searchHotelByName").val();
    queryObj.page = --pageNum;
    queryObj.pageSize = PAGE_SIZE;

    $.ajax({
        url: "/hotels/search",
        type: "GET",
        data: queryObj,
        dataType: 'json',

        success: function (data) {

            var numOfPages = data.totalPages;

            $('#hotelResult').empty();
            $.each(data.content, function (key, value) {

                $('#hotelTemplate').tmpl(value).appendTo('#hotelResult');

                if (key == 0) {
                    $("#collapseHotel" + value.id).attr("class", "panel-collapse collapse in");
                }
            })
            showPagination(searchByName, numOfPages, pageNum)
        },

        error: function () {
            alert("ERROR");
        }
    });
}

function searchHotels(pageNum) {

    var query = {};
    query.country = '';

    var country = $('#countrySelect2').val();
    if (country != null) {
        $.each(country, function (key, value) {
            query.country += value + ",";
        })
    } else {
        query.country = ALL_COUNTRIES;
    }


    query.rating = $("#rating").val();
    query.comfort = $("#comfort").val();
    query.cleanliness = $("#cleanliness").val();
    query.location = $("#location").val();
    query.valueForMoney = $("#value_for_money").val();

    query.page = --pageNum;
    query.pageSize = PAGE_SIZE;
    query.property = $("#sort").val();


    $.ajax({
        url: "/hotels/result",
        type: "GET",
        encoding: "UTF-8",
        contentType: "application/json; charset=utf-8",
        data: query,
        dataType: 'json',

        success: function (data) {
            var numOfPages = data.totalPages;

            $('#hotelResult').empty();

            $.each(data.content, function (key, value) {

                if (value.imgUrl == null) {
                    value.imgUrl = 'http://placehold.it/170&text=Not+found!';
                }

                $('#hotelTemplate').tmpl(value).appendTo('#hotelResult');
                if (key == 0) {
                    $("#collapseHotel" + value.id).attr("class", "panel-collapse collapse in");
                }
            })

            showPagination(searchHotels, numOfPages, pageNum)
        },
        error: function () {
            alert("ERROR");
        }

    });
}

function search() {
    searchByName(1);
}

function showSearchResult() {
    searchHotels(1);
}

function showPagination(callback, numOfPages, pageNum) {

    if (pageNum == 0) {
        $(".pagin").html('<ul class="pagination-md"></ul>');
    }

    $('.pagination-md').twbsPagination({
        totalPages: numOfPages,
        visiblePages: 10,
        startPage: 1,
        onPageClick: function (event, page) {
            callback(page);

        }
    })
}

function openFeedbackModal(hotelId) {
    $('#feedbackModal' + hotelId).modal('show');
}

function leaveFeedback(hotelId) {

    var feedbackQuery = {};
    feedbackQuery.cleanliness = $("#cleanliness-fb" + hotelId).val();
    feedbackQuery.comfort = $("#comfort-fb" + hotelId).val();
    feedbackQuery.location = $("#location-fb" + hotelId).val();
    feedbackQuery.valueForMoney = $("#value_for_money-fb" + hotelId).val();
    feedbackQuery.comment = $("#comment" + hotelId).val();
    feedbackQuery.hotelId = hotelId;


    $('#myModal' + hotelId).modal('hide');

    $.ajax({
        url: "/hotels/feedback",
        type: "POST",
        data: feedbackQuery
    })
}

if ($('#hotel_page').length) {
    $(this.$element).ready(function () {

        $("#sort").select2();

        $("#countrySelect2").val(["AllCountry"]).select2({
            placeholder: "Оберіть країну",
            allowClear: true
        });

        $.getJSON('/hotels/allCountry', {
            ajax: 'true'
        }, function (country) {

            var html = ' ';
            var length = country.length;

            for (var i = 0; i < length; i++) {
                html += '<option value="' + country[i].name + '">'
                    + country[i].name + '</option>';
            }

            $('#countrySelect2').html(html);
        });
    })

    showSearchResult();
}

