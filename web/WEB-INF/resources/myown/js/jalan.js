var dataTables;
function initTable() {
    dataTables = $('#dataTables').DataTable({
        ajax: '../TugasAkhir/jalan.json',
        columns: [{
                "data": "namaJalan"
            }, {
                "data": "kodePos"
            }, {
                "data": "nomorJalan",
                "render": function (data, type, row) {
                    return "<center>\n\
                            <button class='detail btn btn-success' value='" + data + "'> <i class='fa fa-search'></i> Detail</button>\n\
                            <button class='edit btn btn-primary' onclick='update(this);' value='" + data + "' data-toggle='modal' data-target='#myModal'> <i class='fa fa-edit'></i> Edit</button> \n\
                            <button onclick='del(this);' class='delete btn btn-danger' value='" + data + "'> <i class='fa fa-trash-o'></i> Delete</button>\n\
                        </center>";
                }
            }],
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": 5
    });
    
    $('#dataTables tbody').on('click', 'td .detail', function () {
        var tr = $(this).closest('tr');
        var row = dataTables.row(tr);

        if (row.child.isShown()) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child(format(row.data())).show();
            tr.addClass('shown');
        }
    });
}

function format ( d ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>Kode Pos</td>'+
            '<td>:</td>'+
            '<td>'+d.kodePos+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Kelurahan</td>'+
            '<td>:</td>'+
            '<td>'+d.kelurahan+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Kecamatan</td>'+
            '<td>:</td>'+
            '<td>'+d.kecamatan+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Kabupaten/Kota</td>'+
            '<td>:</td>'+
            '<td>'+d.kabKota+'</td>'+
        '</tr>'+        
        '<tr>'+
            '<td>Kode Wilayah</td>'+
            '<td>:</td>'+
            '<td>'+d.kodeWilayah+'</td>'+
        '</tr>'+        
        '<tr>'+
            '<td>Wilayah</td>'+
            '<td>:</td>'+
            '<td>'+d.namaWilayah+'</td>'+
        '</tr>'+        
    '</table>';
}

function del(i) {
    var id = "nomorJalan=" + $(i).val();
    var act = confirm("Anda yakin ingin menghapus data ini?");
    if (act == true) {
        $.ajax({
            type: "POST",
            url: "delete-jalan",
            data: id,
            success: function (returndata) {
                notify("info", "Berhasil", "glyphicon glyphicon-ok");
            },
            error: function () {
                notify("danger", "Gagal", "glyphicon glyphicon-remove");
            },
            complete: function (status) {
                dataTables.ajax.reload();
            }
        });
    }
}

function update(i) {
    $.ajax({
        type: "GET",
        url: "update-jalan.json",
        data: "nomorJalan=" + $(i).val(),
        dataType: "json",
        success: function (object) {
            $("#form-jalan-edit input[name=nomorJalan]").val(object.nomorJalan);
            $("#form-jalan-edit input[name=nomorWilayah]").val(object.nomorWilayah);
            $("#form-jalan-edit input[name=namaJalan]").val(object.namaJalan);
            $("#form-jalan-edit input[name=kodePos]").val(object.kodePos);
        },
        error: function () {
            notify("danger", "Gagal", "glyphicon glyphicon-remove");
        },
        complete: function (status) {
            dataTables.ajax.reload();
        }
    });
}
function typeAhead() {
    $("input[name=kodePos]").typeahead({
        source: function (query, process) {
            $.getJSON("../TugasAkhir/wilayah.json", function (objects) {
                var d = [];
                $(objects.data).each(function (i, object) {
                    d.push(object.kodePos + "." + object.kelurahan + "." + object.kecamatan + "." + object.kabKota + "." + object.nomorWilayah + "." + object.namaWilayah);
                });
                process(d);
            });

        },
        //                    scrollHeight:3,
        //                    displayField: 'subWilayah',
        highlighter: function (item) {
            var result = item.split(".");
            return "<div><strong>" + result[0] + "</strong> - " + result[1] + " - " + result[2] + " - " + result[3] + " - " + result[5] + "</div>";
        },
        updater: function (item) {
            var result = item.split(".");
            $("input[name=nomorWilayah]").val(result[4]);
            return result[0];
        }
    });
}

function submit() {
//    NEW DATA
    $("#form-jalan").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: getField()
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var formData = new FormData($(this)[0]);
        $.ajax({
            url: "jalan.htm",
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            beforeSend: function () {
            },
            success: function (returndata) {
                notify("success", "Berhasil", "glyphicon glyphicon-ok");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                notify("warning", xhr.responseText, "glyphicon glyphicon-remove");
            },
            complete: function (status) {
                dataTables.ajax.reload();
                $("#form-jalan")[0].reset();
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();
            }
        });
        return false;
    });

//    EDIT DATA
    $("#form-jalan-edit").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: getField()
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var formData = new FormData($(this)[0]);
        $.ajax({
            url: "update-jalan",
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                notify("success", "Berhasil", "glyphicon glyphicon-ok");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                notify("warning", xhr.responseText, "glyphicon glyphicon-remove");
            },
            complete: function (status) {
                dataTables.ajax.reload();
                $("#form-jalan-edit")[0].reset();
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();
                $('#myModal').modal('hide')
            }
        });
        return false;
    });
}

function getField() {
    return {
        namaJalan: {
            validators: {
                notEmpty: {
                    message: 'Wajib diisi'
                }
            }
        },
        kodePos: {
            validators: {
                notEmpty: {
                    message: 'Wajib diisi'
                }
            }
        }
    };
}

function upload(){
    $("#form-jalan-upload").submit(function(event){        
        event.preventDefault();
        var formData = new FormData($(this)[0]); 
        $.ajax({
            url: "upload-jalan",
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            beforeSend:function(){
                
            },
            success: function (returndata) {                
                notify("success", "Berhasil", "glyphicon glyphicon-ok");                                             
            },
            error: function (xhr, ajaxOptions, thrownError) {
                notify("warning", xhr.responseText, "glyphicon glyphicon-remove");                
            },
            complete:function(status){                
                dataTables.ajax.reload();
                $("#form-jalan-upload")[0].reset();                
//                $("#modalImport").modal("hide");  
//                $("#progressBar").show();
            } 
        });
        return false;
    });
}

$(document).ready(function () {
    initTable();
    submit();
    upload();
    typeAhead();
});