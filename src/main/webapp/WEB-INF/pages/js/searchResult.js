var queryObj = {};
function showResults(){



    queryObj.country = $("#country").val();
    queryObj.region = $("#region").val();
    queryObj.oneStar = $("#oneStar").val();
    queryObj.twoStar = $("#twoStar").val();
    queryObj.threeStar = $("#threeStar").val();
    queryObj.fourStar = $("#fourStar").val();
    queryObj.fiveStar = $("#fiveStar").val();
    queryObj.foodOne = $("#foodOne").val();
    queryObj.foodTwo = $("#foodTwo").val();
    queryObj.foodThree = $("#foodThree").val();
    queryObj.foodFour = $("#foodFour").val();
    queryObj.foodFive = $("#foodFive").val();
    queryObj.foodSix = $("#foodSix").val();
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
            $('#searchTemplate').tmpl(data).appendTo('#searchResult');
        }
    });
}
