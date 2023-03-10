<%@page import="model.dao.CatDAO"%>
<%@page import="model.bean.Cat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Kết quả tìm kiếm</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath() %>/admin/add-cat" class="btn btn-success btn-md">Thêm</a>
                                </div>
                            
                            </div>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên danh mục</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%                                	
                                	if(request.getAttribute("listC")!=null){
                                		@SuppressWarnings("unchecked")
                                		ArrayList<Cat> listCat = (ArrayList<Cat>)request.getAttribute("listC");
                                		if(listCat!=null&&listCat.size()>0){
                                			for(Cat objC: listCat){
                                %>
                                    <tr>
                                        <td><%=objC.getId() %></td>
                                        <td class="center"><%=objC.getName() %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/edit-cat?id=<%=objC.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/delete-cat?id=<%=objC.getId() %>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
									<%}}else{
										%>
										<tr>
											<td colspan="3" align="center">Chưa có danh mục</td>
										</tr>
										<%								
									}} %>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<!-- <script>
    document.getElementById("song").classList.add('active-menu');
</script> -->
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>