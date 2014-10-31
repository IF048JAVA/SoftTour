
$('[id|=transitInfo-All] table').bootstrapTable({

    onLoadSuccess: function (date) {

        console.log('Event: onLoadSuccess, data: ', date);

        $('[id|=transitInfo-All] table tbody tr td:last-child').each(function () {

            if ($(this).text() == "-") {
                $(this).empty();
                $(this).append('<button type="button"class="transitOrderButton btn btn-info btn-xs" onclick="orderTransit(this)">Замовити</button>')
            }
        });
    }
});

function orderTransit(e){

    var dateForTransitOrder = {};
    dateForTransitOrder.date = $(e).parent().parent().children("td:nth-child(3)").text();
    dateForTransitOrder.cityFrom = $(e).parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().children('.form-inline').children('.form-group').children('[id|=cityFrom]').val();
    dateForTransitOrder.cityTo = $(e).parent().parent().children("td:nth-child(2)").text();

    console.log(dateForTransitOrder);

    $.ajax({
        url: "/dateForTransitOrder",
        type: 'GET',
        dataType: 'json',
        data: dateForTransitOrder,
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function(result) {
            console.log("Success: " +result.responseText);
        },
        complete: function(result) {
            console.log("complete:" +result.responseText);
        },
        error:function(xhr, status) {
            console.log("ERROR");
            console.log(xhr);
            console.log(status);
        }
    });

}
