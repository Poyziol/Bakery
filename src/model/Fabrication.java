package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Fabrication {
    int idFabrication;
    Date dateFabrication;
    Recette recette;
    double prixRevient;
    
    public Fabrication() {
    }
    
    public Fabrication(int idFabrication, Date dateFabrication, Recette recette, double prixRevient) {
        this.idFabrication = idFabrication;
        this.dateFabrication = dateFabrication;
        this.recette = recette;
        this.prixRevient = prixRevient;
    }

    public int getIdFabrication() {
        return idFabrication;
    }
    public void setIdFabrication(int idFabrication) {
        this.idFabrication = idFabrication;
    }
    public Date getDateFabrication() {
        return dateFabrication;
    }
    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }
    public Recette getRecette() {
        return recette;
    }
    public void setRecette(Recette recette) {
        this.recette = recette;
    }
    public double getPrixRevient() {
        return prixRevient;
    }
    public void setPrixRevient(double prixRevient) {
        this.prixRevient = prixRevient;
    }
    
    public static String insert(Date dateFabrication, int idRecette, double prixRevient, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "insert into Fabrication(dateFabrication, idRecette, prixRevient) values (?, ?, ?)";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setDate(1, dateFabrication);
            prepStat.setInt(2, idRecette);
            prepStat.setDouble(3, prixRevient);
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

    public static List<Fabrication> getAll(Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Fabrication> listFabrication = new ArrayList<>();
        Fabrication fabrication = null;
        String requete = "select * from Fabrication";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                fabrication = new Fabrication();

                fabrication.setIdFabrication(result.getInt("idFabrication"));
                fabrication.setDateFabrication(result.getDate("dateFabrication"));
                fabrication.setRecette(Recette.getById(result.getInt("idRecette"), connect));
                fabrication.setPrixRevient(result.getDouble("prixRevient"));

                listFabrication.add(fabrication);
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

        return listFabrication;
    }

    public static List<Fabrication> getAllByCriteria(int idCategorie, int idIngredient, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Fabrication> listFabrication = new ArrayList<>();
        Fabrication fabrication = null;
        String requete = "SELECT * FROM getFabricationsBy_Criteria(?, ?)";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idCategorie);
            prepStat.setInt(2, idIngredient);
            result = prepStat.executeQuery();
            while(result.next()) {
                fabrication = new Fabrication();

                fabrication.setIdFabrication(result.getInt("idFabrication"));
                fabrication.setDateFabrication(result.getDate("dateFabrication"));
                fabrication.setRecette(Recette.getById(result.getInt("idRecette"), connect));
                fabrication.setPrixRevient(result.getDouble("prixRevient"));

                listFabrication.add(fabrication);
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

        return listFabrication;
    }

    public static Fabrication getById(int idFabrication, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Fabrication fabrication = new Fabrication();
        String requete = "select * from Fabrication where idFabrication = ?";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idFabrication);
            result = prepStat.executeQuery();
            if(result.next()) {
                fabrication.setIdFabrication(result.getInt("idFabrication"));
                fabrication.setDateFabrication(result.getDate("dateFabrication"));
                fabrication.setRecette(Recette.getById(result.getInt("idRecette"), connect));
                fabrication.setPrixRevient(result.getDouble("prixRevient"));
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

        return fabrication;
    }

    public static String update(int idFabrication, Date dateFabrication, int idRecette, double prixRevient, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "update Fabrication set dateFabrication = ?, idRecette = ?, prixRevient = ? where idFabrication = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setDate(1, dateFabrication);
            prepStat.setInt(2, idRecette);
            prepStat.setDouble(3, prixRevient);
            prepStat.setInt(4, idFabrication);
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

    public static String delete(int idFabrication, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "delete from Fabrication where idFabrication = ?";
        String message = "";
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idFabrication);
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
