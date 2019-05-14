<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Dashboard</title>
    <%@ include file="fragments-after-logging/head.jsp" %>
</head>

<body>
<%@ include file="fragments-after-logging/header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="fragments-after-logging/sidePanel.jsp" %>
        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <%@ include file="fragments-after-logging/menuButtons.jsp" %>
                <div class="dashboard-alerts">
                    <h4>${successfulDataUpdate}</h4> <h4>${successfulPasswordUpdate}</h4> <h4>${noSuperAdmin}</h4>
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: ${numberOfRecipes}</span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: ${numberOfPlans}</span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span>
                    <c:choose>
                        <c:when test="${not empty noRecipePlan}">
                            <c:out value="${noRecipePlan}"></c:out>
                        </c:when>
                        <c:otherwise>
                            ${recipePlan.plan.name}
                        </c:otherwise>
                    </c:choose>
                </h2>
                <c:choose>
                    <c:when test="${not empty noRecipePlan}">
                    </c:when>
                    <c:otherwise>
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">${recipePlan.dayName.name}</th>
                                <th class="col-8"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="d-flex">
                                <td class="col-2">${recipePlan.mealName}</td>
                                <td class="col-8">${recipePlan.recipe.name}</td>
                                <td class="col-2">
                                    <a href="/app/recipe/details?recipeId=${recipePlan.recipe.id}"
                                       class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>

<%@ include file="fragments-after-logging/jsCode.jsp" %>

</body>
</html>