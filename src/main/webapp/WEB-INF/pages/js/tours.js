$(document).ready(function () {})
var usedIds = new Array();
var favData = {};
    function clearArray() {
        usedIds=[];
    }
    function parseTour (countryPar) {
        showModal();
        var queryObj = {};
        var indexBudget = $("#indexBudget").val();
        var travelers = $("#Travelers").val();
        queryObj.country = countryPar;
        queryObj.minPrice = Math.floor(indexBudget*0.7);
        queryObj.maxPrice = Math.floor(indexBudget*1.1);

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