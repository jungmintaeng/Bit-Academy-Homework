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
var lastNo = 0;
var isEnd = false;
var messageBox = function(title, message, closeFunc){
	$("#dialog-message").attr("title", title);
	$("#dialog-message p").text(message);
	$("#dialog-message").dialog({
		modal:true,
		buttons :{
			OK: function(){
				$(this).dialog("close");
			}
		},
		close: closeFunc
	});
}
var deleteDialog = $()
var getList = function(){
	var $list = $('#list-guestbook');
	if(lastNo == 0){
		$('.deletable').remove();
	}
	$.ajax({
		url : "${pageContext.servletContext.contextPath}/api/guestbook/list/" + lastNo,
		type : "get",
		dataType : "json",
		data : "",
		success : function(response) {
			//끝 감지
			if(response.data.length < 5){
				isEnd = true;
				$("#btn_fetch").prop("disabled", true).removeClass("btn-hover");
			}
			$.each(response.data, function(index, guest_book_vo){
				$list.append(render(guest_book_vo));
			});
			lastNo = $("#list-guestbook li:last-child > a").attr("data-no");
		},
		error : function(xhr, status, e) {
			console.error(e);
		}
	});
}
var add = function(event){
	event.preventDefault();
	if($('#input-name').val() == ""){
		messageBox("등록 실패", "사용자 이름을 입력하세요.", function(){	$("#input-name").focus();	});
		return;
	}
	if($('#input-password').val() == ""){
		messageBox("등록 실패", "비밀번호를 입력하세요.", function(){	$("#input-password").focus();	});
		return;
	}
	if($('#tx-content').val() == ""){
		messageBox("등록 실패", "내용을 입력하세요.", function(){	$("#tx-content").focus();	});
		return;
	}
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
			//TODO:prepend
			if(response.result !="success"){
				messageBox("등록 실패", '글쓰기에 실패하였습니다.');
				return;
			}
			$("#list-guestbook").prepend(render(response.data));
			$("#add-form")[0].reset();
		},
		error : function(xhr, status, e) {
			messageBox("등록 실패", '글쓰기에 실패하였습니다.');
		}
	});
}
var doDelete = function(event){
	event.preventDefault();
	var data = {};
	data.password=$('#password-delete').val();
	data.no=$('#dialog-delete-form').data('no');
	$.ajax({
		url : "${pageContext.servletContext.contextPath}/api/guestbook/delete",
		type : "post",
		dataType : "json",
		data : data,
		success : function(response) {
			if(response.data < 0){
				$('p.validateTips.error').show();
				$("#password-delete").val("").focus();
			}else{
				$('#list-guestbook li a[data-no="' + response.data + '"]').parent().remove();
				$('p.validateTips.error').hide();
				$('#dialog-delete-form').dialog('close');
			}
		},
		error : function(xhr, status, e) {
			messageBox("삭제 실패", "삭제에 실패하였습니다.");
		}
	});
}
var render = function(vo){
	return (
	"<li class='deletable'>"+
	"<img class='left' src='${pageContext.request.contextPath }/assets/images/user.png' alt='프로필 사진'>" +
	"<strong>" + vo.name + "</strong>" +
	"<p>" +
	vo.content +
	"</p>" +
	"<strong>" + vo.reg_date + "</strong>" +
	"<a data-no='" + vo.no + "'><img src='${pageContext.request.contextPath }/assets/images/delete.png' alt='삭제'>삭제</a>" +
	"</li>");
}
$(document).ready(function(){
	$('#dialog-delete-form').hide();
	$(document).on('click', 'ul#list-guestbook li a', function(){
		$('p.validateTips.error').hide();
		$('#password-delete').val("");
		$('#dialog-delete-form').data('no',$(this).attr("data-no")).dialog({modal:true});
	});
	$("#btn_fetch").click(getList);
	$("#add-form").submit(add);
	$("#delete-form").submit(doDelete);
	getList();
});
</script>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<div id="guestbook-ajax-title">
					<img src="${pageContext.request.contextPath }/assets/images/guestbook.png"><h1>방명록</h1>
				</div>
				<form id="add-form">
					<input type="text" id="input-name" placeholder="이름" name="name">
					<input type="password" id="input-password" placeholder="비밀번호" name="password">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input class="btn-hover" type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">
				<!-- Some GuestBooks will be added dynamically -->
				</ul>
				<button class="btn-hover" id="btn_fetch">더보기</button>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form id="delete-form">
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all"><br/>
					<input class="btn-hover" type="submit" value="삭제">
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