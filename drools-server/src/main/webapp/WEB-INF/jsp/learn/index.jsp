<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<c:set var="ctx" value="<%=basePath%>"/>
<%--<%@include file="" %>--%>
<html xmlns:th="http://www.thymeleaf.org" lang="en" class="no-js">

<head>
    <meta charset="utf-8" />
    <meta name="author" content="" />
    <meta name="keywords" content="" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <title>learn Resources</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- main JS libs -->
    <script src="../static/vanilla-cream-css/js/libs/modernizr.min.js" th:src="@{/vanilla-cream-css/js/libs/modernizr.min.js}"></script>
    <script src="../static/vanilla-cream-css/js/libs/jquery-1.10.0.js" th:src="@{/vanilla-cream-css/js/libs/jquery-1.10.0.js}"></script>
    <script src="../static/vanilla-cream-css/js/libs/jquery-ui.min.js" th:src="@{/vanilla-cream-css/js/libs/jquery-ui.min.js}"></script>
    <script src="../static/vanilla-cream-css/js/libs/bootstrap.min.js" th:src="@{/vanilla-cream-css/js/libs/bootstrap.min.js}"></script>

    <!-- Style CSS -->
    <link rel="stylesheet" href="../static/vanilla-cream-css/css/bootstrap.css" th:href="@{/vanilla-cream-css/css/bootstrap.css}" />
    <link rel="stylesheet" href="../static/vanilla-cream-css/style.css"  th:href="@{/vanilla-cream-css/style.css}"  />
    <!-- scripts -->
    <script src="../static/vanilla-cream-css/js/general.js" th:src="@{/vanilla-cream-css/js/general.js}"></script>

    <!-- Include all needed stylesheets and scripts here -->

    <!--[if lt IE 9]><script src="../static/vanilla-cream-css/js/respond.min.js" th:src="@{/vanilla-cream-css/js/respond.min.js}"></script><![endif]-->
    <!--[if gte IE 9]>
    <style type="text/css">
        .gradient {filter: none !important;}
    </style>
    <![endif]-->

</head>
<body style="background-image: none;">
<div class="body_wrap">
    <div class="container">
        <div class="alert alert-success text-center" role="alert">Sptring Boot学习资源大奉送，爱我就关注嘟嘟公众号：嘟爷java超神学堂</div>
        <table class="table table-striped table-bordered">
            <tr>
                <td>作者</td>
                <td>教程名称</td>
                <td>地址</td>
            </tr>
            <c:forEach var="learn" items="${learnResourceList}">
                <tr class="text-info">
                    <td text="${learn.author}">${learn.author}</td>
                    <td text="${learn.title}">${learn.author}</td>
                    <td><a  href="${learn.url}" class="btn btn-search btn-green" target="_blank"><span>点我</span></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>