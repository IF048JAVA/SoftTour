function openModalWindow() {
    $('#myModal').modal('show');
}

function closeModalWindow() {
    $('#myModal').modal('hide');
}

function setData() {

}
function searchHotels() {

    var queryObj = '';

    var country=new Array();
    country=$('#countrySelect2').val();
    queryObj += (queryObj.length==0)?"country="+country:"&country="+country;
    queryObj += (queryObj.length==0)?"rating="+$("#rating").val():"&rating="+$("#rating").val();
    queryObj += (queryObj.length==0)?"comfort="+$("#comfort").val():"&comfort="+$("#comfort").val();
    queryObj += (queryObj.length==0)?"cleanliness="+$("#cleanliness").val():"&cleanliness="+$("#cleanliness").val();
    queryObj += (queryObj.length==0)?"location="+$("#location").val():"&location="+$("#location").val();
    queryObj += (queryObj.length==0)?"valueForMoney="+$("#value_for_money").val():"&valueForMoney="+$("#value_for_money").val();


    $.ajax({
        url: "/hotels/result",
        type: "GET",
        data: queryObj,
        dataType: 'json',

        success: function (data) {

            var len = data.length;
            for (var i = 0; i < len; i++) {
                $("#hotel" + i).html(data[i].name);
                $("#hotelImg" + i).attr("src", data[i].imgUrl);
                $("#hotelName" + i).html("  " + data[i].name);
                $("#hotelRegion" + i).html("  " + data[i].region.name);
                $("#hotelCountry" + i).html("  " + data[i].region.country.name);
                $("#hotelRating" + i).html("  " + data[i].rating);
                $("#hotelComfort" + i).html("  " + data[i].comfort);
                $("#hotelLocation" + i).html("  " + data[i].location);
                $("#hotelCleanliness" + i).html("  " + data[i].cleanliness);
                $("#hotelValueForMoney" + i).html("  " + data[i].valueForMoney);
            }
        },

        error: function () {
            alert("ERROR");
        }



    });
}

$(document).ready(function () {

    $("#countrySelect2").select2({
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

    $.get("hotelResult.html", function (data) {
        $("#hotels-result").append(data);
    });


    $.getJSON('/hotels/all', {
        ajax: 'true'
    }, function (hotel) {
        var len = hotel.length;
        for (var i = 0; i < len; i++) {
            $("#hotel" + i).html(hotel[i].name);
            $("#hotelImg" + i).attr("src", hotel[i].imgUrl);
            $("#hotelName" + i).append("  " + hotel[i].name);
            $("#hotelRegion" + i).append("  " + hotel[i].region.name);
            $("#hotelCountry" + i).append("  " + hotel[i].region.country.name);
            $("#hotelRating" + i).append("  " + hotel[i].rating);
            $("#hotelComfort" + i).append("  " + hotel[i].comfort);
            $("#hotelLocation" + i).append("  " + hotel[i].location);
            $("#hotelCleanliness" + i).append("  " + hotel[i].cleanliness);
            $("#hotelValueForMoney" + i).append("  " + hotel[i].valueForMoney);
        }
    });

});