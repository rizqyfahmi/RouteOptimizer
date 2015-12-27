/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var mapACO;
var markerACO;
var markersACO = [];
function setMarkersACO(newMarker) {
    markersACO.push(newMarker);
}
function removeMarkersACO() {
    $(markersACO).each(function (i, object) {
        object.setMap(null);
    });
    markersACO = [];
}
function initializeACO() {
    $.getJSON('../TugasAkhirFinal/acoTour.json', function (objects) {

        var myOptions = {
            zoom: 13,
            center: new google.maps.LatLng(-6.914744, 107.609810),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        mapACO = new google.maps.Map(document.getElementById('mapACO'), myOptions);
        var flightPlanCoordinates = [];

        $(objects.data).each(function (i, v) {            
            flightPlanCoordinates.push(new google.maps.LatLng(v.lat, v.lng));
        });
        flightPlanCoordinates.push(new google.maps.LatLng(objects.data[0].lat, objects.data[0].lng));
        var flightPath = new google.maps.Polyline({
            path: flightPlanCoordinates,
            geodesic: true,
            strokeColor: '#FF0000',
            strokeOpacity: 1.0,
            strokeWeight: 2
        });

        flightPath.setMap(mapACO);
    });


//    var mapProp = {
//        center: new google.maps.LatLng(-6.914744, 107.609810),
//        zoom: 13,
//        mapTypeId: google.maps.MapTypeId.ROADMAP
//    };
//    map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
//    placeMarker();

    initWilayahACO();
    initKantorACO();
}

function initWilayahACO() {
    $.ajax({
        type: "GET",
        url: "acoTour.json",
        dataType: "json",
        success: function (object) {


            $(object.data).each(function (i, v) {
//                alert(v.status);
                var index = (i).toString();
                var icon = "";
                if ((v.status).toString() == "true") {
                    icon = 'https://rangkaianmakna.files.wordpress.com/2015/08/number_' + index + '.png';
                } else {
                    icon = 'https://rangkaianmakna.files.wordpress.com/2015/09/pin-marker-telkom.png';
                }


                markerACO = new google.maps.Marker({
                    position: new google.maps.LatLng(v.lat, v.lng),
//                    label: index,
                    icon: icon,
                    map: mapACO
                });
//                marker.setMap(map);
                var newMarker = markerACO;
                setMarkersACO(newMarker);
                var infowindow = new google.maps.InfoWindow({
                    content: v.keterangan + "<br/>" + v.alamat
                });
                google.maps.event.addListener(newMarker, 'click', function () {
                    infowindow.open(mapACO, newMarker);
                });
            });
        },
        error: function (xhr, ajaxOptions, thrownError) {
            notify("warning", xhr.responseText, "glyphicon glyphicon-remove");
        },
        complete: function (status) {
//            dataTables.ajax.reload();
        }
    });
}

function initKantorACO() {
    $.getJSON('../TugasAkhirFinal/kantorMap.json', function (objects) {
//        alert(objects);
        $(objects.data).each(function (i, v) {
//            alert(v.nama);
            markerACO = new google.maps.Marker({
                position: new google.maps.LatLng(v.lat, v.lng),
//                    label: index,
                icon: 'https://rangkaianmakna.files.wordpress.com/2015/09/pin-marker-telkom.png',
                map: mapACO
            });
//                marker.setMap(map);
            var newMarker = markerACO;
            setMarkersACO(newMarker);
            var infowindow = new google.maps.InfoWindow({
                content: v.nama + "<br/>" + v.alamat
            });
            google.maps.event.addListener(newMarker, 'click', function () {
                infowindow.open(mapACO, newMarker);
            });
        });
    });

}
//google.maps.event.addDomListener(window, 'load', initialize);
$(document).ready(function () {
    initializeACO();
});
