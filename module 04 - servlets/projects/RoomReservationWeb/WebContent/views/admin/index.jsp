<%@ page import="java.text.SimpleDateFormat, java.util.ArrayList, java.sql.ResultSet, models.Reservation" %>
<%
ArrayList<Reservation> reservations = (ArrayList<Reservation>) request.getAttribute("reservations");
SimpleDateFormat dateFormat = new SimpleDateFormat ("E, MMMM d, YYYY");
SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a"); 
%>
<%@ include file="../common/adminHeader.jsp" %>
<table class="table">
	<thead>
		<tr>
			<th>Room</th>
			<th>User</th>
			<th>Start</th>
			<th>End</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>
	<% for( Reservation r: reservations ){ %>
		<tr>
			<td><%=r.getRoom() %></td>
			<td><%=r.getUser() %></td>
			<td><%=dateFormat.format( r.getStartDate() ) %> at <%=timeFormat.format( r.getStartTime() ) %></td>
			<td><%=dateFormat.format( r.getEndDate() ) %> at <%=timeFormat.format( r.getEndTime() ) %></td>
			<td>
				<% if( r.getStatus().equals("Pending")) { %>
					<a href="/RoomReservationWeb/admin/reservation?action=approve&id=<%=r.getId() %>">Approve</a>
				<% }else{ %>
					<a href="/RoomReservationWeb/admin/reservation?action=deny&id=<%=r.getId() %>">Deny</a>
				<% } %>
			</td>
		</tr>
	<% } %>
	</tbody>
</table>

<%@ include file="../common/adminFooter.jsp" %>