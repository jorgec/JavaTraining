<%@ page import="java.util.ArrayList, java.sql.ResultSet, models.Account" %>
<%
ArrayList<Account> accounts = (ArrayList<Account>) request.getAttribute("accounts");

%>
<%@ include file="../common/adminHeader.jsp" %>
<form method="post" action="/RoomReservationWeb/admin/users">
	<fieldset>
		<legend>Add User</legend>
		
		<div class="form-group">
			<label >
				First Name
			</label>
			<input type="text" name="firstName" class="form-control" required>				
		</div>
		<div class="form-group">
			<label >
				Last Name
			</label>
			<input type="text" name="lastName" class="form-control" required>				
		</div>
		<div class="form-group">
			<label >
				Username
			</label>
			<input type="text" name="username" class="form-control" required>				
		</div>
		<div class="form-group">
			<label >
				Password
			</label>
			<input type="password" name="password" class="form-control" required>				
		</div>
		<div class="form-group">
			<label>
				Role
			</label>
			<select name="role" class="form-control">
				<option value="user">User</option>
				<option value="admin">Admin</option>
			</select>
		</div>
		
		<p style="text-align: right"><button class="btn btn-primary">Create</button></p>
		
		
	</fieldset>
</form>

<table class="table">
	<thead>
		<tr>
			<th>Last Name</th>
			<th>First Name</th>
			<th>Username</th>
			<th>Role</th>
		</tr>
	</thead>
	<tbody>
	<% for( Account r: accounts ){ %>
		<tr>
			<td><%=r.getLastName() %></td>
			<td><%=r.getFirstName() %></td>
			<td><%=r.getUsername() %></td>
			<td><%=r.getRole() %></td>
		</tr>
	<% } %>
	</tbody>
</table>

<%@ include file="../common/adminFooter.jsp" %>