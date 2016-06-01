jQuery(document).ready(function () {
    jQuery('#list').click(function (event) {
        event.preventDefault();
        jQuery('#products .item').addClass('list-group-item');
    });
    jQuery('#grid').click(function (event) {
        event.preventDefault();
        jQuery('#products .item').removeClass('list-group-item');
        jQuery('#products .item').addClass('grid-group-item');
    });

    jQuery(function () {
        jQuery('#fromdate').datetimepicker({
            format: "YYYY-MM-DD"
        });
        
        jQuery('#todate').datetimepicker({
            format: "YYYY-MM-DD"
        });
    });

});


