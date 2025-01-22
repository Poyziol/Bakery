<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="model.*" %>
<%@page import="java.util.*" %>

<%
    List<Ingredient> listIngredient = (List<Ingredient>)request.getAttribute("listIngredient");
    List<Stock_Ingredient> listStock = (List<Stock_Ingredient>)request.getAttribute("listStock");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historique des ingredients</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <%@include file="header.html" %>
    
    <!-- Contenu principal -->
    <div class="container my-5">
        <h1 class="text-center mb-4">Historique des ingredients</h1>

        <!-- Formulaire d'ajout ou de modification -->
        <div class="card p-4 shadow-sm mb-5 bg-light">
            <h2 class="h5 mb-3">Achat d'ingredient</h2>
            <form action="stock_ingredient" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="dateMouvement" class="form-label">Date: </label>
                        <input type="date" class="form-control" name="dateMouvement" id="dateMouvement">
                    </div>
                    <div class="col-md-6">
                        <label for="ingredient" class="form-label">Ingredient: </label>
                        <select class="form-select" name="idIngredient" id="ingredient">
                            <% for(Ingredient ingredient : listIngredient) { %>
                            <option value="<%= ingredient.getIdIngredient() %>"><%= ingredient.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="prixUnitaire" class="form-label">Prix Unitaire: </label>
                        <input type="number" step="0.01" class="form-control" name="prixUnitaire" id="prixUnitaire">
                    </div>
                    <div class="col-md-6">
                        <label for="quantite" class="form-label">Quantite: </label>
                        <input type="number" step="0.01" class="form-control" name="quantite" id="quantite">
                    </div>
                </div>
                <div class="mt-4 d-flex justify-content-end">
                    <button type="reset" class="btn btn-secondary me-3">Annuler</button>
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                </div>
            </form>
        </div>

        <!-- Filtre des données -->
        <div class="card p-4 shadow-sm mb-5 bg-light">
            <h2 class="h5 mb-3">Filtre des données</h2>
            <form action="stock_ingredient?action=s" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="dateMouvement" class="form-label">Date de mouvement: </label>
                        <input type="date" class="form-control" name="dateMouvement" id="dateMouvement">
                    </div>
                    <div class="col-md-6">
                        <label for="mouvement" class="form-label">Mouvement: </label>
                        <select class="form-select" name="mouvement" id="mouvement">
                            <option value="">Tous</option>
                            <option value="true">Achat</option>
                            <option value="false">Fabrication</option>
                        </select>
                    </div>
                </div>
                <div class="mt-4 d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary">Rechercher</button>
                </div>
            </form>
        </div>

        <!-- Tableau des données -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h2 class="h5 mb-3">Mouvement de stock</h2>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Date de mouvement</th>
                            <th>Ingredient</th>
                            <th>Prix Unitaire</th>
                            <th>Unite</th>
                            <th>Quantite</th>
                            <th>Mouvement</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Stock_Ingredient stock : listStock) { %>
                        <tr>
                            <td><%= stock.getDateMouvement() %></td>
                            <td><%= stock.getIngredient().getNom() %></td>
                            <td><%= stock.getPrixUnitaire() %></td>
                            <td><%= stock.getIngredient().getUnite().getNom() %></td>
                            <td><%= stock.getQuantite() %></td>
                            <td><%= stock.getMouvement() %></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <%@include file="footer.html" %>

    <script src="assets/js/bootstrap.bundle.min.js"></script>

</body>

</html>
