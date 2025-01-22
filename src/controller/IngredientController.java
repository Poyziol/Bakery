package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Connexion;
import model.Ingredient;
import model.Unite;

@WebServlet("/ingredient")
public class IngredientController extends HttpServlet {
    private Connection co = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection co = Connexion.connection()) {

            Ingredient ingredient = new Ingredient();
            List<Unite> listUnite = Unite.getAll(co);
            if(request.getParameter("action") != null) {
                String action = request.getParameter("action");
                int idIngredient = Integer.parseInt(request.getParameter("idIngredient"));
                if(action.equals("d")) {
                    String message = Ingredient.delete(idIngredient, co);

                    request.setAttribute("message", message);
                }
                else if(action.equals("u")) {
                    ingredient = Ingredient.getById(idIngredient, co);
                    request.setAttribute("action", action);
                }
            }
            Map<Ingredient, Double> listIngredient = Ingredient.getAllIngredient_Stock(co);

            request.setAttribute("ingredient", ingredient);
            request.setAttribute("listUnite", listUnite);
            request.setAttribute("listIngredient", listIngredient);
            request.getRequestDispatcher("/ingredient.jsp").forward(request, response);
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
        int idUnite = Integer.parseInt(request.getParameter("idUnite"));
        String message = "";
        try(Connection co = Connexion.connection()) {
            if ( (request.getParameter("action")) != null && (request.getParameter("action").equals("u")) ) {
                int idIngredient = Integer.parseInt(request.getParameter("idIngredient"));
                message = Ingredient.update(idIngredient, nom, idUnite, co);
            }
            else {
                message = Ingredient.insert(nom, idUnite, co);
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
