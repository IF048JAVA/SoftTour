//
//    $('[id|=transitInfo-All] table tbody tr td:last-child').each(function () {
//
//        if ($(this).text() == "-") {
//            $(this).empty();
//            $(this).append('<button type="button"class="btn btn-info btn-xs">Замовити</button>')
//        }
//    });


    $('.table-hover').bootstrapTable({onLoadSuccess: function () {

        console.log("LOAD!!!!!!!!!!!!!!!!!!!!!!!!!!")

        $('[id|=transitInfo-All] table tbody tr td:last-child').each(function () {

            if ($(this).text() == "-") {
                $(this).empty();
                $(this).append('<button type="button"class="btn btn-info btn-xs">Замовити</button>')
            }
        });

        console.log("LOAD")

}})

