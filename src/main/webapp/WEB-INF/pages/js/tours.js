$(document).ready(function () {})

    function searchTours(countryPar) {
        showModal();
        var queryObj = {};
        queryObj.country = countryPar;
        queryObj.minPrice = $("#minPrice").val();
        queryObj.maxPrice = $("#maxPrice").val();
        if ($('#minPrice').val() == '')
            queryObj.minPrice = 0;
        if ($('#maxPrice').val() == '')
            queryObj.maxPrice = 9999;
        $.ajax({
            url: "/result",
            type: "POST",
            data: queryObj,
            dataType: 'json',

            success: function (data) {
                $('#indexResult').empty();
                function createCollapse(idData, s) {
                    var idDataNew = idData + 1;
                    $("#indexResult").append('<div class="panel panel-default"><div class="panel-heading" >' +
                        '<a class="panel-title collapsed" data-toggle="collapse" data-parent="#indexResult" href="#panel-element-h' +
                        idDataNew + '">' +
                        '<span class="tabTitleFont">Країна:</span>' +
                        '<span id="tourCountry-h' + idDataNew + '" class="tabulatedTitle"> ' + s[idData].hotel.region.country.name + '</span>' +
                        '<span class="tabTitleFont">Тривалість туру:</span>'+
                        '<span id="tourDays-h' + idDataNew + '" class="tabulatedTitle"> ' + s[idData].days + ' Днів'+'</span>' +
                        '<span class="tabTitleFont">Вартість туру:</span>' +
                        '<span id="tourPrice-h' + idDataNew + '" class="tabulatedTitle"> ' + s[idData].price + ' $'+'</span>' +
                        '<span class="tabTitleFont">Харчування:</span>' +
                        '<span id="tourFood-h' + idDataNew + '" class="tabulatedTitle"> ' + s[idData].food.name + '</span>' +
                        '<span class="tabTitleFont">Дата:</span>'+
                        '<span id="tourDate-h' + idDataNew + '" class="tabulatedTitle"> ' + s[idData].date + '</span>' +
                        '</a>' +
                        '</div>' +
                        '<div id="panel-element-h' + idDataNew + '" class="panel-collapse collapse">' +
                        '<div class="panel-body">' +
                        'Info about tour #' + idDataNew + '...' +
                        '</div></div></div>');

                }

                var ss = JSON.parse(JSON.stringify(data));
                $("#panel1").css("visibility", "visible");
                $("#resText").css("visibility", "visible");
                for (var i = 0; i < ss.length; i++)
                    createCollapse(i, ss);

            },

            error: function () {
                if ($('#minPrice').val() == '' || $('#maxPrice').val() == '')
                    alert("Введіть мінімальну та максимальну ціну"); else
                    alert("Error");
            }

        });


    }
