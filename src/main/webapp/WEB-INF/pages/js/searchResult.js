var queryObj = {};
function showResults(){



    queryObj.country = $("#country").val();
    queryObj.region = $("#region").val();
    if($("#oneStar").prop('checked'))queryObj.oneStar = $("#oneStar").val();
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
//        contentType: 'application/json',
//           mimeType: 'application/json',
        /*data: ({
            text: queryObj
        }),*/
        success: function(data){
            var result = data.Tour;
            $('#searchResult').empty();
            $('#searchResult').append('<p align="center"><h3>Результати пошуку:</h3></p>');
            $('#searchTemplate').tmpl(data).appendTo('#searchResult');
        }
    });
}