<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <!-- Common imports in pages -->
    <jsp:include page="../header.jsp" />
    <title>Visualizza elemento</title>

</head>
<body class="d-flex flex-column h-100">
<!-- Fixed navbar -->
<jsp:include page="../navbar.jsp" />

<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <div class='card'>
            <div class='card-header'>
                Visualizza dettaglio
            </div>

            <div class='card-body'>
                <dl class="row">
                    <dt class="col-sm-3 text-right">Id:</dt>
                    <dd class="col-sm-9">${show_cliente_attr.id}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Nome:</dt>
                    <dd class="col-sm-9">${show_cliente_attr.nome}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Cognome:</dt>
                    <dd class="col-sm-9">${show_cliente_attr.cognome}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Indirizzo:</dt>
                    <dd class="col-sm-9">${show_cliente_attr.indirizzo}</dd>
                </dl>

                <dl class = "row">
                    <dt class = "col-sm-3 text-right">Attivo:</dt>
                    <dd class="col-sm-9">${show_cliente_attr.attivo}</dd>
                </dl>


                <!-- info Ordini -->
                <p>
                    <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                        Info Ordini
                    </a>
                </p>
                <div class="collapse" id="collapseExample">
                    <div class="card card-body">
                        <c:choose>
                        <c:when test="${empty show_cliente_attr.ordini}">
                        <div class="alert alert-warning mb-0" role="alert">
                            Questo cliente non ha ancora effettuato nessun ordine.
                        </div>
                        </c:when>
                            <c:otherwise>
                                <table class="table table-sm table-striped table-hover mb-0">
                                    <thead>
                                    <tr>
                                        <th>ID Ordine</th>
                                        <th>Data Ordine</th>
                                        <th>Stato</th>
                                        <th>Azione</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${show_cliente_attr.ordini}" var="ordineItem">
                                        <tr>
                                            <td>${ordineItem.id}</td>

                                            <td>${ordineItem.dataOrdine.toString().replace('T', ' ')}</td>

                                            <td>
                                    <span class="badge ${ordineItem.closed ? 'bg-danger' : 'bg-success'}">
                                            ${ordineItem.closed ? 'Chiuso' : 'Aperto'}
                                    </span>
                                            </td>

                                            <td>
                                                <a class="btn btn-outline-primary btn-sm" href="${pageContext.request.contextPath}/ordine/show/${ordineItem.id}">
                                                    Vedi Dettaglio
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>

                    <!-- end info Ordine -->
                </div>

                <!-- end card body -->
            </div>

            <div class='card-footer'>
                <a href="${pageContext.request.contextPath }/cliente/" class='btn btn-outline-secondary' style='width:80px'>
                    <i class='fa fa-chevron-left'></i> Back
                </a>
            </div>
            <!-- end card -->
        </div>

        <!-- end container -->
    </div>
    </div>

</main>
<jsp:include page="../footer.jsp" />

</body>
</html>