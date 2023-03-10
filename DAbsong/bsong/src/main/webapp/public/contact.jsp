<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
      <h2><span>Liên hệ</span></h2>
      <div class="clr"></div>
      <p>Khi bạn có thắc mắc, vui lòng gửi liên hệ, chúng tôi sẽ phản hồi trong thời gian sớm nhất.</p>
    </div>
    <div class="article">
      <h2>Gửi liên hệ đến chúng tôi</h2>
      <%
      	if(request.getParameter("msg")!=null){
      		int msg = Integer.parseInt(request.getParameter("msg"));
      		if(msg==1){
      			%>
      			<p style="color: green;">Thêm thông tin thành công!</p>
      			<%
      		}else{
      			%>
      			<p style="color: green;">Thêm thông tin thất bại!</p>
      			<%
      		}
      	}
      %>
      <div class="clr"></div>
      <form class="form" action="<%=request.getContextPath() %>/public/contact" method="post" id="sendemail">
        <ol>
          <li>
            <label for="name">Họ tên (required)</label>
            <input id="name" value="" name="name" class="text" />
          </li>
          <li>
            <label for="email">Email (required)</label>
            <input id="email" value="" name="email" class="text"  />
          </li>
          <li>
            <label for="website">Website</label>
            <input id="website" value="" name="website" class="text" />
          </li>
          <li>
            <label>Nội dung</label>
            <textarea class="ckeditor " id="message" name="message" rows="8" cols="50" required="required"></textarea>
            <label for="message" class="error"></label>
          </li>
          <li>
           <input alt="Submit" type="image"  name="imageField" id="imageField" src="<%=request.getContextPath() %>/templates/public/images/submit.gif" class="send" />
            
            <div class="clr"></div>
          </li>
        </ol>
      </form>
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
