if( $('#panel-1').length){

    $('#panel-1').ready (function () {


        $.getJSON("/userProfile/userFavorite", function (favorites) {

            $.each(favorites, function (key, value) {
                value.tour.departureTime = value.tour.departureTime.slice(0,-3)
                if (value.tour.hotel.imgUrl==''){
                    value.tour.hotel.imgUrl='http://placehold.it/170&text=Not+found!';
                }
                console.log(value.tour.hotel.imgUrl);
            })

            $('#favoriteTemplate').tmpl(favorites).appendTo('#panel-1');
            $('#panel-1').append(
                    "<script type='text/javascript' src=\"js/star-rating.min.js\"/>"+
                    "<script type='text/javascript' src=\"js/bootstrap-table.min.js\"/>"+
                    "<script type='text/javascript' src=\"js/cityFrom.js\"/>"+
                    "<script type='text/javascript' src=\"js/transitOrderButton.js\"/>"+
                    "<script type='text/javascript' src=\"js/select2.min.js\"/>");

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
                console.log(data.id);
            },
            error:function(data,status,er) {
                console.log("error:")
            }
        });
    }
}





