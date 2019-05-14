<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>Active users</title>
    <%@ include file="/fragments-after-logging/head.jsp" %>
</head>

<body>
<%@ include file="/fragments-after-logging/header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="/fragments-after-logging/sidePanel.jsp" %>

        <div class="m-4 p-3 width-medium">
            <div class="m-4 p-3 border-dashed view-height">

                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">LISTA UŻYTKOWNIKÓW</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="/app/dashboard" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>
                <div class="schedules-content">
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-1">ID</th>
                            <th class="col-3">IMIĘ</th>
                            <th class="col-6">NAZWISKO</th>
                            <th class="col-2 center">AKCJE</th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">
                        <c:forEach items="${admins}" var="admin" varStatus="count">
                            <tr class="d-flex">
                                <td class="col-1">${count.count}</td>
                                <td class="col-3">${admin.firstName}</td>
                                <td class="col-6">${admin.lastName}</td>
                                <td class="col-2 center">
                                    <a href="/app/admin/block-user?userId=${admin.id}"
                                       class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="/fragments-after-logging/jsCode.jsp" %>

</body>
</html>