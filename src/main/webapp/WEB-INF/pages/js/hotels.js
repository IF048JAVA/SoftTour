
function openModalWindow() {
    $('#myModal').modal('show');
}

function closeModalWindow() {
    $('#myModal').modal('hide');
}

$(document).ready(function() {
    $("#countrySelect2").select2({
        placeholder: "Всі країни"
    });

    $.getJSON('/hotels/allCountry', {
        ajax : 'true'
    }, function(country){
        var html = ' ';
        var len = country.length;
        for (var i = 0; i < len; i++) {
            html += '<option value="' + country[i].id + '">'
                +country[i].name + '</option>';
        }
        $('#countrySelect2').html(html);
    });
});


