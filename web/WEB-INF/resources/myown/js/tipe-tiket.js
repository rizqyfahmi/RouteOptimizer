/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var dataTables;
function initTable(){        
    dataTables = $('#dataTables').DataTable({      
        ajax : '../TugasAkhir/tipe-tiket.json',                    
        columns: [{            
            "data": "namaTipeTiket"
        },{
            "data": "nilaiTipeTiket"
        },{
            "data":   "nomorTipeTiket",
            "render": function ( data, type, row ) {
                return "<center><button class='edit btn btn-primary' onclick='update(this);' value='"+data+"' data-toggle='modal' data-target='#myModal'> <i class='fa fa-edit'></i> Edit</button> <button onclick='del(this);' class='delete btn btn-danger' value='"+data+"'> <i class='fa fa-trash-o'></i> Delete</button></center>";
            }
        }],
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": 5 
    });
        
}



function del(i){        
    var id = "nomorTipeTiket="+$(i).val();      
    var act = confirm("Anda yakin ingin menghapus data ini?");
    if (act==true) {
        $.ajax({
            type: "POST",
            url: "delete-tipe-tiket.htm",
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
    //    var url = "update-tipe-tiket.htm";      
    //    createForm(url, "nomorTipeTiket", $(i).val())
    
    $.ajax({
        type: "GET",
        url: "update-tipe-tiket.json",
        data: "nomorTipeTiket="+$(i).val(),
        dataType : "json",
        success: function (object) {            
            $("#form-tipeTiket-edit input[name=nomorTipeTiket]").val(object.nomorTipeTiket);            
            $("#form-tipeTiket-edit input[name=namaTipeTiket]").val(object.namaTipeTiket);                       
            $("#form-tipeTiket-edit input[name=nilaiTipeTiket]").val(object.nilaiTipeTiket);                       
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
    $("#form-tipeTiket").bootstrapValidator({
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
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        var formData = new FormData($(this)[0]);
        $.ajax({
            url: "tipe-tiket.htm",
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
                $("#form-tipeTiket")[0].reset();                                
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();                    
            } 
        });
        return false;       
    }); 
    
//    EDIT DATA
    $("#form-tipeTiket-edit").bootstrapValidator({
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
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        var formData = new FormData($(this)[0]);
        $.ajax({
            url: "update-tipe-tiket.htm",
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
                $("#form-tipeTiket-edit")[0].reset();                                
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

 