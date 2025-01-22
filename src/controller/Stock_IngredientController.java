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
import model.Connexion;
import model.Ingredient;
import model.Stock_Ingredient;

@WebServlet("/stock_ingredient")
public class Stock_IngredientController extends HttpServlet {
    private Connection co = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection co = Connexion.connection()) {
            List<Stock_Ingredient> listStock = null;

            if ( (request.getAttribute("action")) != null && (request.getAttribute("action").equals("s")) ) {
                Date dateMouvement = (Date)request.getAttribute("dateMouvement");
                Boolean mouvement = (Boolean)request.getAttribute("mouvement");

                listStock = Stock_Ingredient.getAllByCriteria(dateMouvement, mouvement, co);
            }
            else {
                listStock = Stock_Ingredient.getAll(co);
            }

            List<Ingredient> listIngredient = Ingredient.getAll(co);

            request.setAttribute("listIngredient", listIngredient);
            request.setAttribute("listStock", listStock);

            request.getRequestDispatcher("/historique_ingredient.jsp").forward(request, response);
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
            String dateString = request.getParameter("dateMouvement");
            Date dateMouvement = null;
            if (dateString != null && !dateString.isEmpty()) {
                dateMouvement = Date.valueOf(dateString);
            }

            if ( (request.getParameter("action")) != null && (request.getParameter("action").equals("s")) ) {
                String action = request.getParameter("action");
                String mouvementString = request.getParameter("mouvement");
                Boolean mouvement = null;
                if (mouvementString != null && !mouvementString.isEmpty()) {
                    mouvement = Boolean.parseBoolean(mouvementString);
                }
                    
                request.setAttribute("action", action);
                request.setAttribute("dateMouvement", dateMouvement);
                request.setAttribute("mouvement", mouvement);
            }
            else {
                double prixUnitaire = Double.parseDouble(request.getParameter("prixUnitaire"));
                double quantite = Double.parseDouble(request.getParameter("quantite"));
                int idIngredient = Integer.parseInt(request.getParameter("idIngredient"));
                
                String message = "";
                message = Stock_Ingredient.insert(dateMouvement, true, prixUnitaire, quantite, idIngredient, co);
                
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
