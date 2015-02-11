$(function() {
    $(".datepicker")
        .attr("readonly", "readonly")
        .datepicker({
            dateFormat : 'mm-dd-yy',
            changeMonth : true,
            changeYear : true,
            yearRange: "-130:+0",
            maxDate: new Date()
        });
});