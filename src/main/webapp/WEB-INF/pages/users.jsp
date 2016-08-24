<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 07.08.2016
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Users Page</title>

    <link rel="stylesheet" type="text/css" href="../../resources/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../../resources/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../../resources/css/font-awesome.min.css" />
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="../../index.jsp">Back to main</a>
        </div>

        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <c:url var="addAction" value="/users/add"/>
                <form:form action="${addAction}" commandName="user" class="navbar-form navbar-left">
                    <div class="form-group">
                        <c:if test="${!empty user.name}">
                            <form:input path="id" class="form-control" readonly="true" size="1" disabled="true"/>
                            <form:hidden path="id"/>
                        </c:if>
                        <form:input required="required" type="text" path="name" class="form-control"  placeholder="Name" size="25" pattern="^[a-zA-Z\s]+$"  maxlength="25" title="Name - only EN letters"/>
                        <form:input required="required" type="text" path="age" class="form-control"  placeholder="Age" value="18" size="1" pattern="^(1[89]|[2-9]\d)$" maxlength="2" minlength="1"  title="Age - only 18-99"/>
                        <form:select type="text" path="admin" class="form-control" size="1">
                            <form:option value="0" label="no" path="admin"/>
                            <form:option value="1" label="yes" path="admin"/>
                        </form:select>
                    </div>
                    <c:if test="${!empty user.name}">
                        <button type="submit" class="btn btn-default">Edit</button>
                    </c:if>
                    <c:if test="${empty user.name}">
                        <button type="submit" class="btn btn-default">Add</button>
                    </c:if>
                </form:form>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:url var="filterAction" value="/filterusers"/>
                <form:form action="${filterAction}" commandName="user" class="navbar-form navbar-left">
                    <div class="form-group">
                        <form:input required="required" type="text" path="age" class="form-control" placeholder="Age" value="18" pattern="^(1[89]|[2-9]\d)$" title="Age - only 18-99" maxlength="2" minlength="1"/>
                    </div>
                    <button type="submit" class="btn btn-default">Filter</button>
                </form:form>

                <c:url var="deleteAge" value="/users"/>
                <form:form action="${deleteAge}" commandName="user" class="navbar-form navbar-left">
                    <button type="submit" class="btn btn-default">Delete filter</button>
                </form:form>
            </ul>
        </div>

    </div>

</nav>

<div class="container">
    <div class="well">
        <strong>List of Users</strong>
    </div>
    <c:if test="${!empty listUsers}">
        <table class="table table-stripped">
            <tr>
                <th width="60">ID</th>
                <th width="170">Name</th>
                <th width="60">Age</th>
                <th width="90">is Admin</th>
                <th width="140">Created Date</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listUsers}" var="user" varStatus="itr">
                <tr>
                    <th>${user.id}</th>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.admin}</td>
                    <td>${user.createdDate}</td>
                    <td><a href="<c:url value='/edit/${user.id}'/>"><img src="../../resources/css/img/edit.png" width="20" height="20"/></a></td>
                    <td><a href="<c:url value='/remove/${user.id}'/>"><img src="../../resources/css/img/delete.png" width="18" height="18"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <tag:paginate max="15" offset="${offset}" count="${count}"
                  uri="/users" next="&raquo;" previous="&laquo;" />
</div>

<script type="text/javascript" src="../../resources/js/jquery.js"></script>
<script type="text/javascript" src="../../resources/js/bootstrap.min.js"></script>

</body>
</html>
