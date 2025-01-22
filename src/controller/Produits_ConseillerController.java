package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Connexion;
import model.Produit_fini;
import model.Produits_conseiller;

@WebServlet("/produits_conseiller")
public class Produits_ConseillerController extends HttpServlet  {
    private Connection co = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produits_conseiller> listProduitsConseiller = null;

        try(Connection co = Connexion.connection()) {
            if (request.getAttribute("idMois") != null) {
                int annee= (int)request.getAttribute("annee");
                int idMois = (int)request.getAttribute("idMois");
                listProduitsConseiller = Produits_conseiller.getAllByCriteria(annee, idMois, co);
            }
            else {
                listProduitsConseiller = Produits_conseiller.getAll(co);
            }
            List<Produit_fini> listProduit = Produit_fini.getAll(co);

            request.setAttribute("listProduit", listProduit);
            request.setAttribute("listProduitsConseiller", listProduitsConseiller);
            request.getRequestDispatcher("produits_conseiller.jsp").forward(request, response);
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
        int idMois = Integer.parseInt(request.getParameter("idMois"));
        int annee = Integer.parseInt(request.getParameter("annee"));

        try(Connection co = Connexion.connection()) {
            if ( (request.getParameter("action")) != null && (request.getParameter("action").equals("s")) ) {
                request.setAttribute("annee", annee);
                request.setAttribute("idMois", idMois);
            }
            else {
                String[] produitsString = request.getParameterValues("idProduit[]");
                List<Integer> listProduit = new ArrayList<>();
                for (String idProduit : produitsString) {
                    listProduit.add(Integer.parseInt(idProduit));
                }
                String message = "";
                message = Produits_conseiller.insert(annee, idMois, listProduit, co);
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

