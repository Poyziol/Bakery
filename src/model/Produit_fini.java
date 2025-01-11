package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Connexion;

public class Produit_fini {
    int idProduit;
    String nom;
    double prixRevient;
    double prixVente;
    String description;
    Categorie categorie;
    Recette recette;
    
    public Produit_fini() {
    }

    public Produit_fini(int idProduit, String nom, double prixRevient, double prixVente, String description,
            Categorie categorie, Recette recette) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.prixRevient = prixRevient;
        this.prixVente = prixVente;
        this.description = description;
        this.categorie = categorie;
        this.recette = recette;
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
    public double getPrixRevient() {
        return prixRevient;
    }
    public void setPrixRevient(double prixRevient) {
        this.prixRevient = prixRevient;
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
    public Recette getRecette() {
        return recette;
    }
    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    public static String insert(String nom, double prixRevient, double prixVente, String description, int idCategorie, int idRecette) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "insert into Produit_fini (nom, prixRevient, prixVente, description, idCategorie, idRecette) values (?, ?, ?, ?, ?, ?)";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setDouble(2, prixRevient);
            prepStat.setDouble(3, prixVente);
            prepStat.setString(4, description);
            prepStat.setInt(5, idCategorie);
            prepStat.setInt(6, idRecette);
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
            if(connect != null) {
                connect.close();
            }
        }
        return message;
    }

    public static List<Produit_fini> getAll() throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Produit_fini> listProduit = new ArrayList<>();
        Produit_fini produit = null;
        String requete = "select * from Produit_fini";
        
        try {
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                produit = new Produit_fini();

                produit.setIdProduit(result.getInt("idProduit"));
                produit.setNom(result.getString("nom"));
                produit.setPrixRevient(result.getDouble("prixRevient"));
                produit.setPrixVente(result.getDouble("PrixVente"));
                produit.setDescription(result.getString("description"));
                produit.setCategorie(Categorie.getById(result.getInt("idCategorie")));
                produit.setRecette(Recette.getById(result.getInt("idRecette")));

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
            if(connect != null) {
                connect.close();
            }
        }

        return listProduit;
    }

    public static Produit_fini getById(int idProduit) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Produit_fini produit = new Produit_fini();
        String requete = "select * from Produit_fini where idEmploye = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idProduit);
            result = prepStat.executeQuery();
            if(result.next()) {     
                produit.setIdProduit(result.getInt("idProduit"));
                produit.setNom(result.getString("nom"));
                produit.setPrixRevient(result.getDouble("prixRevient"));
                produit.setPrixVente(result.getDouble("PrixVente"));
                produit.setDescription(result.getString("description"));
                produit.setCategorie(Categorie.getById(result.getInt("idCategorie")));
                produit.setRecette(Recette.getById(result.getInt("idRecette")));
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
            if(connect != null) {
                connect.close();
            }
        }

        return produit;
    }

    public static String update(int idProduit, String nom, double prixRevient, double prixVente, String description, int idCategorie, int idRecette) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "update Produit_fini set nom = ?, prixRevient = ?, prixVente = ?, description = ?, idCategorie = ?, idRecette = ? where idProduit = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setDouble(2, prixRevient);
            prepStat.setDouble(3, prixRevient);
            prepStat.setString(4, description);
            prepStat.setInt(5, idCategorie);
            prepStat.setInt(6, idRecette);
            prepStat.setInt(7, idProduit);
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
            if(connect != null) {
                connect.close();
            }
        }
        return message;
    }

    public static String delete(int idProduit) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Produit_fini where idProduit = ?";
        String message = "";
        try {
            connect = Connexion.connection();
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
            if(connect != null) {
                connect.close();
            }
        }
        return message;
    }

}
