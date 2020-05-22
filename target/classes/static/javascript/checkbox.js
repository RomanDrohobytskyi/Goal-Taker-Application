/*
Function to select and deselect all checkboxes by id
 */
function selectDeselectCheckbox(id) {

    var checkboxes = jQuery('input:checkbox');
    var countCheckedCheckboxes = checkboxes.filter(':checked').length;

    if ( countCheckedCheckboxes <= 1){
        jQuery(id).prop('checked', true);
        jQuery(id).parent().addClass('checked');
    } else {
        jQuery(id).prop('checked', false);
        jQuery(id).parent().remove('checked');
    }
    /*
    checkboxes.change(function(){
        var countCheckedCheckboxes = checkboxes.filter(':checked').length;
        jQuery('#count-checked-checkboxes').text(countCheckedCheckboxes);

        jQuery('#edit-count-checked-checkboxes').val(countCheckedCheckboxes);
    });
*/
}

function showSelectedCheckboxesCount(element, spanId) {
    var countCheckedCheckboxes = getSelectedCheckboxCount(element);
    // adding text to span
    jQuery(spanId).text(countCheckedCheckboxes);
}

function getSelectedCheckboxCount(element){
    var checkboxes = jQuery(element);
    var countCheckedCheckboxes = checkboxes.filter(':checked').length;
    return countCheckedCheckboxes;
}

/*
id - select list ID
inputValueToChange - value from select list on which check box must be checked
attributeName - attribute name to check/uncheck checkbox
*/
    function checkCheckBoxForSelectedInput(id, inputValueToChange, attributeName){
        var value = jQuery(id).attr("value");

            if(value == inputValueToChange){
            jQuery(attributeName).prop('checked', true);
            jQuery(attributeName).parent().addClass('checked');
            } else {
            jQuery(attributeName).prop('checked', false);
            jQuery(attributeName).parent().removeClass('checked');
            }
    }
