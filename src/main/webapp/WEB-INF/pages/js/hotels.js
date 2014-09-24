function openModalWindow() {
    $('#myModal').modal('show');
}

function closeModalWindow() {
    $('#myModal').modal('hide');
}

function searchHotels() {

    var queryObj = {};

    queryObj.country = $("#countrySelect2").val();
    queryObj.rating = $("#rating").val();
    queryObj.comfort = $("#comfort").val();
    queryObj.cleanliness = $("#cleanliness").val();
    queryObj.location = $("#location").val();
    queryObj.valueForMoney = $("#value_for_money").val();

    $.ajax({
        url: "/hotels/result",
        type: "GET",
        data: queryObj,
        dataType: 'json',

        success: function (data) {
            alert(JSON.stringify(data))
        },

        error: function () {
            alert("ERROR");
        }


    });
}

$(document).ready(function () {
    $("#countrySelect2").select2({
        placeholder: "Всі країни"
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

});
