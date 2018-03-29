<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
</head>
<script type="text/javascript">
	function manageCategoryAjax(requestURL) {
		$.ajax({
			url : requestURL,
			type : "get",
			dataType : "json",
			data : "",
			success : function(response) {
				console.log(response.data);
				getCategories();
			},
			error : function(xhr, status, e) {
				console.error(e);
			}
		});
	}

	function getCategories() {
		$('.deletable').remove();
		$
				.ajax({
					url : "${pageContext.servletContext.contextPath}/${blog.userID}/api/category/${blog.no}",
					type : "get",
					dataType : "json",
					data : "",
					success : function(response) {
						$.each(
										response.data,
										function(index, category) {
											var deleteURL = "'${pageContext.servletContext.contextPath}/${blog.userID}/api/category/delete/"
													+ category.no + "'";
											$('#category_list')
													.append(
															'<tr class="deletable">'
																	+ '<td class="t">'
																	+ (index+1)
																	+ '</td>'
																	+ '<td class="t">'
																	+ category.name
																	+ '</td>'
																	+ '<td class="t">'
																	+ category.postCount
																	+ '</td>'
																	+ '<td class="t">'
																	+ category.description
																	+ '</td>'
																	+ '<td class="t"><button onclick="manageCategoryAjax('
																	+ deleteURL
																	+ ')"><img class="d"'+
								'	src="${pageContext.request.contextPath}/assets/images/delete.jpg"></button></td>'
																	+ '</tr> ');
										});
					},
					error : function(xhr, status, e) {
						console.error(e);
					}
				});
	}

	$(document).ready(function() {
		getCategories();
		$('#add_category_btn').on('click',function(){
			manageCategoryAjax('${pageContext.servletContext.contextPath}/${blog.userID}/api/category/add/${blog.no}?n=' +
					$('#add_category_name').val() + '&d=' + $('#add_category_desc').val()
			);
		});
	});
</script>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin_menu.jsp">
					<c:param name="r" value="category"></c:param>
				</c:import>
				<table id="category_list" class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input id="add_category_name" type="text" name="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input id="add_category_desc" type="text" name="desc"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><button id="add_category_btn">카테고리 추가</button></td>
					</tr>
				</table>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>