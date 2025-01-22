package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Connexion;
import model.Ingredient;
import model.Produit_fini;
import model.Recette;

@WebServlet("/recette")
public class RecetteController extends HttpServlet {
    private Connection co = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection co = Connexion.connection()) {
            List<Produit_fini> listProduit = Produit_fini.getAll(co);
            List<Ingredient> listIngredient = Ingredient.getAll(co);
            List<Recette> listRecette = Recette.getAll(co);

            request.setAttribute("listProduit", listProduit);
            request.setAttribute("listIngredient", listIngredient);
            request.setAttribute("listRecette", listRecette);
            request.getRequestDispatcher("/recette.jsp").forward(request, response);
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
        int idProduit = Integer.parseInt(request.getParameter("idProduit"));
        double qteEstimee = Double.parseDouble(request.getParameter("qteEstimee"));
        String[] idIngredients = request.getParameterValues("idIngredient[]");
        String[] qteIngredients = request.getParameterValues("qteIngredient[]");
        Map<Integer, Double> ingredients = new HashMap<>();
        for (int i = 0; i < qteIngredients.length; i++) {
            int idIngredient = Integer.parseInt(idIngredients[i]);
            double qteIngredient = Double.parseDouble(qteIngredients[i]);
            ingredients.put(idIngredient, qteIngredient);
        }
        
        String message = "";
        try(Connection co = Connexion.connection()) {
            message = Recette.insert(idProduit, qteEstimee, ingredients, co);
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
