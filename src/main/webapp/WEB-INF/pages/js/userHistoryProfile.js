if( $('#panel-2').length){
    $('#panel-2').ready (function () {

        var tourArr = [];
        $.getJSON("/userProfile/userHistory", function (history) {

            $('#historyTemplate').tmpl(history).appendTo('#panel-2');
        })
    })
}