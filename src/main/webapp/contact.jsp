<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kontakt</title>
    <%@ include file="fragments/head.jsp" %>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<section class="dashboard-section ">
    <div class="container pt-4 pb-4 ">
        <div class="border-dashed view-height">
            <div class="container w-50 align-middle">
                <h3 class="mb-4 padding-small">Skontaktuj się z nami!</h3>
                <div class="form-group mb-3">
                    <input type="email" class="form-control border border-secondary rounded mb-3" placeholder="Twój mail"
                           aria-label="Recipient's mail" aria-describedby="basic-addon2">
                    <textarea class="form-control border border-secondary mb-3" placeholder="Twoja wiadomość.."
                              aria-label="Recipient's message" aria-describedby="basic-addon2"></textarea>
                        <button class="btn btn-color border-0 rounded mb-3 float-right" type="submit" id="basic-addon2"><a
                                href="index.html">Lorem</a></button>
                </div>
                <div class="container d-flex-row ">
                    <a href="#">
                        <i class="fab fa-facebook-square mr-4 icon-social text-muted"></i>
                    </a>
                    <a href="#">
                        <i class="fab fa-twitter-square mr-4 icon-social text-muted"></i>

                    </a>
                    <a href="#">
                        <i class="fab fa-instagram icon-social text-muted"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>


</section>

<%@ include file="fragments/jsCode.jsp" %>
</body>
</html>
