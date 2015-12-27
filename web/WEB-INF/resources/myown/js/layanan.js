/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var dataTables;
function initTable(){        
    dataTables = $('#dataTables').DataTable({      
        ajax : '../TugasAkhir/layanan.json',                    
        columns: [{            
            "data": "namaLayanan"
        },{
            "data": "nilaiLayanan"
        },{
            "data":   "nomorLayanan",
            "render": function ( data, type, row ) {
                return "<center><button class='edit btn btn-primary' onclick='update(this);' value='"+data+"' data-toggle='modal' data-target='#myModal'> <i class='fa fa-edit'></i> Edit</button> <button onclick='del(this);' class='delete btn btn-danger' value='"+data+"'> <i class='fa fa-trash-o'></i> Delete</button></center>";
            }
        }],
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": 5 
    });
        
}



function del(i){        
    var id = "nomorLayanan="+$(i).val();      
    var act = confirm("Anda yakin ingin menghapus data ini?");
    if (act==true) {
        $.ajax({
            type: "POST",
            url: "delete-layanan.htm",
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
    //    var url = "update-layanan.htm";      
    //    createForm(url, "nomorLayanan", $(i).val())
    
    $.ajax({
        type: "GET",
        url: "update-layanan.json",
        data: "nomorLayanan="+$(i).val(),
        dataType : "json",
        success: function (object) {            
            $("#form-layanan-edit input[name=nomorLayanan]").val(object.nomorLayanan);            
            $("#form-layanan-edit input[name=namaLayanan]").val(object.namaLayanan);                       
            $("#form-layanan-edit input[name=nilaiLayanan]").val(object.nilaiLayanan);                       
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
    $("#form-layanan").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: {
            namaLayanan: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }
            },
            nilaiLayanan: {
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
            url: "layanan.htm",
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
                $("#form-layanan")[0].reset();                                
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();                    
            } 
        });
        return false;       
    }); 
    
//    EDIT DATA
    $("#form-layanan-edit").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: {
            namaLayanan: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }
            },
            nilaiLayanan: {
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
            url: "update-layanan.htm",
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
                $("#form-layanan-edit")[0].reset();                                
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

 