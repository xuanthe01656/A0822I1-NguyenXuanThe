<%@page import="model.bean.Contact"%>
<%@page import="model.bean.User"%>
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
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>Message</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                if(request.getAttribute("listCt")!=null){
                                	@SuppressWarnings("unchecked")
                              		ArrayList<Contact> listCt = (ArrayList<Contact>)request.getAttribute("listCt");
                                	if(listCt.size()>0){
                              			for(Contact objCt: listCt){
                                %>
                                    <tr>
                                        <td><%=objCt.getId() %></td>
                                        <td class="center"><%=objCt.getName() %></td>
                                        <td class="center"><%=objCt.getEmail() %></td>  
                                        <td class="center"><%=objCt.getWebsite() %></td>
                                        <td class="center"><%=objCt.getMessage() %></td>                                    
                                        <td class="center">         
                                            <a href="<%=request.getContextPath() %>/admin/delete-contact?id=<%=objCt.getId() %>" onclick="return confirm('Bạn thật sự muốn xóa ?')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>