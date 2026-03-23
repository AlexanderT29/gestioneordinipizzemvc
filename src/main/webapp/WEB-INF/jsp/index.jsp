<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
    <jsp:include page="./header.jsp" />
    <link href="${pageContext.request.contextPath}/assets/css/features.css" rel="stylesheet">

    <title>Gestione Ordini Pizza</title>

    <style>
        .carousel-item img{
            height: 450px;
            object-fit: contain;
        }
    </style>
</head>
<body class="d-flex flex-column h-100">

<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="people-circle" viewBox="0 0 16 16">
        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
    </symbol>
    <symbol id="collection" viewBox="0 0 16 16">
        <path d="M2.5 3.5a.5.5 0 0 1 0-1h11a.5.5 0 0 1 0 1h-11zm2-2a.5.5 0 0 1 0-1h7a.5.5 0 0 1 0 1h-7zM0 13a1.5 1.5 0 0 0 1.5 1.5h13A1.5 1.5 0 0 0 16 13V6a1.5 1.5 0 0 0-1.5-1.5h-13A1.5 1.5 0 0 0 0 6v7zm1.5.5A.5.5 0 0 1 1 13V6a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5h-13z"/>
    </symbol>
    <symbol id="toggles2" viewBox="0 0 16 16">
        <path d="M9.465 10H12a2 2 0 1 1 0 4H9.465c.34-.588.535-1.271.535-2 0-.729-.195-1.412-.535-2z"/>
        <path d="M6 15a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm0 1a4 4 0 1 1 0-8 4 4 0 0 1 0 8zm.535-10a3.975 3.975 0 0 1-.409-1H4a1 1 0 0 1 0-2h2.126c.091-.355.23-.69.41-1H4a2 2 0 1 0 0 4h2.535z"/>
        <path d="M14 4a4 4 0 1 1-8 0 4 4 0 0 1 8 0z"/>
    </symbol>
    <symbol id="chevron-right" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
    </symbol>
</svg>
<jsp:include page="./navbar.jsp"></jsp:include>

<main class="flex-shrink-0">
    <div class="container">

        <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
        </div>

        <div class="p-5 mb-4 bg-light rounded-3">

            <div class="container-fluid py-5" style="background: orange">

                <h1 class="display-5 fw-bold">Gestione Ordini Pizzeria</h1>
                <p class="col-md-8 fs-4">Pannello di controllo per la gestione del menù, anagrafica clienti e storico ordini.</p>
                <a class="btn btn-primary btn-lg" style="background: darkgreen" href="${pageContext.request.contextPath}/ordine">Vai agli Ordini</a>
            </div>
        </div>

    </div>

    <div id="carouselExampleSlideOnly" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">

        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://t4.ftcdn.net/jpg/04/44/86/71/360_F_444867155_rhsOresSJWWz85dJ7x65fuxH7zyJOMlR.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Pizza napoletana originale</h5>
                    <p>La pizza è fatta con gli ingredienti della terra.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="https://cdn.thefork.com/tf-lab/image/upload/restaurant/67a2b2a5-9ce5-4628-a15e-cbc8d8c9ecea/e27ea305-dcbc-40b3-a9d8-94f2fc8c062f.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Ingredienti Freschi</h5>
                    <p>J eni tuttu frisci appena cucghiutu.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="https://www.molinosquillario.it/wp-content/uploads/2023/01/pizza-napoletana-forno-1024x683.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Mancia, arricriati ma un ti squadari..</h5>
                    <p>Cogliti la ligna pi fari u focu o ti manci'i petri..</p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <div class="container px-4 py-5" id="featured-3">
        <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">

            <div class="feature col" >
                <div class="feature-icon bg-gradient"  style = "background: red">
                    <svg class="bi" width="1em" height="1em"><use xlink:href="#collection"/></svg>
                </div>
                <h2>Catalogo Pizze</h2>
                <p>Visualizza il menù, aggiungi nuove pizze, aggiorna i prezzi base e gestisci gli ingredienti.</p>
                <a href="${pageContext.request.contextPath}/pizza/search" class="icon-link">
                    Vai alla funzionalità
                    <svg class="bi" width="1em" height="1em"><use xlink:href="#chevron-right"/></svg>
                </a>
            </div>

            <div class="feature col">
                <div class="feature-icon bg-gradient"  style = "background: orangered">
                    <svg class="bi" width="1em" height="1em"><use xlink:href="#people-circle"/></svg>
                </div>
                <h2>Anagrafica Clienti</h2>
                <p>Ricerca i clienti registrati, inseriscine di nuovi a sistema e visualizza il loro storico.</p>
                <a href="${pageContext.request.contextPath}/cliente/search" class="icon-link">
                    Vai alla funzionalità
                    <svg class="bi" width="1em" height="1em"><use xlink:href="#chevron-right"/></svg>
                </a>
            </div>

            <div class="feature col">
                <div class="feature-icon bg-gradient" style = "background: orange">
                    <svg class="bi" width="1em" height="1em"><use xlink:href="#toggles2"/></svg>
                </div>
                <h2>Nuovo Ordine</h2>
                <p>Crea un nuovo ordine associandolo a un cliente e aggiungi le pizze desiderate.</p>
                <a href="${pageContext.request.contextPath}/ordine/insert" class="icon-link">
                    Vai alla funzionalità
                    <svg class="bi" width="1em" height="1em"><use xlink:href="#chevron-right"/></svg>
                </a>
            </div>

        </div>

        <div class="row g-5 py-5 row-cols-1 row-cols-lg-3">
            <div class="feature col" >
                <div class="feature-icon bg-gradient"  style = "background: red">
                    <svg class="bi" width="1em" height="1em"><use xlink:href="#collection"/></svg>
                </div>
                <h2>Cerca Ordini tra due Date</h2>
                <p>Cerca i tuoi ordini tra due date in modo veloce!</p>
                <a href="${pageContext.request.contextPath}/ordine/searchdate" class="icon-link">
                    Vai alla funzionalità
                    <svg class="bi" width="1em" height="1em"><use xlink:href="#chevron-right"/></svg>
                </a>
            </div>
        </div>
    </div>

</main>

<jsp:include page="./footer.jsp" />
</body>
</html>