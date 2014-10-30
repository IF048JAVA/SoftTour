if( $('#panel-2').length){
    $('#panel-2').ready (function () {

        var tourArr = [];
        $.getJSON("/userProfile/userHistory", function (history) {

            $.each(history, function (key, value) {
                value.tour.departureTime = value.tour.departureTime.slice(0,-3)
                if (value.tour.hotel.imgUrl==''){
                    value.tour.hotel.imgUrl='http://placehold.it/170&text=Not+found!';
                }
            })

            $('#historyTemplate').tmpl(history).appendTo('#panel-2');
        })
    })
}