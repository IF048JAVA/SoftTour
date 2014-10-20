function showResults(){

    var queryObj = {};
    queryObj.departure = $("#to").val();
    queryObj.from = $("#from").val();

    $.ajax({
        url: "/search/getTour",
        type: "GET",

        dataType: 'json',
        contentType: 'application/json',
           mimeType: 'application/json',
        data: ({
            text: queryObj
        }),
        success: function(data){
            var result = data.Tour;
            $('#searchTemplate').tmpl(data).appendTo('#searchResult');
        }
    });
}
