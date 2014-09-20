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
	
	if (name.length < 6 || name.length > 30 ) {
		alert("Ви ввели некоректне ім'я ! \n" +
			"Ім'я повинно містити від 6 до 30 символів !");
		return false;
	} else if (email.length < 7 || email.length >30) {
		alert("Ви ввели некоректний email ! \n" +
		"Email повинен містити від 7 до 30 символів !");
		return false;
	} else if (6 > password.length || password.length > 10) {
		alert("Ви ввели некоректний пароль ! \n" +
		"Пароль повинен містити від 6 до 10 символів !");
		return false;
	} else if (password != confirmPassword) {
		alert("Некоректно підтверджений пароль ! ");
		return false;
	}else if (birthday.length != 10) {
		alert("Ви ввели некоректну дату народження ! \n" +
			"Введіть дату згідно шаблону dd/mm/yyyy");
		return false;
	} else if (phone.length > 20) {
		alert("Некоректно введений номер телефону ! \n" +
				"Номер повинний містити менше ніж 21 символ !");
		return false;
	}
	
}