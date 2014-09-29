function checkName(field){
    reg = /^[a-zA-Z'][a-zA-Z-' ]+[a-zA-Z']?$/;
    if(field.value.length==0){
        document.getElementById("text_help").innerHTML = "Введіть ім'я";
        return false;
    }else if(!field.value.match(reg)){
        document.getElementById("text_help").innerHTML = "Введіть коректне ім'я";
        return false;
    }
    else {
        document.getElementById("text_help").innerHTML = "";
        return true;
    }
}

function checkEmail(field) {
    reg = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
    if(field.value.length==0){
        document.getElementById("text_help2").innerHTML = "Введіть e-mail";
        return false;
    }else if(!field.value.match(reg)){
        document.getElementById("text_help2").innerHTML = "Введіть коректний e-mail";
        return false;
    }
    else {
        document.getElementById("text_help2").innerHTML = "";
        return true;
    }
}

function checkArea(field){
    if(field.value.length==0){
        document.getElementById("text_help3").innerHTML = "Введіть коментар";
        return false;
    }else if(field.value<10){
        document.getElementById("text_help3").innerHTML = "Закороткий коментар";
        return false;
    }
    else {
        document.getElementById("text_help3").innerHTML = "";
        return true;
    }
}
