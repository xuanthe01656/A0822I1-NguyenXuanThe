<%@page import="model.bean.SongsJoinCat"%>
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
                <h2>Sửa bài hát</h2>
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
                                <form role="form" class="form" action="<%=request.getContextPath() %>/admin/edit-song" method="post" enctype="multipart/form-data" id="form">
                                <%
                                
                                if(request.getAttribute("objSJ")!=null&&request.getAttribute("objS")!=null){
                                	SongsJoinCat objS = (SongsJoinCat)request.getAttribute("objSJ");
                                	Songs objS1 = (Songs) request.getAttribute("objS");
                                %>
                                    <div class="form-group">
                                        <label for="name">Tên bài hát</label>
                                        <input type="text" id="name" value="<%=objS.getName() %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục bài hát</label>
                                        <select id="category" name="category" class="form-control">
	                                       <option value ="<%=objS.getCid()%>"><%=objS.getCategories() %></option>
                                        <%
                                        	CatDAO objCDAO = new CatDAO();
                                        	ArrayList<Cat> listC = objCDAO.getItems();
                                        		if(listC.size()>0){
                                        			for(Cat objC: listC){
                                        %>
	                                        <option value ="<%=objC.getId()%>"><%=objC.getName() %></option>
	                                        <%}}else{
	                                        	%>
	                                        	<option>Không có danh mục</option>
	                                        	<%
	                                        } %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <img style="height: 200px; width: 200px;" alt="" src="<%=request.getContextPath() %>/templates/admin/assets/img/<%=objS.getPicture() %>">
                                        <input value="" type="file" name="picture" />
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea id="preview" class="form-control" rows="3" name="preview"><%=objS1.getPreview_text() %></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label >Chi tiết</label>
                                        <textarea id="detail" class="form-control ckeditor" id="detail" rows="5" name="detail"><%=objS1.getDetail_text() %></textarea><br>
                                        <label for="detail" class="error"></label>
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>