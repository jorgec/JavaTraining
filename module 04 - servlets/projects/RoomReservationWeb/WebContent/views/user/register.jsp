<%@ include file="../common/header.jsp" %>

<form method="post" action="user?action=register">
	<fieldset>
		<legend>Register</legend>
		<%
			if(request.getAttribute("errorMsg") != null){ %>
				<div class="alert alert-danger" role="alert">
					<%=request.getAttribute("errorMsg") %>
				</div>				
			<%	
			}
		%>
		
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" name="username" class="form-control" required>
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" name="password" class="form-control" required>
			
			<label for="password-repeat">Password (Repeat)</label>
			<input type="password" name="password-repeat" class="form-control" required>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>	
	</fieldset>
</form>

<%@ include file="../common/footer.jsp" %>