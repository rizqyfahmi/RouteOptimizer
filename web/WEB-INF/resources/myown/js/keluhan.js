/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var dataTables;
function initTable(){        
    dataTables = $('#dataTables').DataTable({      
        ajax : '../TugasAkhir/keluhan.json',                    
        columns: [{            
            "data": "namaKeluhan"
        },{
            "data": "nilaiKeluhan"
        },{
            "data":   "nomorKeluhan",
            "render": function ( data, type, row ) {
                return "<center><button class='edit btn btn-primary' onclick='update(this);' value='"+data+"' data-toggle='modal' data-target='#myModal'> <i class='fa fa-edit'></i> Edit</button> <button onclick='del(this);' class='delete btn btn-danger' value='"+data+"'> <i class='fa fa-trash-o'></i> Delete</button></center>";
            }
        }],
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": 5 
    });
        
}



function del(i){        
    var id = "nomorKeluhan="+$(i).val();      
    var act = confirm("Anda yakin ingin menghapus data ini?");
    if (act==true) {
        $.ajax({
            type: "POST",
            url: "delete-keluhan.htm",
            data: id,
            success: function (returndata) {                
                notify("info", "Berhasil", "glyphicon glyphicon-ok");
                dataTables.ajax.reload();
            },
            error: function(){                
                notify("warning", "Gagal", "glyphicon glyphicon-remove");
            },
            complete:function(status){
                 
            } 
        });                           
    }            
}

function update(i){     
    $.ajax({
        type: "GET",
        url: "update-keluhan.json",
        data: "nomorKeluhan="+$(i).val(),
        dataType : "json",
        success: function (object) {            
            $("#form-keluhan-edit input[name=nomorKeluhan]").val(object.nomorKeluhan);            
            $("#form-keluhan-edit input[name=namaKeluhan]").val(object.namaKeluhan);                       
            $("#form-keluhan-edit input[name=nilaiKeluhan]").val(object.nilaiKeluhan);                       
        },
        error: function(){                
            notify("warning", "Gagal", "glyphicon glyphicon-remove");
        },
        complete:function(status){                                  
            dataTables.ajax.reload(); 
        } 
    });
}

function submit(){
//    NEW DATA
    $("#form-keluhan").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: {
            namaKeluhan: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }
            },
            nilaiKeluhan: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }            
            }
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        var formData = new FormData($(this)[0]);
        $.ajax({
            url: "keluhan.htm",
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            beforeSend:function(){                                
            },
            success: function (returndata) {                
                notify("info", "Berhasil", "glyphicon glyphicon-ok");                                                                                 
            },
            error: function(e){
                notify("warning", "Gagal", "glyphicon glyphicon-remove");                
            },
            complete:function(status){     
                dataTables.ajax.reload();
                $("#form-keluhan")[0].reset();                                
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();                    
            } 
        });
        return false;       
    }); 
    
//    EDIT DATA
    $("#form-keluhan-edit").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: {
            namaKeluhan: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }
            },
            nilaiKeluhan: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }            
            }
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        var formData = new FormData($(this)[0]);
        $.ajax({
            url: "update-keluhan.htm",
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
            complete:function(status){     
                dataTables.ajax.reload();
                $("#form-keluhan-edit")[0].reset();                                
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();     
                $('#myModal').modal('hide')   
            } 
        });
        return false;       
    }); 
}

$(document).ready(function(){ 
    initTable();
    submit();
});

 