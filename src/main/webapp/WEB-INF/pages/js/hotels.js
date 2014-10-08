var ALL_COUNTRIES = "allCountries";
var PAGE_SIZE = 5;

function openModalWindow(id) {
    $('#myModal' + id).modal('show');
}

function closeModalWindow(id) {
    $('#myModal' + id).modal('hide');
}

$(".hotel_search").on("submit", function (e) {
    e.preventDefault();
});

function searchByName(pageNum) {

    var queryObj = {};
    queryObj.name = $("#searchHotelByName").val();
    queryObj.pageNum = --pageNum;
    queryObj.pageSize = PAGE_SIZE;

    $.ajax({
        url: "/hotels/search",
        type: "GET",
        data: queryObj,
        dataType: 'json',

        success: function (data) {

            $('#hotelResult').empty();
            $.each(data, function (key, value) {

            $('#hotelTemplate').tmpl(value).appendTo('#hotelResult');
            })
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
    query.pageNum = --pageNum;
    query.pageSize = PAGE_SIZE;

    $.ajax({
        url: "/hotels/result",
        type: "GET",
        data: query,
        dataType: 'json',

        success: function (data) {
            $('#hotelResult').empty();
            $.each(data, function (key, value) {

                $('#hotelTemplate').tmpl(value).appendTo('#hotelResult');
            })
        },

        error: function () {
            alert("ERROR");
        }
    });
}

function search(){
    showPagination(searchByName);
}

function showSearchResult(){
    showPagination(searchHotels);
}

function showPagination(callback) {

    callback(1);

    $(".pagin").html('<ul class="pagination-md"></ul>');

    $('.pagination-md').twbsPagination({
        totalPages: 10,
        visiblePages: 7,
        startPage: 1,
        onPageClick: function (event, page) {

            callback(page);

            }
        })
}

if ($('#hotel_page').length) {
    $(this.$element).ready(function () {

        showSearchResult();

        $("#countrySelect2").val(["AllCountry"]).select2({
            placeholder: "Оберіть країну",
            allowClear: true
        });

        $.getJSON('/hotels/allCountry', {
            ajax: 'true'
        }, function (country) {

            var html = ' ';
            var len = country.length;

            for (var i = 0; i < len; i++) {
                html += '<option value="' + country[i].name + '">'
                    + country[i].name + '</option>';
            }

            $('#countrySelect2').html(html);
        });
    })
}

