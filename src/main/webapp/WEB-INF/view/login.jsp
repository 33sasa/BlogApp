<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
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
		<div class="text-center">
			<%
			if (message != null) {
			%>
			<div class="alert alert-danger" role="alert">
				<%=message%>
			</div>
			<%
			}
			%>
			<h1 class="h3 mt-2 mb-3 font-weight-normal">ログイン画面</h1>
			<form class="w-25 mx-auto" action="LoginServlet" method="post">
				<div>
					<label>ユーザー名</label> <input type="text" name="name" required
						autofocus />
				</div>
				<div>
					<label>パスワード</label> <input type="password" name="password"
						required />
				</div>
				<div>
					<input class="btn btn-outline-primary my-1" type="submit"
						value="ログイン" />
				</div>
				<div>
				  <a href="NewUserServlet" class="btn btn-danger">新規登録</a>
        </div>
			</form>
		</div>
	</div>
</body>
</html>