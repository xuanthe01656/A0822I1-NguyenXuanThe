<%@page import="model.bean.Cat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm bài hát</h2>
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
                                <form role="form" class="form" action="<%=request.getContextPath() %>/admin/edit-cat" method="post" enctype="multipart/form-data" id="form">
                                    <div class="form-group">
                                        <label for="categories">Danh mục bài hát</label>
                                        <%
                                        	if(request.getAttribute("objC")!=null){
                                        		Cat objC = (Cat)request.getAttribute("objC");
                                        %>
                                        <input type="text" id="categories" value="<%=objC.getName() %>" name="categories" class="form-control" />
                                        <%} %>
                                    </div>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>