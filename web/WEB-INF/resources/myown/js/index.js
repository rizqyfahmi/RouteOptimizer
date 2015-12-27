/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function upload() {
    $("#file").change(function () {
        $(this).closest('form').trigger('submit');
    });
}
function setKantor() {    
    $.ajax({
        type: "GET",
        url: "kantor.json",
        dataType: "json",
        success: function (object) {
            var kantor = "";
            $(object.data).each(function (i, v) {
                kantor += "<option value='"+v.id+"'>"+v.nama+"</option>";               
            });
            $("#id").html(kantor);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            notify("warning", xhr.responseText, "glyphicon glyphicon-remove");
        },
        complete: function (status) {
//            dataTables.ajax.reload();
        }
    });
}

$(document).ready(function () {
//    upload();
//alert(1);
    setKantor();
    
});

 