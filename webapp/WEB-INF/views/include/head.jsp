<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<table class="no-drag" width="1200" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td>
            <!--  상단 왼쪽 로고  -------------------------------------------->
            <table border="0" cellspacing="0" cellpadding="0" width="182">
                <tr>
                    <td>
                        <a id="manager" href="${cPath}/user/login/admin" style="font-weight: bold;" title="no" id="no">
                            관리자 홈페이지 (서비스시 삭제)
                        </a>
                    </td>
                </tr>
            </table>
        </td>
        <td align="right" valign="bottom">
            <!--  상단메뉴 시작 (main_topmemnu.jsp) : Home/로그인/회원가입/장바구니/주문배송조회/즐겨찾기추가  ---->
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <c:choose>
                        <c:when test="${empty authUser }">
                            <td><a href="${cPath}/"><img src="${cPath}/assets/images/top_menu01.gif" border="0"></a>
                            </td>
                            <td><img src="${cPath}/assets/images/top_menu_line.gif" width="11"></td>
                            <td><a href="${cPath}/user/login"><img src="${cPath}/assets/images/top_menu02.gif"
                                                                   border="0"></a></td>
                            <td><img src="${cPath}/assets/images/top_menu_line.gif" width="11"></td>
                            <td><a href="${cPath}/user/join/agree"><img src="${cPath}/assets/images/top_menu03.gif"
                                                                        border="0"></a></td>
                            <td><img src="${cPath}/assets/images/top_menu_line.gif" width="11"></td>
                            <td><a href="${cPath}/buy/cart"><img src="${cPath}/assets/images/top_menu05.gif" border="0"></a>
                            </td>
                            <td><img src="${cPath}/assets/images/top_menu_line.gif" width="11"></td>
                            <td><a href="${cPath}/buy/order/list"><img src="${cPath}/assets/images/top_menu06.gif"
                                                                       border="0"></a></td>
                        </c:when>

                        <c:otherwise>
                            <td style="color:gray;">${authUser.name}님 환영합니다 ! </td>
                            <td><img src="${cPath}/assets/images/top_menu_line.gif" width="11"></td>
                            <td><a href="${cPath}/"><img src="${cPath}/assets/images/top_menu01.gif" border="0"></a></td>
                            <td><img src="${cPath}/assets/images/top_menu_line.gif" width="11"></td>
                            <!-- 로그아웃 -->
                            <td><a href="${cPath}/user/logout"><img src="${cPath}/assets/images/top_menu02_1.gif"
                                                                    border="0"></a></td>
                            <td><img src="${cPath}/assets/images/top_menu_line.gif" width="11"></td>
                            <!-- 회원정보수정 -->
                            <td><a href="${cPath}/user/modify/${authUser.no}"><img src="${cPath}/assets/images/top_menu03_1.gif"
                                                                    border="0"></a></td>
                            <td><img src="${cPath}/assets/images/top_menu_line.gif" width="11"></td>
                            <td><a href="${cPath}/buy/cart"><img src="${cPath}/assets/images/top_menu05.gif" border="0"></a>
                            </td>
                            <td><img src="${cPath}/assets/images/top_menu_line.gif" width="11"></td>
                            <td><a href="${cPath}/buy/order/list"><img src="${cPath}/assets/images/top_menu06.gif"
                                                                       border="0"></a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </table>
        </td>
    </tr>
</table>

<!-- 상단 메인 이미지 --------------------------------------------------->
<div class="top-menu no-drag">
    <a class="logo" href="${cPath}">
        <p>
            MUSIC<span>24</span>
        </p>
    </a>
    <div class="search-section">
        <input id="search-bar" type="text" placeholder="가수, 음반 검색">
        <a href="#">
            검색
        </a>
    </div>
</div>