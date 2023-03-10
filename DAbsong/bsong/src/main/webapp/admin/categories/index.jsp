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
                <h2>Quản lý danh mục</h2>
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
                                <div class="col-sm-6" style="text-align: right;">
                                    <form action="<%=request.getContextPath()%>/admin/search-cat" method="post" >
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name ="key" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
                            <%
                            	if(request.getParameter("msg")!=null){
                            		int msg = Integer.parseInt(request.getParameter("msg"));
                            		if(msg==1){
                            			%>
                            			<div><span>Thêm danh mục thành công</span></div>
                            			<%
                            		}else{
                            			if(msg==2){
                            			%>
                            			<div><span>Thêm danh mục thất bại</span></div>
                            			<%
                            			}else{
                            				if(msg==3){
                            					%>
                            					<div><span>Sửa danh mục thành công!</span></div>
                            					<%
                            				}else{
                            					if(msg==4){
                            						%>
                            						<div><span>Sửa danh mục thất bại!</span></div>
                            						<%
                            					}else{
                            						if(msg==5){
                            							%>
                            							<div><span>Xóa danh mục thành công!</span></div>
                            							<%
                            						}else{
                            							if(msg==6){
                            							%>
                            							<div><span>Xóa danh mục thất bại!</span></div>
                            							<%
                            							}else{
                            								if(msg==7){
                            									%>
                                    							<div><span>Sửa danh mục thất bại do tên danh mục đã tồn tại!</span></div>
                                    							<%	
                            								}else{
                            									if(msg==8){
                                									%>
                                        							<div><span>Không được quyền thêm User!</span></div>
                                        							<%	
                                								}else{
                                									if(msg==9){
                                    									%>
                                            							<div><span>Không được quyền sửa User!</span></div>
                                            							<%	
                                    								}else{
                                    									if(msg==8){
                                        									%>
                                                							<div><span>Không được quyền xóa User!</span></div>
                                                							<%	
                                        								}
                                    								}
                                								}
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
                                        <th>Tên danh mục</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%                                	
                                	if(request.getAttribute("listCat")!=null){
                                		@SuppressWarnings("unchecked")
                                		ArrayList<Cat> listCat = (ArrayList<Cat>)request.getAttribute("listCat");
                                		if(listCat!=null&&listCat.size()>0){
                                			for(Cat objC: listCat){
                                %>
                                    <tr>
                                        <td><%=objC.getId() %></td>
                                        <td class="center"><%=objC.getName() %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/edit-cat?id=<%=objC.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/delete-cat?id=<%=objC.getId() %>" onclick="return confirm('Bạn thật sự muốn xóa ?')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
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
                           <!--  <div class="row">
                                <div class="col-sm-6">
                                <%
                                if(request.getAttribute("index")!=null){
                                	int index = (Integer)request.getAttribute("index");
                                	int index1=0;
                                	int index2=0;
                                    CatDAO objCDAO = new CatDAO();
                                    int totalCat = objCDAO.getNumCat();
                                    int numPage = objCDAO.getNumberPage();
                                    int numCat = 0;
                                    if(index<numPage){
                                    	numCat=(index-1)*3+3;
                                    }else{
                                    	numCat= totalCat;
                                    }
                                  %>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=(index-1)*3+1 %> đến <%=numCat %> của <%=totalCat %> danh mục</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/song?index=<%=index-1%>">Trang trước</a></li>
                                            <%                      
                                            for(int i = 1; i<=numPage;i++){ %>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/danh-muc-song?index=<%=i%>"><%=i %></a></li>                                           
                                            <%}%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/song?index=<%=index+1%>">Trang tiếp</a></li>
                                            <%}%>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div> -->

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>