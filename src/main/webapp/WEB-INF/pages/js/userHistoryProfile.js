if( $('#panel-2').length){
    $('#panel-2').ready (function () {

        var tourArr = [];
        $.getJSON("/userProfile/userHistory", function (history) {

            $.each(history, function(key, value){
                tourArr.push(value.tour)
            })

            $('#historyTemplate').tmpl(tourArr).appendTo('#panel-2');
        })
    })
}