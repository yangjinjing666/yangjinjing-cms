<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link href="/resource/bootstrap-4.3.1/css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="/resource/js/jquery-3.2.1/jquery.js" ></script>
	<script type="text/javascript" src="/resource/bootstrap-4.3.1/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resource/js/jqueryvalidate/jquery.validate.js"></script>
	<script type="text/javascript" src="/resource/js/jqueryvalidate/localization/messages_zh.js"></script>
<title>Insert title here</title>

</head>
<body>
<form action="addColectionArticle" method="post">
	<div class="container">
		<div class="row justify-content-center" >
			<h3>${article.title}</h3>
		</div>
		<div style="margin-top:30px">
			${article.content}
		</div>
	</div>
	<input type="hidden" name="article_id" value="${article.id }">
	<input type="hidden" name="article_text" value="${article.title }">
	输入URL：<input type="text" name="url">
	<button>保存</button>
</form>
</body>
</html>