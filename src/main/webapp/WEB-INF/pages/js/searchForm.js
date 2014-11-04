//function check hotel checkbox
function checkHotel(){
    var two = document.getElementById("twoStar");
    var three = document.getElementById("threeStar");
    var four = document.getElementById("fourStar");
    var five = document.getElementById("fiveStar");
    if(two.checked == true||three.checked == true||four.checked == true||five.checked == true){
        document.getElementById("checkHelp").innerHTML = "";
        return true;
    } else {
        document.getElementById("checkHelp").innerHTML = "Виберіть готель";
        return false;
    }
}

//function check checkbox_food
function checkFood(){
    var one = document.getElementById("foodOne");
    var two = document.getElementById("foodTwo");
    var three = document.getElementById("foodThree");
    var four = document.getElementById("foodFour");
    var five = document.getElementById("foodFive");
    var six = document.getElementById("foodSix");
    if(one.checked == true||two.checked == true||three.checked == true||four.checked == true||five.checked == true||six.checked == true){
        document.getElementById("checkHelp2").innerHTML = "";
        return true;
    } else {
        document.getElementById("checkHelp2").innerHTML = "Виберіть тип харчування";
        return false;
    }
}

//function check date in search.jsp
function checkDate(field){

    if(field.value.length==0){
        document.getElementById("helpDate").innerHTML = "Введіть дату";
        return false;
    } else {
        document.getElementById("helpDate").innerHTML = "";
        return true;
    }
}

//function check price in search.jsp
function checkPrice(field){
    regEx = /^[0-9]+$/;
    if(field.value.length==0){
        document.getElementById("helpPrice").innerHTML = "Введіть ціну";
        return false;
    }else if(!field.value.match(regEx)){
        document.getElementById("helpPrice").innerHTML = "Введіть коректно ціну";
        return false;
    }
    else {
        document.getElementById("helpPrice").innerHTML = "";
        return true;
    }
}

//function get country from database to select country
function getCountry(){

        $.ajax({
            url: "search/getCountry",
            type: "POST",
            dataType: 'json',

            success: function(data){
                $('#selectCountry').tmpl(data).appendTo('#country');
                getRegion();
            }
        })
}
var queryObjOne = {};

//function get regions from database to region select
function getRegion(){
    queryObjOne.country = $("#country").val();
    $.ajax({
        url: "search/getRegion",
        type: "POST",
        data: queryObjOne,
        dataType: 'json',

        success:function(data){

            var select;
            select = document.getElementById('region');
            select.options.length = 0;

                $('#selectRegion').tmpl(data).appendTo('#region');

        }
    })
}

//calendar to date in search.jsp
$(document).ready(function () {

    $('#dateFrom').datepicker({

        format: 'dd.mm.yy'

    });
    $('#dateTo').datepicker({

        format: 'dd.mm.yy'

    });

});