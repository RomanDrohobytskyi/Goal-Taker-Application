/**
 * Validating element length for range min, max.
 * <br>
 * Show Alert if out of range
 * @param elementName
 * @param elementId
 * @param min - minimal element length
 * @param max - maximal element length
 * @returns {boolean}
 */
function validateLength(elementName, elementId, min, max) {
    var value = document.getElementById(elementId).value;
    if (value.length < min || value.length > max) {
        alert(elementName + " - '" +value + "' - out of range < " + min + "; " +max + " >" );
        return false;
    }
}