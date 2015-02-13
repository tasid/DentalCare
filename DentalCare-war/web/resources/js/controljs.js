$(function() {
    $(".datepicker")
        .attr("readonly", "readonly")
        .datepicker({
            dateFormat : 'mm-dd-yy',
            changeMonth : true,
            changeYear : true,
            yearRange: "-130:+0"
        });
        
    $(".datepicker-upto-current-date")
        .attr("readonly", "readonly")
        .datepicker({
            dateFormat : 'mm-dd-yy',
            changeMonth : true,
            changeYear : true,
            yearRange: "-130:+0",
            maxDate: new Date()
        });
        
    $(".datepicker-from-current-date")
        .attr("readonly", "readonly")
        .datepicker({
            dateFormat : 'mm-dd-yy',
            changeMonth : true,
            changeYear : true,
            yearRange: "-130:+0",
            minDate: new Date()
        });
});