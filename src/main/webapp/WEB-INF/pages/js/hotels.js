var ALL_COUNTRIES = "allCountries";

function openModalWindow() {
    $('#myModal').modal('show');
}

function closeModalWindow() {
    $('#myModal').modal('hide');
}

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

    var query = '';
    var country = new Array();
    country = $('#countrySelect2').val();
    if(country!=null){
        query += (query.length == 0) ? "country=" + country : "&country=" + country;
    } else {
        query += (query.length == 0) ? "country=" + ALL_COUNTRIES : "&country=" + ALL_COUNTRIES;
    }

    query += (query.length == 0) ? "rating=" + $("#rating").val() : "&rating=" + $("#rating").val();
    query += (query.length == 0) ? "comfort=" + $("#comfort").val() : "&comfort=" + $("#comfort").val();
    query += (query.length == 0) ? "cleanliness=" + $("#cleanliness").val() : "&cleanliness=" + $("#cleanliness").val();
    query += (query.length == 0) ? "location=" + $("#location").val() : "&location=" + $("#location").val();
    query += (query.length == 0) ? "valueForMoney=" + $("#value_for_money").val() : "&valueForMoney=" + $("#value_for_money").val();

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
