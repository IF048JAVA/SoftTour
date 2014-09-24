$(document).ready(function(){
            
        $('#Countryselector').change(function(){
            loadRegion($(this).find(':selected').val())
        })
        })
        function loadRegion(country){
            if (country=="Єгипет") {
            $("#Regionselector").children().remove()
            $("#Regionselector").append("<option>Дахаб</option><option>Макаді Бей</option><option>Марса Алам</option>")
            }
            if (country=="Греція") {
            $("#Regionselector").children().remove()
            $("#Regionselector").append("<option>Агія Тріада</option><option>Астіпалея</option><option>Аттика</option>")
            }
            if (country=="Туреччина") {
            $("#Regionselector").children().remove()
            $("#Regionselector").append("<option>Аланья</option><option>Анталія</option><option>Белек</option>")
            }
        }