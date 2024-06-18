function countMSDto(id) {
    let count = document.getElementById("counter" + id).value;
    document.getElementById("counter" + id).value = 0;
    return count;
}
function showCount(id) {
    let show = countMSDto(id);
    console.log(show)
}