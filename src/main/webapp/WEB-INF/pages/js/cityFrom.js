
$('#myProfile').ready(function () {

        $("[id|=cityFrom]").select2({
            placeholder: "Оберіть місто"
        });

    var cities = [
        'Вінниця',
        'Дніпропетровськ',
        'Донецьк',
        'Житомир',
        'Запоріжжя',
        'Івано-Франківськ',
        'Київ',
        'Кіровоград',
        'Луганськ',
        'Луцьк',
        'Львів',
        'Миколаїв',
        'Одеса',
        'Полтава',
        'Рівне',
        'Суми',
        'Тернопіль',
        'Ужгород',
        'Харків',
        'Херсон',
        'Хмельницький',
        'Черкаси',
        'Чернівці',
        'Чернігів']

    allCitieFrom="<option></option>";
    $.each(cities, function( index, value ) {
        allCitieFrom += '<option value="' + value + '">'
            + value + '</option>';
    });

    $("[id|=cityFrom]").html(allCitieFrom);

    })



