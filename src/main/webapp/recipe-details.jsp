<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <title>Zaplanuj Jedzonko</title>
    <%@ include file="fragments-po-zalogowaniu/head.jsp" %>
</head>

<body>
<%@ include file="fragments-po-zalogowaniu/header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="fragments-po-zalogowaniu/panelBoczny.jsp" %>

        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding"><h3 class="color-header text-uppercase">Szczegóły przepisu</h3></div>
                        <div class="col d-flex justify-content-end mb-2"><a href="/app/recipe/list" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a></div>
                    </div>
         
                    <table class="table borderless">
                        <tbody>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Nazwa Przepisu</th>
                                <td class="col-7">
                                    ${recipeName}
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Opis przepisu</th>
                                <td class="col-7">${recipeDescription}</td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                                <td class="col-7">
                                        ${recipePreparationTime}
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="row d-flex">
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3></div>
                        <div class="col-2"></div>
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                    </div>
                    <div class="row d-flex">
                        <div class="col-5 p-4">
                            <p>${recipePreparation}</p>
                        </div>
                        <div class="col-2"></div>
                        <ul class="col-5 p-4 list-unstyled">
                            <c:forEach items="${ingredients}" var="ingredient">
                            <li>${ingredient}</li>
                            </c:forEach>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
</section>

<%@ include file="fragments-po-zalogowaniu/jsCode.jsp" %>

</body>
</html>