<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp" />
    <title>Inserisci Nuovo Ordine</title>
    <style>

        .pizza-checkbox:checked {
            background-color: #ff5722 !important;
            border-color: #ff5722 !important;
        }

        .pizza-checkbox:focus {
            border-color: #ff5722;
            box-shadow: 0 0 0 0.25rem rgba(255, 87, 34, 0.25);
        }
    </style>
</head>
<body class="d-flex flex-column h-100">

<jsp:include page="../navbar.jsp" />

<main class="flex-shrink-0">
    <div class="container">
        <div class='card'>
            <div class='card-header'>
                <h5>Inserisci nuovo ordine</h5>
            </div>
            <div class='card-body'>

                <form:form method="post" modelAttribute="insert_ordine_attr" action="${pageContext.request.contextPath}/ordine/save" class="row g-3">

                    <div class="col-md-6">
                        <label for="dataOrdine" class="form-label">Data e Ora Ordine <span class="text-danger">*</span></label>
                        <spring:bind path="dataOrdine">
                            <input type="datetime-local" class="form-control ${status.error ? 'is-invalid' : ''}" name="dataOrdine" id="dataOrdine" value="${status.value}" required>
                        </spring:bind>
                        <form:errors path="dataOrdine" cssClass="text-danger" />
                    </div>

                    <div class="col-md-6">
                        <label for="costoTotale" class="form-label">Costo Totale (€) <span class="text-danger">*</span></label>
                        <spring:bind path="costoTotale">
                            <input type="number" step="0.01" class="form-control ${status.error ? 'is-invalid' : ''}" name="costoTotale" id="costoTotale" value="${status.value}" required>
                        </spring:bind>
                        <form:errors path="costoTotale" cssClass="text-danger" />
                    </div>

                    <div class="col-md-6 mt-4">
                        <label for="clienteDTO.id" class="form-label">Cliente <span class="text-danger">*</span></label>
                        <spring:bind path="clienteDTO.id">
                            <select class="form-select ${status.error ? 'is-invalid' : ''}" name="clienteDTO.id" id="clienteDTO.id" required>
                                <option value="" selected>- Seleziona un Cliente -</option>
                                <c:forEach items="${clienti_list_attribute}" var="clienteItem">
                                    <option value="${clienteItem.id}" ${clienteItem.id == status.value ? 'selected' : ''}>${clienteItem.nome} ${clienteItem.cognome}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                        <form:errors path="clienteDTO.id" cssClass="text-danger" />
                    </div>

                    <div class="col-md-12 mt-4">
                        <label class="form-label fw-bold">Seleziona Pizze <span class="text-danger">*</span></label>
                        <div class="border p-3 rounded">

                            <c:forEach items="${pizze_list_attribute}" var="pizzaItem">
                                <div class="form-check mb-2" >
                                    <input class="form-check-input pizza-checkbox" type="checkbox"
                                           name="pizzeIds" id="pizza_${pizzaItem.id}" value="${pizzaItem.id}"
                                           data-prezzo="${pizzaItem.prezzo}">
                                    <label class="form-check-label" for="pizza_${pizzaItem.id}">
                                        <strong>${pizzaItem.descrizione}</strong> (€${pizzaItem.prezzo})
                                    </label>
                                </div>
                            </c:forEach>

                        </div>
                        <form:errors path="pizzeIds" cssClass="text-danger" />
                    </div>

                    <div class = "col-md-12 mt-4">
                        <label for="fattorino" class = "form-label" >Seleziona Fattorino<span class="text-danger">*</span></label>
                        <input type="text" class="form-cotrol" name="fattorino" id="fattorino" required>

                    </div>

                    <div class="col-12 mt-4">
                        <button type="submit" class="btn btn-outline-orange">Conferma</button>
                        <a class="btn btn-outline-secondary ml-2" href="${pageContext.request.contextPath}/ordine">Annulla</a>
                    </div>

                </form:form>

            </div>
        </div>
    </div>
</main>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const checkboxes = document.querySelectorAll('.pizza-checkbox');
        const costoTotaleInput = document.getElementById('costoTotale');

        checkboxes.forEach(function(checkbox) {
            checkbox.addEventListener('change', function() {
                let totale = 0.0;
                document.querySelectorAll('.pizza-checkbox:checked').forEach(function(checkedBox) {
                    totale += parseFloat(checkedBox.getAttribute('data-prezzo'));
                });
                totale = totale * 1.15;
                costoTotaleInput.value = totale.toFixed(2);
            });
        });
    });
</script>
<jsp:include page="../footer.jsp" />

</body>
</html>