<%@page import="library.DefineLb2"%>
<%@page import="library.DefineLb"%>
<%@page import="model.bean.Contact"%>
<%@page import="model.dao.ContactDAO"%>
<%@page import="model.bean.User"%>
<%@page import="model.dao.UserDAO"%>
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
                <h2>Quản lý liên hệ</h2>
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
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/search-contact">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name ="key" class="form-control input-sm" placeholder="Nhập tên" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
							<%
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									if(msg==1){
										%>
										<div><span style="background-color: yellow; color: red; font-style: italic;">Xóa liên hệ thành công!</span></div>
										<%
									}else{										
										%>
										<div><span style="background-color: yellow; color: red; font-style: italic;">Xóa liên hệ không thành công!</span></div>
										<%
									}
								}
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>message</th>
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
                            <div class="row">
                                <div class="col-sm-6">
                                <%
                                if(request.getAttribute("index")!=null){
                                	int index = (Integer)request.getAttribute("index");
                                	int dfNum = DefineLb.NUMBER_PER_PAGE;
	                                ContactDAO objCtDAO = new ContactDAO();
									int totalContact = objCtDAO.getNumContact();
									int numPage = objCtDAO.getNumberPage();
									int number_page = DefineLb2.NUMBER_PAGE;
									int numcur =number_page + index -1;
									int numCt = 0;
									String act = "active";
									if(index<numPage){
										numCt = (index-1)*3+3;
									}else{
										numCt = totalContact;
									}
                               %>
                                   <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=(index-1)*3+1 %> đến <%=numCt %> của <%=totalContact %> danh sách</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        <%
                                        	if(index>1){
                                        		%>
                                        			<li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/contact?index=<%=index-1%>">Trang trước</a></li>
                                        		<%
                                        	}
                                        %>
                                            <% for(int i=1; i<=numPage; i++){%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/contact?index=<%=i%>"><%=i %></a></li>
                                            <%}%>
                                            <%
                                            	if(index>=1&&index<numPage){
                                            		%>
                                            		<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/contact?index=<%=index+1%>">Trang tiếp</a></li>
                                            		<%
                                            	}
                                            %>
                                        	<%} %>
                                        </ul>
                                    </div>
                                </div>
                            </div>
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