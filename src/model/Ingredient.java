package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Connexion;

public class Ingredient {
    int idIngredient;
    String nom;
    Unite unite;

    public Ingredient() {
    }

    public Ingredient(int idIngredient, String nom, Unite unite) {
        this.idIngredient = idIngredient;
        this.nom = nom;
        this.unite = unite;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public static String insert(String nom, int idUnite) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "insert into Ingredient(nom, idUnite) values (?, ?)";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setInt(2, idUnite);
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

    public static List<Ingredient> getAll() throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Ingredient> listIngredient = new ArrayList<>();
        Ingredient ingredient = null;
        String requete = "select * from Ingredient";
        
        try {
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                ingredient = new Ingredient();

                ingredient.setIdIngredient(result.getInt("idIngredient"));
                ingredient.setNom(result.getString("nom"));
                ingredient.setUnite(Unite.getById(result.getInt("idUnite")));

                listIngredient.add(ingredient);
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

        return listIngredient;
    }

    public static Ingredient getById(int idIngredient) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Ingredient ingredient = new Ingredient();
        String requete = "select * from Ingredient where idIngredient = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idIngredient);
            result = prepStat.executeQuery();
            if(result.next()) {
                ingredient.setIdIngredient(result.getInt("idIngredient"));
                ingredient.setNom(result.getString("nom"));
                ingredient.setUnite(Unite.getById(result.getInt("idUnite")));
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

        return ingredient;
    }

    public static String update(int idIngredient, String nom, int idUnite) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "update Ingredient set nom = ?, idUnite = ? where idIngredient = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setInt(2, idUnite);
            prepStat.setInt(3, idIngredient);
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

    public static String delete(int idIngredient) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Ingredient where idIngredient = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idIngredient);
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
