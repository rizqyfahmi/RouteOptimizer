<%-- 
    Document   : beranda
    Created on : Aug 17, 2015, 10:48:22 AM
    Author     : RIZQY FAHMI
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
        <script src="${pageContext.request.contextPath}/resources/myown/js/index.js"></script>       
    </head>
    <body class="skin-red sidebar-collapse sidebar-mini">
        <div class="wrapper">
            <%@include file="layout/header.jsp" %>
            <%@include file="layout/sidebar.jsp" %>
            <div class="content-wrapper">
                <!-- Main content -->
                <section class="content">
                    <!--img src="$!{pageContext.request.contextPath}/resources/myown/icons/telkom 3.png" height="300" style="display: block;margin-left: auto;margin-right: auto;"/-->                                                    
                    <form:form method="POST" cssClass="form" enctype="multipart/form-data" modelAttribute="tiket" id="form-beranda" >                                                
                        <div class="row">
                            <div class="col-md-12">                                              
                                <!--<div class="box box-danger" id="googleMap" style="margin: 0 auto; margin-top: 1%; padding-bottom: 40%;"></div>-->
                                <div class="box box-danger collapsed-box" style="margin: 0 auto; margin-top: 1%;">
                                    <div class="box-header with-border">
                                        <i class="fa fa-cogs"></i>
                                        <h3 class="box-title">Parameter Ant Colony Optimization</h3>
                                        <div class="box-tools pull-right">                                                
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label>C</label>
                                            <input type="text" class="form-control" name="c" value="1"/>
                                        </div> 
                                        <div class="form-group">
                                            <label>Alpha</label>
                                            <input type="text" class="form-control" name="alpha" value="1"/>
                                        </div> 
                                        <div class="form-group">
                                            <label>Betha</label>
                                            <input type="text" class="form-control" name="betha" value="5"/>
                                        </div> 
                                        <div class="form-group">
                                            <label>Q</label>
                                            <input type="text" class="form-control" name="q" value="1"/>
                                        </div> 
                                        <div class="form-group">
                                            <label>Evaporation</label>
                                            <input type="text" class="form-control" name="evaporation" value="0.5"/>
                                        </div> 
                                        <div class="form-group">
                                            <label>Max Iteration</label>
                                            <input type="text" class="form-control" name="maxIterations" value="100"/>
                                        </div> 
                                    </div><!-- /.box-body --> 
                                </div>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                        <div class="row">
                            <div class="col-md-12">                                              
                                <!--<div class="box box-danger" id="googleMap" style="margin: 0 auto; margin-top: 1%; padding-bottom: 40%;"></div>-->
                                <div class="box box-danger collapsed-box" style="margin: 0 auto; margin-top: 1%;">
                                    <div class="box-header with-border">
                                        <i class="fa fa-cogs"></i>
                                        <h3 class="box-title">Parameter Simple Additive Weighting</h3>
                                        <div class="box-tools pull-right">                                                
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label>Service Level Guarantee</label>
                                            <select name="weight" class="form-control">
                                                <option value="5">Sangat Penting</option>
                                                <option value="4" selected="true">Penting</option>
                                                <option value="3">Cukup Penting</option>
                                                <option value="2">Kurang Penting</option>
                                                <option value="1">Tidak Penting</option>
                                            </select>                                            
                                        </div>                                                                                                        
                                        <div class="form-group">
                                            <label>Lama Pengaduan</label>
                                            <select name="weight" class="form-control">
                                                <option value="5">Sangat Penting</option>
                                                <option value="4" selected="true">Penting</option>
                                                <option value="3">Cukup Penting</option>
                                                <option value="2">Kurang Penting</option>
                                                <option value="1">Tidak Penting</option>
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label>Sisa Waktu Optimal</label>
                                            <select name="weight" class="form-control">
                                                <option value="5" selected="true">Sangat Penting</option>
                                                <option value="4">Penting</option>
                                                <option value="3">Cukup Penting</option>
                                                <option value="2">Kurang Penting</option>
                                                <option value="1">Tidak Penting</option>
                                            </select>
                                        </div> 
                                    </div><!-- /.box-body --> 
                                </div>
                            </div><!-- /.col -->
                        </div><!-- /.row --> 

                        <div class="row">
                            <div class="col-md-12">                                              
                                <!--<div class="box box-danger" id="googleMap" style="margin: 0 auto; margin-top: 1%; padding-bottom: 40%;"></div>-->
                                <div class="box box-danger" style="margin: 0 auto; margin-top: 1%;">
                                    <div class="box-header with-border">
                                        <i class="fa fa-location-arrow"></i>
                                        <h3 class="box-title">Tiket</h3>
                                        <div class="box-tools pull-right">                                                
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>
                                    <div class="box-body">
                                        <!--                                        <div class="form-group">
                                                                                    <label>Kantor Telkom</label>
                                                                                    <select name="id" id="id" class="form-control"></select>
                                                                                </div>                                                                                                        -->
                                        <div class="form-group">
                                            <label>Tiket</label>
                                            <input type="file" class="filestyle" id="file" data-buttonName="btn-default" data-buttonText="Browse" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
                                        </div> 
                                    </div><!-- /.box-body --> 
                                    <div class="box-footer">
                                        <button type="submit" class="btn btn-danger">Simpan</button>
                                    </div>
                                </div>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </form:form>
                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->
        </div>                     
    </body>
</html>
