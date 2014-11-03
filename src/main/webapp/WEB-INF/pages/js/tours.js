$(document).ready(function () {})
var usedIds = new Array();
var favData = {};
var queryObj = {};
var countryParam = '';
var countryExpandParameter = '';
queryObj.numberOfPage = 0;
    /*clears array of ids (used ids - ids of tours which were open on index page)*/
    function clearArray() {
        usedIds=[];
    }
    /* Sends parameters to indexController and views the result list of tours. */
    function parseTour (countryPar,numberOfPage,countryParameter) {
        countryParam=countryPar;
        countryExpandParameter = countryParameter;
        $('#indexResult').empty();//clear div-container(needs to prevent doubling information or stacking information).
        showModal();//shows invisible div-container
        $('#indexResult').append('<div class="col-md-12" id="loading"><img src="img/preloader.gif"></div><br>');
        var indexBudget = $("#indexBudget").val(); //reading parameters from view
        if (indexBudget > 0) {} else indexBudget = 1500;
        var travelersAdult = $("#TravelersAdult").val();
        if (travelersAdult > 0){} else travelersAdult = 1;
        var travelersChildren = $("#TravelersChildren").val();
        if (travelersChildren > 0){} else travelersChildren = 0;
        queryObj.countryParameter = countryParameter;//setting parameters
        queryObj.numberOfPage = numberOfPage;
        queryObj.country = countryPar;
            queryObj.minPrice = Math.floor(indexBudget * 0.9);
            queryObj.maxPrice = Math.floor(indexBudget * 1.1);
        queryObj.travelersAdult = travelersAdult;
        queryObj.travelersChildren = travelersChildren;
        //sending object with parameters to /parseTour and getting results
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
                var toursForTemplate = [];

                $.each(data, function( index, value ) {

                    var currentTour = {};
                    currentTour.id = index;
                    currentTour.tour = value;

                    toursForTemplate.push(currentTour);

                })
                favData=data;
                console.log (data);
                $('#indexResult').empty();
                $('#indexResult').append('<div class="col-md-12"><strong>Результати пошуку:</strong></div><br>');
                $('#indexTemplate').tmpl(toursForTemplate).appendTo('#indexResult');
                $('#indexResult').append(
                        "<script type='text/javascript' src=\"js/star-rating.min.js\"/>"+
                        "<script type='text/javascript' src=\"js/bootstrap-table.min.js\"/>"+
                        "<script type='text/javascript' src=\"js/cityFrom.js\"/>"+
                        "<script type='text/javascript' src=\"js/transitOrderButton.js\"/>"+
                        "<script type='text/javascript' src=\"js/select2.min.js\"/>");
                $('#indexResult').append('<button type="button" class="btn btn-default pull-left" onclick="expandParse(-1)">Попередні</button>' +
                    '<button type="button" class="btn btn-default pull-right" onclick="expandParse(1)">Наступні</button>');

            },

            error: function () {
                alert("Something went wrong please try again.");
            }

        });

    }
/*Reads id of tour from view (when favorite button is clicked(star button))
* And sending request to /saveFavorites method in indexController.
* Also changes empty star button to full star button*/
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
/*Deletes favorites (launch by click on full star button):
Sends to deleteFavorites method in indexController.
Also changes full star button to empty.
 */
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
}
/*Saves historyRecord by sending current opened tour to saveHistoryRecord method in IndexController.*/
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
/*Realization for buttons "next page" & "previous page"*/
function expandParse (incDec){
    if (queryObj.numberOfPage!=1||incDec!=-1){
        var numb = queryObj.numberOfPage+incDec;
        parseTour(countryParam,numb,countryExpandParameter);
    }

}
/*Loads image and stars to current opened tour. */
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
                    if (data.hotel.imgUrl==null || data.hotel.imgUrl==''){
                        data.hotel.imgUrl='http://placehold.it/170&text=Not+found!';
                    }
                    $("#imgHold" + id).append('<img src="' + data.hotel.imgUrl + '" class="hotel-img-inTour img-circle" id="hotelImg\${id}">');
                },
        error: function(){
        saveHistoryRecord(id);
        console.log("Loading error.");
        $("#imgHold" + id).empty();
        $("#imgHold" + id).append('<img src="' + 'http://placehold.it/170&text=Not+found!' + '" class="hotel-img-inTour img-circle" id="hotelImg\${id}">');
        }
        })
}

