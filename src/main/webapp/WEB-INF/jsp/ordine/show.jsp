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
                    <dd class="col-sm-9">${show_ordine_attr.id}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Data Ordine:</dt>
                    <dd class="col-sm-9">${show_ordine_attr.dataOrdine}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Stato:</dt>
                    <dd class="col-sm-9">${show_ordine_attr.closed}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Prezzo Totale:</dt>
                    <dd class="col-sm-9">${show_ordine_attr.costoTotale}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Codice</dt>
                    <dd class="col-sm-9">${show_ordine_attr.codice}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Cliente Associato:</dt>
                    <dd class="col-sm-9 d-flex justify-content-between align-items-center">
                        <span>${show_ordine_attr.clienteDTO.nome} ${show_ordine_attr.clienteDTO.cognome}</span>
                        <a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/cliente/show/${show_ordine_attr.clienteDTO.id}">
                            Visualizza Cliente
                        </a>
                    </dd>
                </dl>


                <!-- info Pizze -->
                <p>
                    <a class="btn btn-outline-puro" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                        Info Pizze
                    </a>
                </p>
                <div class="collapse" id="collapseExample">
                    <div class="card card-body">
                        <c:choose>
                            <c:when test="${empty show_ordine_attr.insiemePizze}">
                                <div class="alert alert-warning mb-0" role="alert">
                                    Questo ordine non ha ancora nessuna pizza ordinata!
                                </div>
                            </c:when>
                            <c:otherwise>
                                <table class="table table-sm table-striped table-hover mb-0">
                                    <thead>
                                    <tr>
                                        <th>ID Pizza</th>
                                        <th>Descrizione</th>
                                        <th>Ingredienti</th>
                                        <th>Prezzo</th>
                                        <th>Stato</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${show_ordine_attr.insiemePizze}" var="pizzaItem">
                                        <tr>
                                            <td>${pizzaItem.id}</td>

                                            <td>${pizzaItem.descrizione}</td>

                                            <td>${pizzaItem.ingredienti}</td>

                                            <td>${pizzaItem.prezzo}</td>

                                            <td>
                                    <span class="badge ${ordineItem.attiva ? 'bg-danger' : 'bg-success'}">
                                            ${ordineItem.attiva ? 'Non disponibile' : 'Disponibile'}
                                    </span>
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
                    <a href="${pageContext.request.contextPath }/ordine" class='btn btn-outline-secondary' style='width:80px'>
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