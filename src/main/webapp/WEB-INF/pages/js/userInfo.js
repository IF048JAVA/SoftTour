if( $('#myProfile').length){

        $.getJSON("/userProfile/currentUser", function (userDates) {

            console.log(userDates)

            $('#name').val(userDates.name);
            $('#email').val(userDates.email);
            $('#birthday').val(userDates.birthday);
            $('#sex').val(userDates.sex.toLowerCase());
            $('#phone').val(userDates.phone);

        })
}

function userUpdate() {

    var userToUpdate = {};

    userToUpdate.name = $('#name').val();
    userToUpdate.email = $('#email').val();
    userToUpdate.name = $('#birthday').val();
    userToUpdate.sex = $('#sex').val().toUpperCase();
    userToUpdate.phone = $('#phone').val();

    console.log(userToUpdate)

//    var userx = {"name": "Andriy", "id": 1, "password": "1111", "role": {"name": "registeredUser", "id": 3}, "birthday": "1987-11-24", "email": "elips@gmail.com", "age": 26, "sex": "MALE", "phone": "+38(095)800-08-77"};

    var userx = {};
    userx.name= "aaa";
    userx.id= 1;
    userx.password = 1111;
    userx.role = {"name":"registeredUser","id":3}
    userx.age = 24;
    userx.birthday= "1987-11-24";
    userx.email= "elips@gmail.com";
    userx.sex= "MALE";
    userx.phone= "+38(095)800-08-77";

    console.log(userx)


    $.ajax({
        url: "/userProfile/userToUpdate",
        type: 'POST',
        dataType: 'json',
        data: userx,
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function(data) {
            alert(data.id + " " + data.name);
        },
        error:function(data,status,er) {
            alert("error:")
        }
    });
}
