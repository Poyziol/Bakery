package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recette {
    int idRecette;
    Produit_fini produit;
    double qteEstimee;
    Map<Ingredient, Double> ingredients;

    public Recette() {
    }

    public Recette(int idRecette, Produit_fini produit, double qteEstimee, Map<Ingredient, Double> ingredients) {
        this.idRecette = idRecette;
        this.produit = produit;
        this.qteEstimee = qteEstimee;
        this.ingredients = ingredients;
    }

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public Produit_fini getProduit() {
        return produit;
    }

    public void setProduit(Produit_fini produit) {
        this.produit = produit;
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

    public static String insertDetail_Recette(int idRecette, Map<Integer, Double> ingredients, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "insert into Recette_Detail(idRecette, idIngredient, quantite) values (?, ?, ?)";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
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
        }
        return message;
    }

    public static String insert(int idProduit, double qteEstimee, Map<Integer, Double> ingredients, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet generatedKeys = null;
        String requete = "insert into Recette(idProduit, qteEstimee) values (?, ?)";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStat.setInt(1, idProduit);
            prepStat.setDouble(2, qteEstimee);
            int result = prepStat.executeUpdate();
            if(result > 0) {
                generatedKeys = prepStat.getGeneratedKeys();

                if (generatedKeys.next()) {
                    int idRecette = generatedKeys.getInt(1); // 1 correspond à la première colonne du ResultSet
                    message = insertDetail_Recette(idRecette, ingredients, connect);
                } else {
                    message = "Clé générée non disponible.";
                }
            }
            else {
                message = "Insertion echoue";
            }
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(generatedKeys  != null) {
                generatedKeys .close();
            }
            if(prepStat != null) {
                prepStat.close();
            }
        }
        return message;
    }

    public static Map<Ingredient, Double> getAllIngredients_Recette(int idRecette, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Map<Ingredient, Double> listIngredients = new HashMap<>();
        Ingredient ingredient = null;
        double qte = 0;
        String requete = "select * from Recette_Detail where idRecette = ?";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idRecette);
            result = prepStat.executeQuery();
            while(result.next()) {
                ingredient = Ingredient.getById(result.getInt("idIngredient"), connect);
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
        }

        return listIngredients;
    }

    public static List<Recette> getAll(Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Recette> listRecette = new ArrayList<>();
        Recette recette = null;
        String requete = "select * from Recette";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                recette = new Recette();

                recette.setIdRecette(result.getInt("idRecette"));
                recette.setProduit(Produit_fini.getById(result.getInt("idProduit"), connect));
                recette.setQteEstimee(result.getDouble("qteEstimee"));
                recette.setIngredients(getAllIngredients_Recette(recette.getIdRecette(), connect));

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
        }

        return listRecette;
    }

    public static Recette getById(int idRecette, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Recette recette = new Recette();
        String requete = "select * from Recette where idRecette = ?";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idRecette);
            result = prepStat.executeQuery();
            if(result.next()) {
                recette.setIdRecette(result.getInt("idRecette"));
                recette.setProduit(Produit_fini.getById(result.getInt("idProduit"), connect));
                recette.setQteEstimee(result.getDouble("qteEstimee"));
                recette.setIngredients(getAllIngredients_Recette(idRecette, connect));
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

        return recette;
    }

    public static String update(int idRecette, int idProduit, double qteEstimee, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "update Recette set idProduit = ?, qteEstimee = ? where idRecette = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idProduit);
            prepStat.setDouble(2, qteEstimee);
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
        }
        return message;
    }

    public static String deleteIngredients_Recette(int idRecette, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "delete from Recette_Detail where idRecette = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
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
        }
        return message;
    }

    public static String delete(int idRecette, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "delete from Recette where idRecette = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            message = deleteIngredients_Recette(idRecette, connect);
            if (message.equals("Suppression reussi")) {
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
