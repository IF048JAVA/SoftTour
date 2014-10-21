$(function() {
    var availableTags = [
        "Івано-Франківськ",
        "Львів",
        "Тернопіль"
    ];
    $(".cityFromAutocomplete").autocomplete({
        source: availableTags
    });
});