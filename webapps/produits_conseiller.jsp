<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="model.*" %>
<%@page import="java.util.*" %>
<%@page import="java.time.Month" %>
<%@page import="java.time.format.TextStyle" %>
<%@page import="java.util.Locale" %>

<%
    List<Produit_fini> listProduit = (List<Produit_fini>)request.getAttribute("listProduit");
    List<Produits_conseiller> listProduitsConseiller = (List<Produits_conseiller>)request.getAttribute("listProduitsConseiller");
    Locale locale = Locale.FRENCH;
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Produits Conseillés</title>
    <link href="assets/css/bootstrap.min.css"  rel="stylesheet">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <%@include file="header.html" %>
    
    <!-- Contenu principal -->
    <div class="container my-5">
        <h1 class="text-center mb-4">Gestion des produits conseillés</h1>

        <!-- Formulaire d'ajout ou de modification -->
        <div class="card p-4 shadow-sm mb-5 bg-light">
            <h2 class="h5 mb-3">Insertion de Produits conseillés</h2>
            <form action="produits_conseiller" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="idMois" class="form-label">Mois: </label>
                        <select class="form-select" id="idMois" name="idMois">
                            <% for(Month mois : Month.values()) { 
                            String nomMois = mois.getDisplayName(TextStyle.FULL, locale);  %>
                            <option value="<%= mois.getValue() %>"><%= nomMois %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="annee" class="form-label">Annee: </label>
                        <select class="form-select" id="annee" name="annee">
                            <% for(int annee = 2000; annee <= 2100; annee++) { %> 
                            <option value="<%= annee %>"><%= annee %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="row g-3 align-items-center">
                    <div class="col-md-6">
                        <label for="produit" class="form-label">Produit fini: </label>
                        <select class="form-select" name="idProduit" id="produit">
                            <% for(Produit_fini produit : listProduit) { %>
                            <option value="<%= produit.getIdProduit() %>"><%= produit.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-2 d-flex align-items-center justify-content-end">
                        <button type="button" onclick="addProduit()" class="btn btn-secondary">Ajouter</button>
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

        <!-- Filtre des données -->
        <div class="card p-4 shadow-sm mb-5 bg-light">
            <h2 class="h5 mb-3">Filtre des données</h2>
            <form action="produits_conseiller?action=s" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="annee" class="form-label">Annee: </label>
                        <select class="form-select" id="annee" name="annee">
                            <option value="0">Tout les années</option>
                            <% for(int annee = 2000; annee <= 2100; annee++) { %>
                            <option value="<%= annee %>"><%= annee %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="idMois" class="form-label">Mois: </label>
                        <select class="form-select" name="idMois" id="idMois">
                            <option value="0">Tous les mois</option>
                            <% for(Month mois : Month.values()) { 
                            String nomMois = mois.getDisplayName(TextStyle.FULL, locale);  %>
                            <option value="<%= mois.getValue() %>"><%= nomMois %></option>
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
                <h2 class="h5 mb-3">Liste des produits conseillés</h2>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Annee</th>
                            <th>Mois</th>
                            <th>Produits</th>
                            <th>Descriptions</th>
                            <th>Categories</th>
                            <th>Parfums</th>
                            <th>Prix de ventes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Produits_conseiller produits_conseiller : listProduitsConseiller) {
                        String nomMois = produits_conseiller.getMonth().getDisplayName(TextStyle.FULL, locale);
                        int rowMonth = (produits_conseiller.getProduits().size() + 1);
                        int rowYear = rowMonth;
                        %>
                        <tr>
                            <td rowspan="<%= rowYear %>"><%= produits_conseiller.getAnnee() %></td>
                            <td rowspan="<%= rowMonth %>"><%= nomMois %></td>
                        </tr>
                        <% for(Produit_fini produit : produits_conseiller.getProduits()) { %>
                        <tr>
                            <td><%= produit.getNom() %></td>
                            <td><%= produit.getDescription() %></td>
                            <td><%= produit.getCategorie().getNom() %></td>
                            <td><%= produit.getParfum().getNom() %></td>
                            <td><%= produit.getPrixVente() %></td>
                        </tr>
                        <% } %>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <%@include file="footer.html" %>

    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script>
        function addProduit() {
            const container = document.getElementById("dataHidden");

            const contain = document.createElement("div");
            contain.className = "row g-3";

            const selectProduit = document.getElementById("produit");
            const produit = selectProduit.value;
            const nameProduit = selectProduit.options[selectProduit.selectedIndex].text;

            // Champ input caché pour l'ID du produit
            const inputProduit = document.createElement("input");
            inputProduit.type = "hidden";
            inputProduit.name = "idProduit[]";
            inputProduit.value = produit;

            // Colonne pour afficher le nom du produit
            const colProduit = document.createElement("div");
            colProduit.className = "col-md-6";
            const produitText = document.createElement("input");
            produitText.type = "text";
            produitText.className = "form-control text-center";
            produitText.value = nameProduit;
            produitText.readOnly = true;
            colProduit.appendChild(produitText);

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
            contain.appendChild(inputProduit);
            contain.appendChild(colProduit);
            contain.appendChild(colButton);

            // Ajouter le conteneur à la zone des produits
            container.appendChild(contain);

            // Réinitialiser les champs
            document.getElementById("produit").selectedIndex = 0;
        }
    </script>

</body>

</html>
