package aff;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import model.Connexion;
import model.Produits_conseiller;

public class Main {
    public static void main(String[] args) throws Exception {
        Locale locale = Locale.FRENCH;
        for (Month month : Month.values()) {
            String mois = month.getDisplayName(TextStyle.FULL, locale); 
            System.out.println("idMois: " + month.getValue() + ", mois: " + mois);
        }

        List<Produits_conseiller> listProduits_conseillers = Produits_conseiller.getAll(Connexion.connection());
        for (Produits_conseiller produit : listProduits_conseillers) {
            System.out.println("annee: " + produit.getAnnee() + ", mois: " + produit.getMonth().name() + ", countProduit: " + produit.getProduits().size());
        }

        List<Produits_conseiller> listProduitsConseiller = Produits_conseiller.getAllByCriteria(0, 1, Connexion.connection());
        for (Produits_conseiller produit : listProduitsConseiller) {
            System.out.println("annee: " + produit.getAnnee() + ", mois: " + produit.getMonth().name() + ", countProduit: " + produit.getProduits().size());
        }

    }
}
