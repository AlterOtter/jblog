<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" >
	var request = new XMLHttpRequest();
	function searchFunction(e) {
		//encodeURIComponent(nameValue) 한글 보낼시 깨짐방지
		request.open("GET",'${blogMainPath}'+"/"+e,true);
		request.onreadystatechange=searchProccess;
		request.send(null);
	}
	function searchProccess() {
		var p = document.getElementById("blog-main-content");
		if(request.readyState==4 && request.status ==200){
			var object = eval('('+request.responseText+')');
			//p.val(object.contents);
			$('#blog-main-content').text(object.contents.replaceAll(/\n/g, '<br>')); 
			console.log(object.contents);
		}
	}
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogvo.title}</h1>
			<c:import url="/WEB-INF/views/include/managmentMenu.jsp"/>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>Spring Camp 2016 참여기</h4>
					<% pageContext.setAttribute("newLine", "\n"); %>
					<p id="blog-main-content" >
					<c:choose>
						<c:when test="${0 ne blogInfo.post.size()}">
							${fn:replace(blogInfo.post.get(0).contents,newLine,"<br/>")}
						</c:when>
					</c:choose>
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${blogInfo.post}" var="vo"  varStatus="status" >
						<li onclick="searchFunction(${vo.no});">${vo.title}<span>${vo.reg_date}</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogvo.imgs}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${blogInfo.category}" var="vo"  varStatus="status" >
				<li><a href="">${vo.name}</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>