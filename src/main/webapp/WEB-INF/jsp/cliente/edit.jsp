<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp" />

    <style>
        .error_field {
            color: red;
        }
    </style>
    <title>Modifica Elemento</title>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp" />

<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <%-- se l'attributo in request ha errori --%>
        <spring:hasBindErrors  name="edit_cliente_attr">
            <%-- alert errori --%>
            <div class="alert alert-danger " role="alert">
                Attenzione!! Sono presenti errori di validazione
            </div>
        </spring:hasBindErrors>

        <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
        </div>

        <div class='card'>
            <div class='card-header'>
                <h5>Modifica elemento</h5>
            </div>
            <div class='card-body'>

                <h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

                <form:form modelAttribute="edit_cliente_attr"  method="post" action="${pageContext.request.contextPath }/cliente/update" novalidate="novalidate" class="row g-3">
                    <form:hidden path="id"/>

                    <div class="col-md-6">
                        <label for="nome" class="form-label">Nome <span class="text-danger">*</span></label>
                        <spring:bind path="nome">
                            <input type="text" name="nome" id="nome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il nome" value="${edit_cliente_attr.nome }" required>
                        </spring:bind>
                        <form:errors  path="nome" cssClass="error_field" />
                    </div>

                    <div class="col-md-6">
                        <label for="cognome" class="form-label">Cognome <span class="text-danger">*</span></label>
                        <spring:bind path="cognome">
                            <input type="text" name="cognome" id="cognome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il cognome" value="${edit_cliente_attr.cognome }" required>
                        </spring:bind>
                        <form:errors  path="cognome" cssClass="error_field" />
                    </div>

                    <div class="col-md-6">
                        <label for="indirizzo" class="form-label">Indirizzo <span class="text-danger">*</span></label>
                        <spring:bind path="indirizzo">
                            <input type="text" class="form-control ${status.error ? 'is-invalid' : ''}" name="indirizzo" id="indirizzo" placeholder="Inserire l'indirizzo" value="${edit_cliente_attr.indirizzo}" required>
                        </spring:bind>
                        <form:errors  path="indirizzo" cssClass="error_field" />
                    </div>

                    <div class="col-md-6">
                        <label for="attivo" class="form-label">Stato Cliente</label>
                        <spring:bind path="attivo">
                            <select class="form-select ${status.error ? 'is-invalid' : ''}" name="attivo" required>
                                <option value="true" ${status.value == true ? 'selected' : ''}>Attivo</option>
                                <option value="false" ${status.value == false ? 'selected' : ''}>Disattivato</option>
                            </select>
                        </spring:bind>
                    </div>

                    <div class="col-12">
                        <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
                    </div>

                </form:form>

                <!-- end card-body -->
            </div>
            <!-- end card -->
        </div>

        <!-- end container -->
    </div>
</main>
<jsp:include page="../footer.jsp" />

</body>
</html>