<%@page import="library.DefineLb2"%>
<%@page import="library.DefineLb"%>
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
                <h2>Quản lý bài hát</h2>
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
                                    <a href="<%=request.getContextPath() %>/admin/add-song" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/search-song">
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
										<div><span style="background-color: yellow; color: red; font-style: italic;">Sửa bài hát thành công!</span></div>
										<%
									}else{
										if(msg==2){
										%>
										<div><span style="background-color: yellow; color: red; font-style: italic;">Sửa bài hát không thành công!</span></div>
										<%
										}else{
											if(msg==3){
												%>
												<div><span style="background-color: yellow; color: red; font-style: italic;">Xóa bài hát thành công!</span></div>
												<%
											}else{
												if(msg==4){
												%>
												<div><span style="background-color: yellow; color: red; font-style: italic;">Xóa bài hát không thành công!</span></div>
												<%
												}else{
													if(msg==5){
														%>
														<div><span style="background-color: yellow; color: red; font-style: italic;">Sửa bài hát không thành công do tên trùng!</span></div>
														<%
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
                                        <th>Tên bài hát</th>
                                        <th>Danh mục</th>
                                        <th>Lượt đọc</th>
                                        <th>Hình ảnh</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                if(request.getAttribute("listSong")!=null){
                                	@SuppressWarnings("unchecked")
                              		ArrayList<SongsJoinCat> listSong = (ArrayList<SongsJoinCat>)request.getAttribute("listSong");
                                	if(listSong.size()>0){
                              			for(SongsJoinCat objS: listSong){
                                %>
                                    <tr>
                                        <td><%=objS.getId() %></td>
                                        <td class="center"><%=objS.getName() %></td>
                                        <td class="center"><%=objS.getCategories() %></td>
                                        <td class="center"><%=objS.getCount_view() %></td>
                                        <td class="center">
											<img width="200px" height="200px" src="<%=request.getContextPath() %>/templates/admin/assets/img/<%=objS.getPicture() %>" alt="<%=objS.getName() %>"/>
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
                            <div class="row">
                            	<%
    								if(request.getAttribute("index")!=null){
    									int index =(Integer)request.getAttribute("index");
    									int dfNum = DefineLb.NUMBER_PER_PAGE;
                            			SongDAO objS = new SongDAO();
										int numPage = objS.getNumberPage();
										int number_page = DefineLb2.NUMBER_PAGE;
										int numcur =number_page + index -1;
										int totalSong=objS.getNumSong();
										String atc = "active";
										int numS=0;
										 	
										if(index<numPage){
											numS = (index-1)*dfNum+dfNum;
										}else{
											numS = totalSong;
										}
									
								%>
                                <div class="col-sm-6" style="width: 300px;">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=(index-1)*dfNum+1 %> đến <%=numS %> của <%=totalSong %> bài</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right; width: 690px;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                    
                                        <ul class="pagination">
                                        	<%
                                        	if(index>1){
                                        	%>
                                        	<li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/song?index=1">Về trang 1</a></li>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/song?index=<%=index-1%>">Trang trước</a></li>
                                            <%} %>
                                            <% 
                                            	if(numcur<=numPage){
                                            		for(int i=index; i<=numcur; i++){%>
                                            <li class="paginate_button <%if(i==index){%><%=atc %><%} %> " aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/song?index=<%=i%>"><%=i %></a></li>
                                            <%}}else{
                                            	for(int i=number_page+1; i<=numPage; i++){%>
                                            	
                                                <li class="paginate_button <%if(i==index){%><%=atc %><%} %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/song?index=<%=i%>"><%=i %></a></li>
                                                
                                                <%}
                                            } %>
                                            <% 
                                            	if(numcur>numPage&&number_page>=numPage){
                                            		for(int i=1; i<=numPage; i++){%>
                                            <li class="paginate_button <%if(i==index){%><%=atc %><%} %> " aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/song?index=<%=i%>"><%=i %></a></li>
                                            <%}}%>
                                            <%
                                            	if(index>=1&&index<numPage){
                                            %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/song?index=<%=index+1%>">Trang tiếp</a></li>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/song?index=<%=numPage%>">Đến trang cuối</a></li>
                                            <%} %>
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>