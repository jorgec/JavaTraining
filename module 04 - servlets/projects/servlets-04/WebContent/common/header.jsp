<% 
	String action = null;
	if( request.getParameter("action") != null ){
		action = request.getParameter("action");
	}else{
		action = "home";
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Simple Online Banking</title>
	
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<div class="container">
		<header class="header clearfix">
			<nav>
				<ul class="nav nav-pills float-right">
					<li class="nav-item"><a class="nav-link <% if( action.equals("home")  ){ %>active<%} %>" href="/servlets-04/">Home
					</a></li>
					<% if( request.getAttribute("isLoggedIn") == "true" ){ %>
						
						<li class="nav-item">
							<a class="nav-link <% if( action.equals("logout")  ){ %>active<%} %>" href="user?action=logout">Logout</a>
						</li>
					<% }else{ %>
						<li class="nav-item">
							<a class="nav-link <% if(action.equals("register")){ %>active<%} %>" href="user?action=register">Register</a>
						</li>
						<li class="nav-item">
							<a class="nav-link <% if( action.equals("login")  ){ %>active<%} %>" href="user?action=login">Login</a>
						</li>
					<%} %>
				</ul>
			</nav>
			<h3 class="text-muted">Simple Online Banking</h3>
		</header>

		<main role="main">
