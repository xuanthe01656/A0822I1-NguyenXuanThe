<%@page import="model.bean.User"%>
<%@page import="model.bean.Songs"%>
<%@page import="model.bean.Cat"%>
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
                <h2>Sửa người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" class="form" action="<%=request.getContextPath() %>/admin/edit-user" method="post" id="form">
                                <%
                                if(request.getAttribute("objU")!=null){
                                	User objU = (User)request.getAttribute("objU");
                                %>
                                    <div class="form-group">
                                        <label for="username">Username</label>
                                        <input type="text" id="username" value="<%=objU.getUsername() %>" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                       <input type="password" id="password" name="password" value="<%=objU.getPassword() %>" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="fullname">Fullname</label>
                                       <input type="text" id="fullname" name="fullname" value="<%=objU.getFullname() %>" class="form-control">
                                    </div>
                                    <%} %>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
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