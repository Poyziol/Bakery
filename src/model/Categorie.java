package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Connexion;

public class Categorie {
    int idCategorie;
    String nom;
    String description;

    public Categorie() {
    }

    public Categorie(int idCategorie, String nom, String description) {
        this.idCategorie = idCategorie;
        this.nom = nom;
        this.description = description;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String insert(String nom, String description) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "insert into Categorie(nom, description) values (?, ?)";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setString(2, description);
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

    public static List<Categorie> getAll() throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Categorie> listCategorie = new ArrayList<>();
        Categorie categorie = null;
        String requete = "select * from Categorie";
        
        try {
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                categorie = new Categorie();

                categorie.setIdCategorie(result.getInt("idCategorie"));
                categorie.setNom(result.getString("nom"));
                categorie.setDescription(result.getString("description"));

                listCategorie.add(categorie);
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

        return listCategorie;
    }

    public static Categorie getById(int idCategorie) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Categorie categorie = new Categorie();
        String requete = "select * from Categorie where idCategorie = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idCategorie);
            result = prepStat.executeQuery();
            if(result.next()) {
                categorie.setIdCategorie(result.getInt("idCategorie"));
                categorie.setNom(result.getString("nom"));
                categorie.setDescription(result.getString("description"));
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

        return categorie;
    }

    public static String update(int idCategorie, String nom, String description) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "update Categorie set nom = ?, description = ? where idCategorie = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setString(2, description);
            prepStat.setInt(3, idCategorie);
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

    public static String delete(int idCategorie) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Categorie where idCategorie = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idCategorie);
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
