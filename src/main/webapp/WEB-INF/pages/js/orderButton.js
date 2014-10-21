

//$('[id|=transitInfo-All] table tbody tr td:last-child').ready (function () {
//
//    alert("11111111")
//
//    if (2==2){
//
//        alert("22222222")
//        this.append('<button type="button"class="btn btn-info btn-xs">Замовити</button>')
//    }
//})





$('[id|=transitInfo-All] table tbody tr td:last-child').each(function( index ) {



    if ($(this).text()=="-") {
        $(this).empty();
        $(this).append('<button type="button"class="btn btn-info btn-xs">Замовити</button>')

    }
});