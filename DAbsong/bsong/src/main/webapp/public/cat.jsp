<%@page import="library.StringLb"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    <%
    	if(request.getAttribute("objCat")!=null){
    		Cat objCat = (Cat)request.getAttribute("objCat");
    %>
		<h1><%=objCat.getName() %></h1>
    </div>
    <%
    	if(request.getAttribute("listSong")!=null){
    		ArrayList<Songs> listSong = (ArrayList<Songs>)request.getAttribute("listSong");
    		int i=1;
    		int cid=0;
    		if(listSong.size()>0){
    			for(Songs objS: listSong){
    				cid=objS.getCat_id();
    %>
    <div class="article">
      <h2><a href="<%=request.getContextPath() %>/<%=StringLb.makeSlug(objS.getCategory().getName()) %>/<%=StringLb.makeSlug(objS.getName()) %>-<%=objS.getId() %>/<%=objS.getCat_id() %>.html" title="<%=objS.getName() %>"><%=objS.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=objS.getDate_create() %>. Lượt xem: <%=objS.getCounter() %> <a href="<%=request.getContextPath() %>/<%=StringLb.makeSlug(objS.getCategory().getName()) %>/<%=StringLb.makeSlug(objS.getName()) %>-<%=objS.getId() %>/<%=objS.getCat_id() %>.html" class="com"><span><%=i %></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/templates/admin/assets/img/<%=objS.getPicture() %>" width="177" height="213" alt="<%=objS.getName() %>" class="fl" /></div>
      <div class="post_content">
        <p><%=objS.getPreview_text() %></p>
        <p class="spec"><a href="<%=request.getContextPath() %>/<%=StringLb.makeSlug(objS.getCategory().getName()) %>/<%=StringLb.makeSlug(objS.getName()) %>-<%=objS.getId() %>/<%=objS.getCat_id() %>.html" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
    <%i++;}} %>
    <%
    if(request.getAttribute("numPage")!=null){
		int numPage = (Integer)request.getAttribute("numPage");
		int pageNum=(Integer)request.getAttribute("index");
		String atc="active1";
		
    %>
    <p class="pages"><small>Trang <%=pageNum %> của <%=numPage %></small>
    <%
    for(int j=1; j<=numPage; j++){
    %>
    <a class="<%if(pageNum==j){%> <%=atc%><%} %>" href="<%=request.getContextPath()%>/<%=StringLb.makeSlug(objCat.getName()) %>-<%=cid%>/page/<%=j%>"><%=j %></a>
   
    <%}}}} %>
     <a href="#">&raquo;</a></p>
  </div>
  
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<script>  
window.addEventListener('load', function() {
    $(".sb_menu >li>a[href='" + location.pathname + "']").parent().addClass("active2").each(function() {

       $(".sb_menu >li>a[href='" + $(this).attr("href") + "']").parent().addClass("active2")

   });
});
</script> 
<%@ include file="/templates/public/inc/footer.jsp" %>