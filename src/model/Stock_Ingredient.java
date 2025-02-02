package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Stock_Ingredient {
    int idStock;
    Date dateMouvement;
    String mouvement;
    double prixUnitaire;
    double quantite;
    Ingredient ingredient;

    public Stock_Ingredient() {
    }

    public Stock_Ingredient(int idStock, Date dateMouvement, String mouvement, double prixUnitaire, double quantite,
            Ingredient ingredient) {
        this.idStock = idStock;
        this.dateMouvement = dateMouvement;
        this.mouvement = mouvement;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.ingredient = ingredient;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public String getMouvement() {
        return mouvement;
    }

    public void setMouvement(String mouvement) {
        this.mouvement = mouvement;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public static String insert(Date dateMouvement, boolean isEntree, double prixUnitaire, double quantite, int idIngredient, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "insert into Stock_Ingredient(dateMouvement, isEntree, prixUnitaire, quantite, idIngredient) values (?, ?, ?, ?, ?)";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setDate(1, dateMouvement);
            prepStat.setBoolean(2, isEntree);
            prepStat.setDouble(3, prixUnitaire);
            prepStat.setDouble(4, quantite);
            prepStat.setInt(5, idIngredient);
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

    public static List<Stock_Ingredient> getAll(Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Stock_Ingredient> listStock = new ArrayList<>();
        Stock_Ingredient stockIngredient = null;
        String requete = "select * from Stock_Ingredient";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                stockIngredient = new Stock_Ingredient();

                stockIngredient.setIdStock(result.getInt("idStock"));
                stockIngredient.setDateMouvement(result.getDate("dateMouvement"));
                boolean isEntree = result.getBoolean("isEntree");
                if (isEntree) {
                    stockIngredient.setMouvement("Achat");
                }
                else {
                    stockIngredient.setMouvement("Fabrication");
                }
                stockIngredient.setPrixUnitaire(result.getDouble("prixUnitaire"));
                stockIngredient.setQuantite(result.getDouble("quantite"));
                stockIngredient.setIngredient(Ingredient.getById(result.getInt("idIngredient"), connect));

                listStock.add(stockIngredient);
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

        return listStock;
    }

    public static List<Stock_Ingredient> getAllByCriteria(Date dateMouvement, Boolean mouvement, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Stock_Ingredient> listStock = new ArrayList<>();
        Stock_Ingredient stockIngredient = null;
        String requete = "select * from getIngredientsBy_Criteria(?, ?)";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setDate(1, dateMouvement);
            prepStat.setObject(2, mouvement);
            result = prepStat.executeQuery();
            while(result.next()) {
                stockIngredient = new Stock_Ingredient();

                stockIngredient.setIdStock(result.getInt("idStock"));
                stockIngredient.setDateMouvement(result.getDate("dateMouvement"));
                boolean isEntree = result.getBoolean("isEntree");
                if (isEntree) {
                    stockIngredient.setMouvement("Achat");
                }
                else {
                    stockIngredient.setMouvement("Fabrication");
                }
                stockIngredient.setPrixUnitaire(result.getDouble("prixUnitaire"));
                stockIngredient.setQuantite(result.getDouble("quantite"));
                stockIngredient.setIngredient(Ingredient.getById(result.getInt("idIngredient"), connect));

                listStock.add(stockIngredient);
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

        return listStock;
    }

    public static Stock_Ingredient getById(int idStock, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Stock_Ingredient stockIngredient = new Stock_Ingredient();
        String requete = "select * from Stock_Ingredient where idStock = ?";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idStock);
            result = prepStat.executeQuery();
            if(result.next()) {         
                stockIngredient.setIdStock(result.getInt("idStock"));
                stockIngredient.setDateMouvement(result.getDate("dateMouvement"));
                boolean isEntree = result.getBoolean("isEntree");
                if (isEntree) {
                    stockIngredient.setMouvement("Vente");
                }
                else {
                    stockIngredient.setMouvement("Achat");
                }                stockIngredient.setPrixUnitaire(result.getDouble("prixUnitaire"));
                stockIngredient.setQuantite(result.getDouble("quantite"));
                stockIngredient.setIngredient(Ingredient.getById(result.getInt("idIngredient"), connect));
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

        return stockIngredient;
    }

    public static String update(int idStock, Date dateMouvement, boolean isEntree, double prixUnitaire, double quantite, int idIngredient, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "update Stock_Ingredient set dateMouvement = ?, isEntree = ?, prixUnitaire = ?, quantite = ?, idIngredient = ? where idStock = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setDate(1, dateMouvement);
            prepStat.setBoolean(2, isEntree);
            prepStat.setDouble(3, prixUnitaire);
            prepStat.setDouble(4, quantite);
            prepStat.setInt(5, idIngredient);
            prepStat.setInt(6, idStock);
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

    public static String delete(int idStock, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "delete from Stock_Ingredient where idStock = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idStock);
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
