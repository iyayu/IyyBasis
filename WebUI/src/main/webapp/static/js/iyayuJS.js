
/*
 * 根据URL键获取值
 * layero: DOM
 * iframeIndex: iframe层下标
 * input type=text 的id
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

/*
 * 根据id获取 input type=text 值
 * layero: DOM
 * iframeIndex: iframe层下标
 * input type=text 的id
 */
function getIframeTextById(layero, iframeIndex, textId) {
    return $($(layero).find("iframe")[iframeIndex].contentWindow.document).find("#" + textId).val();
}


/*
 * 根据id获取 Select 选中值
 * layero: DOM
 * iframeIndex: iframe层下标
 * input type=text 的id
 */
function getIframeSelectValueById(layero, iframeIndex, selectId) {
    return $($(layero).find("iframe")[iframeIndex].contentWindow.document).find("#" + selectId).find("option:selected").val();
}