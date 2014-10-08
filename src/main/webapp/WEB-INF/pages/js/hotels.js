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

            var numOfPages = data.totalPages;

            $('#hotelResult').empty();
            $.each(data.content, function (key, value) {

                $('#hotelTemplate').tmpl(value).appendTo('#hotelResult');
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
    query.pageNum = --pageNum;
    query.pageSize = PAGE_SIZE;

    $.ajax({
        url: "/hotels/result",
        type: "GET",
        data: query,
        dataType: 'json',

        success: function (data) {
            var numOfPages = data.totalPages;

            $('#hotelResult').empty();

            $.each(data.content, function (key, value) {

                $('#hotelTemplate').tmpl(value).appendTo('#hotelResult');
            })

            showPagination(searchHotels, numOfPages, pageNum)
        },
        error: function () {
            alert("ERROR");
        }

    });

    return numOfPages;
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
        visiblePages: 15,
        startPage: 1,
        onPageClick: function (event, page) {
            callback(page);

        }
    })
}


if ($('#hotel_page').length) {

    $(this.$element).ready(function () {

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

    showSearchResult();
}

