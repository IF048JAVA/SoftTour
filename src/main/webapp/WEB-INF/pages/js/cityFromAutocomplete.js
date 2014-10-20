$(function() {
    var availableTags = [
        "Івано-Франківськ",
        "Львів"

    ];
    $(".cityFromAutocomplete").autocomplete({
        source: availableTags
    });
});