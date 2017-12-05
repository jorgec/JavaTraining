<%@ page import="java.sql.ResultSet"%>
<% ResultSet transactions = (ResultSet) request.getAttribute("transactions"); %>
<%@ include file="../common/accountHeader.jsp"%>

<h1><strong>Balance:</strong>
	<%=request.getAttribute("balance") %></h1>
<div class="row mb-3">
	<%
	if(request.getAttribute("errorMsg") != null){ %>
	<div class="alert alert-danger" role="alert">
		<%=request.getAttribute("errorMsg") %>
	</div>
	<%	
	}
	%>
	<div class="col-md-6">
		<div class="card">
			<div class="card-body">
				<h3 class="card-title">Deposit</h3>
			</div>
			<form method="post"
				action="transactions?action=deposit&accountId=<%=request.getAttribute("accountId")%>"
				style="padding: 20px;">
				<div class="form-group">
					<label>Amount</label> <input type="text" name="amount">
				</div>
				<button type="submit" class="btn btn-success">Deposit</button>
			</form>
		</div>
	</div>
	<div class="col-md-6">
		<div class="card">
			<div class="card-body">
				<h3 class="card-title">Withdraw</h3>
			</div>
			<form method="post"
				action="transactions?action=withdraw&accountId=<%=request.getAttribute("accountId")%>"
				style="padding: 20px;">
				<div class="form-group">
					<label>Amount</label> <input type="text" name="amount">
				</div>
				<button type="submit" class="btn btn-danger">Withdraw</button>
			</form>
		</div>
	</div>
</div>

<div class="mb-3">
<h2>Transactions</h2>

<table class="table">
	<thead>
		<tr>
			<th style="width: 20px;">ID</th>
			<th style="width:20%;">Date</th>
			<th>Transaction Type</th>
			<th>Amount</th>
		</tr>
	</thead>
	<tbody>
	<% while(transactions.next()) { %>
		<tr>
			<td><%=transactions.getInt("id") %></td>
			<td><%=transactions.getDate("transactionDate") %></td>
			<td><%=transactions.getString("transactionType") %></td>
			<td><%=transactions.getBigDecimal("amount") %></td>
		</tr>
	<% } %>
	</tbody>
</table>
</div>


<%@ include file="../common/footer.jsp"%>