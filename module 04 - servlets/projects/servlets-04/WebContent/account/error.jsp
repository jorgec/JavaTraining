<%@ include file="../common/header.jsp"%>

<%
	if(request.getAttribute("errorMsg") != null){ %>
		<div class="alert alert-danger" role="alert">
			<%=request.getAttribute("errorMsg") %>
		</div>
	<%	
	}
%>

<%@ include file="../common/footer.jsp"%>