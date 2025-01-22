<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="model.*" %>
<%@page import="java.util.*" %>

<%
    String action = "";
    if (request.getParameter("action") != null) {
        action = request.getParameter("action");
    }
    Produit_fini produitU = (Produit_fini)request.getAttribute("produit"); 
    List<Categorie> listCategorie = (List<Categorie>)request.getAttribute("listCategorie");
    List<Produit_fini> listProduit = (List<Produit_fini>)request.getAttribute("listProduit");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion de produit</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <%@include file="header.html" %>
    
    <!-- Contenu principal -->
    <div class="container my-5">
        <h1 class="text-center mb-4">Gestion de produit</h1>

        <!-- Formulaire d'ajout ou de modification -->
        <div class="card p-4 shadow-sm mb-5 bg-light">
            <h2 class="h5 mb-3">Insertion de produit</h2>
            <form action="produit" method="post">
                <input type="hidden" name="action" value="<%= action %>">
                <input type="hidden" name="idProduit" value="<%= produitU.getIdProduit() %>">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="nom" class="form-label">Nom: </label>
                        <input type="text" class="form-control" name="nom" value="<%= produitU.getNom() %>" id="nom">
                    </div>
                    <div class="col-md-6">
                        <label for="description" class="form-label">Description: </label>
                        <input type="text" class="form-control" name="description" value="<%= produitU.getDescription() %>" id="description">
                    </div>
                </div>
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="categorie" class="form-label">Categorie: </label>
                        <select class="form-select" name="idCategorie" id="categorie">
                            <% for(Categorie categorie : listCategorie) { %>
                            <option value="<%= categorie.getIdCategorie() %>"><%= categorie.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="prixVente" class="form-label">Prix de vente: </label>
                        <input type="number" step="0.01" class="form-control" name="prixVente" value="<%= produitU.getPrixVente() %>" id="prixVente">
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
                <h2 class="h5 mb-3">Liste des produits</h2>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Description</th>
                            <th>Categorie</th>
                            <th>Prix de vente</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Produit_fini produit : listProduit) { %>
                        <tr>
                            <td><%= produit.getNom() %></td>
                            <td><%= produit.getDescription() %></td>
                            <td><%= produit.getCategorie().getNom() %></td>
                            <td><%= produit.getPrixVente() %></td>
                            <td>
                                <a href="produit?idProduit=<%= produit.getIdProduit() %>&action=u"><button class="btn btn-warning btn-sm">Modiffier</button></a>
                                <a href="produit?idProduit=<%= produit.getIdProduit() %>&action=d"><button class="btn btn-warning btn-sm">Supprimer</button></a>
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
