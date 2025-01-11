package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Connexion;

public class Employe {
    int idEmploye;
    String nom;
    String prenoms;
    Date dateNaissance;
    String adresse;
    String telephone;
    Fonction fonction;

    public Employe() {
    }

    public Employe(int idEmploye, String nom, String prenoms, Date dateNaissance, String adresse, String telephone,
            Fonction fonction) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.prenoms = prenoms;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.fonction = fonction;
    }
    
    public int getIdEmploye() {
        return idEmploye;
    }
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenoms() {
        return prenoms;
    }
    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public Fonction getFonction() {
        return fonction;
    }
    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public static String insert(String nom, String prenoms, Date dateNaissance, String adresse, String telephone, int idFonction) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "insert into Employe(nom, prenoms, dateNaissance, adresse, telephone, idFonction) values (?, ?, ?, ?, ?, ?)";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setString(2, prenoms);
            prepStat.setDate(3, dateNaissance);
            prepStat.setString(4, adresse);
            prepStat.setString(5, telephone);
            prepStat.setInt(6, idFonction);
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

    public static List<Employe> getAll() throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Employe> listEmploye = new ArrayList<>();
        Employe employe = null;
        String requete = "select * from Employe";
        
        try {
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                employe = new Employe();

                employe.setIdEmploye(result.getInt("idEmploye"));
                employe.setNom(result.getString("nom"));
                employe.setPrenoms(result.getString("prenoms"));
                employe.setDateNaissance(result.getDate("dateNaissance"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTelephone(result.getString("telephone"));
                employe.setFonction(Fonction.getById(result.getInt("idFonction")));

                listEmploye.add(employe);
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

        return listEmploye;
    }

    public static Employe getById(int idEmploye) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Employe employe = new Employe();
        String requete = "select * from Employe where idEmploye = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idEmploye);
            result = prepStat.executeQuery();
            if(result.next()) {                
                employe.setIdEmploye(result.getInt("idEmploye"));
                employe.setNom(result.getString("nom"));
                employe.setPrenoms(result.getString("prenoms"));
                employe.setDateNaissance(result.getDate("dateNaissance"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTelephone(result.getString("telephone"));
                employe.setFonction(Fonction.getById(result.getInt("idFonction")));
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

        return employe;
    }

    public static String update(int idEmploye, String nom, String prenoms, Date dateNaissance, String adresse, String telephone, int idFonction) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "update Employe set nom = ?, prenoms = ?, dateNaissance = ?, adresse = ?, telephone = ?, idFonction = ? where idEmploye = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nom);
            prepStat.setString(2, prenoms);
            prepStat.setDate(3, dateNaissance);
            prepStat.setString(4, adresse);
            prepStat.setString(5, telephone);
            prepStat.setInt(6, idFonction);
            prepStat.setInt(7, idEmploye);
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

    public static String delete(int idEmploye) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Employe where idEmploye = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idEmploye);
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
