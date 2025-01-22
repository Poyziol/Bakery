<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="model.*" %>
<%@page import="java.util.*" %>

<%
    List<Produit_fini> listProduit = (List<Produit_fini>)request.getAttribute("listProduit");
    List<Categorie> listCategorie = (List<Categorie>)request.getAttribute("listCategorie");
    List<Parfum> listParfum = (List<Parfum>)request.getAttribute("listParfum");
    List<Vente> listVente = (List<Vente>)request.getAttribute("listVente");
    List<Client> listClient = (List<Client>)request.getAttribute("listClient");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion de vente</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <%@include file="header.html" %>
    
    <!-- Contenu principal -->
    <div class="container my-5">
        <h1 class="text-center mb-4">Gestion de Vente</h1>

        <!-- Formulaire d'ajout ou de modification -->
        <div class="card p-4 shadow-sm mb-5 bg-light">
            <h2 class="h5 mb-3">Insertion de vente</h2>
            <form action="vente" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="dateVente" class="form-label">Date de vente: </label>
                        <input type="date" class="form-control" name="dateVente" id="dateVente">
                    </div>
                    <div class="col-md-6">
                        <label for="client" class="form-label">Client: </label>
                        <select class="form-select" name="idClient" id="client">
                            <% for(Client client : listClient) { %>
                            <option value="<%= client.getIdClient() %>"><%= client.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="produit" class="form-label">Produit: </label>
                        <select class="form-select" id="produit">
                            <% for(Produit_fini produit : listProduit) { %>
                            <option value="<%= produit.getIdProduit() %>"><%= produit.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="quantite" class="form-label">Quantite: </label>
                        <input type="number" step="0.01" class="form-control" id="quantite">
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

        <!-- Filtre des listes -->
        <div class="card p-4 shadow-sm mb-5 bg-light">
           <h3 class="h5 mb-3">Filtre des données</h3>
           <form action="vente?action=s" method="post">
                <div class="row g-3">
                    <div class="col-md-4">
                        <label for="categorie" class="form-label">Categorie: </label>
                        <select class="form-select" name="idCategorie" id="categorie">
                            <option value="0">Tous les categries</option>
                            <% for(Categorie categorie : listCategorie) { %>
                            <option value="<%= categorie.getIdCategorie() %>"><%= categorie.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="parfum" class="form-label">Parfum: </label>
                        <select class="form-select" name="idParfum" id="parfum">
                            <option value="0">Tous les parfums</option>
                            <% for(Parfum parfum : listParfum) { %>
                            <option value="<%= parfum.getIdParfum() %>"><%= parfum.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="dateVente" class="form-label">Date de Vente: </label>
                        <input type="date" class="form-control" name="dateVente" id="dateVente">
                    </div>
                    <div class="mt-4 d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </div>
                </div>
           </form>
        </div> 

        <!-- Tableau des données -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h2 class="h5 mb-3">Liste des ventes</h2>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Date de vente</th>
                            <th>Client</th>
                            <th>Produit</th>
                            <th>Categorie</th>
                            <th>Parfum</th>
                            <th>Quantite</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Vente vente : listVente) { %>
                        <tr>
                            <td><%= vente.getDateVente() %></td>
                            <td><%= vente.getClient().getNom() %></td>
                            <td><%= vente.getProduit().getNom() %></td>
                            <td><%= vente.getProduit().getCategorie().getNom() %></td>
                            <td><%= vente.getProduit().getParfum().getNom() %></td>
                            <td><%= vente.getQuantite() %></td>
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

            const selectProduit = document.getElementById("produit");
            const produit = selectProduit.value;
            const nameProduit = selectProduit.options[selectProduit.selectedIndex].text;
            const qteProduit = document.getElementById("quantite").value;

            // Champ input caché pour l'ID du produit
            const inputProduit = document.createElement("input");
            inputProduit.type = "hidden";
            inputProduit.name = "idProduit[]";
            inputProduit.value = produit;

            // Champ input caché pour la quantité du produit
            const inputQuantite = document.createElement("input");
            inputQuantite.type = "hidden";
            inputQuantite.name = "quantite[]";
            inputQuantite.value = qteProduit;

            // Colonne pour afficher le nom du produit
            const colProduit = document.createElement("div");
            colProduit.className = "col-md-6";
            const produitText = document.createElement("input");
            produitText.type = "text";
            produitText.className = "form-control text-center";
            produitText.value = nameProduit;
            produitText.readOnly = true;
            colProduit.appendChild(produitText);

            // Colonne pour afficher la quantité
            const colQuantite = document.createElement("div");
            colQuantite.className = "col-md-4";
            const quantiteText = document.createElement("input");
            quantiteText.type = "text";
            quantiteText.className = "form-control text-center";
            quantiteText.value = qteProduit;
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
            contain.appendChild(inputProduit);
            contain.appendChild(inputQuantite);
            contain.appendChild(colProduit);
            contain.appendChild(colQuantite);
            contain.appendChild(colButton);

            // Ajouter le conteneur à la zone des ingrédients
            container.appendChild(contain);

            // Réinitialiser les champs
            document.getElementById("produit").selectedIndex = 0;
            document.getElementById("qteProduit").value = "";
        }
    </script>

</body>

</html>
