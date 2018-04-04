<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<script type="text/javascript">
page = 0;
$(document).ready(function(){
	$('#dialog-delete-form').hide();
	$(document).on('click', 'ul#list-guestbook li a', function(){
		$('p.validateTips.error').hide();
		$('#password-delete').val("");
		$('#dialog-delete-form').data('no',$(this).attr("data-no")).dialog();
	});
	getList();
});

getList = function(){
	if(page == 0){
		$('.deletable').remove();
	}
	$.ajax({
		url : "${pageContext.servletContext.contextPath}/api/guestbook/list/" + (page + 1),
		type : "get",
		dataType : "json",
		data : "",
		success : function(response) {
			$.each(response.data, function(index, guest_book_vo){
				$('#list-guestbook').append("<li class='deletable'>"+
						"<img class='left' src='${pageContext.request.contextPath }/assets/images/user.png' alt='프로필 사진'>" +
						"<strong>" + guest_book_vo.name + "</strong>" +
						"<p>" +
						guest_book_vo.content +
						"</p>" +
						"<strong>" + guest_book_vo.reg_date + "</strong>" +
						"<a data-no='" + guest_book_vo.no + "'><img src='${pageContext.request.contextPath }/assets/images/delete.png' alt='삭제'>삭제</a>" +
					"</li>");
			});
			if(response.data.length > 1){
				page = page + 1;
			}
		},
		error : function(xhr, status, e) {
			console.error(e);
		}
	});
}

add = function(){
	var data = {};
	data.content=$('#tx-content').val();
	data.name=$('#input-name').val();
	data.password=$('#input-password').val();
	$.ajax({
		url : "${pageContext.servletContext.contextPath}/api/guestbook/add",
		type : "post",
		dataType : "json",
		data : data,
		success : function(response) {
			if(response.result !="success" || response.data==false){
				alert('글쓰기에 실패하였습니다.');
			}else{
				$('#tx-content').val("");
				$('#input-name').val("");
				$('#input-password').val("");
			}
			page=0;
			getList();
		},
		error : function(xhr, status, e) {
			if(response.result !="success" || response.data==false){
				alert('글쓰기에 실패하였습니다.');
			}else{
				$("#dialog-delete-form").dialog('close');
			}
			page=0;
			getList();
		}
	});
}

doDelete = function(){
	var data = {};
	data.password=$('#password-delete').val();
	data.no=$('#dialog-delete-form').data('no');
	$.ajax({
		url : "${pageContext.servletContext.contextPath}/api/guestbook/delete",
		type : "post",
		dataType : "json",
		data : data,
		success : function(response) {
			if(response.result !="success" || response.data==false){
				$('p.validateTips.error').show();
			}else{
				$('#list-guestbook li a[data-no="' + data.no + '"]').parent().remove();
				$('p.validateTips.error').hide();
				$('#dialog-delete-form').dialog('close');
			}
			$('#password-delete').val("");
		},
		error : function(xhr, status, e) {
			$('p.validateTips.error').show();
			$('#password-delete').val("");
		}
	});
}

</script>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<div id="guestbook-ajax-title">
					<img src="${pageContext.request.contextPath }/assets/images/guestbook.png"><h1>방명록</h1>
				</div>
				<form id="add-form" method="post" onsubmit="add();return false;">
					<input type="text" id="input-name" placeholder="이름" name="name">
					<input type="password" id="input-password" placeholder="비밀번호" name="password">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">
				<!-- Some GuestBooks will be added dynamically -->
				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form onsubmit="doDelete();return false;">
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value=""><br/>
					<input type="submit" value="삭제">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="r" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>