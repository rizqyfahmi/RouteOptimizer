/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
//    var val = $(".main-sidebar .sidebar .sidebar-menu .li .a").html();
//    alert(val);
    var currentUrl = String(window.location.href).split("/")[String(window.location.href).split("/").length-1];
    $(".main-sidebar .sidebar .sidebar-menu li").each(function(i, v){
        if ($(v).find("a").attr("href")==currentUrl) {
            $(v).attr("class", "active");
        }
    });
});