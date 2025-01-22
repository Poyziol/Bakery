<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil Boulangerie</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/index.css">
</head>
<body>
    <%@include file="header.html" %>

    <header class="hero-section text-center text-white">
        <div class="container">
            <h1 class="display-4">Bienvenue à la Boulangerie Artisanale</h1>
            <p class="lead">Explorez vos produits, gérez vos stocks et vos employés en toute simplicité.</p>
        </div>
    </header>

    <main class="container my-5">
        <div class="row">
            <div class="col-md-4 mb-3">
                <div class="card menu-card">
                    <div class="card-body text-center">
                        <i class="bi bi-basket3-fill icon"></i>
                        <h5 class="card-title">Gestion des Produits</h5>
                        <p class="card-text">Ajoutez, modifiez et consultez les produits disponibles.</p>
                        <a href="crud.html" class="btn btn-primary">Accéder</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card menu-card">
                    <div class="card-body text-center">
                        <i class="bi bi-people-fill icon"></i>
                        <h5 class="card-title">Gestion des Employés</h5>
                        <p class="card-text">Supervisez les employés et leurs fonctions.</p>
                        <a href="crud.html" class="btn btn-primary">Accéder</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card menu-card">
                    <div class="card-body text-center">
                        <i class="bi bi-boxes icon"></i>
                        <h5 class="card-title">Gestion des Stocks</h5>
                        <p class="card-text">Suivez les stocks d'ingrédients et de produits finis.</p>
                        <a href="crud.html" class="btn btn-primary">Accéder</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 mb-3">
                <div class="card menu-card">
                    <div class="card-body text-center">
                        <i class="bi bi-clipboard-data icon"></i>
                        <h5 class="card-title">Recettes</h5>
                        <p class="card-text">Créez et visualisez les recettes de vos produits.</p>
                        <a href="crud.html" class="btn btn-primary">Accéder</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card menu-card">
                    <div class="card-body text-center">
                        <i class="bi bi-truck icon"></i>
                        <h5 class="card-title">Fournisseurs</h5>
                        <p class="card-text">Gérez vos relations avec les fournisseurs.</p>
                        <a href="crud.html" class="btn btn-primary">Accéder</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card menu-card">
                    <div class="card-body text-center">
                        <i class="bi bi-gear-fill icon"></i>
                        <h5 class="card-title">Statistiques</h5>
                        <p class="card-text">Configurez les options du système.</p>
                        <a href="statistique.html" class="btn btn-primary">Accéder</a>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <%@include file="footer.html" %>

    <script src="assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
