﻿<%@page import="model.bean.User"%>
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
                <h2>Quản lý người dùng</h2>
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
                                    <a href="<%=request.getContextPath() %>/admin/add-user" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/search-user">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name ="key" class="form-control input-sm" placeholder="Nhập username" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
							<%
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									if(msg==1){
										%>
										<div><span style="background-color: yellow; color: red; font-style: italic;">Sửa user thành công!</span></div>
										<%
									}else{
										if(msg==2){
										%>
										<div><span style="background-color: yellow; color: red; font-style: italic;">Sửa user không thành công!</span></div>
										<%
										}else{
											if(msg==3){
												%>
												<div><span style="background-color: yellow; color: red; font-style: italic;">Xóa user thành công!</span></div>
												<%
											}else{
												if(msg==4){
													%>
													<div><span style="background-color: yellow; color: red; font-style: italic;">Xóa user không thành công!</span></div>
													<%
												}else{
													if(msg==5){
														%>
														<div><span style="background-color: yellow; color: red; font-style: italic;">Không được phép thêm User!</span></div>
														<%
													}else{
														if(msg==6){
															%>
															<div><span style="background-color: yellow; color: red; font-style: italic;">Không được phép sửa User!</span></div>
															<%
														}else{
															if(msg==7){
																%>
																<div><span style="background-color: yellow; color: red; font-style: italic;">Không được phép xóa User!</span></div>
																<%
															}
														}
													}
												}
											}
										}
									}
								}
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Username</th>
                                        <th>Fullname</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                	if(request.getAttribute("listU")!=null){
                                		@SuppressWarnings("unchecked")
                                		ArrayList<User> listU = (ArrayList<User>)request.getAttribute("listU");
                                		if(listU.size()>0){
                                			for(User objU: listU){
                                	%>
                                    <tr>
                                        <td><%=objU.getId() %></td>
                                        <td class="center"><%=objU.getUsername() %></td>
                                        <td class="center"><%=objU.getFullname() %></td>
                                        <td class="center">
                                        <%
                                        	if(session.getAttribute("objU")!=null){
                                        		User objU1 = (User) session.getAttribute("objU");
                                        		if("admin".equals(objU1.getUsername())){
                                        %>
                                            <a href="<%=request.getContextPath() %>/admin/edit-user?id=<%=objU.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/delete-user?id=<%=objU.getId() %>" onclick="return confirm('Bạn thật sự muốn xóa ?')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                            <%		}else{
                                            	if(objU1.getUsername().equals(objU.getUsername())){
                                            	%>
                                            	<a href="<%=request.getContextPath() %>/admin/edit-user?id=<%=objU.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            	<%
                                            }}
                                        		}%>
                                        </td>
                                    </tr>
                                    <%}}else{
                                    	%>
                                    	<tr><td colspan="3">Không có danh sách nào cả.</td></tr>
                                    	<%
                                    	}} %>	
                                </tbody>
                            </table>
                            <!-- <div class="row">
                            <%
                            	if(request.getAttribute("index")!=null){
                            		int index =(Integer)request.getAttribute("index");
    								UserDAO objUDAO = new UserDAO();
    								int totalUser = objUDAO.getNumUser();
									int numPage = objUDAO.getNumberPage();
									int numU=0;
									if(index<numPage){
										numU = (index-1)*3+3;
									}else{
										if(index==numPage){
										numU=totalUser;
										}
									}
								
							%>
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=(index-1)*3+1 %> đến <%=numU %> của <%=totalUser %></div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                    
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/user?index=<%=index-1%>">Trang trước</a></li>
                                            <% for(int i=1; i<=numPage; i++){%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/user?index=<%=i%>"><%=i %></a></li>
                                            <%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/user?index=<%=index+1%>">Trang tiếp</a></li>
                                        <%} %>
                                        </ul>
                                    </div>
                                </div>
                            </div>-->
                            
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>