<%-- 
    Document   : Ticket
    Created on : 07 Jul 15, 11:49:38
    Author     : X450JN
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="title"/></title>   
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/myown/icons/telkom 2.png" sizes="192x192" />
        <%@include file="layout/css_includes.jsp" %>
        <%@include file="layout/js_includes.jsp" %>    
        
        <script src="${pageContext.request.contextPath}/resources/myown/js/result.js"></script>       
        <script src="${pageContext.request.contextPath}/resources/myown/js/mapFIFO.js"></script>   
        <script src="${pageContext.request.contextPath}/resources/myown/js/mapACO.js"></script>    
         
    </head>
    <body class="skin-red sidebar-collapse sidebar-mini">
        <div class="wrapper">
            <%@include file="layout/header.jsp" %>
            <%@include file="layout/sidebar.jsp" %>
            <div class="content-wrapper">
                <!-- Main content -->
                <section class="content"> 
                    <div class="row">
                        <a href="#tab_3" data-toggle="tab">
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box bg-red">
                                    <span class="info-box-icon"><i class="ion ion-arrow-graph-up-right"></i></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text"><i class="ion ion-arrow-graph-up-right"></i></span>
                                        <span class="info-box-number">FIFO</span>
                                        <div class="progress">
                                            <div class="progress-bar" style="width: 100%"></div>
                                        </div>
                                        <span class="progress-description">
                                            Antrean
                                        </span>
                                    </div><!-- /.info-box-content -->                                    
                                </div><!-- /.info-box -->
                            </div><!-- /.col -->
                        </a>  
                        <a href="#tab_2" data-toggle="tab">
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box bg-red">
                                    <span class="info-box-icon"><i class="ion ion-ios-pulse-strong"></i></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text"><i class="ion ion-ios-pulse-strong"></i></span>
                                        <span class="info-box-number">ACO</span>
                                        <div class="progress">
                                            <div class="progress-bar" style="width: 100%"></div>
                                        </div>
                                        <span class="progress-description">
                                            Optimasi
                                        </span>
                                    </div><!-- /.info-box-content -->                                    
                                </div><!-- /.info-box -->
                            </div><!-- /.col -->
                        </a>  
                        <a href="#tab_1" data-toggle="tab">
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box bg-red">
                                    <span class="info-box-icon"><i class="ion ion-android-star-outline"></i></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text"><i class="ion ion-android-star-outline"></i></span>
                                        <span class="info-box-number">SAW</span>
                                        <div class="progress">
                                            <div class="progress-bar" style="width: 100%"></div>
                                        </div>
                                        <span class="progress-description">
                                            Perankingan
                                        </span>
                                    </div><!-- /.info-box-content -->
                                </div><!-- /.info-box -->
                            </div><!-- /.col -->
                        </a> 
                        
                        <a href="#tab_4" data-toggle="tab">
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box bg-red">
                                    <span class="info-box-icon"><i class="ion ion-map"></i></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text"><i class="ion ion-map"></i></span>
                                        <span class="info-box-number">Result</span>
                                        <div class="progress">
                                            <div class="progress-bar" style="width: 100%"></div>
                                        </div>
                                        <span class="progress-description">
                                            Peta Lokasi
                                        </span>
                                    </div><!-- /.info-box-content -->                                    
                                </div><!-- /.info-box -->
                            </div><!-- /.col -->
                        </a>                        
                    </div><!-- /.navigation -->
                    <div class="tab-content">
                        <!--dataTable-->
                        <div class="tab-pane fade" id="tab_1">
                            <div class="row">
                                <div class="col-md-12">                                              
                                    <!--<div class="box box-danger" id="googleMap" style="margin: 0 auto; margin-top: 1%; padding-bottom: 40%;"></div>-->
                                    <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">
                                        <div class="box-header with-border">
                                            <i class="fa fa-table"></i>
                                            <h3 class="box-title">Table SAW</h3>
                                            <div class="box-tools pull-right">                                                
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <table id="tableSaw" class="table table-bordered table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Ranking</th>
                                                        <th>Kantor Telkom</th>
                                                        <th>Jarak Perjalanan (Km)</th>                                                
                                                        <th>Durasi Perjalanan (Jam)</th>                                                                                                                                             
                                                        <th>Sisa Teknisi (Orang)</th>                                                                                                                                             
                                                        <th>SAW</th>                                                                                                                                             
                                                    </tr>
                                                </thead>
                                                <tbody></tbody>
                                            </table>
                                        </div><!-- /.box-body --> 
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row -->    
                            <div class="row">
                                <div class="col-md-12">                                              
                                    <!--<div class="box box-danger" id="googleMap" style="margin: 0 auto; margin-top: 1%; padding-bottom: 40%;"></div>-->
                                    <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">
                                        <div class="box-header with-border">
                                            <i class="fa fa-table"></i>
                                            <h3 class="box-title">Tiket</h3>
                                            <div class="box-tools pull-right">                                                
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <table id="tableTiket" class="table table-bordered table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Nomor Kunjungan</th>
                                                        <th>Nomor Tiket</th>
                                                        <th>SLG</th>                                                
                                                        <th>Alert</th>                                                
                                                        <th>Reg ID</th>                                                
                                                        <th>Headline</th>                                                
                                                        <th>CID / SID / Nomor Speedy</th>                                                
                                                        <th>Layanan</th>                                                
                                                        <th>Lama Gangguan</th>                                                                                                                                             
                                                    </tr>
                                                </thead>
                                                <tbody></tbody>
                                            </table>
                                        </div><!-- /.box-body --> 
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row -->  
                            
                        </div><!-- /.tab-pane -->
                        <div class="tab-pane fade" id="tab_2">
                            <div class="row">
                                <div class="col-md-12">                                    
                                    <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">                                                                                
                                        <div class="box-header with-border">
                                            <i class="fa fa-line-chart"></i>
                                            <h3 class="box-title">Grafik Iterasi Terhadap Jarak</h3>
                                            <div class="box-tools pull-right">
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>                                                
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div id="line-chart-distance" style="height: 300px; width: 100%; text-align: center; margin: 0 auto;"></div>
                                        </div><!-- /.box-body -->
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row -->  
                            <div class="row">
                                <div class="col-md-12">                                    
                                    <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">                                                                                
                                        <div class="box-header with-border">
                                            <i class="fa fa-line-chart"></i>
                                            <h3 class="box-title">Grafik Iterasi Terhadap Durasi</h3>
                                            <div class="box-tools pull-right">
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>                                                
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div id="line-chart-duration" style="height: 300px; width: 100%; text-align: center; margin: 0 auto;"></div>
                                        </div><!-- /.box-body -->
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row -->  
                            <div class="row">
                                <div class="col-md-12">                                              
                                    <!--<div class="box box-danger" id="googleMap" style="margin: 0 auto; margin-top: 1%; padding-bottom: 40%;"></div>-->
                                    <!--<div class="box box-danger collapsed-box" style="margin: 0 auto; margin-top: 1%;">-->
                                    <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">
                                        <div class="box-header with-border">
                                            <i class="fa fa-table"></i>
                                            <h3 class="box-title">Hasil Optimasi</h3>
                                            <div class="box-tools pull-right">                                                
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <table id="tableResult" class="table table-bordered table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Kantor Telkom</th>
                                                        <th>Jarak Perjalanan (Km)</th>                                                
                                                        <th>Durasi Perjalanan (Jam)</th>  
                                                        <!--<th>Sisa Teknisi (Orang)</th>-->  
                                                    </tr>
                                                </thead>
                                                <tbody></tbody>
                                            </table>
                                        </div><!-- /.box-body --> 
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row --> 
                              
                        </div>
                        <div class="tab-pane fade" id="tab_3">
                            <div class="row">
                                <div class="col-md-12">                                              
                                    <!--<div class="box box-danger" id="googleMap" style="margin: 0 auto; margin-top: 1%; padding-bottom: 40%;"></div>-->
                                    <!--<div class="box box-danger collapsed-box" style="margin: 0 auto; margin-top: 1%;">-->
                                    <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">
                                        <div class="box-header with-border">
                                            <i class="fa fa-table"></i>
                                            <h3 class="box-title">Hasil FIFO</h3>
                                            <div class="box-tools pull-right">                                                
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <table id="tableResultFifo" class="table table-bordered table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Kantor Telkom</th>
                                                        <th>Jarak Perjalanan (Km)</th>                                                
                                                        <th>Durasi Perjalanan (Jam)</th>  
                                                        <!--<th>Sisa Teknisi (Orang)</th>-->  
                                                    </tr>
                                                </thead>
                                                <tbody></tbody>
                                            </table>
                                        </div><!-- /.box-body --> 
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row --> 
                            <div class="row" style="display: none;">
                                <div class="col-md-12">                                              
                                    <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">
                                        <div class="box-header with-border">
                                            <i class="fa fa-table"></i>
                                            <h3 class="box-title">Table SAW</h3>
                                            <div class="box-tools pull-right">                                                
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <table id="tableSawFifo" class="table table-bordered table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Ranking</th>
                                                        <th>Kantor Telkom</th>
                                                        <th>Jarak Perjalanan (Km)</th>                                                
                                                        <th>Durasi Perjalanan (Jam)</th>                                                                                                                                             
                                                        <th>Sisa Teknisi (Orang)</th>                                                                                                                                             
                                                        <th>SAW</th>                                                                                                                                             
                                                    </tr>
                                                </thead>
                                                <tbody></tbody>
                                            </table>
                                        </div><!-- /.box-body --> 
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row -->    
                            <div class="row">
                                <div class="col-md-12">                                              
                                    <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">
                                        <div class="box-header with-border">
                                            <i class="fa fa-table"></i>
                                            <h3 class="box-title">Tiket</h3>
                                            <div class="box-tools pull-right">                                                
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <table id="tableTiketFifo" class="table table-bordered table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Nomor Kunjungan</th>
                                                        <th>Nomor Tiket</th>
                                                        <th>SLG</th>                                                
                                                        <th>Alert</th>                                                
                                                        <th>Reg ID</th>                                                
                                                        <th>Headline</th>                                                
                                                        <th>CID / SID / Nomor Speedy</th>                                                
                                                        <th>Layanan</th>                                                
                                                        <th>Lama Gangguan</th>                                                                                                                                             
                                                    </tr>
                                                </thead>
                                                <tbody></tbody>
                                            </table>
                                        </div>  
                                    </div>
                                </div> 
                            </div>    
                            
                            
                        </div><!-- /.tab-pane -->
                        
                        <div class="tab-pane active" id="tab_4">
                            <div class="row">
                                <div class="col-md-12">                                              
                                    <div class="box box-danger"  style="margin: 0 auto; margin-top: 1%">
                                        <div class="box-header with-border">
                                            <i class="fa fa-map-marker"></i>
                                            <h3 class="box-title">Peta Lokasi FIFO</h3>
                                            <div class="box-tools pull-right">                                                
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body chart-responsive">
                                            <div id="mapFIFO" style="height: 800px;"></div>
                                        </div><!-- /.box-body -->
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row -->  
                             
                            <!--div class="row">
                                <div class="col-md-12">                                              
                                    <div class="box box-danger"  style="margin: 0 auto; margin-top: 1%">
                                        <div class="box-header with-border">
                                            <i class="fa fa-map-marker"></i>
                                            <h3 class="box-title">Peta Lokasi ACO</h3>
                                            <div class="box-tools pull-right">                                                
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body chart-responsive">
                                            <div id="mapACO" style="height: 800px;"></div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- /.row --> 
                           
                            <div class="row">
                                <div class="col-md-12">                                              
                                    <div class="box box-danger"  style="margin: 0 auto; margin-top: 1%">
                                        <div class="box-header with-border">
                                            <i class="fa fa-map-marker"></i>
                                            <h3 class="box-title">Peta Lokasi ACO</h3>
                                            <div class="box-tools pull-right">                                                
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body chart-responsive">
                                            <div id="googleMap" style="height: 800px;"></div>
                                        </div><!-- /.box-body -->
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row --> 
                        </div>

                    </div><!-- /.tab-content -->
                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->
        </div>                    
    </body>
</html>
