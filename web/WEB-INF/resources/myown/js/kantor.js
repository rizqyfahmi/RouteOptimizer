/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var map;
var marker;
var markers = [];
var dataTables;

/*GMAP Function*/

function setMarkers(newMarker) {
    markers.push(newMarker);
}

function placeMarker() {
    $.getJSON("../TugasAkhirFinal/kantor.json", function (objects) {
//        var image = 'D:\PROJECTS\Spring 4\TugasAkhir\web\WEB-INF\resources\myown\icons\telkom.png';        
        var image = {
//            url: 'https://rangkaianmakna.files.wordpress.com/2015/09/plasa-telkom-marker.png'
//            url: 'https://rangkaianmakna.files.wordpress.com/2015/09/pin-marker.png'
            url: 'https://rangkaianmakna.files.wordpress.com/2015/09/pin-marker-telkom.png'
            // This marker is 20 pixels wide by 32 pixels high.
//            size: new google.maps.Size(42,68)
            // The origin for this image is (0, 0).
//            origin: new google.maps.Point(0, 0),
//            // The anchor for this image is the base of the flagpole at (0, 32).
//            anchor: new google.maps.Point(0, 32)
        };

        $(objects.data).each(function (i, object) {
//            alert(object.lat);
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(object.lat, object.lng),
                icon: image
//                label:"Telkom"
            });
            marker.setMap(map);
            var newMarker = marker;
            setMarkers(newMarker);
            var infowindow = new google.maps.InfoWindow({
                content: " NAMA KANTOR : " + object.nama + "<br/>"
                        + " ALAMAT : " + object.alamat + "<br/>"
                        + " TELEPON: " + object.telepon + "<br/>"
                        + " FAX: " + object.fax
            });
            google.maps.event.addListener(newMarker, 'click', function () {
                infowindow.open(map, newMarker);
            });

        });
    });
}

function removeMarkers() {
    $(markers).each(function (i, object) {
        object.setMap(null);
    });
    markers = [];
}

function initialize() {
    var mapProp = {
        center: new google.maps.LatLng(-6.914744, 107.609810),
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
    placeMarker();

}
google.maps.event.addDomListener(window, 'load', initialize);

function initTable() {
    dataTables = $('#dataTables').DataTable({
        ajax: '../TugasAkhirFinal/kantor.json',
        columns: [{
                "data": "nama"
            }, {
                "data": "alamat"
            }, {
                "data": "telepon"
            }, {
                "data": "fax"
            }, {
                "data": "id",
                "render": function (data, type, row) {
                    return "<center>\n\
                            <button class='detail btn btn-success' value='"+data+"'> <i class='fa fa-search'></i> Detail</button> \n\
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

function format(d) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
            '<tr>' +
            '<td>Latitude</td>' +
            '<td>:</td>' +
            '<td>' + d.lat + '</td>' +
            '</tr>' +
            '<tr>' +
            '<td>Longitude</td>' +
            '<td>:</td>' +
            '<td>' + d.lng + '</td>' +
            '</tr>' +           
            '</table>';
}

function del(i) {
    removeMarkers();
    var id = "id=" + $(i).val();
    var act = confirm("Anda yakin ingin menghapus data ini?");
    if (act == true) {
        $.ajax({
            type: "POST",
            url: "delete-kantor.htm",
            data: id,
            success: function (returndata) {
                notify("info", "Berhasil", "glyphicon glyphicon-ok");
                dataTables.ajax.reload();
                placeMarker();
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
    $.ajax({
        type: "GET",
        url: "update-kantor.json",
        data: "id=" + $(i).val(),
        dataType: "json",
        success: function (object) {
            $("#form-kantor-edit input[name=id]").val(object.id);
            $("#form-kantor-edit input[name=nama]").val(object.nama);
            $("#form-kantor-edit input[name=regional]").val(object.regional);
            $("#form-kantor-edit input[name=area]").val(object.area);
            $("#form-kantor-edit input[name=alamat]").val(object.alamat);
            $("#form-kantor-edit input[name=telepon]").val(object.telepon);
            $("#form-kantor-edit input[name=fax]").val(object.fax);            
            $("#form-kantor-edit input[name=teknisi]").val(object.teknisi);            
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
    $("#form-kantor").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: getField()
    }).on('success.form.bv', function (e) {
        removeMarkers();
        e.preventDefault();
        var formData = new FormData($(this)[0]);
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({
            'address': $("#form-kantor input[name=alamat]").val()
        }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                var lat = results[0].geometry.location.lat();
                var lng = results[0].geometry.location.lng();
                formData.append("lat", lat);
                formData.append("lng", lng);
                $.ajax({
                    url: "kantor",
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
                        placeMarker();
                    },
                    error: function (e) {
                        notify("warning", "Gagal", "glyphicon glyphicon-remove");
                    },
                    complete: function (status) {
                        dataTables.ajax.reload();
                        $("#form-kantor")[0].reset();
                        var $form = $(e.target);
                        $form.data('bootstrapValidator').resetForm();
                    }
                });
            }
        });
        return false;
    });

    //    EDIT DATA
    $("#form-kantor-edit").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
            live: 'submit'
        },
        fields: getField()
    }).on('success.form.bv', function (e) {
        removeMarkers();

        e.preventDefault();
        var formData = new FormData($(this)[0]);
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({
            'address': $("#form-kantor-edit input[name=alamat]").val()
        }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                var lat = results[0].geometry.location.lat();
                var lng = results[0].geometry.location.lng();
                formData.append("lat", lat);
                formData.append("lng", lng);
                $.ajax({
                    url: "update-kantor.htm",
                    type: 'POST',
                    data: formData,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (returndata) {
                        notify("info", "Berhasil", "glyphicon glyphicon-ok");
                        placeMarker();
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        notify("warning", xhr.responseText, "glyphicon glyphicon-remove");
                    },
                    complete: function (status) {
                        dataTables.ajax.reload();
                        $("#form-kantor-edit")[0].reset();
                        var $form = $(e.target);
                        $form.data('bootstrapValidator').resetForm();
                        $('#myModal').modal('hide')
                    }
                });
            }
        });


        return false;
    });
}

function getField() {
    return {
        kodeWilayah: {
            validators: {
                notEmpty: {
                    message: 'Wajib diisi'
                }
            }
        },
        namaWilayah: {
            validators: {
                notEmpty: {
                    message: 'Wajib diisi'
                }
            }
        },
        kabKota: {
            validators: {
                notEmpty: {
                    message: 'Wajib diisi'
                }
            }
        },
        kecamatan: {
            validators: {
                notEmpty: {
                    message: 'Wajib diisi'
                }
            }
        },
        kelurahan: {
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
    }


}

$(document).ready(function () {
    initTable();
    submit();
    upload();
});

 