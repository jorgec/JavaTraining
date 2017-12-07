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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Room Reservation System</title>
	
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<div class="container">
		<header class="header clearfix">
			<nav>
				<ul class="nav nav-pills float-right">
					<li class="nav-item"><a class="nav-link <% if( action.equals("home")  ){ %>active<%} %>" href="/RoomReservationWeb/">Home
					</a></li>
					<% if( request.getAttribute("isLoggedIn") == "true" ){ %>
						
						<li class="nav-item">
							<a class="nav-link <% if( action.equals("logout")  ){ %>active<%} %>" href="/RoomReservationWeb/account?action=logout">Logout</a>
						</li>
					<% }else{ %>
						<li class="nav-item">
							<a class="nav-link <% if( action.equals("login")  ){ %>active<%} %>" href="/RoomReservationWeb/account?action=login">Login</a>
						</li>
					<%} %>
				</ul>
			</nav>
			<h3 class="text-muted">Room Reservation System</h3>
		</header>

		<main role="main">
