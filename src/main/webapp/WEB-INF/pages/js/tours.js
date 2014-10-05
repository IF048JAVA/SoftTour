$(document).ready(function () {})

    function searchTours(countryPar) {
        showModal();
        var queryObj = {};
        queryObj.country = countryPar;
        queryObj.minPrice = $("#minPrice").val();
        queryObj.maxPrice = $("#maxPrice").val();
        if ($('#minPrice').val() == '')
            queryObj.minPrice = 0;
        if ($('#maxPrice').val() == '')
            queryObj.maxPrice = 9999;
        $.ajax({
            url: "/result",
            type: "POST",
            data: queryObj,
            dataType: 'json',

            success: function (data) {
                $('#indexResult').empty();
                $('#indexTemplate').tmpl(data).appendTo('#indexResult');

            },

            error: function(){
                    alert("Error");
            }

        });


    }
function tourParse() {


    $.ajax({
        url: "/tourParse",
        type: "POST",
        dataType: 'json',

        success: function () {
            $("#indexResult").append("done");

        },

        error: function(){
            $("#indexResult").append("error");
        }

    });
}
