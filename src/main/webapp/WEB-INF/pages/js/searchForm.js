function checkHotel(field){
    if(field.checked == true){
        return true;
    } else {
        document.getElementById("checkHelp").innerHTML = "Виберіть готель";
        return false;
    }
}

function checkFood(field){
    if(field.checked == true){
        return true;
    } else {
        document.getElementById("checkHelp2").innerHTML = "Виберіть тип харчування";
        return false;
    }
}

function checkDate(field){
    regExD = /^(0?[1-9]|[12][0-9]|3[01])[\.-](0?[1-9]|1[012])[\.-]\d{2}$/;
    if(field.value.length==0){
        document.getElementById("helpDate").innerHTML = "Введіть дату";
        return false;
    } else if(!field.value.match(regExD)){
        document.getElementById("helpDate").innerHTML = "Введіть коректно дату";
        return false;
    } else {
        document.getElementById("helpDate").innerHTML = "";
        return true;
    }
}

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