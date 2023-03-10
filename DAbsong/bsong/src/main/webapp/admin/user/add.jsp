﻿<%@page import="model.bean.Cat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CatDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <%
        	if(request.getParameter("msg")!=null){
        		int msg = Integer.parseInt(request.getParameter("msg"));
        		if(msg==1){
        			%>
        			<div><span style="background-color: yellow; color: red;">Thêm người dùng thành công!</span></div>
        			<%
        		}else{
        			%>
        			<div><span style="background-color: yellow; color: red;">Thêm người dùng thất bại!</span></div>
        			<%
        		}
        	}
        %>
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" class="form" action="<%=request.getContextPath() %>/admin/add-user" method="post" id="form">
                                    <div class="form-group">
                                        <label for="username">Username</label>
                                        <input type="text" id="username" value="" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                       <input type="password" id="password" name="password" value="" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="fullname">Fullname</label>
                                       <input type="text" id="fullname" name="fullname" value="" class="form-control">
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>