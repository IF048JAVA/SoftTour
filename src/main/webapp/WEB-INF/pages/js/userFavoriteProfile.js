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



