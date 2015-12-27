/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var dataTables;
function initTable(){        
    dataTables = $('#dataTables').DataTable({      
        ajax : '../TugasAkhir/slg.json',                    
        columns: [{            
            "data": "namaSlg"
        },{
            "data": "nilaiSlg"
        },{
            "data":   "nomorSlg",
            "render": function ( data, type, row ) {
                return "<center><button class='edit btn btn-primary' onclick='update(this);' value='"+data+"' data-toggle='modal' data-target='#myModal'> <i class='fa fa-edit'></i> Edit</button> <button onclick='del(this);' class='delete btn btn-danger' value='"+data+"'> <i class='fa fa-trash-o'></i> Delete</button></center>";
            }
        }],
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": 5 
    });
        
}



function del(i){        
    var id = "nomorSlg="+$(i).val();      
    var act = confirm("Anda yakin ingin menghapus data ini?");
    if (act==true) {
        $.ajax({
            type: "POST",
            url: "delete-slg.htm",
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
        url: "update-slg.json",
        data: "nomorSlg="+$(i).val(),
        dataType : "json",
        success: function (object) {               
            $("#form-slg-edit input[name=nomorSlg]").val(object.nomorSlg);            
            $("#form-slg-edit input[name=namaSlg]").val(object.namaSlg);                       
            $("#form-slg-edit input[name=nilaiSlg]").val(object.nilaiSlg);                       
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
    $("#form-slg").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: {
            namaSlg: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }
            },
            nilaiSlg: {
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
            url: "slg.htm",
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
                $("#form-slg")[0].reset();                                
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();                    
            } 
        });
        return false;       
    }); 
    
//    EDIT DATA
    $("#form-slg-edit").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: {
            namaSlg: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }
            },
            nilaiSlg: {
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
            url: "update-slg.htm",
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
                $("#form-slg-edit")[0].reset();                                
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

 