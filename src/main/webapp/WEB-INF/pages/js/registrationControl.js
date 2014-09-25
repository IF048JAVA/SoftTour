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
	}

	/*RegExp regExp = new RegExp("[A-Za-z0-9]+@[A-Za-z0-9]+\.[A-Za-z]{2,4}");
	var validEmail = regExp.exec(email);
	alert("QQQ:" + validEmail);
	return false;
//	if(validEmail == null) {
//		alert("Ви ввели некоректний email !");	
//		return false;
//	} 
*/	
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
	
	if (phone.length > 20) {
		alert("Некоректно введений номер телефону ! \n" +
				"Номер повинний містити менше ніж 21 символ !");
		return false;
	}
	
}