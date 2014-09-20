var name;
var email;
var password;
var confirmPassword;
var phone;

function registrationControl(firstNameInput, EmailInput, 
					PasswordInput, ConfirmPasswordInput, PhoneNumberInput){
	name = document.getElementById(firstNameInput).value;
	email = document.getElementById(EmailInput).value;
	password = document.getElementById(PasswordInput).value;
	confirmPassword = document.getElementById(ConfirmPasswordInput).value;
	phone = document.getElementById(PhoneNumberInput).value;
	
	if (name.length < 6 ) {
		alert("Ви ввели некоректне ім'я ! \n" +
			"Ім'я повинно містити принаймні 6 символів !");
		return false;
	} else if (name.length > 30) {
		alert("Ви ввели некоректне ім'я ! \n" +
		"Ім'я повинно містити менше ніж 31 символ !");
		return false;
	} else if (email.length < 7) {
		alert("Ви ввели некоректний email ! \n" +
		"Email повинен містити принаймні 7 символів !");
	} else if (6 > password.length || password.length > 10 ) {
		alert("Ви ввели некоректний пароль ! \n" +
		"Пароль повинен містити від 6 до 10 символів !");
		return false;
	} else if (password != confirmPassword) {
		alert("Некоректно підтверджений пароль ! ");
		return false;
	} else if (phone.length > 20) {
		alert("Некоректно введений номер телефону ! \n" +
				"Номер повинний містити менше ніж 21 символ !");
		return false;
	}
	
}