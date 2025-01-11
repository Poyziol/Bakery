package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Connexion;

public class Fonction {
    int idFonction;
    String nom;
    String description;

    public Fonction() {
    }

    public Fonction(int idFonction, String nom, String description) {
        this.idFonction = idFonction;
        this.nom = nom;
        this.description = description;
    }

    public int getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(int idFonction) {
        this.idFonction = idFonction;
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
        String requete = "insert into Fonction(nom, description) values (?, ?)";
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

    public static List<Fonction> getAll() throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Fonction> listFonction = new ArrayList<>();
        Fonction fonction = null;
        String requete = "select * from Fonction";
        
        try {
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                fonction = new Fonction();

                fonction.setIdFonction(result.getInt("idFonction"));
                fonction.setNom(result.getString("nom"));
                fonction.setDescription(result.getString("description"));

                listFonction.add(fonction);
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

        return listFonction;
    }

    public static Fonction getById(int idFonction) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Fonction fonction = new Fonction();
        String requete = "select * from Fonction where idFonction = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idFonction);
            result = prepStat.executeQuery();
            if(result.next()) {
                fonction.setIdFonction(result.getInt("idFonction"));
                fonction.setNom(result.getString("nom"));
                fonction.setDescription(result.getString("description"));
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

        return fonction;
    }

    public static String update(int idFonction, String nom, String description) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "update Fonction set nom = ?, description = ? where idFonction = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setString(2, description);
            prepStat.setInt(3, idFonction);
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

    public static String delete(int idFonction) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Fonction where idFonction = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idFonction);
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
