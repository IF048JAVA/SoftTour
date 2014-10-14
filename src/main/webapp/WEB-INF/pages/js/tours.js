$(document).ready(function () {})

var favData = {};
    function searchTours(countryPar) {
        showModal();

        queryObj.country = countryPar;
        queryObj.minPrice = $("#Budget").val()*0.7;
        queryObj.maxPrice = $("#maxPrice").val()*1.1;
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
                favData=data;
                $('#indexResult').empty();
                $('#indexTemplate').tmpl(data).appendTo('#indexResult');
            },

            error: function () {
                    alert("Error");
            }

        });
    }
    function parseTour (countryPar) {
        showModal();
        var queryObj = {};
        var Budget = $("#Budget").val();
        queryObj.country = countryPar;
        queryObj.minPrice = Budget*0.7;
        queryObj.maxPrice = Budget*1.1;

        $.ajax({
            url: "/parseTour",
            type: "POST",
            data: queryObj,
            dataType: 'json',

            success: function (data) {
                showModal();
                var new_id=0;
                $.each(data,function(key,value){
                    value.id=new_id;
                    new_id++;
                })
                favData=data;
                console.log (data);
                $('#indexResult').empty();
                $('#indexResult').append('<strong>Результати пошуку:</strong>');
                $('#indexTemplate').tmpl(data).appendTo('#indexResult');

            },

            error: function () {
                alert("Error");
            }

        });
    }
function saveFavorites (id){
    var favObj = {}
    $.each(favData,function(key,value){
        if(value.id == id){
            favObj = value;
        }
    })
    console.log(favObj);
    $.ajax({
        url: "/saveFavorites",
        type: "POST",
        data: JSON.stringify(favObj),
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json'
    })
    $("#deleteButtonF"+id).remove();
    //$("#")
}