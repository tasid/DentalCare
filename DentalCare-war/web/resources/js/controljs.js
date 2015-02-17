$(function() {
    $(".datepicker")
        .attr("readonly", "readonly")
        .datepicker({
            dateFormat : 'yy-mm-dd',
            changeMonth : true,
            changeYear : true,
            yearRange: "-130:+0"
        });
        
    $(".datepicker-upto-current-date")
        .attr("readonly", "readonly")
        .datepicker({
            dateFormat : 'yy-mm-dd',
            changeMonth : true,
            changeYear : true,
            yearRange: "-130:+0",
            maxDate: new Date()
        });
        
    $(".datepicker-from-current-date")
        .attr("readonly", "readonly")
        .datepicker({
            dateFormat : 'yy-mm-dd',
            changeMonth : true,
            changeYear : true,
            yearRange: "-130:+0",
            minDate: new Date()
        });
    
    $(".patient-list-online-appointment").attr("readonly", "readonly");
    
    var items = [];
    loadAvailableTimes = function(id, date) {
        var $orderItems = $("select#serviceItem");
        $orderItems.empty();
        $.ajax({
                url: '/DentalCare-war/appointment/availTimes;id=' + id + ";date=" + date,
                type: 'GET',
                dataType: "json",
                success : function(items) {				
                        $.each(items, function(index, item){
                                $orderItems.append("<option value=" + item.itemId + ">" + item.name + "</option>");
                        });
                }
        });
    };
});