<%@ page import="java.sql.ResultSet" %>
<% ResultSet userAccounts = (ResultSet) request.getAttribute("userAccounts"); %>
<%@ include file="../common/accountHeader.jsp" %>

<h1>Create Account</h1>
<form method="post" action="transactions?action=create_account">

	<div class="form-check">
		<label class="control-label" for="accountType">Account Type</label>
		<label class="form-check-label">
			<input type="radio" name="accountType" value="Savings">
			Savings
		</label>
		<label class="form-check-label">
			<input type="radio" name="accountType" value="Checking">
			Checking
		</label>
	</div>
	
	<button type="submit" class="btn btn-primary">Create</button>

</form>

<hr>

<h2>My Accounts</h2>
<ol>
<% while(userAccounts.next()) { %>
	<li>
		<a href='transactions?accountId=<%=userAccounts.getInt("id")%>&action=view_account'>
			<%=userAccounts.getString("accountType") %>
		</a>
	</li>
<% } %>
</ol>

<%@ include file="../common/footer.jsp" %>