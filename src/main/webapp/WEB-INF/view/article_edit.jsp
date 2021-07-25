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
		<h1 class="h3 mt-2 mb-3 font-weight-normal">記事編集画面</h1>
		<%
		for (ArticleDTO a : articlelist) {
		%>
		<form class="text-center form-horizontal" method="post"
			action="ArticleUpdateServlet">
			<input type="hidden" name="id" value="<%=a.getId()%>" />
			<div class="d-flex flex-row">
				<label class="col-sm-1 control-label">タイトル</label>
				<input class="col-sm-4 control-label" type="text" name="title"
					value="<%=a.getTitle()%>" required autofocus />
			</div><br />
      <div class="d-flex flex-row">
			 <label class="col-sm-1 control-label">本文</label>
			 <textarea class="col-sm-8 control-label" rows="10"><%=a.getBody()%></textarea>
			</div><br />
			<div class="d-flex flex-row">
				<label class="col-sm-1 control-label">編集中の作者</label>
				<label class="col-sm-1 control-label"><%=a.getUser_name()%></label>
			</div><br />
			<div class="d-flex flex-row">
				<label class="col-sm-1 control-label">最終更新日</label>
				<label class="col-sm-1 control-label"><%=a.getDate()%></label>
			</div><br />
      <div class="text-center">
        <input class="btn btn-outline-primary my-1" type="submit" />
      </div>
		</form>
		<%
		}
		%>
    <div>
      <button class="btn btn-danger my-1" type="button" onclick="history.back()">戻る</button>
    </div>
	</div>
</body>
</html>