<!-- header -->
<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
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
	                 <h1><a href="#">VNE-Login</a></h1>
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
			            <form autocomplete="on" class="form2" action="<%=request.getContextPath()%>/Login" method="post">
			            	<img width="100px" height="100px" class="img-circle" src="<%=request.getContextPath() %>/templates/admin/assets/img/icon-180x180.png">
			                <h6>Đăng nhập</h6>
			                <%
			                	if(request.getParameter("msg")!=null){
			                		int msg = Integer.parseInt(request.getParameter("msg"));
			                		if(msg==1){
			                			%>
			                			<p class="error">Sai tên đăng nhập hoặc mật khẩu!!<a href="<%=request.getContextPath() %>/resetpassword" Style="color: green;">Quên mật khẩu?</a><p>
			                			<%
			                		}else{
			                			if(msg==2){
				                			%>
				                			<p class="error">Tài khoản không tồn tại !!. <a href="<%=request.getContextPath() %>/register" Style="color: green;">Tạo tài khoản?</a><p>
				                			<%
				                		}
			                		}
			                	}
			                %>
			                <div class="form-group">
			                	<label class="text-left pull-left" for="username">Tên đăng nhập</label>
			               		<input autocomplete="off" class="form-control" type="text" placeholder="Username" name="username" >
			                </div>
			                
			                <div class="form-group">
			                	<label class="text-left pull-left" for="password">Mật khẩu</label>
			                	<input autocomplete="off" class="form-control" type="password" placeholder="Password" name="password">
			                </div>
			                
			                <div class="action">
			                    <button type="submit" class="btn btn-primary signup btn-block" >Login</button>
			                </div>  
			             </form>             
			            </div>			           
			        </div>					
			        <div class="already">
			            <p>Don't have an account yet?</p>
			            <a href="<%=request.getContextPath() %>/register">Sign Up</a>
			        </div>
			    </div>
			</div>
		</div>
	</div>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins)<script src="https://code.jquery.com/jquery.js"></script> -->
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/custom.js"></script>
  </body>
</html>