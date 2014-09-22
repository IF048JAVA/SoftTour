function checkForm(name, email, area){
	name = document.getElementById(name).value;
	email = document.getElementById(email).value;
	area = document.getElementById(area).value;
    reg = "/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/";
	
	if(name.length<1){
		alert("Введіть коректне ім'я");
		return false;
	}else if(reg.match(email)){
		alert("Невірний e-mail");
		return false;
	}else if(area.length<1){
		alert("Введіть коментар");
		return false;
	}
}