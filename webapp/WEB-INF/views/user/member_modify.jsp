<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
  <title>비트닷컴 쇼핑몰</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link href="${cPath}/assets/css/common.css" rel="stylesheet" type="text/css">
  <style>
    @import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
  </style>
</head>
<script type="text/javascript">
    var setCurrentValue = function (numberString, id1, id2, id3) {
        numberString = numberString.split("-");
        document.getElementById(id1).value = numberString[0];
        document.getElementById(id2).value = numberString[1];
        document.getElementById(id3).value = numberString[2];
    }
    window.onload = function () {
        var phoneNumber = "${user.phoneNumber}";
        var telNumber = "${user.telNumber}";
        var birthdate = "${user.birthdate}";

        setCurrentValue(phoneNumber, "phone1", "phone2", "phone3");
        setCurrentValue(telNumber, "tel1", "tel2", "tel3");
        setCurrentValue(birthdate, "birthday1", "birthday2", "birthday3");

        var p1 = document.getElementById("phone1");
        var p2 = document.getElementById("phone2");
        var p3 = document.getElementById("phone3");
        var phoneNumber = document.getElementById("phone_number");

        p1.onchange = p2.onchange = p3.onchange = function () {
            phoneNumber.value = p1.value.toString() + "-" + p2.value.toString() + "-" + p3.value.toString();
        }

        var b1 = document.getElementById("birthday1");
        var b2 = document.getElementById("birthday2");
        var b3 = document.getElementById("birthday3");
        var birthdate = document.getElementById("birthdate");

        b1.onchange = b2.onchange = b3.onchange = function () {
            birthdate.value = b1.value.toString() + "-" + b2.value.toString() + "-" + b3.value.toString();
        }

        var t1 = document.getElementById("tel1");
        var t2 = document.getElementById("tel2");
        var t3 = document.getElementById("tel3");
        var telNumber = document.getElementById("tel_number");

        t1.onchange = t2.onchange = t3.onchange = function () {
            telNumber.value = t1.value.toString() + "-" + t2.value.toString() + "-" + t3.value.toString();
        }

        phoneNumber.value = p1.value.toString() + "-" + p2.value.toString() + "-" + p3.value.toString();
        birthdate.value = b1.value.toString() + "-" + b2.value.toString() + "-" + b3.value.toString();
        telNumber.value = t1.value.toString() + "-" + t2.value.toString() + "-" + t3.value.toString();
    }
</script>
<body style="margin:0">
<div class="container">
  <c:if test="${authUser.role != 'admin'}">
    <jsp:include page="/WEB-INF/views/include/head.jsp"/>
    <jsp:include page="/WEB-INF/views/include/navigation.jsp"/>
  </c:if>
  <c:if test="${authUser.role == 'admin'}">
    <c:import url="/WEB-INF/views/include/admin-menu.jsp"/>
  </c:if>

  <table width="1200" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td height="10" colspan="2"></td>
    </tr>
    <tr>
      <td height="100%" valign="top">
      </td>
      <td width="10"></td>
      <td valign="top">

        <!-------------------------------------------------------------------------------------------->
        <!-- 시작 : 다른 웹페이지 삽입할 부분                                                                                                                                                            -->
        <!-------------------------------------------------------------------------------------------->
        <table border="0" cellpadding="0" cellspacing="0" width="1200">
          <tr>
            <td height="13"></td>
          </tr>
          <tr>
            <td height="30" align="center">
              <img src="${cPath}/assets/images/login_title.gif" width="1200" height="30" border="0">
            </td>
          </tr>
          <tr>
            <td height="13"></td>
          </tr>
        </table>
        <table border="0" cellpadding="0" cellspacing="0" width="1200" class="cmfont">
          <tr>
            <td><img src="${cPath}/assets/images/member_edit.gif" border="0"></td>
          </tr>
          <tr>
            <td height="10"></td>
          </tr>
        </table>

        <c:if test="${empty who || who != 'admin'}">
        <form name="form2" method="post" action="${cPath}/user/modify">
          </c:if>
          <c:if test="${not empty who || who == 'admin'}">
          <form name="form2" method="post" action="${cPath}/admin/member/edit">
            </c:if>
            <input type="hidden" name="no" value="${user.no}">
            <table border="0" cellpadding="5" cellspacing="1" width="1200" bgcolor="cccccc">
              <tr>
                <td align="center" bgcolor="efefef">
                  <table border="0" cellpadding="0" cellspacing="5" width="100%" bgcolor="white">
                    <tr>
                      <td align="center">
                        <table border="0" cellpadding="0" cellspacing="0" width="1200" class="cmfont">
                          <tr>
                            <td width="127" height="30">
                              <img align="absmiddle" src="${cPath}/assets/images/i_dot.gif" border="0"> <font
                                    color="898989"><b>비밀번호</b></font>
                            </td>
                            <td>
                              <input TYPE="password" name="password" maxlength="10" size="20" class="cmfont1">
                            </td>
                          </tr>
                          <tr>
                            <td width="127" height="30">
                              <img align="absmiddle" src="${cPath}/assets/images/i_dot.gif" border="0"> <font
                                    color="898989"><b>비밀번호 확인</b></font>
                            </td>
                            <td>
                              <input TYPE="password" name="password2" maxlength="10" size="20" class="cmfont1">
                            </td>
                          </tr>
                          <tr>
                            <td colspan="2" height="10"></td>
                          </tr>
                          <tr>
                            <td colspan="2" bgcolor="E6DDD5"></td>
                          </tr>
                          <tr>
                            <td colspan="2" height="10"></td>
                          </tr>
                        </table>
                        <table border="0" cellpadding="0" cellspacing="0" width="1200" class="cmfont">
                          <tr>
                            <td width="127" height="30">
                              <img align="absmiddle" src="${cPath}/assets/images/i_dot.gif" border="0"> <font
                                    color="898989"><b>이 름</b></font>
                            </td>
                            <td>
                              <input type="text" autocomplete="off" name="name" maxlength="10" value="${user.name}"
                                     size="20" class="cmfont1">
                            </td>
                          </tr>
                          <tr>
                            <td width="127" height="30">
                              <img align="absmiddle" src="${cPath}/assets/images/i_dot.gif" border="0"> <font
                                    color="898989"><b>생년월일</b></font>
                            </td>
                            <td>
                              <input type="text" autocomplete="off" id='birthday1' size="4" maxlength="4" value=""
                                     class="cmfont1"> <font color="898989">년</font>
                              <input type="text" autocomplete="off" id='birthday2' size="2" maxlength="2" value=""
                                     class="cmfont1"> <font color="898989">월</font>
                              <input type="text" autocomplete="off" id='birthday3' size="2" maxlength="2" value=""
                                     class="cmfont1"> <font color="898989">일</font>
                              <input id="birthdate" type="hidden" name="birthdate">
                              <!-- <input type="radio" name='sm' value = "1" checked> <font color="898989">양력</font>
                                                        <input type="radio" name='sm' value = "2" > <font color="898989">음력</font></td> -->
                          </tr>
                          <tr>
                            <td colspan="2" height="10"></td>
                          </tr>
                          <tr>
                            <td colspan="2" bgcolor="E6DDD5"></td>
                          </tr>
                          <tr>
                            <td colspan="2" height="10"></td>
                          </tr>
                        </table>
                        <table border="0" cellpadding="0" cellspacing="0" width="1200" class="cmfont">
                          <tr>
                            <td width="127" height="30">
                              <img align="absmiddle" src="${cPath}/assets/images/i_dot.gif" border="0"> <font
                                    color="898989"><b>전화 번호</b></font>
                            </td>
                            <td>
                              <input type="text" autocomplete="off" id='tel1' size="4" maxlength="4" value=""
                                     class="cmfont1"><font color="898989">-</font>
                              <input type="text" autocomplete="off" id='tel2' size="4" maxlength="4" value=""
                                     class="cmfont1"><font color="898989">-</font>
                              <input type="text" autocomplete="off" id='tel3' size="4" maxlength="4" value=""
                                     class="cmfont1">
                              <input id="tel_number" type="hidden" name="telNumber">
                            </td>
                          </tr>
                          <tr>
                            <td width="127" height="30">
                              <img align="absmiddle" src="${cPath}/assets/images/i_dot.gif" border="0"> <font
                                    color="898989"><b>핸드폰 번호</b></font>
                            </td>
                            <td>
                              <input id="phone1" type="text" size="4" maxlength="4" value="" class="cmfont1"><font
                                    color="898989">-</font>
                              <input id="phone2" type="text" size="4" maxlength="4" value="" class="cmfont1"><font
                                    color="898989">-</font>
                              <input id="phone3" type="text" size="4" maxlength="4" value="" class="cmfont1">
                              <input id="phone_number" type="hidden" name="phoneNumber">
                            </td>
                          </tr>
                          <tr>
                            <td width="127" height="50">
                              <img align="absmiddle" src="${cPath}/assets/images/i_dot.gif" border="0"> <font
                                    color="898989"><b>주 소</b></font>
                            </td>
                            <td>
                              <input type="text" autocomplete="off" name='zipcode' size="7" maxlength="7"
                                     value="${user.zipcode}" class="cmfont1">
                              <a href="javascript:FindZip(0)"><img align="absmiddle"
                                                                   src="${cPath}/assets/images/b_zip.gif"
                                                                   border="0"></a><br>
                              <input type="text" autocomplete="off" name='address' size="50" maxlength="200"
                                     value="${user.address}" class="cmfont1"><br>
                            </td>
                          </tr>
                          <tr>
                            <td width="127" height="30">
                              <img align="absmiddle" src="${cPath}/assets/images/i_dot.gif" border="0"> <font
                                    color="898989"><b>E-Mail</b></font>
                            </td>
                            <td>
                              <input type="text" autocomplete="off" name='email' size="50" maxlength="50"
                                     value="${user.email}" class="cmfont1">
                            </td>
                          </tr>
                        </table>

                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
            <table border="0" cellpadding="0" cellspacing="0" width="1200" class="cmfont">
              <tr>
                <td height="45" align="right">
                  <input type="image" src="${cPath}/assets/images/b_add.gif" border="0">&nbsp;&nbsp;
                  <a href="javascript:form2.reset();"><img src="${cPath}/assets/images/b_reset.gif" border="0"></a>
                </td>
              </tr>
            </table>
          </form>


          <!-------------------------------------------------------------------------------------------->
          <!-- 끝 : 다른 웹페이지 삽입할 부분                                                                                                                                                              -->
          <!-------------------------------------------------------------------------------------------->

      </td>
    </tr>
  </table>

  <c:if test="${authUser.role != 'admin'}">
    <jsp:include page="/WEB-INF/views/include/footer.jsp"/>
  </c:if>
</div>
</body>
</html>