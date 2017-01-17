<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td colspan="2">request header</td>
		</tr>
		<tr>
			<td>name</td>
			<td>value</td>
		</tr>

		<%
			Enumeration<String> names = request.getHeaderNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				String value = request.getHeader(name);
		%>
		<tr>
			<td><%=name%></td>
			<td><%=value%></td>
		</tr>
		<%
			}
		%>
	</table>
	<br />
	<table cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td colspan="2">request attr</td>
		</tr>
		<tr>
			<td>attrName</td>
			<td>attrValue</td>
		</tr>
		<tr>
			<td>RemoteAddr</td>
			<td><%=request.getRemoteAddr()%></td>
		</tr>
		<tr>
			<td>getRemoteHost</td>
			<td><%=request.getRemoteHost()%></td>
		</tr>
		<tr>
			<td>getRemotePort</td>
			<td><%=request.getRemotePort()%></td>
		</tr>
		<tr>
			<td>getServerName</td>
			<td><%=request.getServerName()%></td>
		</tr>
		<tr>
			<td>getServerPort</td>
			<td><%=request.getServerPort()%></td>
		</tr>
		<tr>
			<td>getLocalAddr</td>
			<td><%=request.getLocalAddr()%></td>
		</tr>
		<tr>
			<td>getLocalName</td>
			<td><%=request.getLocalName()%></td>
		</tr>
		<tr>
			<td>getLocalPort</td>
			<td><%=request.getLocalPort()%></td>
		</tr>
	</table>
</body>
</html>