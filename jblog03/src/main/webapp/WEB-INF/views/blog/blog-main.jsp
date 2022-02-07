<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript">
	var request = new XMLHttpRequest();
	function searchFunction(e,g) {
		//encodeURIComponent(nameValue) 한글 보낼시 깨짐방지
		console.log(e);
		console.log(g);
		request.open("GET",'${blogMainPath}'+'/'+e+'/'+g,true);
		request.onreadystatechange=searchProccess;
		request.send(null);
	}
	function searchProccess() {
		var p = document.getElementById("blog-main-content");
		if(request.readyState==4 && request.status ==200){
			var post = eval('('+request.responseText+')');
			//p.val(object.contents);
			$('#blog-main-title').text(post.title);
			$('#blog-main-content').html(post.contents); 
		}
	}
	
	function getcategorylist(e) {
		$.ajax({ 
			url: "${blogMainPath}"+"/"+e,
			type: "GET", cache: false,
			dataType: "json", 
			data:"", 
			success: function(data){ 
				var ul_list = $("#blog-list");
				$("#blog-list").empty();
				console.log(typeof(data));
				for(var i=0;i<data.length;i++){
					$("#blog-list").append('<li onclick="searchFunction('+data[i].category_no.toString() +"," +data[i].no.toString()+');">'+data[i].title+'<span>'+data[i].reg_date+'</span></li>');
					
				}
				
			 }, 
			 error: function (request, status, error){ 
				 
			 
			 } 
			});
	}
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogvo.title}</h1>
			<c:import url="/WEB-INF/views/include/managmentMenu.jsp" />
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test="${0 ne blogInfo.post.size()}">
							<h4 id="blog-main-title">${blogInfo.post.get(0).title}</h4>
							<%
							pageContext.setAttribute("newLine", "\n");
							%>
							<p id="blog-main-content">
								${fn:replace(blogInfo.post.get(0).contents,newLine,"<br/>")}</p>
						</c:when>
					</c:choose>
				</div>
				<ul class="blog-list" id="blog-list">
					<c:forEach items="${blogInfo.post}" var="vo" varStatus="status">
						<li onclick="searchFunction(${vo.category_no},${vo.no});">${vo.title}<span>${vo.reg_date}</span></li>
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
			<ul >
				<c:forEach items="${blogInfo.category}" var="vo" varStatus="status">
					<li onclick="getcategorylist(${vo.no})">${vo.name}</li>
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