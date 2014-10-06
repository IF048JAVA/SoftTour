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

    function deleteFavorite(id) {

        var favoriteToDelete = {};

        favoriteToDelete.id = id;


        console.log(favoriteToDelete);

        $.ajax({
            url: "/userProfile/favoriteToDelete",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(favoriteToDelete),
            contentType: 'application/json',
            mimeType: 'application/json',

            success: function(data) {
                alert(data.id);
            },
            error:function(data,status,er) {
                alert("error:")
            }
        });

    }
}





