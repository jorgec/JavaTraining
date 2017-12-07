<%@ page import="java.util.ArrayList, java.sql.ResultSet, models.Room, models.Building, models.RoomType" %>
<%
ArrayList<RoomType> roomTypes = (ArrayList<RoomType>) request.getAttribute("roomTypes");
%>
<%@ include file="../common/adminHeader.jsp" %>
<form method="post" action="/RoomReservationWeb/admin/roomTypes">
	<fieldset>
		<legend>Add Room Type</legend>
		
		<div class="form-group">
			<label for="name">
				Name
			</label>
				<input type="text" name="name" class="form-control" required>				
			
		</div>
		
		
		<p style="text-align: right"><button class="btn btn-primary">Create</button></p>
		
		
	</fieldset>
</form>

<table class="table">
	<thead>
		<tr>

			<th>Room Type</th>

		</tr>
	</thead>
	<tbody>
	<% for( RoomType r: roomTypes){ %>
		<tr>
			<td><%=r.getName() %></td>

		</tr>
	<% } %>
	</tbody>
</table>

<%@ include file="../common/adminFooter.jsp" %>