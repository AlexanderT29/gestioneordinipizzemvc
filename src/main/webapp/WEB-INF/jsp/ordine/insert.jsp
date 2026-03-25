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
                            <input type="number" step="0.01" class="form-control ${status.error ? 'is-invalid' : ''}" name="costoTotale" id="costoTotale" value="${status.value}" required readonly>
                        </spring:bind>
                        <form:errors path="costoTotale" cssClass="text-danger" />

                        <div id="blocco-sconto" class="mt-2 d-none">
                            <span class="text-success">Prezzo Scontato: </span>
                            <span id="valore-sconto" class="text-success fw-bold fs-5">0.00</span>
                            <span class="text-success fw-bold fs-5"> €</span>
                        </div>

                        <div id="banner-traguardo" class="alert mt-3 d-none mb-0" role="alert">
                        </div>
                    </div>

                    <div class="col-md-6 mt-4">
                        <label for="clienteDTO.id" class="form-label">Cliente <span class="text-danger">*</span></label>
                        <spring:bind path="clienteDTO.id">
                            <select class="form-select ${status.error ? 'is-invalid' : ''}" name="clienteDTO.id" id="clienteDTO.id" required>
                                <option value="" data-ordini="0" selected>- Seleziona un Cliente -</option>
                                    <c:forEach items="${clienti_list_attribute}" var="clienteItem">
                                        <option value="${clienteItem.id}" data-ordini="${clienteItem.ordini}" ${clienteItem.id == status.value ? 'selected' : ''}>
                                            ${clienteItem.nome} ${clienteItem.cognome}
                                        </option>
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
        const clienteSelect = document.getElementById('clienteDTO.id');
        const bannerTraguardo = document.getElementById('banner-traguardo');
        const bloccoSconto = document.getElementById('blocco-sconto');
        const valoreSconto = document.getElementById('valore-sconto');

        function ricalcolaTutto() {
            let totale = 0.0;
            document.querySelectorAll('.pizza-checkbox:checked').forEach(function(checkedBox) {
                totale += parseFloat(checkedBox.getAttribute('data-prezzo'));
            });
            totale = totale * 1.15;
            costoTotaleInput.value = totale.toFixed(2);
            bloccoSconto.classList.add('d-none');
            bannerTraguardo.classList.add('d-none');
            if (clienteSelect.selectedIndex > 0) {
                const opzioneSelezionata = clienteSelect.options[clienteSelect.selectedIndex];
                const numeroOrdini = parseInt(opzioneSelezionata.getAttribute('data-ordini'));
                if (numeroOrdini === 9) {
                    let totaleScontato = totale * 0.90;
                    valoreSconto.innerText = totaleScontato.toFixed(2);
                    bloccoSconto.classList.remove('d-none');
                    bannerTraguardo.innerHTML = " 10° ordine! Il Cliente diventerà <strong>SILVER</strong>!";
                    bannerTraguardo.className = "alert alert-secondary border-secondary mt-3";
                    bannerTraguardo.classList.remove('d-none');
                }
                else if (numeroOrdini === 19) {
                    let totaleScontato = totale * 0.80;
                    valoreSconto.innerText = totaleScontato.toFixed(2);
                    bloccoSconto.classList.remove('d-none');
                    bannerTraguardo.innerHTML = " 20° ordine! Il Cliente diventerà <strong>GOLD</strong>!";
                    bannerTraguardo.className = "alert alert-warning border-warning mt-3 text-dark";
                    bannerTraguardo.classList.remove('d-none');
                }
            }
        }
        checkboxes.forEach(function(checkbox) {
            checkbox.addEventListener('change', ricalcolaTutto);
        });
        clienteSelect.addEventListener('change', ricalcolaTutto);
    });
</script>
<jsp:include page="../footer.jsp" />

</body>
</html>