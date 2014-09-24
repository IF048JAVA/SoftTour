function checkForm(name, email, area){
	name = document.getElementById(name).value;
	email = document.getElementById(email).value;
	area = document.getElementById(area).value;
    reg =  /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
	
	if(name.length<1){
		alert("Введіть будь ласка ім'я");
		return false;
	}else if(!email.match(reg)){
		alert("Невірний e-mail");
		return false;
	}else if(area.length<10){
		alert("Введіть будь ласка коментар (мінімум 10 символів)");
		return false;
	}
}