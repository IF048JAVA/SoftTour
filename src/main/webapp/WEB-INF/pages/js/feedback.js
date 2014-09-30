function checkName(field){
    reg = /^[а-яА-ЯёЁіІїЇєЄa-zA-Z0-9]+$/;
    if(field.value.length==0||field.value.length==1){
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
    }else if(field.value.length<10){
        document.getElementById("text_help3").innerHTML = "Закороткий коментар";
        return false;
    }
    else {
        document.getElementById("text_help3").innerHTML = "";
        return true;
    }
}

function checkForm(form){
    if(checkName(form["name"])&&checkEmail(form["email"])&&checkArea(["area"])){
        form.submit();
    }else{
        alert("Некоректно заповнена форма");
    }
}