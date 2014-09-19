function checkForm(user_name, user_email, user_area){
	name = document.getElementById(user_name).value;
	email = document.getElementById(user_email).value;
	area = document.getElementById(user_area).value;
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