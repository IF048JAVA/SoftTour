var name;
var email;
var password;
var confirmPassword;
var birthday;
var phone;

function registrationControl(name, email, password, confirmPassword, birthday, phone){
	name = document.getElementById(name).value;
	email = document.getElementById(email).value;
	password = document.getElementById(password).value;
	confirmPassword = document.getElementById(confirmPassword).value;
	birthday = document.getElementById(birthday).value;
	phone = document.getElementById(phone).value;
	
	if (name.length < 2 || name.length > 30 ) {
		alert("Ви ввели некоректне ім'я ! \n" +
			"Ім'я повинно містити від 2 до 30 символів !");
		return false;
	}
	
	var pattEmail = /[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}\b/;
	if (!pattEmail.test(email)) {
		alert("Ви ввели некоректний email !");	
		return false;
	}
	
	if (email.length > 30) {
		alert("Ви ввели некоректний email ! \n" +
			"Email повинен містити від не більше 30 символів !");
		return false;
	}
	
	if (6 > password.length || password.length > 10) {
		alert("Ви ввели некоректний пароль ! \n" +
		"Пароль повинен містити від 6 до 10 символів !");
		return false;
	} 
	
	if (password != confirmPassword) {
		alert("Некоректно підтверджений пароль ! ");
		return false;
	} 
	
	if (birthday.length != 10) {
		alert("Ви ввели некоректну дату народження ! \n" +
			"Введіть дату згідно шаблону yyyy-mm-dd");
		return false;
	} 
	
	if (phone.length != 0) {
		var pattPhone =  /\+38\([0-9]{3}\)[0-9]{3}-[0-9]{2}-[0-9]{2}\b/;
		if (!pattPhone.test(phone)) {
			alert("Некоректно введений номер телефону ! \n" +
					"Номер повиннен відповідати шаблону +38(xxx)xxx-xx-xx ");
			return false;
		}
	}
}