<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<head>
    <title>Strona główna po zalogowaniu</title>
    <%@ include file="fragments-po-zalogowaniu/head.jsp" %>
</head>

<body>
<%@ include file="fragments-po-zalogowaniu/header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="fragments-po-zalogowaniu/panelBoczny.jsp" %>


        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <%@ include file="fragments-po-zalogowaniu/przyciskiMenuAdd.jsp" %>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: ${numberOfRecipies}</span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: ${numberOfPlans}</span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span> ${recipePlan.getPlan().getName()}
                </h2>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">${recipePlan.getDayName().getName()}</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="d-flex">
                        <td class="col-2">${recipePlan.getMealName()}</td>
                        <td class="col-8">${recipePlan.getRecipe().getName()}</td>
                        <td class="col-2">
                            <button type="button" class="btn btn-primary rounded-0">Szczegóły</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>


<%@ include file="fragments-po-zalogowaniu/jsCode.jsp" %>

</body>
</html>