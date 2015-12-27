<%-- 
    Document   : wilayah
    Created on : 10 Jul 15, 14:05:35
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
        <script src="${pageContext.request.contextPath}/resources/myown/js/kantor.js"></script>       
    </head>
    <body class="skin-red sidebar-collapse sidebar-mini">
        <div class="wrapper">
            <%@include file="layout/header.jsp" %>
            <%@include file="layout/sidebar.jsp" %>
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>Kantor Telkom</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-home"></i> Beranda</a></li>
                        <li class="active">Kantor Telkom</li>
                    </ol>
                </section>
                <!-- Main content -->
                <section class="content">
                    <!-- navigation -->
                    <div class="row">
                        <a href="#tab_1" data-toggle="tab">
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="info-box bg-red">
                                    <span class="info-box-icon"><i class="ion ion-compose"></i></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text"><i class="ion ion-compose"></i></span>
                                        <span class="info-box-number">Data</span>
                                        <div class="progress">
                                            <div class="progress-bar" style="width: 100%"></div>
                                        </div>
                                        <span class="progress-description">
                                            Baru
                                        </span>                                    
                                    </div><!-- /.info-box-content -->
                                </div><!-- /.info-box -->
                            </div><!-- /.col -->
                        </a> 
                        <a href="#tab_2" data-toggle="tab">
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="info-box bg-red">
                                    <span class="info-box-icon"><i class="ion ion-ios-list"></i></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text"><i class="ion ion-ios-list"></i></span>
                                        <span class="info-box-number">Lihat</span>
                                        <div class="progress">
                                            <div class="progress-bar" style="width: 100%"></div>
                                        </div>
                                        <span class="progress-description">
                                            Data
                                        </span>
                                    </div><!-- /.info-box-content -->                                    
                                </div><!-- /.info-box -->
                            </div><!-- /.col -->
                        </a>
                        <a href="#tab_3" data-toggle="tab">
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="info-box bg-red">
                                    <span class="info-box-icon"><i class="ion ion-map"></i></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text"><i class="ion ion-map"></i></span>
                                        <span class="info-box-number">Peta Lokasi</span>
                                        <div class="progress">
                                            <div class="progress-bar" style="width: 100%"></div>
                                        </div>
                                        <span class="progress-description">
                                            Peta
                                        </span>
                                    </div><!-- /.info-box-content -->                                    
                                </div><!-- /.info-box -->
                            </div><!-- /.col -->
                        </a>
                    </div><!-- /.navigation -->
                    <!--Content-->
                    <div class="tab-content">
                        <!--dataTable-->
                        <div class="tab-pane fade" id="tab_1">
                            <div class="row">
                                <div class="col-md-12">                                                                                  
                                    <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">
                                        <div class="box-header with-border">
                                            <i class="fa fa-pencil-square-o"></i>
                                            <h3 class="box-title">Tiket Default</h3>
                                            <div class="box-tools pull-right">                                                
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <form:form method="POST" cssClass="form" modelAttribute="kantor" id="form-kantor" >
                                            <div class="box-body">
                                                <div class="form-group hide">                                        
                                                    <input type="text" class="form-control" name="id" value="">
                                                </div>

                                                <div class="form-group">
                                                    <label>Nama Kantor</label>
                                                    <input type="text" class="form-control" name="nama" value="" placeholder="Masukkan nama kantor">
                                                </div>
                                                <div class="form-group">
                                                    <label>Regional</label>
                                                    <input type="text" class="form-control" name="regional" value="" placeholder="Masukkan regional">
                                                </div>
                                                <div class="form-group">
                                                    <label>Area</label>
                                                    <input type="text" class="form-control" name="area" value="" placeholder="Masukkan area">
                                                </div>
                                                <div class="form-group">
                                                    <label>Alamat</label>
                                                    <input type="text" class="form-control" name="alamat" value="" placeholder="Masukkan alamat kantor">
                                                </div>                                                     
                                                <div class="form-group">
                                                    <label>Telepon</label>
                                                    <input type="text" class="form-control" name="telepon" value="" placeholder="Masukkan telepon kantor">
                                                </div>                                                                                                                                                         
                                                <div class="form-group">
                                                    <label>Fax</label>
                                                    <input type="text" class="form-control" name="fax" value="" placeholder="Masukkan fax kantor">
                                                </div>
                                                <div class="form-group">
                                                    <label>Jumlah Teknisi</label>
                                                    <input type="text" class="form-control" name="teknisi" value="" placeholder="Masukkan jumlah teknisi">
                                                </div> 
                                            </div><!-- /.box-body --> 
                                            <div class="box-footer">
                                                <button type="submit" class="btn btn-danger">Simpan</button>
                                            </div>
                                        </form:form> 
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row -->                                
                        </div><!-- /.tab-pane -->
                        <div class="tab-pane fade" id="tab_2">
                            <div class="row">
                                <div class="col-md-12">                                    
                                    <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">                                                                                
                                        <div class="box-header with-border">
                                            <i class="fa fa-table"></i>
                                            <h3 class="box-title">Daftar Kantor</h3>
                                            <div class="box-tools pull-right">
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>                                                
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <table id="dataTables" class="table table-bordered table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Nama Kantor</th>
                                                        <th>Alamat</th>                                                
                                                        <th>Telepon</th>                                                
                                                        <th>Fax</th>                                                
                                                        <th style="width: 230%;"></th>                                                
                                                    </tr>
                                                </thead>
                                                <tbody></tbody>
                                            </table>
                                        </div><!-- /.box-body -->
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row -->                              
                        </div>
                        <div class="tab-pane active" id="tab_3">
                            <div class="row">
                                <div class="col-md-12">                                              
                                    <div class="box box-danger"  style="margin: 0 auto; margin-top: 1%">
                                        <div class="box-header with-border">
                                            <i class="fa fa-map-marker"></i>
                                            <h3 class="box-title">Peta Lokasi</h3>
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
            </div>
        </div>

        <!-- Modal -->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header bg-red">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit Data</h4>
                    </div>

                    <form:form method="POST" cssClass="form" modelAttribute="tipeTiket" id="form-kantor-edit">
                        <div class="modal-body">

                            <div class="form-group hide">                                        
                                <input type="text" class="form-control" name="id" value="">
                            </div>
                            <!--End Hidden Element-->
                            <div class="form-group">
                                <label>Nama Kantor</label>
                                <input type="text" class="form-control" name="nama" value="">
                            </div>
                            <div class="form-group">
                                <label>Regional</label>
                                <input type="text" class="form-control" name="regional" value="">
                            </div>
                            <div class="form-group">
                                <label>Area</label>
                                <input type="text" class="form-control" name="area" value="">
                            </div>
                            <div class="form-group">
                                <label>Alamat</label>
                                <input type="text" class="form-control" name="alamat" value="">
                            </div>                                                     
                            <div class="form-group">
                                <label>Telepon</label>
                                <input type="text" class="form-control" name="telepon" value="">
                            </div>                                                                                             
                            <div class="form-group">
                                <label>Fax</label>
                                <input type="text" class="form-control" name="fax" value="">
                            </div>            
                            <div class="form-group">
                                <label>Jumlah Teknisi</label>
                                <input type="text" class="form-control" name="teknisi" value="">
                            </div>  
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-danger">Simpan</button>
                        </div>                            
                    </form:form> 
                </div>
            </div>
        </div>


    </body>
</html>
