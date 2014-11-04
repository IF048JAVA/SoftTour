function updateData(){

    var html = '<img src="img/preloader.gif" class="preloader">';

    $("#adminLoader").html(html);

    $.ajax({
        url: "userProfile/admin",
        success: function(data){
            $("#adminLoader").empty;
            $("#adminLoader").html(data);
        },
            error: function(){
                $("adminLoader").empty;
                $("#adminLoader").html("error");
            }
    })
}
