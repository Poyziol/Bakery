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
import model.Client;
import model.Connexion;

@WebServlet("/client")
public class ClientController extends HttpServlet {
    private Connection co = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection co = Connexion.connection()) {
            List<Client> listClient = Client.getAll(co);

            request.setAttribute("listClient", listClient);
            request.getRequestDispatcher("/client.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String adresse = request.getParameter("adresse");
        String message = "";
        try(Connection co = Connexion.connection()) {
            message = Client.insert(nom, email, numero, adresse, co);
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
