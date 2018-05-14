<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>우편번호 및 주소 찾기</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="include/font.css">
<script language="javascript">
function SearchZip() 
{
	if (!form.dong.value) 
	{
		alert("검색하실 동명을 입력해 주십시오.");
		form.dong.focus();
		return;
	}
	form.submit();
}
function SendZip(zip_kind) 
{
	if (form1.jusor.value == "") {
		alert("나머지 주소를 입력하여 주십시오.");
		form1.jusor.focus();
		return;
	}
	var str, zip1, zip2, juso;
	str = form1.post_no.value;
	str = str.split("^^");
	zip1 = str[0];
	zip2 = str[1];
	juso = str[2] + " " + form1.jusor.value;

	if (zip_kind==1)			
	{
		opener.form2.o_zip1.value = zip1;
		opener.form2.o_zip2.value = zip2;
		opener.form2.o_juso.value = juso;
	} else if (zip_kind==2)	{
		opener.form2.r_zip1.value = zip1;
		opener.form2.r_zip2.value = zip2;
		opener.form2.r_juso.value = juso;
	} else {
		opener.form2.zip1.value = zip1;
		opener.form2.zip2.value = zip2;
		opener.form2.juso.value = juso;
	}

	self.close();
}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0"topmargin="0"marginwidth="0"marginheight="0">

<script language="javascript">
	window.resizeTo(500, 340);
</script>

<table width="500" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td><p><img src="/assets/images/zipcode_title.gif" border="0"></p></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<form  name="form" method="post" action="zipcode.html">
			<table width="385" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td width="14">&nbsp;</td>
					<td width="355" align="center">
						<table width="86%" border="0" cellspacing="5" cellpadding="0">
							<tr height="20">
								<td colspan="2" valign="middle" width="305">
									<span style="font-size:9pt;"><font color="#666666"><b>검색할 동명을 입력해 주세요</b></font></span>
								</td>
							</tr>
							<tr> 
								<td width="161"><input name="dong" type="text" size="25" maxlength="20" value = "" style = "border:1 solid #E5E5E5;font-size:9pt"></td>
								<td width="134"><a href = "javascript:SearchZip()"><img src="/assets/images/b_search.gif" border="0"></a></td>
							</tr>
						</table>
					</td>
					<td width="16">&nbsp;</td>
				</tr>
			</table>
			</form>
		</td>
	</tr>
</table>

<table width="500" border="0"cellpadding="0"cellspacing="0">
	<form name="form1">
	<tr> 
		<td align="center">
			<select style="BACKGROUND-COLOR: #E3E7EB" border="0" name="post_no" style = "width:440;font-size:9pt">
				<option value="139^^052^^서울 노원구 월계2동">[139-052] 서울 노원구 월계2동 </option>
				<option value="139^^724^^서울 노원구 월계2동 염광학원">[139-724] 서울 노원구 월계2동 염광학원</option>
				<option value="139^^770^^서울 노원구 월계2동 주공아파트">[139-770] 서울 노원구 월계2동 주공아파트 (101∼216동)</option>
				<option value="139^^848^^서울 노원구 월계2동">[139-848] 서울 노원구 월계2동 554∼565</option>
				<option value="139^^848^^서울 노원구 월계2동">[139-848] 서울 노원구 월계2동 580∼622</option>
				<option value="139^^851^^서울 노원구 월계2동">[139-851] 서울 노원구 월계2동 768∼799</option>
				<option value="139^^852^^서울 노원구 월계2동">[139-852] 서울 노원구 월계2동 800∼846</option>
				<option value="139^^852^^서울 노원구 월계2동">[139-852] 서울 노원구 월계2동 850</option>
				<option value="139^^852^^서울 노원구 월계2동">[139-852] 서울 노원구 월계2동 876∼915</option>
				<option value="139^^852^^서울 노원구 월계2동">[139-852] 서울 노원구 월계2동 산17</option>
				<option value="139^^910^^서울 노원구 월계2동 극동아파트">[139-910] 서울 노원구 월계2동 극동아파트</option>
				<option value="139^^911^^서울 노원구 월계2동 대우아파트">[139-911] 서울 노원구 월계2동 대우아파트</option>
				<option value="139^^912^^서울 노원구 월계2동 라이프아파트">[139-912] 서울 노원구 월계2동 라이프아파트</option>
				<option value="139^^913^^서울 노원구 월계2동 청백아파트">[139-913] 서울 노원구 월계2동 청백아파트</option>
				<option value="139^^914^^서울 노원구 월계2동 초안아파트">[139-914] 서울 노원구 월계2동 초안아파트</option>
			</select>
		</td>
	</tr>
	<tr> 
		<td align="center">
			<input type="text" autocomplete="off" name="jusor" size="50" maxlength="50" STYLE="width:440;border:1 solid #E5E5E5"><br>
			<b><font color="#666666"><span style="font-size:9pt;">나머지 주소를 입력해 주세요</span></font></b>
		</td>
	</tr>
	<!-- 회원가입인 경우 : SendZip(0), 주문지인 경우 : SendZip(1), 배송지인 경우 : SendZip(2) -->
	<tr height="55"> 
		<td align="center">
			<a href="javascript:SendZip(0)"><img src="/assets/images/b_ok1.gif" border="0"></a>
		</td>
	</tr>
</form>
</table>

</body>
</html>
