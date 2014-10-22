if( $('#myProfile').length){

        $.getJSON("/userProfile/currentUser", function (userDates) {

            console.log(userDates)

            $('#name').val(userDates.name);
            $('#email').val(userDates.email);
            $('#birthday').val(userDates.birthday);
            $('#sex').val(userDates.sex);
            $('#phone').val(userDates.phone);
        })
}

