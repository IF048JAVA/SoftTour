$(document).ready(function () {})
var usedIds = new Array();
var favData = {};
var queryObj = {};
var countryParam = '';
var countryExpandParameter = '';
queryObj.numberOfPage = 0;
    function clearArray() {
        usedIds=[];
    }
    function parseTour (countryPar,numberOfPage,countryParameter) {
        countryParam=countryPar;
        countryExpandParameter = countryParameter;
        $('#indexResult').empty();
        showModal();
        $('#indexResult').append('<div class="col-md-12" id="loading"><img src="img/preloader.gif"></div><br>');
        var indexBudget = $("#indexBudget").val();
        if (indexBudget > 0) {} else indexBudget = 1500;
        var travelersAdult = $("#TravelersAdult").val();
        if (travelersAdult > 0){} else travelersAdult = 1;
        var travelersChildren = $("#TravelersChildren").val();
        if (travelersChildren > 0){} else travelersChildren = 0;
        queryObj.countryParameter = countryParameter;
        queryObj.numberOfPage = numberOfPage;
        queryObj.country = countryPar;
            queryObj.minPrice = Math.floor(indexBudget * 0.9);
            queryObj.maxPrice = Math.floor(indexBudget * 1.1);
        queryObj.travelersAdult = travelersAdult;
        queryObj.travelersChildren = travelersChildren;

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
                $('#indexResult').append('<div class="col-md-12"><strong>Результати пошуку:</strong></div><br>');
                $('#indexTemplate').tmpl(data).appendTo('#indexResult');
                $('#indexResult').append('<button type="button" class="btn btn-default pull-left" onclick="expandParse(-1)">Попередні</button>' +
                    '<button type="button" class="btn btn-default pull-right" onclick="expandParse(1)">Наступні</button>');

                checkFavorites();
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
    $("#results"+id).append('<span id="deleteButtonF'+id+'" data-role="button" class="pull-right"><i class="glyphicon glyphicon-star cursor-pointer" onclick="deleteFavorites('+id+')"><//i><//span>')
}

function deleteFavorites (id){
    var favObj = {}
    $.each(favData,function(key,value){
        if(value.id == id){
            favObj = value;
        }
    })
    console.log(favObj);
    $.ajax({
        url: "/deleteFavorites",
        type: "POST",
        data: JSON.stringify(favObj),
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json'
    })
    $("#deleteButtonF"+id).remove();
    $("#results"+id).append('<span id="deleteButtonF'+id+'" data-role="button" class="pull-right"><i class="glyphicon glyphicon-star-empty cursor-pointer" onclick="saveFavorites('+id+')"><//i><//span>')

    //$("#")
}
function saveHistoryRecord(id) {
    if(usedIds[id]!="used")
    {var hisObj = {}
    $.each(favData,function(key,value){
        if(value.id == id){
            hisObj = value;
        }
    })
    console.log(hisObj);
    $.ajax({
        url: "/saveHistoryRecord",
        type: "POST",
        data: JSON.stringify(hisObj),
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json'
    })}
    usedIds[id]="used"
}

function expandParse (incDec){
    if (queryObj.numberOfPage!=1||incDec!=-1){
        var numb = queryObj.numberOfPage+incDec;
        parseTour(countryParam,numb,countryExpandParameter);
    }

}
function loadAddInfo (id) {
    var infObj = {}
    $.each(favData,function(key,value){
        if(value.id == id){
            infObj = value;
        }
    })
    console.log(JSON.stringify(infObj));
    $.ajax({
        url: "/parseHotel",
        type: "POST",
        data: JSON.stringify(infObj),
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function(data) {
            saveHistoryRecord(id);

                    console.log(data);
                    console.log(data.hotel.imgUrl);
                    $("#imgHold" + id).empty();
                    $("#imgHold" + id).append('<img src="' + data.hotel.imgUrl + '" class="hotel-img-inTour img-circle" id="hotelImg\${id}">');
                },
        error: function(){console.log("ERROR");}
        })
}
/*function checkFavorites(){
    var infObj = {}
    $.each(favData,function(key,value) {
        infObj = value;
        var id = infObj.id;
        $.ajax({
            url: "/checkFavorites",
            type: "POST",
            data: JSON.stringify(infObj),
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                console.log(data);
                if (data) {
                    $("#deleteButtonF"+id).remove();
                    $("#results" + id).append('<span id="deleteButtonF'+ id +'" data-role="button" class="pull-right"><i class="glyphicon glyphicon-star cursor-pointer" onclick="deleteFavorites('+id+')"><//i><//span>')
                    console.log("favorite");
                } else {
                    $("#deleteButtonF" + id).remove();
                    $("#results" + id).append('<span id="deleteButtonF' + id + '" data-role="button" class="pull-right"><i class="glyphicon glyphicon-star-empty cursor-pointer" onclick="saveFavorites(' + id + ')"><//i><//span>')
                    console.log("not favorite");
                }
            }
        })
    })
}*/
