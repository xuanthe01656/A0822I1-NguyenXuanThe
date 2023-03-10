<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/authregister/assets/styles.css" />
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/authregister/js/jquery/jquery-3.6.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/authregister/js/jquery-validation-1.19.5/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/authregister/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/authregister/jsBT2.js"></script>
</head>
<body>
	<form class="form" action="<%=request.getContextPath()%>/register" method="post">
		<h2>ĐĂNG KÝ THÀNH VIÊN</h2>
		<p>Bạn đã có tài khoản? <a href="<%=request.getContextPath()%>/Login">Đăng Nhập</a></p>
		 <%
        	if(request.getParameter("msg")!=null){
        		int msg = Integer.parseInt(request.getParameter("msg"));
        		if(msg==1){
        			%>
        			<div><span style="background-color: yellow; color: red;">Đăng ký thành công!</span></div>
        			<script>
        				 window.location="<%=request.getContextPath()%>/Login";
        			</script>
        			<%
        		}else{
        			if(msg==2){
        			%>
        			<div><span style="background-color: yellow; color: red;">Đăng ký thất bại thất bại!</span></div>
        			<%
        				}else{
                			if(msg==3){
                    			%>
                    			<div><span style="background-color: yellow; color: red;">Tên đăng nhập đã tồn tại!</span></div>
                    			<%
                    				}
                    			}
        			}
        		}
        %>
        
		<div>
			<label>Tên truy cập (*):</label>
			<input name="username" id="username" value="" type="text" placeholder="Choose your username" />
			<div class="ajax-data"></div>
		</div>
		
		<p>
			<label>Mật khẩu (*):</label>
			<input id="password" name="password" value="" type="password" placeholder="Creat a new password" />
		</p>
		<p>
			<label>Xác nhận mật khẩu (*):</label>
			<input name="repassword" value="" type="password" placeholder="Confirm your password" />
		</p>
		<p>
			<label>Họ và tên (*):</label>
			<input name="fullname" value="" type="text" placeholder="Write your name here.." />
		</p>
		<p>
			<label>Sinh nhật (*):</label>
			<input name="birthday" value="" type="text" placeholder="Tell us your birth day" />
		</p>
		<div>
			<label>Giới tính (*):</label>
			<p>
				<span>Nam</span><input type="radio" value="nam" name="gioitinh" required title="Hãy chọn giới tính." />
				<span>Nữ</span><input type="radio" value="nu" name="gioitinh" />
			</p>
			<br />
			<label for="gioitinh" class="error"></label>
		</div>
		<p>
			<label>Địa chỉ (*):</label>
			<input name="address" value="" type="text" placeholder="Tell us your address" />
		</p>
		<p>
			<label>Số điện thoại:</label>
			<input name="phone" value="" type="text" placeholder="Enter your phone number.." />
		</p>
		<div>
			<label>Email (*):</label>
			<input id="email" name="email" value="" type="text" placeholder="Let us know how to contact you back.." />
			<div class="ajax-data1"></div>
		</div>
		<p>
			<label>Facebook:</label>
			<input name="facebook" value="" type="text" placeholder="Enter your facebook profile" />
		</p>
		<p>
			<label>Thông tin thêm:(*)</label>
			<textarea class="ckeditor" name="moreinfo" placeholder="What would you like to tell us.."></textarea>
			<label for="moreinfo" class="error"></label>
		</p>
		<p>
			<input type="submit" name="submit" value="ĐĂNG KÝ" />
			<input type="reset" name="reset" value="RESET" />
		</p>
		<div class="contact">
			<span class="fa fa-phone"></span>0000.000.000
			<span class="fa fa-envelope-o"></span>***@***.***.**
		</div>
	</form>
	<script>
		$(function() {
		    $('#username').on('input', function() {
		        var changedField = $(this);
		        var username = $("#username").val();
		                    
		        $.ajax({
					url: '<%=request.getContextPath()%>/UserHandel',
					type: 'POST',
					cache: false,
					data: {ausername: username},
					success: function(data){
						$('.ajax-data').html(data);
					},
					error: function (){
						alert('Có lỗi xảy ra');
					},
				});
				return false;
		    });
		});
		$(function() {
		    $('#email').on('input', function() {
		        var changedField = $(this);
		        var email = $("#email").val();
		                    
		        $.ajax({
					url: '<%=request.getContextPath()%>/UserHandel',
					type: 'POST',
					cache: false,
					data: {aemail: email},
					success: function(data){
						$('.ajax-data1').html(data);
					},
					error: function (){
						alert('Có lỗi xảy ra');
					},
				});
				return false;
		    });
		});
	</script>
</body>
</html>