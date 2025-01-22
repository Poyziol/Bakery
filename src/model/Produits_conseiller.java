package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Produits_conseiller {
    int idProduitConseiller;
    int annee;
    Month month;
    List<Produit_fini> produits;
    
    public Produits_conseiller() {
    }

    public Produits_conseiller(int idProduitConseiller, int annee, Month month, List<Produit_fini> produits) {
        this.idProduitConseiller = idProduitConseiller;
        this.annee = annee;
        this.month = month;
        this.produits = produits;
    }

    public int getIdProduitConseiller() {
        return idProduitConseiller;
    }
    public void setIdProduitConseiller(int idProduitConseiller) {
        this.idProduitConseiller = idProduitConseiller;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public Month getMonth() {
        return month;
    }
    public void setMonth(Month month) {
        this.month = month;
    }
    public List<Produit_fini> getProduits() {
        return produits;
    }
    public void setProduits(List<Produit_fini> produits) {
        this.produits = produits;
    }
    
    public static String insert(int annee, int idMois, List<Integer> listIdProduit, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        String requete = "insert into Produit_conseiller(annee, idMois, idProduit) values (?, ?, ?)";
        String message = "";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            
            prepStat = connect.prepareStatement(requete);
            for (Integer idProduit : listIdProduit) {
                prepStat.setInt(1, annee);
                prepStat.setInt(2, idMois);
                prepStat.setInt(3, idProduit);
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
        }
        return message;
    }

    public static List<Produit_fini> getAllProduit(int annee, int idMois, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Produit_fini> produits = new ArrayList<>();
        Produit_fini produit = null;
        String requete = "select * from Produit_conseiller where (? = 0 OR annee = ?) and (? = 0 OR idMois = ?)";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, annee);
            prepStat.setInt(2, annee);
            prepStat.setInt(3, idMois);
            prepStat.setInt(4, idMois);
            result = prepStat.executeQuery();
            while(result.next()) {
                produit = Produit_fini.getById(result.getInt("idProduit"), connect);

                produits.add(produit);
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

        return produits;
    }

    public static List<Produits_conseiller> getAll(Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Produits_conseiller> listProduit = new ArrayList<>();
        Produits_conseiller produit = null;
        String requete = "select * from Produit_Conseiller";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                if ( (produit != null) && (produit.getMonth().getValue() == result.getInt("idMois")) ) {
                    continue;
                }
                produit = new Produits_conseiller();

                produit.setIdProduitConseiller(result.getInt("idProduitConseiller"));
                produit.setAnnee(result.getInt("annee"));
                produit.setMonth(Month.of(result.getInt("idMois")));
                produit.setProduits(getAllProduit(produit.getAnnee(), produit.getMonth().getValue(), connect));

                listProduit.add(produit);
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

        return listProduit;
    }

    public static List<Produits_conseiller> getAllByCriteria(int annee, int idMois, Connection connect) throws Exception {
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Produits_conseiller> listProduit = new ArrayList<>();
        Produits_conseiller produit = null;
        String requete = "select * from Produit_conseiller where (? = 0 OR annee = ?) and (? = 0 OR idMois = ?)";
        
        try {
            if (connect == null) {
                connect = Connexion.connection();
            }
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, annee);
            prepStat.setInt(2, annee);
            prepStat.setInt(3, idMois);
            prepStat.setInt(4, idMois);
            result = prepStat.executeQuery();
            while(result.next()) {
                if ( (produit != null) && (produit.getMonth().getValue() == result.getInt("idMois")) && (produit.getAnnee() == result.getInt("annee")) ) {
                    continue;
                }
                produit = new Produits_conseiller();

                produit.setIdProduitConseiller(result.getInt("idProduitConseiller"));
                produit.setAnnee(result.getInt("annee"));
                produit.setMonth(Month.of(result.getInt("idMois")));
                produit.setProduits(getAllProduit(produit.getAnnee(), produit.getMonth().getValue(), connect));

                listProduit.add(produit);
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

        return listProduit;
    }

}
