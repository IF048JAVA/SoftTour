var ALL_COUNTRIES = "allCountries";

function openModalWindow() {
    $('#myModal').modal('show');
}

function closeModalWindow() {
    $('#myModal').modal('hide');
}

$(".hotel_search").on("submit", function (e) {
    e.preventDefault();
});

function searchByName() {
    var queryObj = {};
    queryObj.name = $("#searchHotelByName").val();
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

function searchHotels() {

    var query = {};
    query.country = '';

    var country = $('#countrySelect2').val();
    if(country!=null){
    $.each(country, function(key, value){
        query.country+=value + ",";
    })
    } else {
        query.country = ALL_COUNTRIES;
    }

    query.rating = $("#rating").val();
    query.comfort = $("#comfort").val();
    query.cleanliness = $("#cleanliness").val();
    query.location = $("#location").val();
    query.valueForMoney = $("#value_for_money").val();

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

$(document).ready(function () {

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

    $.getJSON('/hotels/all', {
        ajax: 'true'
    }, function (hotel) {

        $.each(hotel, function (key, value) {

            $('#hotelTemplate').tmpl(value).appendTo('#hotelResult');
        })
    })
});
