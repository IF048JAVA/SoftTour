function getAllTransit(tourId) {

    var transitDates = {};

    transitDates.currentTourId = tourId;
    transitDates.cityFrom = $('#cityFrom'+transitDates.currentTourId).val();

    console.log(transitDates);

    $.ajax({
        url: "/transitDates",
        type: 'POST',
        dataType: 'json',
        data: transitDates,
        contentType: 'application/x-www-form-urlencoded'
    });
}