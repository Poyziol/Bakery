package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Vente {
    int idVente;
    Date dateVente;
    Produit_fini produit;
    double quantite;
    Client client;

    public Vente() {
    }

    public Vente(int idVente, Date dateVente, Produit_fini produit, double quantite, Client client) {
        this.idVente = idVente;
        this.dateVente = dateVente;
        this.produit = produit;
        this.quantite = quantite;
        this.client = client;
    }

    public int getIdVente() {
        return idVente;
    }
    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }
    public Date getDateVente() {
        return dateVente;
    }
    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }
    public Produit_fini getProduit() {
        return produit;
    }
    public void setProduit(Produit_fini produit) {
        this.produit = produit;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public static String insert(Date dateVente, int idProduit, double quantite,int idclient, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "insert into Vente (dateVente, idProduit, quantite, idClient) values (?, ?, ?, ?)";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setDate(1, dateVente);
            prepStat.setInt(2, idProduit);
            prepStat.setDouble(3, quantite);
            prepStat.setInt(4, idclient);
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

    public static List<Vente> getAll(Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Vente> listVente = new ArrayList<>();
        Vente vente = null;
        String requete = "select * from Vente";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                vente = new Vente();

                vente.setIdVente(result.getInt("idVente"));
                vente.setDateVente(result.getDate("dateVente"));
                vente.setProduit(Produit_fini.getById(result.getInt("idProduit"), connect));
                vente.setQuantite(result.getDouble("quantite"));
                vente.setClient(Client.getById(result.getInt("idClient"), connect));

                listVente.add(vente);
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

        return listVente;
    }

    public static List<Vente> getAllByCriteria(int idCategorie, int idParfum, Date dateVente, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Vente> listVente = new ArrayList<>();
        Vente vente = null;
        String requete = "select * from getVentesBy_Criteria(?, ?, ?)";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idCategorie);
            prepStat.setInt(2, idParfum);
            prepStat.setDate(3, dateVente);
            result = prepStat.executeQuery();
            while(result.next()) {
                vente = new Vente();

                vente.setIdVente(result.getInt("idVente"));
                vente.setDateVente(result.getDate("dateVente"));
                vente.setProduit(Produit_fini.getById(result.getInt("idProduit"), connect));
                vente.setQuantite(result.getDouble("quantite"));
                vente.setClient(Client.getById(result.getInt("idClient"), connect));

                listVente.add(vente);
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

        return listVente;
    }

    public static Vente getById(int idVente, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Vente vente = new Vente();
        String requete = "select * from Vente where idVente = ?";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idVente);
            result = prepStat.executeQuery();
            if(result.next()) {     
                vente.setIdVente(result.getInt("idVente"));
                vente.setDateVente(result.getDate("dateVente"));
                vente.setProduit(Produit_fini.getById(result.getInt("idProduit"), connect));
                vente.setQuantite(result.getDouble("quantite"));
                vente.setClient(Client.getById(result.getInt("idClient"), connect));
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

        return vente;
    }

}
