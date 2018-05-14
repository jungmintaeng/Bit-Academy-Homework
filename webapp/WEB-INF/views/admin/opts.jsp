<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
	<title>쇼핑몰 관리자 홈페이지</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.servletContext.contextPath }/assets/css/font.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<script type="text/javascript">

    var deleteOption = function(event){
        event.preventDefault();
        var $a = $(this);
        var targetNo = $a.attr("data-no");
        $.ajax({
            url : "${cPath}/admin/option/delete/" + targetNo,
            type : "get",
            dataType : "json",
            data : "",
            success : function(response) {
                if(parseInt(response.data) > 0){
                    $a.parent().parent().remove();
                }
            },
            error : function(xhr, status, e) {
                console.log("err");
                console.error(e);
            }
        });
        return false;
    }

	var addSmallOption = function(){
	    var newName = $("#newOptionName").val();
	    if(newName == ""){
	        alert("옵션명을 입력하세요.");
	        return false;
			}

      $.ajax({
          url : "${cPath}/admin/option/small/new/${parent.no}/" + newName,
          type : "get",
          dataType : "json",
          data : "",
          success : function(response) {
              var newOption = response.data;
              if(newOption == null){
                  alert('옵션을 추가하지 못했습니다.');
                  return;
              }
              var $list = $('#option-list');
              $list.append(
                  '<tr bgcolor="#F2F2F2" height="20">' +
                  '<td width="300" align="left">' + newOption.name + '</td>' +
                  '<td width="100" align="center">' +
                  '<a href="${cPath}/admin/option/small/edit/${parent.no}/'+ newOption.no +'">수정</a>/'+
                  '<a class="deleteBtn" data-no="' + newOption.no + '" href="">삭제</a>' +
                  '</td></tr>');
              $('#newOptionName').val("");
          },
          error : function(xhr, status, e) {
              console.log("err");
              console.error(e);
              alert('옵션을 추가하지 못했습니다.');
          }
      });

	}

	$(function(){
	    $("#newBtn").click(addSmallOption);
      $(document).on('click', '.deleteBtn', deleteOption);
	});
    //option/small/new/{parentNo}/{name}
</script>
<body bgcolor="white" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="container" style="margin: 0 auto; width:900px;text-align: center;">
	<br>
	<jsp:include page="/WEB-INF/views/include/admin-menu.jsp"/>
	<hr width='900' size='3'>
	<table width="900" border="0" cellspacing="0" cellpadding="0">
		<tr height="50">
			<td align="left"  width="200" valign="center">&nbsp 옵션명 : <font color="#0457A2"><b>${parent.name}</b></font></td>
			<td align="right" width="300" valign="center">
				<input id="newOptionName" type="text" style="font-size: 15px;"> &nbsp;<input id="newBtn" type="button" value="추가">
			</td>
		</tr>
		<tr><td height="5" colspan="2"></td></tr>
	</table>

	<table id="option-list" width="900" border="1" cellspacing="0" bordercolordark="white" bordercolorlight="black">
		<tr bgcolor="#CCCCCC" height="20">
			<td width="300" align="center"><font color="#142712">소옵션명</font></td>
			<td width="100" align="center"><font color="#142712">수정/삭제</font></td>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr bgcolor="#F2F2F2" height="20">
				<td width="300" align="left">${item.name}</td>
				<td width="100" align="center">
					<a href="${cPath}/admin/option/small/edit/${parent.no}/${item.no}">수정</a>/
					<a class="deleteBtn" data-no="${item.no}" href="">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	<a href="${cPath}/admin/option/list"><strong>돌아가기</strong></a>
</div>
</body>
</html>