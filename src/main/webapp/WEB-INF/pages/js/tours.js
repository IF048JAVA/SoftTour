$(document).ready(function () {})
var usedIds = new Array();
var favData = {};
var queryObj = {};
var countryParam = '';
queryObj.numberOfPage = 0;
    function clearArray() {
        usedIds=[];
    }
    function parseTour (countryPar,numberOfPage) {
        countryParam=countryPar;
        $('#indexResult').empty();
        showModal();
        $('#indexResult').append('<div class="col-md-12" id="loading"><img src="img/loading.gif"></div><br>');
        var indexBudget = $("#indexBudget").val();
        var travelers = $("#TravelersAdult").val();
        var travelersChildren =
        queryObj.numberOfPage = numberOfPage;
        queryObj.country = countryPar;
        queryObj.minPrice = Math.floor(indexBudget*0.9);
        queryObj.maxPrice = Math.floor(indexBudget*1.1);
        queryObj.travelersAdult = $("#TravelersAdult").val();
        queryObj.travelersChildren = $("#TravelersChildren").val();

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
    $("#results"+id).append('<span id="deleteButtonF'+id+'" data-role="button" class="pull-right"><i class="glyphicon glyphicon-star cursor-pointer" onclick="saveFavorites('+id+')"><//i><//span>')

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
        parseTour(countryParam,numb);
    }

}
function loadAddInfo (id) {
    var infObj = {}
    $.each(favData,function(key,value){
        if(value.id == id){
            infObj = value;
        }
    })
    $.ajax({
        url: "/openCollapse",
        type: "POST",
        data: JSON.stringify(infObj),
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json'
    })
}