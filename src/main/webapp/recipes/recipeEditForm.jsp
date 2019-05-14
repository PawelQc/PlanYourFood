<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Edit recipe</title>
    <%@ include file="/fragments-after-logging/head.jsp" %>
</head>
<body>
<%@ include file="/fragments-after-logging/header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="/fragments-after-logging/sidePanel.jsp" %>

        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <form action="/app/recipe/edit?recipeId=${recipe.id}" method="POST">
                    <div class="mt-4 ml-4 mr-4">
                        <h4>${errorNotCompleteData}</h4>
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">Edycja przepisu</h3></div>
                            <div class="col d-flex justify-content-end mb-2">
                                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Potwierdź
                                </button>
                            </div>
                        </div>
                        <table class="table borderless">
                            <tbody>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Nazwa Przepisu</th>
                                <td class="col-7">
                                    <input id="name" name="name" class="w-100 p-1" value="${recipe.name}"
                                           placeholder="Wpisz nazwę przepisu">
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Opis przepisu</th>
                                <td class="col-7"><textarea id="description" name="description"
                                                            placeholder="Opisz swój przepis" class="w-100 p-1"
                                                            rows="5">${recipe.description}</textarea></td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                                <td class="col-3">
                                    <input id="preparationTime" name="preparationTime" placeholder="Wpisz czas"
                                           value="${recipe.preparationTime}" class="p-1" type="number" value="">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="row d-flex">
                            <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób
                                przygotowania</h3></div>
                            <div class="col-2"></div>
                            <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                        </div>
                        <div class="row d-flex">
                            <div class="col-5 p-4">
                                <textarea id="preparation" name="preparation" placeholder="Opisz sposób przygotowania"
                                          class="w-100 p-1" rows="10">${recipe.preparation}</textarea>
                            </div>
                            <div class="col-2"></div>

                            <div class="col-5 p-4">
                                <textarea id="ingredients" name="ingredients"
                                          placeholder="Podaj składniki" class="w-100 p-1"
                                          rows="10">${recipe.ingredients}</textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<%@ include file="/fragments-after-logging/jsCode.jsp" %>
</body>
</html>
