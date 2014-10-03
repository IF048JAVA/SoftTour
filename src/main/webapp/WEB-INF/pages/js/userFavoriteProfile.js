if( $('#panel-1').length){
    var tourArr = [];
    $('#panel-1').ready (function () {


        $.getJSON("/userProfile/userFavorite", function (favorites) {

            $.each(favorites, function (key, value) {
                tourArr.push(value.tour)

            })
            $('#favoriteTemplate').tmpl(tourArr).appendTo('#panel-1');
        })

    })

    function delFavFunction(id) {

            $('#panel-favorite' + id).remove();

    }
}

function userUpdate() {

    var userToUpdate = {};

    userToUpdate.name = $('#name').val();
    userToUpdate.email = $('#email').val();
    userToUpdate.name = $('#birthday').val();
    userToUpdate.sex = $('#sex').val().toUpperCase();
    userToUpdate.phone = $('#phone').val();

    console.log(userToUpdate)







    $.ajax({
        url: "/userToUpdate",
        type: 'POST',
        dataType: 'json',
        data: {"name":"Andriy","id":1,"password":"1111","role":{"name":"registeredUser","id":3},"age":26,"birthday":"1987-11-24","email":"elips@gmail.com","sex":"MALE","phone":"+38(095)800-08-77"},
        contentType: 'application/json',
        mimeType: 'application/json'

    });
}

