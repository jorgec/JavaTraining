<%@ page import="java.text.SimpleDateFormat, java.util.ArrayList, java.sql.ResultSet, models.Reservation, models.Room, models.Building, models.RoomType" %>
<%
ArrayList<Reservation> reservations = (ArrayList<Reservation>) request.getAttribute("reservations");
ArrayList<Room> rooms = (ArrayList<Room>) request.getAttribute("rooms");
ArrayList<Building> buildings = (ArrayList<Building>) request.getAttribute("buildings");
ArrayList<RoomType> roomTypes = (ArrayList<RoomType>) request.getAttribute("roomTypes");

SimpleDateFormat dateFormat = new SimpleDateFormat ("E, MMMM d, YYYY");
SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a"); 
%>
<%@ include file="../common/header.jsp" %>
<%
	if(request.getAttribute("errorMsg") != null){ %>
		<div class="alert alert-danger" role="alert">
			<%=request.getAttribute("errorMsg") %>
		</div>				
	<%	
	}
%>
<form method="post" action="/RoomReservationWeb/user">
	<div class="form-group">
		<label>Room</label>
		<select name="room" class="form-control">
			<% for(Room r: rooms){ %>
				<option value="<%=r.getId()%>">
					<%=r.getBuilding() %>:
					<%=r.getName() %>
					(<%=r.getRoomType() %>)
				</option>
			<% } %>
		</select>
	</div>
	<div class="form-group">
		<label>Start</label>
		<input type="text" name="start" class="form-control" placeholder="yyyy-mm-dd hh:mm:ss">
		<small>Ex: 2017-12-01 13:00:00</small>
	</div>
	<div class="form-group">
		<label>End</label>
		<input type="text" name="end" class="form-control" placeholder="yyyy-mm-dd hh:mm:ss">
		<small>Ex: 2017-12-01 15:00:00</small>
	</div>
	
	<p style="text-align: right"><button class="btn btn-primary">Create</button></p>
</form>


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
				<%=r.getStatus() %>
			</td>
		</tr>
	<% } %>
	</tbody>
</table>

<%@ include file="../common/footer.jsp" %>