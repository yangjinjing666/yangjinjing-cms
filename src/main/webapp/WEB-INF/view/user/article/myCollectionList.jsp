<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		<!-- articlePage -->
			<div style="margin-top:20px">
				<c:forEach items="${article}" var="article">
					<div class="row" style="margin-top:5px">
						<div class="col-md-9">
							<a href="/article/detail?id=${article.id}" target="_blank">${article.article_text}</a>
							<br>
							时间：${article.created}
							&nbsp;&nbsp;&nbsp;&nbsp;
        					<input type="button" value="删除"  class="btn btn-danger" onclick="del(${article.id})">
						</div>
					</div>
				</c:forEach>
			</div>

      
	
<script>
	function del(id){
		alert(id)
		if(!confirm("您确认删除么？"))
			return;
		
		$.post('/user/deleteColectionArticle',{id:id},
				function(data){
					if(data==true){
						alert("刪除成功")
						//location.href="#"
						$("#workcontent").load("/user/myCollection");
					}else{
						alert("刪除失敗")
					}
					
		},"json"				
		)
	}
	
</script>
