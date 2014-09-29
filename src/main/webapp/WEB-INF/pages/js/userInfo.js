if( $('#myProfile').length){
    $('#myProfile').ready (function () {


        $.getJSON("/userProfile/currentUser", function (userDates) {

            console.log(userDates.name)

            $('#name').val(userDates.name);
            $('#email').val(userDates.email);
            $('#birthday').val(userDates.birthday);
            $('#sex').val(userDates.sex.toLowerCase());
            $('#phone').val(userDates.phone);

        })
    })
}
