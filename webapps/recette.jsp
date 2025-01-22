<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="model.*" %>
<%@page import="java.util.*" %>

<%
    List<Produit_fini> listProduit = (List<Produit_fini>)request.getAttribute("listProduit");
    List<Ingredient> listIngredient = (List<Ingredient>)request.getAttribute("listIngredient");
    List<Recette> listRecette = (List<Recette>)request.getAttribute("listRecette");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion de recette</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <%@include file="header.html" %>
    
    <!-- Contenu principal -->
    <div class="container my-5">
        <h1 class="text-center mb-4">Gestion de recette</h1>

        <!-- Formulaire d'ajout ou de modification -->
        <div class="card p-4 shadow-sm mb-5 bg-light">
            <h2 class="h5 mb-3">Insertion de recette</h2>
            <form action="recette" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="produit" class="form-label">Produit fini: </label>
                        <select class="form-select" name="idProduit" id="produit">
                            <% for(Produit_fini produit : listProduit) { %>
                            <option value="<%= produit.getIdProduit() %>"><%= produit.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="qteEstimee" class="form-label">Quantite produit: </label>
                        <input type="text" class="form-control" name="qteEstimee" id="qteEstime">
                    </div>
                </div>
                <div class="row g-3 align-items-center">
                    <div class="col-md-6">
                        <label for="ingredient" class="form-label">Ingredient: </label>
                        <select class="form-select" id="ingredient">
                            <% for(Ingredient ingredient : listIngredient) { %>
                            <option value="<%= ingredient.getIdIngredient() %>"><%= ingredient.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="qteIngredient" class="form-label">Quantite: </label>
                        <input type="number" step="0.01" class="form-control" id="qteIngredient">
                    </div>
                    <div class="col-md-2 d-flex align-items-center justify-content-end">
                        <button type="button" onclick="addIngredient()" class="btn btn-secondary">Ajouter</button>
                    </div>
                </div>
                <div id="dataHidden" class="row g-3">

                </div>
                <div class="mt-4 d-flex justify-content-end">
                    <button type="reset" class="btn btn-secondary me-3">Annuler</button>
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                </div>
            </form>
        </div>

        <!-- Tableau des données -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h2 class="h5 mb-3">Liste des recettes</h2>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th rowspan="2">Produit</th>
                            <th rowspan="2">Quantite produit</th>
                            <th colspan="2">Ingredients</th>
                        </tr>
                        <tr>
                            <th>Nom</th>
                            <th>Quantite</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Recette recette : listRecette) { %>
                        <tr>
                            <td><%= recette.getProduit().getNom() %></td>
                            <td><%= recette.getQteEstimee() %></td>
                            <td>
                                <% for(Map.Entry<Ingredient, Double> ingredient : recette.getIngredients().entrySet()) { %>
                                <p><%= ingredient.getKey().getNom() %></p>
                                <% } %>
                            </td>
                            <td>
                                <% for(Map.Entry<Ingredient, Double> ingredient : recette.getIngredients().entrySet()) { %>
                                <p><%= ingredient.getValue() %></p>
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
    <script>
        function addIngredient() {
            const container = document.getElementById("dataHidden");

            const contain = document.createElement("div");
            contain.className = "row g-3";

            const selectIngredient = document.getElementById("ingredient");
            const ingredient = selectIngredient.value;
            const nameIngredient = selectIngredient.options[selectIngredient.selectedIndex].text;
            const qteIngredient = document.getElementById("qteIngredient").value;

            // Champ input caché pour l'ID de l'ingrédient
            const inputIngredient = document.createElement("input");
            inputIngredient.type = "hidden";
            inputIngredient.name = "idIngredient[]";
            inputIngredient.value = ingredient;

            // Champ input caché pour la quantité de l'ingrédient
            const inputQuantite = document.createElement("input");
            inputQuantite.type = "hidden";
            inputQuantite.name = "qteIngredient[]";
            inputQuantite.value = qteIngredient;

            // Colonne pour afficher le nom de l'ingrédient
            const colIngredient = document.createElement("div");
            colIngredient.className = "col-md-6";
            const ingredientText = document.createElement("input");
            ingredientText.type = "text";
            ingredientText.className = "form-control text-center";
            ingredientText.value = nameIngredient;
            ingredientText.readOnly = true;
            colIngredient.appendChild(ingredientText);

            // Colonne pour afficher la quantité
            const colQuantite = document.createElement("div");
            colQuantite.className = "col-md-4";
            const quantiteText = document.createElement("input");
            quantiteText.type = "text";
            quantiteText.className = "form-control text-center";
            quantiteText.value = qteIngredient;
            quantiteText.readOnly = true;
            colQuantite.appendChild(quantiteText);

            // Colonne pour le bouton de suppression
            const colButton = document.createElement("div");
            colButton.className = "col-md-2 d-flex justify-content-end";
            const deleteButton = document.createElement("button");
            deleteButton.type = "button";
            deleteButton.textContent = "Supprimer";
            deleteButton.className = "btn btn-danger btn-sm";
            deleteButton.onclick = function () {
                contain.remove();
            };
            colButton.appendChild(deleteButton);

            // Ajouter tous les éléments au conteneur
            contain.appendChild(inputIngredient);
            contain.appendChild(inputQuantite);
            contain.appendChild(colIngredient);
            contain.appendChild(colQuantite);
            contain.appendChild(colButton);

            // Ajouter le conteneur à la zone des ingrédients
            container.appendChild(contain);

            // Réinitialiser les champs
            document.getElementById("ingredient").selectedIndex = 0;
            document.getElementById("qteIngredient").value = "";
        }
    </script>

</body>

</html>
