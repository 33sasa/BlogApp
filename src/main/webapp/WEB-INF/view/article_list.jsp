<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%
@SuppressWarnings("unchecked")
List<ArticleDTO> articlelist = (List<ArticleDTO>) request.getAttribute("articlelist");
String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous" />
<title>blogApp</title>
</head>
<body>
	<div class="container">
		<%
		if (message != null) {
		%>
		<div class="alert alert-danger" role="alert">
			<%=message%>
		</div>
		<%
		}
		%>
		<h1 class="h3 mt-2 mb-3 font-weight-normal">記事一覧画面</h1>
		<p>
			<%
			if (message != null) {
			%>
			<%=message%>
			<%
			}
			%>
		</p>
		<table class="table table-striped mt-4">
			<thead>
				<tr>
					<th>タイトル</th>
					<th>最終更新日</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
				for (ArticleDTO a : articlelist) {
				%>
				<tr>
					<td><a
						href="ArticleDetailServlet?id=<%=String.valueOf(a.getId())%>"
						class="btn-outline-primary"><%=a.getTitle()%></a></td>
					<td><%=a.getDate()%></td>
					<td><a
						href="ArticleEditServlet?id=<%=String.valueOf(a.getId())%>"
						class="btn btn-primary">更新</a> <a
						href="ArticleDeleteServlet?id=<%=String.valueOf(a.getId())%>"
						class="btn btn-danger" onclick="return confirm('削除してよろしいですか？');">削除</a>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<a href="ArticleNewServlet" class="btn btn-primary">新規登録</a>
		<a href="LogoutServlet" class="h3 mt-2 mb-3 btn text-secondary">ログアウト</a>
	</div>
</body>
</html>