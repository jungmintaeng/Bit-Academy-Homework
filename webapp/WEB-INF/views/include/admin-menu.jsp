<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<table width='900' border='0' cellspacing='0' cellpadding='3'>
<tr> 
  <td bgcolor='#F7F7F7'>
    <div align='center'> 
 	<br>
	 	<table width='780' border='0' cellspacing='0' cellpadding='0'>
	 		<tr>
	    		<td>
	      			<div align='center'> 
	        			<table width='720' border='0' cellspacing='1' cellpadding='6' bgcolor='#CCCCCC'>
	          				<tr> 
	            				<td bgcolor='#F2F2F2'> 
	              					<div align='center'> 
	                					<table width='660' border='0' cellspacing='0' cellpadding='2'>
	                  						<tr> 
							                    <td width='100'><font color='#0457A2'>▶ </font><a href='${cPath}/admin/member/list'><font color='#0457A2'>회원관리</font></a></td>
							                    <td width='100'><font color='#0457A2'>▶ </font><a href='${cPath}/admin/product/list'><font color='#0457A2'>상품관리</font></a></td>
							                    <td width='100'><font color='#0457A2'>▶ </font><a href='${cPath}/admin/order/list'><font color='#0457A2'>주문관리</font></a></td>
							                    <td width='100'><font color='#0457A2'>▶ </font><a href='${cPath}/admin/option/list'><font color='#0457A2'>옵션관리</font></a></td>
							                    <td width='100'><font color='#0457A2'>▶ </font><a href='${cPath}/admin/faq/list'><font color='#0457A2'>FAQ관리</font></a></td>
						                  	</tr>
	                					</table>
	              					</div>
	            				</td>
	          				</tr>
	        			</table>
	      			</div>
	    		</td>
	  		</tr>
		</table>
    </div>
    <br>
  </td>
</tr>
</table>