<%@ page import="java.sql.ResultSet" %>
<%
	ResultSet user_list = (ResultSet) request.getAttribute("user_list");
%>
<%@ include file="../common/header.jsp" %>

<h1>List of Registered Users</h1>
<ol>
<% while(user_list.next()) { %>
	<li><%=user_list.getString("username") %></li>
<% } %>
</ol>

<%@ include file="../common/footer.jsp" %>