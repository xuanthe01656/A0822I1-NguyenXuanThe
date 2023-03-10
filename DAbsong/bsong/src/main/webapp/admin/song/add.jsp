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
                <h2>Thêm bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <%
	        String name = request.getParameter("name");
			String cid = request.getParameter("category");
			String preview = request.getParameter("preview");
			String detail = request.getParameter("detail");
        	if(request.getParameter("msg")!=null){
        		int msg = Integer.parseInt(request.getParameter("msg"));
        		if(msg==1){
        			%>
        			<div><span style="background-color: yellow; color: red;">Thêm bài hát thành công!</span></div>
        			<%
        		}else{
        			%>
        			<div><span style="background-color: yellow; color: red;">Thêm bài hát thất bại!</span></div>
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
                                <form role="form" class="form" action="<%=request.getContextPath() %>/admin/add-song" method="post" enctype="multipart/form-data" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên bài hát</label>
                                        <input type="text" id="name" value="<%if(name!=null){out.print(name);} %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục bài hát</label>
                                        <select id="category" name="category" class="form-control">
                                        <option value="">--Danh mục--</option>
                                        <%
                                        	CatDAO objCDAO = new CatDAO();
                                        	ArrayList<Cat> listC = objCDAO.getItems();
                                        		if(listC.size()>0){
                                        			for(Cat objC: listC){
                                        %>
	                                        <option <%if(cid!=null && cid.equals(String.valueOf(objC.getId()))){out.print("selected");} %> value ="<%=objC.getId()%>"><%=objC.getName() %></option>
	                                        <%}}else{
	                                        	%>
	                                        	<option>Không có danh mục</option>
	                                        	<%
	                                        } %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea id="preview" class="form-control" rows="3" name="preview"><%if(preview!=null){out.print(preview);} %></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label >Chi tiết</label>
                                        <textarea id="detail" class="form-control ckeditor" id="detail" rows="5" name="detail"><%if(detail!=null){out.print(detail);} %></textarea><br>
                                    	 <label for="detail" class="error"></label>
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>