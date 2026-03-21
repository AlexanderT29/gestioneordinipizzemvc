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
        <div class='card'>
            <div class='card-header'>
                <h5>Ricerca Ordini</h5>
            </div>
            <div class='card-body'>

                <form:form method="post" modelAttribute="search_ordine_attr" action="${pageContext.request.contextPath}/ordine/list" class="row g-3">

                    <div class="col-md-4">
                        <label for="costoTotale" class="form-label">Costo Totale (€)</label>
                        <spring:bind path="costoTotale">
                            <input type="number" step="0.01" class="form-control" name="costoTotale" id="costoTotale">
                        </spring:bind>
                    </div>

                    <div class="col-md-4">
                        <label for="closed" class="form-label">Stato Ordine</label>
                        <spring:bind path="closed">
                            <select class="form-select" name="closed" id="closed">
                                <option value="" selected>- Tutti -</option>
                                <option value="true">Chiuso</option>
                                <option value="false">Aperto</option>
                            </select>
                        </spring:bind>
                    </div>

                    <div class="col-md-4">
                        <label for="clienteDTO.id" class="form-label">Cliente</label>
                        <spring:bind path="clienteDTO.id">
                            <select class="form-select" name="clienteDTO.id" id="clienteDTO.id">
                                <option value="" selected>- Tutti i Clienti -</option>
                                <c:forEach items="${clienti_list_attribute}" var="clienteItem">
                                    <option value="${clienteItem.id}">${clienteItem.nome} ${clienteItem.cognome}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                    </div>

                    <div class="col-12 mt-4">
                        <button type="submit" class="btn btn-outline-puro">Cerca</button>
                        <button type="reset" class="btn btn-warning">Pulisci</button>
                        <a class="btn btn-outline-secondary ml-2" href="${pageContext.request.contextPath}/ordine">Annulla</a>
                    </div>

                </form:form>

            </div>
        </div>
    </div>
</main>
<jsp:include page="../footer.jsp" />
</body>
</html>