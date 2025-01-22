package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categorie;
import model.Connexion;
import model.Fabrication;
import model.Ingredient;
import model.Recette;

@WebServlet("/fabrication")
public class FabricationController extends HttpServlet {
    private Connection co = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection co = Connexion.connection()) {
            List<Fabrication> listFabrication = null;

            if (request.getAttribute("idCategorie") != null) {
                int idCategorie = (int)request.getAttribute("idCategorie");
                int idIngredient = (int)request.getAttribute("idIngredient");
                listFabrication = Fabrication.getAllByCriteria(idCategorie, idIngredient, co);
            }
            else {
                listFabrication = Fabrication.getAll(co);
            }

            List<Recette> listRecette = Recette.getAll(co);
            List<Categorie> listCategorie = Categorie.getAll(co);
            List<Ingredient> listIngredient = Ingredient.getAll(co);

            request.setAttribute("listRecette", listRecette);
            request.setAttribute("listFabrication", listFabrication);
            request.setAttribute("listCategorie", listCategorie);
            request.setAttribute("listIngredient", listIngredient);
            request.getRequestDispatcher("/fabrication.jsp").forward(request, response);
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
        try(Connection co = Connexion.connection()) {
            
            if ( (request.getParameter("action")) != null && (request.getParameter("action").equals("s")) ) {
                int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
                int idIngredient = Integer.parseInt(request.getParameter("idIngredient"));
                
                request.setAttribute("idCategorie", idCategorie);
                request.setAttribute("idIngredient", idIngredient);
            }
            else {
                String dateString = request.getParameter("dateFabrication");
                int idRecette = Integer.parseInt(request.getParameter("idRecette"));
                Date dateFabrication = Date.valueOf(dateString);
                
                String message = Fabrication.insert(dateFabrication, idRecette, 0.0, co);
                
                request.setAttribute("message", message);    
            }
        
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
