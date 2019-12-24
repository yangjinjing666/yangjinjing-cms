<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/resource/bootstrap-4.3.1/css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="/resource/js/jquery-3.2.1/jquery.js" ></script>
	<script type="text/javascript" src="/resource/bootstrap-4.3.1/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resource/js/jqueryvalidate/jquery.validate.js"></script>
	<script type="text/javascript" src="/resource/js/jqueryvalidate/localization/messages_zh.js"></script>
<title>Insert title here</title>

</head>
<body>
	<c:forEach items="${complianPage.list }" var="complain">
		<div class="col-md-3">${complain.user.username }</div>
		<div class="col-md-3">${complain.content }</div>
	</c:forEach>
</body>
</html>