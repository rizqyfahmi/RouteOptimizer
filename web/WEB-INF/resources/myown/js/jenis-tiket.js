/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var dataTables;
function initTable() {
    dataTables = $('#dataTables').DataTable({
        ajax: '../TugasAkhir/jenis-tiket.json',
        columns: [{
                "data": "namaJenisTiket"
            }, {
                "data": "nilaiJenisTiket"
            }, {
                "data": "nomorJenisTiket",
                "render": function (data, type, row) {
                    return "<center><button class='edit btn btn-primary' onclick='update(this);' value='" + data + "' data-toggle='modal' data-target='#myModal'> <i class='fa fa-edit'></i> Edit</button> <button onclick='del(this);' class='delete btn btn-danger' value='" + data + "'> <i class='fa fa-trash-o'></i> Delete</button></center>";
                }
            }],
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": 5
    });

}



function del(i) {
    var id = "nomorJenisTiket=" + $(i).val();
    var act = confirm("Anda yakin ingin menghapus data ini?");
    if (act == true) {
        $.ajax({
            type: "POST",
            url: "delete-jenis-tiket.htm",
            data: id,
            success: function (returndata) {
                notify("info", "Berhasil", "glyphicon glyphicon-ok");
                dataTables.ajax.reload();
            },
            error: function () {
                notify("warning", "Gagal", "glyphicon glyphicon-remove");
            },
            complete: function (status) {

            }
        });
    }
}

function update(i) {
    //    var url = "update-tipe-tiket.htm";      
    //    createForm(url, "nomorTipeTiket", $(i).val())

    $.ajax({
        type: "GET",
        url: "update-jenis-tiket.json",
        data: "nomorJenisTiket=" + $(i).val(),
        dataType: "json",
        success: function (object) {
            $("#form-jenisTiket-edit input[name=nomorJenisTiket]").val(object.nomorJenisTiket);
            $("#form-jenisTiket-edit input[name=namaJenisTiket]").val(object.namaJenisTiket);
            $("#form-jenisTiket-edit input[name=nilaiJenisTiket]").val(object.nilaiJenisTiket);
        },
        error: function () {
            notify("warning", "Gagal", "glyphicon glyphicon-remove");
        },
        complete: function (status) {
            dataTables.ajax.reload();
        }
    });
}

function submit() {
//    NEW DATA
    $("#form-jenisTiket").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: {
            namaTipeTiket: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }
            },
            nilaiTipeTiket: {
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
            url: "jenis-tiket.htm",
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
                dataTables.ajax.reload();
                $("#form-jenisTiket")[0].reset();
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();
            }
        });
        return false;
    });

//    EDIT DATA
    $("#form-jenisTiket-edit").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: {
            namaJenisTiket: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }
            },
            nilaiJenisTiket: {
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
            url: "update-jenis-tiket.htm",
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                notify("info", "Berhasil", "glyphicon glyphicon-ok");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                notify("warning", xhr.responseText, "glyphicon glyphicon-remove");
            },
            complete: function (status) {
                dataTables.ajax.reload();
                $("#form-jenisTiket-edit")[0].reset();
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();
                $('#myModal').modal('hide')
            }
        });
        return false;
    });
}

$(document).ready(function () {
    initTable();
    submit();
});

 