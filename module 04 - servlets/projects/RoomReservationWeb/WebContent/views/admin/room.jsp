<%@ page import="java.util.ArrayList, java.sql.ResultSet, models.Room, models.Building, models.RoomType" %>
<%
ArrayList<Room> rooms = (ArrayList<Room>) request.getAttribute("rooms");
ArrayList<Building> buildings = (ArrayList<Building>) request.getAttribute("buildings");
ArrayList<RoomType> roomTypes = (ArrayList<RoomType>) request.getAttribute("roomTypes");
%>
<%@ include file="../common/adminHeader.jsp" %>
<form method="post" action="/RoomReservationWeb/admin/rooms">
	<fieldset>
		<legend>Add Room</legend>
		
		<div class="form-group">
			<label for="name">
				Room Name
			</label>
				<input type="text" name="name" class="form-control" required>				
			
		</div>
		<div class="form-group">
			<label for="building">
				Building
				</label>
				<select name="building" class="form-control">
					<% for( Building b: buildings ){ %>
						<option value="<%=b.getId()%>"><%=b.getName() %></option>
					<% } %>
				</select>
			
		</div>
		
		<div class="form-group">
			<label for="roomType">
				Room Type
				</label>
				<select name="roomType" class="form-control">
					<% for(RoomType rt: roomTypes ){ %>
						<option value="<%=rt.getId()%>"><%=rt.getName() %></option>
					<% } %>
				</select>
		</div>
		
		<div class="form-group">
			<label for="seatingCapacity">
				Seating Capacity
				</label>
				<input type="number" name="seatingCapacity" class="form-control" required>
		</div>
		
		<div class="form-check">
			<label for="isAirConditioned">
				Air Conditioned?
				</label>
				<input type="checkbox" name="isAirConditioned" class="form-checkbox">
		</div>
		
		<p style="text-align: right"><button class="btn btn-primary">Create</button></p>
		
		
	</fieldset>
</form>

<table class="table">
	<thead>
		<tr>
			<th>Room</th>
			<th>Type</th>
			<th>Building</th>
			<th>Capacity</th>
			<th>Airconditioned?</th>
		</tr>
	</thead>
	<tbody>
	<% for( Room r: rooms){ %>
		<tr>
			<td><%=r.getName() %></td>
			<td><%=r.getRoomType() %></td>
			<td><%=r.getBuilding() %></td>
			<td><%=r.getSeatingCapacity() %></td>
			<td>
				<%=r.isAirConditioned() %>
			</td>
		</tr>
	<% } %>
	</tbody>
</table>

<%@ include file="../common/adminFooter.jsp" %>