<%@page import="model.bean.SongsJoinCat"%>
<%@page import="model.bean.Songs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.SongDAO"%>
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
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên bài hát</th>
                                        <th>Danh mục</th>
                                        <th>Lượt đọc</th>
                                        <th>Hình ảnh</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                if(request.getAttribute("listS")!=null){
                                	@SuppressWarnings("unchecked")
                              		ArrayList<SongsJoinCat> listSong = (ArrayList<SongsJoinCat>)request.getAttribute("listS");
                                	if(listSong.size()>0){
                              			for(SongsJoinCat objS: listSong){
                                %>
                                    <tr>
                                        <td><%=objS.getId() %></td>
                                        <td class="center"><%=objS.getName() %></td>
                                        <td class="center"><%=objS.getCategories() %></td>
                                        <td class="center"><%=objS.getCount_view() %></td>
                                        <td class="center">
											<img width="200px" height="200px" src="<%=request.getContextPath() %>/templates/admin/assets/img/doi-thay.jpg" alt="Đổi thay"/>
                                        </td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/edit-song?id=<%=objS.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/delete-song?id=<%=objS.getId() %>" onclick="return confirm('Bạn thật sự muốn xóa ?')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                    <%}}else{
                                    	%>
                                    	<tr><td colspan="6">Không có danh sách nào cả.</td></tr>
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
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>