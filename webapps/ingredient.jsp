<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="model.*" %>
<%@page import="java.util.*" %>

<%
    String action = "";
    if (request.getParameter("action") != null) {
        action = request.getParameter("action");
    }
    Ingredient ingredientU = (Ingredient)request.getAttribute("ingredient"); 
    List<Unite> listUnite = (List<Unite>)request.getAttribute("listUnite");
    Map<Ingredient, Double> listIngredient = (Map<Ingredient, Double>)request.getAttribute("listIngredient");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des ingredients</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <%@include file="header.html" %>
    
    <!-- Contenu principal -->
    <div class="container my-5">
        <h1 class="text-center mb-4">Gestion des ingredients</h1>

        <!-- Formulaire d'ajout ou de modification -->
        <div class="card p-4 shadow-sm mb-5 bg-light">
            <h2 class="h5 mb-3">Insertion d'ingredient</h2>
            <form action="ingredient" method="post">
                <input type="hidden" name="action" value="<%= action %>">
                <input type="hidden" name="idIngredient" value="<%= ingredientU.getIdIngredient() %>">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="ingredientName" class="form-label">Nom de l'ingredient: </label>
                        <input type="text" class="form-control" name="nom" id="ingredientName" value="<%= ingredientU.getNom() %>">
                    </div>
                    <div class="col-md-6">
                        <label for="ingredientUnite" class="form-label">Unite</label>
                        <select class="form-select" name="idUnite" id="ingredientUnite">
                            <% for(Unite unite : listUnite) { %>
                            <option value="<%= unite.getIdUnite() %>"><%= unite.getNom() %></option>
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

        <!-- Tableau des données -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h2 class="h5 mb-3">Liste des ingredients</h2>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Unite</th>
                            <th>Stock</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Map.Entry<Ingredient, Double> ingredientMap : listIngredient.entrySet()) {
                            Ingredient ingredient = ingredientMap.getKey();
                            double stock = ingredientMap.getValue();
                        %>
                        <tr>
                            <td><%= ingredient.getNom() %></td>
                            <td><%= ingredient.getUnite().getNom() %></td>
                            <td><%= stock %></td>
                            <td>
                                <a href="ingredient?idIngredient=<%= ingredient.getIdIngredient() %>&action=u"><button class="btn btn-warning btn-sm">Modiffier</button></a>
                                <a href="ingredient?idIngredient=<%= ingredient.getIdIngredient() %>&action=d"><button class="btn btn-warning btn-sm">Supprimer</button></a>
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
