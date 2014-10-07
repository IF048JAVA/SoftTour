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

            error: function () {
                    alert("Error");
            }

        });
    }
    function parseTour () {
        var queryObject = {};
        $.ajax({
            url: "/parseTour",
            type: "POST",
            data: queryObject,
            dataType: 'json',

            success: function (data) {
                showModal();
                var new_id=0;
                $.each(data,function(key,value){
                    value.id=new_id;
                    new_id++;
                })
                console.log (data);
                $('#indexResult').empty();
                $('#indexTemplate').tmpl(data).appendTo('#indexResult');
            },

            error: function () {
                alert("Error");
            }

        });
    }
function uncollapse(id){
    $(".collapse").collapse('hide')

}