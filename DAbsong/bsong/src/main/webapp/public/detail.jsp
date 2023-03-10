<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
   
      
      <%
      	if(request.getAttribute("objS")!=null){
      		Songs objS = (Songs)request.getAttribute("objS");
      %>
      <h1><%=objS.getName() %></h1>
      <div class="clr"></div>
      <p>Ngày đăng: <%=objS.getDate_create() %>. Lượt xem: <%=objS.getCounter() %></p>
      <div class="vnecontent">
          <%=objS.getDetail_text() %>
      </div>
    </div>
    <%} %>
    <div class="article">
      <h2>Bài viết liên quan</h2>
      <div class="clr"></div>
      <%
      	if(request.getAttribute("listSong")!=null){
      		ArrayList<Songs> listSong = (ArrayList<Songs>)request.getAttribute("listSong");
      		if(listSong.size()>0){
      			for(Songs objS: listSong){
      %>
      <div class="comment"> <a href=""><img src="<%=request.getContextPath() %>/templates/admin/assets/img/<%=objS.getPicture() %>" width="40" height="40" alt="" class="userpic" /></a>
        <h2><a href="<%=request.getContextPath() %>/<%=StringLb.makeSlug(objS.getCategory().getName()) %>/<%=StringLb.makeSlug(objS.getName()) %>-<%=objS.getId() %>/<%=objS.getCat_id() %>.html"><%=objS.getName() %></a></h2>
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
