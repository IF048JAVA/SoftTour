if( $('#panel-1').length){

    $('#panel-1').ready (function () {


        $.getJSON("/userProfile/userFavorite", function (favorites) {

            $.each(favorites, function (key, value) {
                value.tour.departureTime = value.tour.departureTime.slice(0,-3)
            })

            $('#favoriteTemplate').tmpl(favorites).appendTo('#panel-1');
        })
    })

    function delFavFunction(id) {
            $('#panel-favorite' + id).remove();
    }

    function deleteFavorite(id) {

        var favoriteToDelete = {};
        favoriteToDelete.id = id;

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





