package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    int idClient;
    String nom;
    String email;
    int numero;
    String adresse;
    
    public Client() {
    }

    public Client(int idClient, String nom, String email, int numero, String adresse) {
        this.idClient = idClient;
        this.nom = nom;
        this.email = email;
        this.numero = numero;
        this.adresse = adresse;
    }

    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public static String insert(String nom, String email, int numero, String adresse, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "insert into Client(nom, email, numero, adresse) values (?, ?, ?, ?)";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setString(2, email);
            prepStat.setInt(3, numero);
            prepStat.setString(4, adresse);
            int result = prepStat.executeUpdate();
            if(result > 0) {
                message = "Insertion reussi";
            }
            else {
                message = "Insertion echoue";
            }
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(prepStat != null) {
                prepStat.close();
            }
        }
        return message;
    }
    public static List<Client> getAll(Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Client> listClient = new ArrayList<>();
        Client client = null;
        String requete = "select * from Client";
        
        try {
            if (connect == null) {
                connect = Connexion.connection(

                
                );
            }
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                client = new Client();

                client.setIdClient(result.getInt("idClient"));
                client.setNom(result.getString("nom"));
                client.setEmail(result.getString("email"));
                client.setNumero(result.getInt("numero"));
                client.setAdresse(result.getString("adresse"));

                listClient.add(client);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(result != null) {
                result.close();
            }
            if(prepStat != null) {
                prepStat.close();
            }
        }

        return listClient;
    }

    public static Client getById(int idClient, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Client client = new Client();
        String requete = "select * from Client where idClient = ?";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idClient);
            result = prepStat.executeQuery();
            if(result.next()) {
                client.setIdClient(result.getInt("idClient"));
                client.setNom(result.getString("nom"));
                client.setEmail(result.getString("email"));
                client.setNumero(result.getInt("numero"));
                client.setAdresse(result.getString("adresse"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(result != null) {
                result.close();
            }
            if(prepStat != null) {
                prepStat.close();
            }
        }

        return client;
    }

}
