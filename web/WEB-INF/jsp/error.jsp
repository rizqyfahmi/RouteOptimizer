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
    </head>
    <body class="skin-red sidebar-collapse sidebar-mini">
        <div class="wrapper">
            <%@include file="layout/header.jsp" %>
            <%@include file="layout/sidebar.jsp" %>
            <div class="content-wrapper">
                <section class="content">

                    <div class="error-page">
                        <!--<h2 class="headline text-red" style="margin-right: 30px;">Error</h2>-->
                        <div class="error-content">
                            <h3><i class="fa fa-warning text-red"></i> Terjadi Kesalahan!</h3>
                            <a href="beranda">Kembali ke Beranda</a>
<!--                            <form class='search-form'>
                                <div class='input-group'>
                                    <input type="text" name="search" class='form-control' placeholder="Search"/>
                                    <div class="input-group-btn">
                                        <button type="submit" name="submit" class="btn btn-danger btn-flat"><i class="fa fa-search"></i></button>
                                    </div>
                                </div> /.input-group 
                            </form>-->
                        </div>
                    </div><!-- /.error-page -->

                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->
        </div>                    
    </body>
</html>
