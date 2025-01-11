package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Connexion;

public class Type_Produit {
    int idType_Produit;
    String nom;
    String description;    

    public Type_Produit() {
    }

    public Type_Produit(int idType_Produit, String nom, String description) {
        this.idType_Produit = idType_Produit;
        this.nom = nom;
        this.description = description;
    }

    public int getIdType_Produit() {
        return idType_Produit;
    }

    public void setIdType_Produit(int idType_Produit) {
        this.idType_Produit = idType_Produit;
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
        String requete = "insert into Type_Produit(nom, description) values (?, ?)";
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

    public static List<Type_Produit> getAll() throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Type_Produit> listType_Produit = new ArrayList<>();
        Type_Produit type_produit = null;
        String requete = "select * from Type_Produit";
        
        try {
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                type_produit = new Type_Produit();

                type_produit.setIdType_Produit(result.getInt("idType_Produit"));
                type_produit.setNom(result.getString("nom"));
                type_produit.setDescription(result.getString("description"));

                listType_Produit.add(type_produit);
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

        return listType_Produit;
    }

    public static Type_Produit getById(int idType_Produit) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Type_Produit type_produit = new Type_Produit();
        String requete = "select * from Type_Produit where idType_Produit = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idType_Produit);
            result = prepStat.executeQuery();
            if(result.next()) {
                type_produit.setIdType_Produit(result.getInt("idType_Produit"));
                type_produit.setNom(result.getString("nom"));
                type_produit.setDescription(result.getString("description"));
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

        return type_produit;
    }

    public static String update(int idType_Produit, String nom, String description) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "update Type_Produit set nom = ?, description = ? where idType_Produit = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setString(2, description);
            prepStat.setInt(3, idType_Produit);
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

    public static String delete(int idType_Produit) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Type_Produit where idType_Produit = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idType_Produit);
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
