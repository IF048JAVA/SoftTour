function getAllTransit(tourId) {

    var transitDates = {};

    transitDates.currentTourId = tourId;
    transitDates.cityFrom = $('#cityFrom'+tourId).val();

    console.log(transitDates);

    $.ajax({
        url: "/trainTransitDates",
        url: "/busTransitDates",
        type: 'POST',
        dataType: 'json',
        data: transitDates,
        contentType: 'application/x-www-form-urlencoded',

        success: function(date) {





            $("#panel-element-f"+tourId+" panel-body", function(){

                $("#panel-element-f"+tourId+" .panel-body #transitInfo-All-F"+tourId).empty();



                    $("#panel-element-f"+tourId+" .panel-body").append('<div id="transitInfo-All-F'+tourId+'"'+'class="collapse in">'+

                    '<div class="panel panel-success col-sm-12">'+

                        '<h3>'+
                        'Поїздом&nbsp;'+
                            '<img src="../img/Transport-Train-icon.png" width="32" height="32"/>'+
                        '</h3>'+

                        '<table data-method="post" data-toggle="table" data-url="/trainTransitDates?currentTourId='+tourId+'&cityFrom='+transitDates.cityFrom+'">'+
                            '<thead>'+
                                '<tr>'+
                                    '<th data-field="departureCity" data-halign="center" data-align="center" data-valign="middle">Місто відправлення</th>'+
                                    '<th data-field="arrivalCity" data-halign="center" data-align="center" data-valign="middle">Місто прибуття</th>'+
                                    '<th data-field="departureDate" data-halign="center" data-align="center" data-valign="middle">Дата відправлення</th>'+
                                    '<th data-field="departureTime" data-halign="center" data-align="center" data-valign="middle">Час відправлення</th>'+
                                    '<th data-field="arrivalTime" data-halign="center" data-align="center" data-valign="middle">Час прибуття</th>'+
                                    '<th data-field="priceMin" data-halign="center" data-align="center" data-valign="middle">Ціна від</th>'+
                                    '<th data-field="priceMax" data-halign="center" data-align="center" data-valign="middle">Ціна до</th>'+
                                    '<th data-halign="center" data-align="center" data-valign="middle"></th>'+
                                '</tr>'+
                            '</thead>'+
                        '</table>'+

                        '<h3>'+
                        'Автобусом&nbsp;'+
                            '<img src="../img/Transport-Bus-icon.png" width="32" height="32"/>'+
                        '</h3>'+

                            '<table data-method="post" data-toggle="table" data-url="/busTransitDates?currentTourId='+tourId+'&cityFrom='+transitDates.cityFrom+'">'+
                            '<thead>'+
                                '<tr>'+
                                    '<th data-field="departureCity" data-halign="center" data-align="center" data-valign="middle">Місто відправлення</th>'+
                                    '<th data-field="arrivalCity" data-halign="center" data-align="center" data-valign="middle">Місто прибуття</th>'+
                                    '<th data-field="departureDate" data-halign="center" data-align="center" data-valign="middle">Дата відправлення</th>'+
                                    '<th data-field="departureTime" data-halign="center" data-align="center" data-valign="middle">Час відправлення</th>'+
                                    '<th data-field="arrivalTime" data-halign="center" data-align="center" data-valign="middle">Час прибуття</th>'+
                                    '<th data-field="priceMin" data-halign="center" data-align="center" data-valign="middle">Ціна від</th>'+
                                    '<th data-field="priceMax" data-halign="center" data-align="center" data-valign="middle">Ціна до</th>'+
                                    '<th data-halign="center" data-align="center" data-valign="middle"></th>'+
                                '</tr>'+
                            '</thead>'+
                        '</table>'+
                    '</div>'+
                '</div>'+
                        '<script type="text/javascript" src="js/bootstrap-table.min.js"></script>'+
                        '<script type="text/javascript" src="js/orderButton.js">'
                    )


                console.log("#panel-element-f"+tourId+" panel-body");
            });

        },
        error:function(date,status,er) {
        }
    });
}