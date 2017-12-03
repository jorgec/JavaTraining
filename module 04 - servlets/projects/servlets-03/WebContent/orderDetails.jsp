<%
	String firstName = (String) request.getAttribute("firstName");
	String lastName = (String) request.getAttribute("lastName");
	Double price = (Double) request.getAttribute("price");
	Double finalPrice = (Double) request.getAttribute("finalPrice");
%>
<%@ include file="common/header.jsp"%>
<div class="container">

	<h1>
		Order Details for
		<%=firstName%>
		<%=lastName%></h1>

	<div class="card">
		<div class="card-body">
			<h4 class="card-title">Amout</h4>
			<p class="card-text">
				<strong>SubTotal:</strong> <%=price %><br>
				<strong>Total:</strong> <%= finalPrice %>
			</p>
		</div>
	</div>

</div>
<%@ include file="common/footer.jsp"%>