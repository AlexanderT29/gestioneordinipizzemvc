<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp" />
    <title>Ricerca Ordini</title>
</head>
<body class="d-flex flex-column h-100">

<jsp:include page="../navbar.jsp" />

<main class="flex-shrink-0">
    <div class="container">

        <div class="alert alert-danger alert-dismissible fade show ${errorMessage == null ? 'd-none' : ''}" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>

        <div class='card'>
            <div class='card-header'>
                <h5>Ricerca Ordini</h5>
            </div>
            <div class='card-body'>
                <form action="${pageContext.request.contextPath}/ordine/listdate" method="post" class="row g-3">

                    <div class="col-md-6">
                        <label for="dataInizio" class="form-label">Data Inizio Ricerca <span class="text-danger">*</span></label>
                        <input type="datetime-local" class="form-control" name="dataInizio" id="dataInizio" required>
                    </div>

                    <div class="col-md-6">
                        <label for="dataFine" class="form-label">Data Fine Ricerca <span class="text-danger">*</span></label>
                        <input type="datetime-local" class="form-control" name="dataFine" id="dataFine" required>
                    </div>

                    <div class="col-12 mt-4">
                        <button type="submit" class="btn btn-outline-puro">Cerca</button>
                        <button type="reset" class="btn btn-warning">Pulisci</button>
                        <a class="btn btn-outline-secondary ml-2" href="${pageContext.request.contextPath}/ordine">Annulla</a>
                    </div>

                </form>


            </div>
        </div>
    </div>
</main>
<jsp:include page="../footer.jsp" />
</body>
</html>