<%@ include file="../common/header.jsp" %>

<form method="post" action="user?action=login">
	<fieldset>
		<legend>Login</legend>
		
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" name="username" class="form-control" required>
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" name="password" class="form-control" required>
			
			
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>	
	</fieldset>
</form>

<%@ include file="../common/footer.jsp" %>