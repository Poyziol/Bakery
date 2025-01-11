package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Connexion;

public class Fournisseur {
    int idFournisseur;
    String nomEntreprise;
    String telephone;
    String adresse;
    String commentaire;
    List<Type_Produit> type_produits;

    public Fournisseur() {
    }

    public Fournisseur(int idFournisseur, String nomEntreprise, String telephone, String adresse, String commentaire, List<Type_Produit> type_produits) {
        this.idFournisseur = idFournisseur;
        this.nomEntreprise = nomEntreprise;
        this.telephone = telephone;
        this.adresse = adresse;
        this.commentaire = commentaire;
        this.type_produits = type_produits;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public List<Type_Produit> getType_produits() {
        return type_produits;
    }

    public void setType_produits(List<Type_Produit> type_produits) {
        this.type_produits = type_produits;
    }

    public static String insertTypeProduit_Fournisseur(List<Integer> idType_Produits, int idFournisseur) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "insert into Fournisseur_TypeProduit (idFournisseur, idType_Produit) values (?, ?)";
        String message = "";

        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            for(int idType_Produit : idType_Produits) {
                prepStat.setInt(1, idFournisseur);
                prepStat.setInt(2, idType_Produit);
                int result = prepStat.executeUpdate();
                if(result > 0) {
                    message = "Insertion reussi";
                }
                else {
                    message = "Insertion echoue";
                    break;
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

    public static String insert(String nomEntreprise, String telephone, String adresse, String commentaire) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "insert into Fournisseur(nomEntreprise, telephone, adresse, commentaire) values (?, ?, ?, ?)";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nomEntreprise);
            prepStat.setString(2, telephone);
            prepStat.setString(3, adresse);
            prepStat.setString(4, commentaire);
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

    public static List<Type_Produit> getAllTypeProduits_Fournisseur(int idFournisseur) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Type_Produit> listType_Produit = new ArrayList<>();
        Type_Produit type_produit = null;
        String requete = "select * from Fournisseur_TypeProduit where idFournisseur = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idFournisseur);
            result = prepStat.executeQuery();
            while(result.next()) {
                type_produit = Type_Produit.getById(result.getInt("idType_Produit"));

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

    public static List<Fournisseur> getAll() throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Fournisseur> listFournisseur = new ArrayList<>();
        Fournisseur fournisseur = null;
        String requete = "select * from Fournisseur";
        
        try {
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                fournisseur = new Fournisseur();

                fournisseur.setIdFournisseur(result.getInt("idFournisseur"));
                fournisseur.setNomEntreprise(result.getString("nomEntreprise"));
                fournisseur.setTelephone(result.getString("telephone"));
                fournisseur.setAdresse(result.getString("adresse"));
                fournisseur.setCommentaire(result.getString("commentaire"));
                fournisseur.setType_produits(getAllTypeProduits_Fournisseur(fournisseur.getIdFournisseur()));

                listFournisseur.add(fournisseur);
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

        return listFournisseur;
    }

    public static Fournisseur getById(int idFournisseur) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Fournisseur fournisseur = new Fournisseur();
        String requete = "select * from Fournisseur where idFournisseur = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idFournisseur);
            result = prepStat.executeQuery();
            if(result.next()) {                
                fournisseur.setIdFournisseur(result.getInt("idFournisseur"));
                fournisseur.setNomEntreprise(result.getString("nomEntreprise"));
                fournisseur.setTelephone(result.getString("telephone"));
                fournisseur.setAdresse(result.getString("adresse"));
                fournisseur.setCommentaire(result.getString("commentaire"));
                fournisseur.setType_produits(getAllTypeProduits_Fournisseur(idFournisseur));
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

        return fournisseur;
    }

    public static String update(int idFournisseur, String nomEntreprise, String telephone, String adresse, String commentaire) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "update Fournisseur set nomEntreprise = ?, telephone = ?, adresse = ?, commentaire = ? where idFournisseur = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, nomEntreprise);
            prepStat.setString(2, telephone);
            prepStat.setString(3, adresse);
            prepStat.setString(4, commentaire);
            prepStat.setInt(5, idFournisseur);
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

    public static String deleteTypeProduits_Fournisseur(int idFournisseur) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Fournisseur_TypeProduit where idFournisseur = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idFournisseur);
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

    public static String delete(int idFournisseur) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Fournisseur where idFournisseur = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idFournisseur);
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
