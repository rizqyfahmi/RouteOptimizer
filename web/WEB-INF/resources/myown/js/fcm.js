/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function init(){ 
    $.ajax({
        type: "GET",
        url: "fcm.json",
//        data: "nomorTipeTiket="+$(i).val(),
        dataType : "json",
        success: function (object) {            
            $("#form-fcm input[name=clusters]").val(object.clusters);            
            $("#form-fcm input[name=exponent]").val(object.exponent);                       
            $("#form-fcm input[name=maxIter]").val(object.maxIter);                       
            $("#form-fcm input[name=epsilon]").val(object.epsilon);                       
        },
        error: function(){                
            notify("warning", "Gagal", "glyphicon glyphicon-remove");
        },
        complete:function(status){                                  
            
        } 
    });
}

function submit(){
//    NEW DATA
    $("#form-fcm").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: {
            clusters: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }
            },
            exponent: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }                        
            },
            maxIter: {
                validators: {
                    notEmpty: {
                        message: 'Wajib diisi'
                    }
                }            
            },
            epsilon: {
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
            url: "fcm",
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
                init();
//                $("#form-fcm")[0].reset();                                
                var $form = $(e.target);
                $form.data('bootstrapValidator').resetForm();                    
            } 
        });
        return false;       
    }); 
}

$(document).ready(function(){ 
    init();
    submit();
});

 