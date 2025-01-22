package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categorie;
import model.Connexion;
import model.Produit_fini;

@WebServlet("/produit")
public class ProduitController extends HttpServlet {
    private Connection co = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection co = Connexion.connection()) {
            Produit_fini produit = new Produit_fini();
            List<Categorie> listCategorie = Categorie.getAll(co);
            if(request.getParameter("action") != null) {
                String action = request.getParameter("action");
                int idProduit = Integer.parseInt(request.getParameter("idProduit"));
                if(action.equals("d")) {
                    String message = Produit_fini.delete(idProduit, co);

                    request.setAttribute("message", message);
                }
                else if(action.equals("u")) {
                    produit = Produit_fini.getById(idProduit, co);
                    request.setAttribute("action", action);
                }
            }
            List<Produit_fini> listProduit = Produit_fini.getAll(co);

            request.setAttribute("produit", produit);
            request.setAttribute("listCategorie", listCategorie);
            request.setAttribute("listProduit", listProduit);
            request.getRequestDispatcher("/produit.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des données");
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        double prixVente = Double.parseDouble(request.getParameter("prixVente"));
        String description = request.getParameter("description");
        int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
        int idParfum = Integer.parseInt(request.getParameter("idParfum"));
        String message = "";
        try(Connection co = Connexion.connection()) {
            if ( (request.getParameter("action")) != null && (request.getParameter("action").equals("u")) ) {
                int idProduit = Integer.parseInt(request.getParameter("idProduit"));
                message = Produit_fini.update(idProduit, nom, prixVente, description, idCategorie, idParfum, co);
            }
            else {
                message = Produit_fini.insert(nom, prixVente, description, idCategorie, idParfum, co);
            }
            request.setAttribute("message", message);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'insertion");
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            doGet(request, response);
        }
    }
}
