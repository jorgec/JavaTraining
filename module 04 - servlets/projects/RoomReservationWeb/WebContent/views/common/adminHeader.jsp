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
	
	<link rel="stylesheet" href="/RoomReservationWeb/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="/RoomReservationWeb/assets/css/style.css">
</head>
<body>
	<div class="container">
		<header class="header clearfix">
			<nav>
				<ul class="nav nav-pills float-right">
					<li class="nav-item"><a class="nav-link <% if( action.equals("home")  ){ %>active<%} %>" href="/RoomReservationWeb/admin">Home
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
		
		<div class="row">
			<section role="sidebar" class="col-md-3">
				<%@ include file="adminSidebar.jsp" %>
			</section>
	
			<main role="main" class="col-md-9">
