/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var map;
var marker;
var markers = [];
var tableTiket, tableTiketFifo, tableSaw, tableSawFifo, tableTiketWilayah, tableResult, tableResultFifo, line;
function setMarkers(newMarker) {
    markers.push(newMarker);
}
function removeMarkers() {
    $(markers).each(function (i, object) {
        object.setMap(null);
    });
    markers = [];
}
function initialize() {
    var myOptions = {
        zoom: 13,
        center: new google.maps.LatLng(-6.914744, 107.609810),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

//    mapSAW = new google.maps.Map(document.getElementById('mapSAW'), myOptions);
//    mapACO = new google.maps.Map(document.getElementById('mapACO'), myOptions);
    map = new google.maps.Map(document.getElementById('googleMap'), myOptions);
    $.getJSON('../TugasAkhirFinal/info.json', function (objects) {
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

        flightPath.setMap(map);
    });


//    var mapProp = {
//        center: new google.maps.LatLng(-6.914744, 107.609810),
//        zoom: 13,
//        mapTypeId: google.maps.MapTypeId.ROADMAP
//    };
//    map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
//    placeMarker();

//    
    initWilayahResult();
    initKantorResult();
}
google.maps.event.addDomListener(window, 'load', initialize);
//function initMap() {
//    var directionsService = new google.maps.DirectionsService;
//    var directionsDisplay = new google.maps.DirectionsRenderer;
//    var map = new google.maps.Map(document.getElementById('googleMap'), {
//        zoom: 13,
//        center: new google.maps.LatLng(-6.914744, 107.609810),
//    });
//    $.getJSON("../TugasAkhirFinal/result.json", function (objects) {
//        var waypoints = [];
//        var origin, destination;
//        
//        $(objects.data).each(function (i, object) {
//            if (i===0) {
//                origin = object.alamat;
//            }else if (i===(objects.data.length-1)) {
//                destination = object.alamat;
//            }else{
//                waypoints.push({location:object.alamat});
//            }
////            alert(object.alamat);
////            d.push(object.kodePos + "." + object.kelurahan + "." + object.kecamatan + "." + object.kabKota + "." + object.nomorWilayah + "." + object.namaWilayah);
//        });
//        directionsDisplay.setMap(map);
//        calculateAndDisplayRoute(directionsService, directionsDisplay, origin, waypoints, destination);
//    });
//
//    // var onChangeHandler = function() {
//    //   calculateAndDisplayRoute(directionsService, directionsDisplay);
//    // };
//    // document.getElementById('start').addEventListener('change', onChangeHandler);
//    // document.getElementById('end').addEventListener('change', onChangeHandler);
//}

//function calculateAndDisplayRoute(directionsService, directionsDisplay, origin, waypoints, destination) {
//    directionsService.route({
//        // origin: document.getElementById('start').value,
//        // destination: document.getElementById('end').value,
//        origin: origin,
//        waypoints: waypoints,
//        destination: destination,
//        travelMode: google.maps.TravelMode.DRIVING,
//        avoidTolls: true
//    }, function (response, status) {
//        if (status === google.maps.DirectionsStatus.OK) {
//            directionsDisplay.setDirections(response);
//        } else {
//            window.alert('Directions request failed due to ' + status);
//        }
//    });
//}

function initTableTiket() {
    tableTiket = $('#tableTiket').DataTable({
        "bSort": false,
        "ajax": '../TugasAkhirFinal/tiket.json',
        "columns": [
            {"data": "nomorKunjungan"},
            {"data": "nomorTiket"},
            {"data": "slg"},
            {"data": "alert"},
            {"data": "regional"},
            {"data": "headline"},
            {"data": "cid"},
            {"data": "layanan"},
            {"data": "lamaGangguan"}
        ],
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": -1
    });


//    tableResult = $('#tableResult').DataTable({"aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]], "columns": column(),
//        "pageLength": 5});
}

function initTableTiketFifo() {
    tableTiketFifo = $('#tableTiketFifo').DataTable({
        "bSort": false,
        "ajax": '../TugasAkhirFinal/tiketFifo.json',
        "columns": [
            {"data": "nomorKunjungan"},
            {"data": "nomorTiket"},
            {"data": "slg"},
            {"data": "alert"},
            {"data": "regional"},
            {"data": "headline"},
            {"data": "cid"},
            {"data": "layanan"},
            {"data": "lamaGangguan"}
        ],
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": -1
    });
}

function initTableSaw() {
    tableSaw = $('#tableSaw').DataTable({
        "bSort": false,
        "ajax": '../TugasAkhirFinal/saw.json',
        "columns": [
            {"data": "rank"},
            {"data": "kantor"},
            {"data": "jarak"},
            {"data": "durasi"},
            {"data": "teknisi"},
            {"data": "saw"}
        ],
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
//            $('td', nRow).css('background-color', 'Red');
        },
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": -1
    });
}

function initTableSawFifo() {
    tableSawFifo = $('#tableSawFifo').DataTable({
        "bSort": false,
        "ajax": '../TugasAkhirFinal/sawFifo.json',
        "columns": [
            {"data": "rank"},
            {"data": "kantor"},
            {"data": "jarak"},
            {"data": "durasi"},
            {"data": "teknisi"},
            {"data": "saw"}
        ],
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
//            $('td', nRow).css('background-color', 'Red');
        },
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": -1
    });
}

function initTableResult() {
    tableResult = $('#tableResult').DataTable({
        "bSort": false,
        "ajax": '../TugasAkhirFinal/aco.json',
        "columns": [
            {"data": "kantor"},
            {"data": "jarak"},
            {"data": "durasi"}
//            {"data": "teknisi"}            
        ],
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": -1
    });
}

function initTableResultFifo() {
    tableResultFifo = $('#tableResultFifo').DataTable({
        "bSort": false,
        "ajax": '../TugasAkhirFinal/fifo.json',
        "columns": [
            {"data": "kantor"},
            {"data": "jarak"},
            {"data": "durasi"}
//            {"data": "teknisi"}            
        ],
        "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
        "pageLength": -1
    });
}

function initMorris() {

    $.getJSON('../TugasAkhirFinal/iterasi.json', function (objects) {
        var data = [];
        $(objects.data).each(function (i, v) {
            data.push({second: (v.iterasi).toString(), value: v.value});
        });

        new Morris.Line({
            element: 'line-chart',
            data: data,
            xkey: 'second',
            ykeys: ['value'],
            lineColors: ['#199cef']
//            labels: ['value']
        });
    });
}

function initFlotDistance() {
    $.getJSON('../TugasAkhirFinal/iterDistance.json', function (objects) {
        $.plot('#line-chart-distance', objects.dataset,
                {
                    grid: {
                        hoverable: true,
                        borderColor: "#f3f3f3",
                        borderWidth: 1,
                        tickColor: "#f3f3f3"
                    },
                    series: {
                        shadowSize: 0,
                        lines: {
                            show: true
                        },
                        points: {
                            show: true
                        }
                    }, lines: {
                        fill: false,
                        color: ["#3c8dbc", "#f56954"]
                    },
                    yaxis: {
                        show: true
                    },
                    xaxis: {
                        show: true
                    }
                });

        $("<div class='tooltip-inner' id='line-chart-tooltip'></div>").css({
            position: "absolute",
            display: "none",
            opacity: 0.8
        }).appendTo("body");
        $("#line-chart-distance").bind("plothover", function (event, pos, item) {

            if (item) {
                var x = item.datapoint[0].toFixed(2),
                        y = item.datapoint[1].toFixed(2);

                $("#line-chart-tooltip").html("Jarak terbaik pada iterasi ke " + x.split(".")[0] + " = " + y)
                        .css({top: item.pageY + 5, left: item.pageX + 5})
                        .fadeIn(200);
            } else {
                $("#line-chart-tooltip").hide();
            }

        });
    });
}
function initFlotDuration() {
    $.getJSON('../TugasAkhirFinal/iterDuration.json', function (objects) {
        $.plot('#line-chart-duration', objects.dataset,
                {
                    grid: {
                        hoverable: true,
                        borderColor: "#f3f3f3",
                        borderWidth: 1,
                        tickColor: "#f3f3f3"
                    },
                    series: {
                        shadowSize: 0,
                        lines: {
                            show: true
                        },
                        points: {
                            show: true
                        }
                    }, lines: {
                        fill: false,
                        color: ["#3c8dbc", "#f56954"]
                    },
                    yaxis: {
                        show: true
                    },
                    xaxis: {
                        show: true
                    }
                });

        $("<div class='tooltip-inner' id='line-chart-tooltip'></div>").css({
            position: "absolute",
            display: "none",
            opacity: 0.8
        }).appendTo("body");
        $("#line-chart-duration").bind("plothover", function (event, pos, item) {

            if (item) {
                var x = item.datapoint[0].toFixed(2),
                        y = item.datapoint[1].toFixed(2);

                $("#line-chart-tooltip").html("Durasi terbaik pada iterasi ke " + x.split(".")[0] + " = " + y)
                        .css({top: item.pageY + 5, left: item.pageX + 5})
                        .fadeIn(200);
            } else {
                $("#line-chart-tooltip").hide();
            }

        });
    });
}
function initWilayahResult() {
    $.ajax({
        type: "GET",
        url: "info.json",
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


                marker = new google.maps.Marker({
                    position: new google.maps.LatLng(v.lat, v.lng),
//                    label: index,
                    icon: icon,
                    map: map
                });
//                marker.setMap(map);
                var newMarker = marker;
                setMarkers(newMarker);
                var infowindow = new google.maps.InfoWindow({
                    content: v.keterangan + "<br/>" + v.alamat
                });
                google.maps.event.addListener(newMarker, 'click', function () {
                    infowindow.open(map, newMarker);
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

function initKantorResult() {
    $.getJSON('../TugasAkhirFinal/kantorMap.json', function (objects) {
//        alert(objects);
        $(objects.data).each(function (i, v) {
//            alert(v.nama);
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(v.lat, v.lng),
//                    label: index,
                icon: 'https://rangkaianmakna.files.wordpress.com/2015/09/pin-marker-telkom.png',
                map: map
            });
//                marker.setMap(map);
            var newMarker = marker;
            setMarkers(newMarker);
            var infowindow = new google.maps.InfoWindow({
                content: v.nama + "<br/>" + v.alamat
            });
            google.maps.event.addListener(newMarker, 'click', function () {
                infowindow.open(map, newMarker);
            });
        });
    });

}

$(document).ready(function () {
//    initMap();
//    initWilayah();    
    initTableTiket();
    initTableTiketFifo();
    initTableSaw();
    initTableSawFifo();
    initTableResult();
    initTableResultFifo();
    initFlotDistance();
    initFlotDuration();
//    initMorris();
//    initWilayah();

//    initSawCluster();
//    initSawWilayah();
});

 