package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Connexion;

public class Unite {
    int idUnite;
    String nom;
    String description;

    public Unite() {
    }

    public Unite(int idUnite, String nom, String description) {
        this.idUnite = idUnite;
        this.nom = nom;
        this.description = description;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
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
        String requete = "insert into Unite(nom, description) values (?, ?)";
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

    public static List<Unite> getAll() throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Unite> listUnite = new ArrayList<>();
        Unite unite = null;
        String requete = "select * from Unite";
        
        try {
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                unite = new Unite();

                unite.setIdUnite(result.getInt("idUnite"));
                unite.setNom(result.getString("nom"));
                unite.setDescription(result.getString("description"));

                listUnite.add(unite);
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

        return listUnite;
    }

    public static Unite getById(int idUnite) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Unite unite = new Unite();
        String requete = "select * from Unite where idUnite = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idUnite);
            result = prepStat.executeQuery();
            if(result.next()) {
                unite.setIdUnite(result.getInt("idUnite"));
                unite.setNom(result.getString("nom"));
                unite.setDescription(result.getString("description"));
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

        return unite;
    }

    public static String update(int idUnite, String nom, String description) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "update Unite set nom = ?, description = ? where idUnite = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setString(2, description);
            prepStat.setInt(3, idUnite);
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

    public static String delete(int idUnite) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Unite where idUnite = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idUnite);
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
