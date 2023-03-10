<!-- header -->
<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Info</title>
    <link rel="shortcut icon" type="image/ico" href="<%=request.getContextPath() %>/templates/admin/assets/img/icon-180x180.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/style1.css" rel="stylesheet">
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-validation-1.19.5/dist/jquery.validate.min.js"></script>
     <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.metisMenu.js"></script>
     <script src="<%=request.getContextPath() %>/templates/admin/assets/js/js2.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-5">
	              <!-- Logo -->
	              <div class="logo">
	                 <h1><a href="#">VNE-Info</a></h1>
	              </div>
	           </div>
	           <div class="col-md-5">
	              <div class="row">
	                <div class="col-lg-12"></div>
	              </div>
	           </div>
	           <div class="col-md-2">
	              <div class="navbar navbar-inverse" role="banner">
	                  <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
	                    <ul class="nav navbar-nav">
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" style="width: 140px;" data-toggle="dropdown">Xin Chào! Khách. <b class="caret"></b></a>
	                        <!-- <ul class="dropdown-menu animated fadeInUp">
	                          <li><a href="profile.jsp">Profile</a></li>
	                          <li><a href="<%=request.getContextPath() %>/admin/login.jsp">Logout</a></li>
	                        </ul> -->
	                      </li>
	                    </ul>
	                  </nav>
	              </div>
	           </div>
	        </div>
	     </div>
	</div>
<!-- /.header -->

	<div class="page-content container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-wrapper">
			        <div class="box">
			            <div class="content-wrap">
			            <form autocomplete="on" class="form2" action="<%=request.getContextPath()%>/handelinfo" method="post">
			            	<img width="100px" height="100px" class="img-circle" src="<%=request.getContextPath() %>/templates/admin/assets/img/icon-180x180.png">
			                <h6>Thông tin đăng nhập</h6>
			                <%
			                	int msg = 0;
			                	String username = request.getParameter("username");
			                	String email = request.getParameter("email");
			                	if(request.getParameter("msg")!=null){
			                		 msg = Integer.parseInt(request.getParameter("msg"));
			                	}
			                %>
			                <div class="form-group">
			                	<label class="text-left pull-left" for="username">Tên đăng nhập</label>
			               		<input id="username" value="<%if(username!=null){out.print(username);}%>" autocomplete="off" class="form-control" type="text" placeholder="Username" name="username" >
			               		<%
			               		if(msg==2){
		                			%>
		                			<p class="error">Tài khoản không tồn tại !!. <a href="<%=request.getContextPath() %>/register" Style="color: green;">Tạo tài khoản?</a><p>
		                			<%
		                		}
			               		%>
			                </div>
			                
			                <div class="form-group">
			                	<label class="text-left pull-left" for="email">Email</label>
			                	<input id="email" value="<%if(email!=null){out.print(email);} %>" autocomplete="off" class="form-control" type="text" placeholder="Email" name="email">
			                	<%
			                	if(msg==1){
		                			%>
		                				<p class="error">Email không khớp với email đã đăng ký với tài khoản này! bạn chưa có tài khoản<a href="<%=request.getContextPath() %>/register" Style="color: green;">Đăng ký</a><p>
		                			<%
		                		}
			                	%>
			                </div>
			                
			                <div class="action">
			                    <button type="submit" class="btn btn-primary signup btn-block" >Lấy lại mật khẩu</button>
			                </div>  
			             </form>             
			            </div>			           
			        </div>					
			        <div class="already">
			            <p>Don't have an account yet?</p>
			            <a href="<%=request.getContextPath() %>/register">Sign Up</a><br>
			            <p>Already have an account?</p>
			            <a href="<%=request.getContextPath() %>/Login">Sign in</a>
			            
			        </div>
			    </div>
			</div>
		</div>
	</div>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins)<script src="https://code.jquery.com/jquery.js"></script> -->
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/custom.js"></script>
    <script>
		//$(function() {
		    //$('#username').on('input', function() {
		        //var changedField = $(this);
		       // var username = $("#username").val();
		                    
		        //$.ajax({
					//url: '<%=request.getContextPath()%>/reserpassword',
					//type: 'POST',
					//cache: false,
					//data: {ausername: username},
					//success: function(data){
						//$('.ajax-data').html(data);
					//},
					//error: function (){
						//alert('Có lỗi xảy ra');
					//},
				//});
				//return false;
		    //});
		//});
		//$(function() {
		    //$('#email').on('input', function() {
		       // var changedField = $(this);
		        //var email = $("#email").val();
		                    
		        //$.ajax({
					//url: '<%=request.getContextPath()%>/resetpassword',
					//type: 'POST',
					//cache: false,
					//data: {aemail: email},
					//success: function(data){
						//$('.ajax-data1').html(data);
					//},
					//error: function (){
						//alert('Có lỗi xảy ra');
					//},
				//});
				//return false;
		    //});
		//});
	</script>
  </body>
</html>