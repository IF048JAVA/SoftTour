
$('userProfile').ready (function () {

    $.getJSON("/userProfile/userFavorite", function (favorites) {

        var tourArr = [];

        $.each(favorites, function(key, value){
            tourArr.push(value.tour)
        })

        $.each(tourArr, function(index){

            console.log(tourArr[index].hotel.region.country.name)

        })

        console.log(favorites)

    })

})



