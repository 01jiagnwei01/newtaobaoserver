<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司帐号信息</title>
</head>
<body>
<table>
	<tr><td>公司帐号信息</td></tr>
	<tr><td>已售出点数</td><td>${companyAccount.sellPoint }</td></tr>
	<tr><td>已赚回点数</td><td>${companyAccount.getPoints }</td></tr>
	<tr><td>送出点数</td><td>${companyAccount.supplyPoints }</td></tr>
	<tr><td>总结：盈利</td><td>${companyAccount.sellPoint+companyAccount.getPoints-companyAccount.supplyPoints }</td></tr>
</table>
</body>
</html>