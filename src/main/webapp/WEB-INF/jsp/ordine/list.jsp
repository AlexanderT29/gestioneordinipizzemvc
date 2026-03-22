<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp" />
    <title>Lista Ordini</title>
</head>
<body class="d-flex flex-column h-100">

<jsp:include page="../navbar.jsp" />

<main class="flex-shrink-0">
    <div class="container">

        <div class="alert alert-success alert-dismissible fade show ${successMessage == null ? 'd-none' : ''}" role="alert">
            ${successMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <div class="alert alert-danger alert-dismissible fade show ${errorMessage == null ? 'd-none' : ''}" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>

        <div class='card'>
            <div class='card-header'>
                <h5>Lista Ordini</h5>
            </div>
            <div class='card-body'>

                <a class="btn btn-outline-puro mb-3" href="${pageContext.request.contextPath}/ordine/insert">Aggiungi Nuovo</a>
                <a class="btn btn-outline-secondary mb-3" href="${pageContext.request.contextPath}/ordine/search">Ricerca Avanzata</a>

                <table class='table table-striped table-hover'>
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Data</th>
                        <th>Costo Totale</th>
                        <th>Stato</th>
                        <th>Codice</th>
                        <th>Cliente</th>
                        <th>Azioni</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ordine_list_attribute}" var="ordineItem">
                        <tr>
                            <td>${ordineItem.id}</td>
                            <td>${ordineItem.dataOrdine.toString().replace('T', ' ')}</td>
                            <td>€ ${ordineItem.costoTotale}</td>

                            <td>
                                    <span class="badge ${ordineItem.closed ? 'bg-danger' : 'bg-success'}">
                                            ${ordineItem.closed ? 'Chiuso' : 'Aperto'}
                                    </span>
                            </td>
                            <td>${ordineItem.codice}</td>
                            <td>${ordineItem.clienteDTO.nome} ${ordineItem.clienteDTO.cognome}</td>
                            <td>
                                <a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/ordine/show/${ordineItem.id}">Visualizza</a>
                                <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/ordine/edit/${ordineItem.id}">Modifica</a>
                                <a class="btn btn-sm btn-outline-orange" href="${pageContext.request.contextPath}/ordine/delete/${ordineItem.id}">Chiudi</a>
                                <a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/ordine/deletedefinitivo/${ordineItem.id}">Elimina</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</main>
<jsp:include page="../footer.jsp" />
</body>
</html>