$(document).ready(function () {})

var queryObj = {};

function showResults(){
    $('#searchResult').empty();
    showModal();
    $('#searchResult').append('<div class="col-md-12" id="loading"><img src="img/preloader.gif"></div><br>');

    queryObj.country = $("#country").val();
    queryObj.region = $("#region").val();
    if($("#twoStar").prop('checked'))queryObj.twoStar = $("#twoStar").val();
    if($("#threeStar").prop('checked'))queryObj.threeStar = $("#threeStar").val();
    if($("#fourStar").prop('checked'))queryObj.fourStar = $("#fourStar").val();
    if($("#fiveStar").prop('checked'))queryObj.fiveStar = $("#fiveStar").val();
    if($("#foodOne").prop('checked'))queryObj.foodOne = $("#foodOne").val();
    if($("#foodTwo").prop('checked'))queryObj.foodTwo = $("#foodTwo").val();
    if($("#foodThree").prop('checked'))queryObj.foodThree = $("#foodThree").val();
    if($("#foodFour").prop('checked'))queryObj.foodFour = $("#foodFour").val();
    if($("#foodFive").prop('checked'))queryObj.foodFive = $("#foodFive").val();
    if($("#foodSix").prop('checked'))queryObj.foodSix = $("#foodSix").val();
    queryObj.adults = $("#adults").val();
    queryObj.children = $("#children").val();
    queryObj.dateFrom = $("#dateFrom").val();
    queryObj.dateTo = $("#dateTo").val();
    queryObj.nightFrom = $("#nightFrom").val();
    queryObj.nightTo = $("#nightTo").val();
    queryObj.priceFrom = $("#priceFrom").val();
    queryObj.priceTo = $("#priceTo").val();

    $.ajax({
        url: "/search/getTour",
        type: "POST",
        data: queryObj,
        dataType: 'json',

        success: function(data){
            showModal();
            var new_id=0;
            $.each(data,function(key,value){
                value.id=new_id;
                new_id++;
            })
            favData=data;
            console.log (data);
            $('#searchResult').empty();
            $('#searchResult').append('<p align="center"><h3>Результати пошуку:</h3></p>');
            $('#searchTemplate').tmpl(data).appendTo('#searchResult');
        }
    });
}

