<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="model.*" %>
<%@page import="java.util.*" %>

<%
    List<Recette> listRecette = (List<Recette>)request.getAttribute("listRecette");
    List<Categorie> listCategorie = (List<Categorie>)request.getAttribute("listCategorie");
    List<Ingredient> listIngredient = (List<Ingredient>)request.getAttribute("listIngredient");
    List<Fabrication> listFabrication = (List<Fabrication>)request.getAttribute("listFabrication");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion de fabrication</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <%@include file="header.html" %>
    
    <!-- Contenu principal -->
    <div class="container my-5">
        <h1 class="text-center mb-4">Gestion de Fabrication</h1>

        <!-- Formulaire d'ajout ou de modification -->
        <div class="card p-4 shadow-sm mb-5 bg-light">
            <h2 class="h5 mb-3">Insertion d'une fabrication</h2>
            <form action="fabrication" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="dateFabrication" class="form-label">Date de fabrication: </label>
                        <input type="date" class="form-control" name="dateFabrication" id="dateFabrication">
                    </div>
                    <div class="col-md-6">
                        <label for="recette" class="form-label">Recette: </label>
                        <select class="form-select" name="idRecette" id="recette">
                            <% for(Recette recette : listRecette) { %>
                            <option value="<%= recette.getIdRecette() %>"><%= recette.getProduit().getNom() %>(quantite: <%= recette.getQteEstimee() %>)</option>
                            <% } %>
                        </select>
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
            <form action="fabrication?action=s" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="categorie" class="form-label">Categorie: </label>
                        <select class="form-select" name="idCategorie" id="categorie">
                            <option value="0">Tous les categories</option>
                            <% for(Categorie categorie : listCategorie) { %>
                            <option value="<%= categorie.getIdCategorie() %>"><%= categorie.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="ingredient" class="form-label">Ingredient: </label>
                        <select class="form-select" name="idIngredient" id="ingredient">
                            <option value="0">Tous les ingredients</option>
                            <% for(Ingredient ingredient : listIngredient) { %>
                            <option value="<%= ingredient.getIdIngredient() %>"><%= ingredient.getNom() %></option>
                            <% } %>
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
                <h2 class="h5 mb-3">Liste des fabrications</h2>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Date de fabrication</th>
                            <th>Categorie</th>
                            <th>Produit</th>
                            <th>Ingredients</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Fabrication fabrication : listFabrication) { %>
                        <tr>
                            <td><%= fabrication.getDateFabrication() %></td>
                            <td><%= fabrication.getRecette().getProduit().getCategorie().getNom() %></td>
                            <td><%= fabrication.getRecette().getProduit().getNom() %></td>
                            <td>
                                <% for(Map.Entry<Ingredient, Double> ingredient : fabrication.getRecette().getIngredients().entrySet()) { %>
                                <p><%= ingredient.getKey().getNom() %></p>
                                <% } %>
                            </td>
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
