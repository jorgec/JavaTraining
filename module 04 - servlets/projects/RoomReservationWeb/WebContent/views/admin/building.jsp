<%@ page import="java.util.ArrayList, java.sql.ResultSet, models.Room, models.Building, models.RoomType" %>
<%
ArrayList<Building> buildings = (ArrayList<Building>) request.getAttribute("buildings");
%>
<%@ include file="../common/adminHeader.jsp" %>
<form method="post" action="/RoomReservationWeb/admin/buildings">
	<fieldset>
		<legend>Add Building</legend>
		
		<div class="form-group">
			<label for="name">
				Building Name
			</label>
				<input type="text" name="name" class="form-control" required>				
			
		</div>
		
		
		<p style="text-align: right"><button class="btn btn-primary">Create</button></p>
		
		
	</fieldset>
</form>

<table class="table">
	<thead>
		<tr>

			<th>Building</th>

		</tr>
	</thead>
	<tbody>
	<% for( Building r: buildings){ %>
		<tr>
			<td><%=r.getName() %></td>

		</tr>
	<% } %>
	</tbody>
</table>

<%@ include file="../common/adminFooter.jsp" %>