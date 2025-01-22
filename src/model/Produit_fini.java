package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produit_fini {
    int idProduit;
    String nom;
    double prixVente;
    String description;
    Categorie categorie;
    Parfum parfum;

    public Produit_fini() {
        this.nom = " ";
        this.description = " ";
    }

    public Produit_fini(int idProduit, String nom, double prixVente, String description, Categorie categorie, Parfum parfum) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.prixVente = prixVente;
        this.description = description;
        this.categorie = categorie;
        this.parfum = parfum;
    }

    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public Parfum getParfum() {
        return parfum;
    }
    public void setParfum(Parfum parfum) {
        this.parfum = parfum;
    }

    public static String insert(String nom, double prixVente, String description, int idCategorie, int idParfum, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "insert into Produit_fini (nom, prixVente, description, idCategorie, idParfum) values (?, ?, ?, ?, ?)";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setDouble(2, prixVente);
            prepStat.setString(3, description);
            prepStat.setInt(4, idCategorie);
            prepStat.setInt(5, idParfum);
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

    public static List<Produit_fini> getAll(Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Produit_fini> listProduit = new ArrayList<>();
        Produit_fini produit = null;
        String requete = "select * from Produit_fini";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                produit = new Produit_fini();

                produit.setIdProduit(result.getInt("idProduit"));
                produit.setNom(result.getString("nom"));
                produit.setPrixVente(result.getDouble("PrixVente"));
                produit.setDescription(result.getString("description"));
                produit.setCategorie(Categorie.getById(result.getInt("idCategorie"), connect));
                produit.setParfum(Parfum.getById(result.getInt("idParfum"), connect));

                listProduit.add(produit);
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

        return listProduit;
    }

    public static Produit_fini getById(int idProduit, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Produit_fini produit = new Produit_fini();
        String requete = "select * from Produit_fini where idProduit = ?";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idProduit);
            result = prepStat.executeQuery();
            if(result.next()) {     
                produit.setIdProduit(result.getInt("idProduit"));
                produit.setNom(result.getString("nom"));
                produit.setPrixVente(result.getDouble("PrixVente"));
                produit.setDescription(result.getString("description"));
                produit.setCategorie(Categorie.getById(result.getInt("idCategorie"), connect));
                produit.setParfum(Parfum.getById(result.getInt("idParfum"), connect));
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

        return produit;
    }

    public static String update(int idProduit, String nom, double prixVente, String description, int idCategorie, int idParfum, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "update Produit_fini set nom = ?, prixVente = ?, description = ?, idCategorie = ?, idParfum = ? where idProduit = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setDouble(2, prixVente);
            prepStat.setString(3, description);
            prepStat.setInt(4, idCategorie);
            prepStat.setInt(5, idParfum);
            prepStat.setInt(6, idProduit);
            int result = prepStat.executeUpdate();
            if(result > 0) {
                message = "Modification reussi";
            }
            else {
                message = "Modification echoue";
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

    public static String delete(int idProduit, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "delete from Produit_fini where idProduit = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idProduit);
            int result = prepStat.executeUpdate();
            if(result > 0) {
                message = "Suppression reussi";
            }
            else {
                message = "Suppression echoue";
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

}
