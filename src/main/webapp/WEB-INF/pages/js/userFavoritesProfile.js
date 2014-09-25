if( $('#panel-1').length){
    $('#panel-1').ready (function () {

        var tourArr = [];
        $.getJSON("/userProfile/userFavorite", function (favorites) {

            $.each(favorites, function(key, value){
                tourArr.push(value.tour)
            })

            $.each(tourArr, function(index){

                $('#favoriteTemplate').tmpl({ "Name" : "John Doe" }).appendTo('#panel-1');

                console.log(tourArr[index]),
                    console.log(tourArr[index].days),
                    console.log(tourArr[index].hotel.region.country.name)

            })

        })

        console.log(favorites)

    })
}