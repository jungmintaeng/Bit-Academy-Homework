<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
  <title>쇼핑몰 관리자 홈페이지</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link href="${pageContext.servletContext.contextPath }/assets/css/font.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
  <style>
    .option-container {
      display : inline-block;
      margin : 5px 10px;
    }
  </style>
</head>
<script type="text/javascript">
    var inputChange = function (input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(input.files[0]);
            reader.onload = function (e) {
                $($('#thumb-list img')[$(input).parent().parent().attr("data-order")])
                    .attr("src", e.target.result);
            }
        }
    }

    var updateOrder = function () {
        var $list = $('#image-list tr');
        for (var i = 0; i < $list.length; i++) {
            $($list[i]).attr("data-order", i);
            $($list[i]).find('input[type="hidden"]').attr("name", "imageList[" + i + "].uploadName");
            $($list[i]).find('input[type="file"]').attr("name", "imageList[" + i + "].image");
        }
    }

    var updateOptionOrder = function(){
        var $list = $('#option-list div.option-container select');
        for (var i = 0; i < $list.length; i++) {
            $($list[i]).attr("name", "optionDtoList[" + i + "].no");
            $($list[i]).parent().attr("data-order", i);
        }
    }

    var addImage = function () {
        $('#image-list').append("<tr><td><b>상세 이미지</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            '<input class="image-file" type="file" name="">' +
            '<button type="button" class="image-delete-button">삭 제</button></td></tr>');
        $('#thumb-list').append('<img src="#" width="50" height="50" border="1" style="cursor:hand">&nbsp;')
        updateOrder();
    }

    var addOption = function(){
        $('#option-list').append('     <div class="option-container">     <select>' +
            '            <option value="0" selected>옵션선택</option>' +
            '            <c:forEach items="${product.allOptionVos}" var="item">' +
            '              <option value="${item.no}">${item.name}</option>' +
            '            </c:forEach>' +
            '          </select> &nbsp; &nbsp;' +
            '<button type="button" class="option-delete-button">삭제</button></div>');
        updateOptionOrder();
    }

    $(document).ready(function () {
        $(document).on('change', '.image-file', function () {
            $(this).parent().find("input[type='hidden']").val($(this).val());
            inputChange(this);
        });
        $(document).on('click', '#thumb-list img', function(){
            $('#big').attr("src",$(this).attr('src'));
        });
        $(document).on('click', '.image-delete-button', function(){
            var $target = $(event.target).parent().parent();
            var index = $target.attr('data-order');
            $target.remove();
            $($('#thumb-list img')[index]).remove();
            updateOrder();
        });
        $(document).on('click', '.option-delete-button', function(){
            $(event.target).parent().remove();
            updateOptionOrder();
        });
    });

    var checkVal = function(){
        var $element = $('input[type=text], textarea');
        for(var ele in $element){
            if($(ele).val() == ""){
                alert("모든 입력이 이루어지지 않았습니다.");
                return false;
            }
        }

        return true;
    };
</script>
<body bgcolor="white" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="container" style="margin: 0 auto; width:900px;text-align: center;">
  <br>
  <jsp:include page="/WEB-INF/views/include/admin-menu.jsp"/>
  <hr width='900' size='3'>
  <form action="${cPath}/admin/product/new" onsubmit="return checkVal()" method="post" name="form1" enctype="multipart/form-data">
    <table width="900" border="1" cellspacing="0" cellpadding="3" bordercolordark="white" bordercolorlight="black">
      <tr height="23">
        <td width="100" bgcolor="#CCCCCC" align="center">상품분류</td>
        <td width="700" bgcolor="#F2F2F2">
          <select name="categoryNo">
            <option value="0" selected>상품분류를 선택하세요</option>
            <c:forEach items="${product.allCategoryVos}" var="item">
              <option value="${item.no}">${item.name}</option>
            </c:forEach>
          </select>
        </td>
      </tr>
      <tr height="23">
        <td width="100" bgcolor="#CCCCCC" align="center">상품코드</td>
        <td width="700" bgcolor="#F2F2F2">
          <input type="text" autocomplete="off" name="code" value="" size="20" maxlength="20">
        </td>
      </tr>
      <tr>
        <td width="100" bgcolor="#CCCCCC" align="center">상품명</td>
        <td width="700" bgcolor="#F2F2F2">
          <input type="text" autocomplete="off" name="name" value="" size="60" maxlength="60">
        </td>
      </tr>
      <tr>
        <td width="100" bgcolor="#CCCCCC" align="center">제조사</td>
        <td width="700" bgcolor="#F2F2F2">
          <input type="text" autocomplete="off" name="manufacturer" value="" size="30" maxlength="30">
        </td>
      </tr>
      <tr>
        <td width="100" bgcolor="#CCCCCC" align="center">판매가</td>
        <td width="700" bgcolor="#F2F2F2">
          <input type="text" autocomplete="off" name="price" value="" size="12" maxlength="12"> 원
        </td>
      </tr>
      <tr>
        <td width="100" bgcolor="#CCCCCC" align="center">옵션</td>
        <td id="option-list" width="700" bgcolor="#F2F2F2">
          <button onclick="javascript:event.preventDefault();addOption();return false;">옵션 추가</button>
        </td>
      </tr>
      <tr>
        <td width="100" bgcolor="#CCCCCC" align="center">제품설명</td>
        <td width="700" bgcolor="#F2F2F2">
          <textarea name="description" rows="10" cols="80"></textarea>
        </td>
      </tr>
      <tr>
        <td width="100" bgcolor="#CCCCCC" align="center">상품상태</td>
        <td width="700" bgcolor="#F2F2F2">
          <input type="radio" name="state" value="판매중" checked="true"> 판매중
          <input type="radio" name="state" value="판매중지"> 판매중지
          <input type="radio" name="state" value="품절"> 품절
        </td>
      </tr>
      <tr>
        <td width="100" bgcolor="#CCCCCC" align="center">아이콘</td>
        <td width="700" bgcolor="#F2F2F2">
          <input id="new-checkbox" name="new_" type="checkbox" value="true"> New &nbsp;&nbsp
          <%--<input type="hidden" value="false">--%>
          <input id="hit-checkbox" name="hit_" type="checkbox" value="true"> Hit &nbsp;&nbsp
          <%--<input type="hidden"  value="false">--%>
          <input type="checkbox" value="1" onclick="form1.discountRate.disabled=!form1.discountRate.disabled;"> Sale
          &nbsp;&nbsp
          할인율 : <input type="number" autocomplete="off" name="discountRate" min="0" max="100" value="0" size="3"
                       maxlength="3" disabled> %
        </td>
      </tr>
      <tr>
        <td width="100" bgcolor="#CCCCCC" align="center">이미지</td>
        <td width="700" bgcolor="#F2F2F2">
          <table border="0" cellspacing="0" cellpadding="0" align="left">
            <tr>
              <td>
                <table width="390" border="0" cellspacing="0" cellpadding="0" id="image-list">
                  <tr data-order="0">
                    <td>
                      &nbsp;<b>썸네일 이미지</b>
                      <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <input class="image-file" type="file" name="imageList[0].image">
                    </td>
                  </tr>
                </table>
                <br><br><br><br><br>
                <table width="390" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td id="thumb-list" valign="middle">&nbsp;
                      <img src="#" width="50" height="50" border="1" style="cursor:hand">&nbsp;
                    </td>
                  </tr>
                </table>
              </td>
              <td align="right" width="510">
              <img id="big" width="500" src="#" border="1"></td>
            </tr>
          </table>
          <button onclick="javascript:event.preventDefault();addImage();return false;">상세 이미지 추가</button>
      </tr>
    </table>
    <br>
    <table width="900" border="0" cellspacing="0" cellpadding="7">
      <tr>
        <td align="center">
          <input type="submit" value="등록하기"> &nbsp;&nbsp
          <a href="${cPath}/admin/product/list"><input type="button" value="이전화면"></a>
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>
