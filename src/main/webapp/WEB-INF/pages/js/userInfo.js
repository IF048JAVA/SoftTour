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
    userToUpdate.password = $('#password').val();
    userToUpdate.birthday = $('#birthday').val();
    userToUpdate.sex = $('#sex').val().toUpperCase();
    userToUpdate.phone = $('#phone').val();

    console.log(userToUpdate);

    $.ajax({
        url: "/userProfile/userToUpdate",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(userToUpdate),
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
