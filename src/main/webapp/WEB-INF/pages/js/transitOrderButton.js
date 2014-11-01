
$('[id|=transitInfo-All] table').bootstrapTable({

    onLoadSuccess: function (date) {

        console.log('Event: onLoadSuccess, data: ', date);

        $('[id|=transitInfo-All] [id|=trainTransits] tbody tr td:last-child').each(function () {

            if ($(this).text() == "-") {
                $(this).empty();
                $(this).append('<button type="button"class="transitOrderButton btn btn-info btn-xs" onclick="orderTransitTrain(this)">Замовити</button>')
            }
        });

        $('[id|=transitInfo-All] [id|=busTransits] tbody tr td:last-child').each(function () {

            if ($(this).text() == "-") {
                $(this).empty();
                $(this).append('<button type="button"class="transitOrderButton btn btn-info btn-xs" onclick="orderTransitBus(this)">Замовити</button>')
            }
        });
    }
});

function orderTransitTrain(e){

    var dateForTransitOrder = {};
    dateForTransitOrder.date = $(e).parent().parent().children("td:nth-child(3)").text();
    dateForTransitOrder.cityFrom = $(e).closest('.panel-body').children('.form-inline').children('.form-group').children('[id|=cityFrom]').val();
    dateForTransitOrder.cityTo = $(e).parent().parent().children("td:nth-child(2)").text();

    console.log(dateForTransitOrder);

    $.ajax({
        url: "/dateForOrderTrain",
        type: 'GET',
        data: dateForTransitOrder,
        mimeType:"text/html; charset=UTF-8",

        success: function(result) {
            var myWindow = window.open(result);
        },
        error:function() {
            console.log("ERROR");
        }
    });

}

function orderTransitBus(e){

    var dateForTransitOrder = {};
    dateForTransitOrder.date = $(e).parent().parent().children("td:nth-child(3)").text();
    dateForTransitOrder.cityFrom = $(e).closest('.panel-body').children('.form-inline').children('.form-group').children('[id|=cityFrom]').val();
    dateForTransitOrder.cityTo = $(e).parent().parent().children("td:nth-child(2)").text();

    console.log(dateForTransitOrder);

    $.ajax({
        url: "/dateForOrderBus",
        type: 'GET',
        data: dateForTransitOrder,
        mimeType:"text/html; charset=UTF-8",

        success: function(result) {
            var myWindow = window.open(result);
        },
        error:function() {
            console.log("ERROR");
        }
    });

}