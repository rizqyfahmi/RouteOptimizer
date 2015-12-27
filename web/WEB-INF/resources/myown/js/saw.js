/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function init(y, element) {
    $.ajax({
        type: "GET",
        url: "saw.json",
        data: "y=" + y,
        dataType: "json",
        success: function (object) {
            $(object).each(function (i, v) {
//                alert(element+":eq("+i+")");
                $(element + ":eq(" + i + ")").val(v);
            });
        },
        error: function () {
            notify("warning", "Gagal", "glyphicon glyphicon-remove");
        },
        complete: function (status) {

        }
    });
}

function submit(element, url, field) {
//    NEW DATA
    $(element).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: {
            field: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var formData = new FormData($(this)[0]);
        $.ajax({
            url: url,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            beforeSend: function () {
            },
            success: function (returndata) {
                notify("info", "Berhasil", "glyphicon glyphicon-ok");
            },
            error: function (e) {
                notify("warning", "Gagal", "glyphicon glyphicon-remove");
            },
            complete: function (status) {
                init("kepentingan", "#form-kepentingan select");
                init("keuntungan", "#form-keuntungan select");
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();
            }
        });
        return false;
    });
}

$(document).ready(function () {
    init("kepentingan", "#form-kepentingan select");
    init("keuntungan", "#form-keuntungan select");
    submit("#form-kepentingan", "saw-kepentingan", "kepentingan");
    submit("#form-keuntungan", "saw-keuntungan", "keuntungan");
});

 