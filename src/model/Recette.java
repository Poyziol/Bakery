package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Connexion;

public class Recette {
    int idRecette;
    double qteEstimee;
    Map<Ingredient, Double> ingredients;

    public Recette() {
    }

    public Recette(int idRecette, double qteEstimee, Map<Ingredient, Double> ingredients) {
        this.idRecette = idRecette;
        this.qteEstimee = qteEstimee;
        this.ingredients = ingredients;
    }

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public double getQteEstimee() {
        return qteEstimee;
    }

    public void setQteEstimee(double qteEstimee) {
        this.qteEstimee = qteEstimee;
    }

    public Map<Ingredient, Double> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<Ingredient, Double> ingredients) {
        this.ingredients = ingredients;
    }

    public static String insertDetail_Recette(int idRecette, Map<Integer, Double> ingredients) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "insert into Recette_Detail(idRecette, idIngredient, quantite) values (?, ?, ?)";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            for(Map.Entry<Integer, Double> ingredient : ingredients.entrySet()) {
                prepStat.setInt(1, idRecette);
                prepStat.setInt(2, ingredient.getKey());
                prepStat.setDouble(3, ingredient.getValue());
                int result = prepStat.executeUpdate();
                if(result > 0) {
                    message = "Insertion reussi";
                }
                else {
                    message = "Insertion echoue";
                }
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

    public static String insert(double qteEstimee) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "insert into Recette(qteEstimee) values (?)";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setDouble(1, qteEstimee);
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

    public static Map<Ingredient, Double> getAllIngIngredients_Recette(int idRecette) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Map<Ingredient, Double> listIngredients = new HashMap<>();
        Ingredient ingredient = null;
        double qte = 0;
        String requete = "select * from Recette_Detail where idRecette = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idRecette);
            result = prepStat.executeQuery();
            while(result.next()) {
                ingredient = Ingredient.getById(result.getInt("idRecette"));
                qte = result.getDouble("quantite");
                
                listIngredients.put(ingredient, qte);
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

        return listIngredients;
    }

    public static List<Recette> getAll() throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Recette> listRecette = new ArrayList<>();
        Recette recette = null;
        String requete = "select * from Recette";
        
        try {
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                recette = new Recette();

                recette.setIdRecette(result.getInt("idRecette"));
                recette.setQteEstimee(result.getDouble("qteEstimee"));
                recette.setIngredients(getAllIngIngredients_Recette(recette.getIdRecette()));

                listRecette.add(recette);
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

        return listRecette;
    }

    public static Recette getById(int idRecette) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Recette recette = new Recette();
        String requete = "select * from Recette where idRecette = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idRecette);
            result = prepStat.executeQuery();
            if(result.next()) {
                recette.setIdRecette(result.getInt("idRecette"));
                recette.setQteEstimee(result.getDouble("qteEstimee"));
                recette.setIngredients(getAllIngIngredients_Recette(idRecette));
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

        return recette;
    }

    public static String update(int idRecette, double qteEstimee) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "update Recette set qteEstimee = ? where idRecette = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setDouble(1, qteEstimee);
            prepStat.setInt(3, idRecette);
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

    public static String deleteIngredients_Recette(int idRecette) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Recette_Detail where idRecette = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idRecette);
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

    public static String delete(int idRecette) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Recette where idRecette = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idRecette);
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
