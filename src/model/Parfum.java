package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Parfum {
    int idParfum;
    String nom;

    public Parfum() {
    }

    public Parfum(int idParfum, String nom) {
        this.idParfum = idParfum;
        this.nom = nom;
    }

    public int getIdParfum() {
        return idParfum;
    }
    public void setIdParfum(int idParfum) {
        this.idParfum = idParfum;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public static String insert(String nom, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "insert into Parfum(nom) values (?)";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
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

    public static List<Parfum> getAll(Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Parfum> listParfum = new ArrayList<>();
        Parfum parfum = null;
        String requete = "select * from Parfum";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                parfum = new Parfum();

                parfum.setIdParfum(result.getInt("idParfum"));
                parfum.setNom(result.getString("nom"));

                listParfum.add(parfum);
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

        return listParfum;
    }

    public static Parfum getById(int idParfum, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Parfum parfum = new Parfum();
        String requete = "select * from Parfum where idParfum = ?";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idParfum);
            result = prepStat.executeQuery();
            if(result.next()) {
                parfum.setIdParfum(result.getInt("idParfum"));
                parfum.setNom(result.getString("nom"));
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

        return parfum;
    }

    public static String update(int idParfum, String nom, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "update Parfum set nom = ? where idParfum = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setInt(2, idParfum);
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

    public static String delete(int idParfum, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "delete from Parfum where idParfum = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idParfum);
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
