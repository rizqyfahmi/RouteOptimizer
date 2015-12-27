/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var mapFIFO;
var markerFIFO;
var markersFIFO = [];
function setMarkersFIFO(newMarker) {
    markersFIFO.push(newMarker);
}
function removeMarkersFIFO() {
    $(markersFIFO).each(function (i, object) {
        object.setMap(null);
    });
    markersFIFO = [];
}
function initialize() {
    $.getJSON('../TugasAkhirFinal/fifoTour.json', function (objects) {
    
    var myOptions = {
        zoom: 13,
        center: new google.maps.LatLng(-6.914744, 107.609810),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    mapFIFO = new google.maps.Map(document.getElementById('mapFIFO'), myOptions);
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

        flightPath.setMap(mapFIFO);
    });


//    var mapProp = {
//        center: new google.maps.LatLng(-6.914744, 107.609810),
//        zoom: 13,
//        mapTypeId: google.maps.MapTypeId.ROADMAP
//    };
//    map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
//    placeMarker();

    initWilayah();
    initKantor();
}

function initWilayah() {
    $.ajax({
        type: "GET",
        url: "fifoTour.json",
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


                markerFIFO = new google.maps.Marker({
                    position: new google.maps.LatLng(v.lat, v.lng),
//                    label: index,
                    icon: icon,
                    map: mapFIFO
                });
//                marker.setMap(map);
                var newMarker = markerFIFO;
                setMarkersFIFO(newMarker);
                var infowindow = new google.maps.InfoWindow({
                    content: v.keterangan + "<br/>" + v.alamat
                });
                google.maps.event.addListener(newMarker, 'click', function () {
                    infowindow.open(mapFIFO, newMarker);
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

function initKantor() {
    $.getJSON('../TugasAkhirFinal/kantorMap.json', function (objects) {
//        alert(objects);
        $(objects.data).each(function (i, v) {
//            alert(v.nama);
            markerFIFO = new google.maps.Marker({
                position: new google.maps.LatLng(v.lat, v.lng),
//                    label: index,
                icon: 'https://rangkaianmakna.files.wordpress.com/2015/09/pin-marker-telkom.png',
                map: mapFIFO
            });
//                marker.setMap(map);
            var newMarker = markerFIFO;
            setMarkersFIFO(newMarker);
            var infowindow = new google.maps.InfoWindow({
                content: v.nama + "<br/>" + v.alamat
            });
            google.maps.event.addListener(newMarker, 'click', function () {
                infowindow.open(mapFIFO, newMarker);
            });
        });
    });

}
//google.maps.event.addDomListener(window, 'load', initialize);
$(document).ready(function () {  
    initialize();
});
