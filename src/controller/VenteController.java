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
import model.Client;
import model.Connexion;
import model.Parfum;
import model.Produit_fini;
import model.Vente;

@WebServlet("/vente")
public class VenteController extends HttpServlet {
    private Connection co = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try(Connection co = Connexion.connection()) {
            List<Produit_fini> listProduit = Produit_fini.getAll(co);
            List<Categorie> listCategorie = Categorie.getAll(co);
            List<Parfum> listParfum = Parfum.getAll(co);
            List<Vente> listVente = null;
            List<Client> listClient = Client.getAll(co);

            if (request.getAttribute("idCategorie") != null) {
                int idCategorie = (int)request.getAttribute("idCategorie");
                int idParfum = (int)request.getAttribute("idParfum");
                Date dateVente = (Date)request.getAttribute("dateVente");
                listVente = Vente.getAllByCriteria(idCategorie, idParfum, dateVente, co);
            }
            else {
                listVente = Vente.getAll(co);
            }

            request.setAttribute("listProduit", listProduit);
            request.setAttribute("listCategorie", listCategorie);
            request.setAttribute("listParfum", listParfum);
            request.setAttribute("listVente", listVente);
            request.setAttribute("listClient", listClient);

            request.getRequestDispatcher("/vente.jsp").forward(request, response);
        } catch(Exception e) {
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
        String dateString = request.getParameter("dateVente");
        
        try(Connection co = Connexion.connection()) {

            if ( (request.getParameter("action")) != null && (request.getParameter("action").equals("s")) ) {
                int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
                int idParfum = Integer.parseInt(request.getParameter("idParfum"));
                Date dateVente = null;
                if (dateString != null && !dateString.isEmpty()) {
                    dateVente = Date.valueOf(dateString);
                }

                request.setAttribute("idCategorie", idCategorie);
                request.setAttribute("dateVente", dateVente);
                request.setAttribute("idParfum", idParfum);
            }
            else {
                Date dateVente = Date.valueOf(dateString);
                int idProduit = Integer.parseInt(request.getParameter("idProduit"));
                double quantite = Double.parseDouble(request.getParameter("quantite"));
                int idClient = Integer.parseInt(request.getParameter("idClient"));
                
                String message = Vente.insert(dateVente, idProduit, quantite, idClient, co);
                
                request.setAttribute("message", message);    
            } 
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des données");
        }
        finally {
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
