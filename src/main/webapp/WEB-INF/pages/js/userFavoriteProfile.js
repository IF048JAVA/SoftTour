if( $('#panel-1').length){
    $('#panel-1').ready (function () {

        var tourArr = [];
        $.getJSON("/userProfile/userFavorite", function (favorites) {

            $.each(favorites, function(key, value){

                tourArr.push(value.tour)

            })

            $('#favoriteTemplate').tmpl(tourArr).appendTo('#panel-1');
        })
    })
}
