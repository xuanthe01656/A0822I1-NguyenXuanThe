<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
   <%
      	if(request.getAttribute("listS")!=null){
      		ArrayList<Songs> listS = (ArrayList<Songs>)request.getAttribute("listS");
      		int num = listS.size();
      		%>
  <h1>Kết Quả tìm kiếm <span style="font-style: italic; font-size: 12px;">(Có <%=num %> bài hát được tìm thấy)</span></h1>
  <%
      		if(listS.size()>0){
      			int i=1;
      			for(Songs objS: listS){
      %>
    <div class="article">
      <h2><a href="<%=request.getContextPath() %>/<%=StringLb.makeSlug(objS.getCategory().getName()) %>/<%=StringLb.makeSlug(objS.getName()) %>-<%=objS.getId() %>/<%=objS.getCat_id() %>.html" title="<%=objS.getName() %>"><%=objS.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=objS.getDate_create() %>. Lượt xem: <%=objS.getCounter() %> <a href="<%=request.getContextPath() %>/<%=StringLb.makeSlug(objS.getCategory().getName()) %>/<%=StringLb.makeSlug(objS.getName()) %>-<%=objS.getId() %>/<%=objS.getCat_id() %>.html" class="com"><span><%=i %></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/templates/public/images/<%=objS.getPicture() %>" width="177" height="213" alt="<%=objS.getName() %>" class="fl" /></div>
      <div class="post_content">
        <p><%=objS.getPreview_text() %></p>
        <p class="spec"><a href="<%=request.getContextPath() %>/<%=StringLb.makeSlug(objS.getCategory().getName()) %>/<%=StringLb.makeSlug(objS.getName()) %>-<%=objS.getId() %>/<%=objS.getCat_id() %>.html" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
    <%i++;}}else{
    	%>
    	<p>Không có bài hát nào.</p>
    	<%
    }} %>
    <div class="article">
      <h2>Bài viết liên quan</h2>
      <div class="clr"></div>
      <%
      	if(request.getAttribute("listSong")!=null){
      		ArrayList<Songs> listSong = (ArrayList<Songs>)request.getAttribute("listSong");
      		if(listSong.size()>0){
      			for(Songs objS: listSong){
      %>
      <div class="comment"> <a href="<%=request.getContextPath() %>/public/detail?id=<%=objS.getId() %>&cid=<%=objS.getCat_id() %>"><img src="<%=request.getContextPath() %>/templates/public/images/<%=objS.getPicture() %>" width="40" height="40" alt="" class="userpic" /></a>
        <h2><a href=""><%=objS.getName() %></a></h2>
        <p><%=objS.getPreview_text() %></p>
      </div>
      <%}}}else{
    	  %>
    	  <p>Không có bài liên quan</p>
    	  <%
      } %>
    </div>
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
