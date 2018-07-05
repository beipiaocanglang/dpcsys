<%--
  Created by IntelliJ IDEA.
  User: canglang
  Date: 2018/7/5
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>dubbo 框架测试</title>
</head>
<body>
    <table>
        <tr>
            <th>
                <td>姓名</td>
                <td>年龄</td>
            </th>
        </tr>

        <c:forEach items="${itemList}" var="item">
            <tr>
                <th>
                    <td>${item.name}</td>
                    <td>${item.age}</td>
                </th>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
