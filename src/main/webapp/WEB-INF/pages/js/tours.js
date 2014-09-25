function searchTours(countryPar) {
    showModal();
    var queryObj = {};

    queryObj.country = countryPar;
    queryObj.minPrice=$("#minPrice").val();
    queryObj.maxPrice=$("#maxPrice").val();

    $.ajax({
        url: "/result",
        type: "POST",
        data: queryObj,
        dataType: 'json',

        success: function (data) {
            var ss = JSON.parse(JSON.stringify(data));
            $("#panel1").css("visibility","visible");
            $("#tourCountry-h1").append(ss[0].hotel.region.country.name);
            $("#tourPrice-h1").append(ss[0].price);
            $("#tourFood-h1").append(ss[0].food.name);
        },

        error: function () {
            if ($('#minPrice').val()==''||$('#maxPrice').val()=='')
            alert ("Введіть мінімальну та максимальну ціну"); else
            alert ("Error");
        }

    });


}
