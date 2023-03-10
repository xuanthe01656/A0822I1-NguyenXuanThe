<%@page import="model.dao.UserDAO"%>
<%@page import="model.dao.SongDAO"%>
<%@page import="model.dao.CatDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>TRANG QUẢN TRỊ VIÊN</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-bars"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/cat" title="">Quản lý danh mục</a></p>
                        <%
                        CatDAO objCDAO = new CatDAO();
                        int num = objCDAO.getNumCat();
                        %>
                        <p class="text-muted">Có <%=num %> danh mục</p>
                        
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-bell-o"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/song" title="">Quản lý bài hát</a></p>
                        <%
                        SongDAO objSDAO = new SongDAO();
                        int num1 = objSDAO.getNumSong();
                        %>
                        <p class="text-muted">Có <%=num1 %> bài hát</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-rocket"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/user" title="">Quản lý người dùng</a></p>
                        <%
                        UserDAO objUDAO = new UserDAO();
                        int num2 = objUDAO.getNumUser();
                        %>
                        <p class="text-muted">Có <%=num2 %> người dùng</p>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<script>
    document.getElementById("index").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>