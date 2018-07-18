<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>dubbo 框架测试</title>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.js"></script>
    <script>
        $(function () {
            $.ajax({
                type: 'GET',
                url: '/dfieldCheck/test',
                dataType: 'json',
                contentType: "application/json",
                success: function (test) {
                    console.log(test);
                    for (var a=0;a<test.length;a++){
                        console.log(test[a].name);
                    }
                }
            })
        })
    </script>
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
