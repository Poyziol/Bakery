DROP DATABASE IF EXISTS boulangerie;
CREATE DATABASE boulangerie;
\c boulangerie;

CREATE TABLE Fonction(
   idFonction SERIAL,
   nom VARCHAR(50) NOT NULL,
   description VARCHAR(255),
   PRIMARY KEY(idFonction)
);

CREATE TABLE Employe(
   idEmploye SERIAL,
   nom VARCHAR(50) ,
   prenoms VARCHAR(50),
   dateNaissance DATE,
   adresse VARCHAR(50) NOT NULL,
   telephone VARCHAR(50) UNIQUE,
   idFonction INTEGER NOT NULL,
   PRIMARY KEY(idEmploye),
   FOREIGN KEY(idFonction) REFERENCES Fonction(idFonction)
);

CREATE TABLE Users(
   idUser SERIAL,
   email VARCHAR(100) UNIQUE,
   motDePasse VARCHAR(255) NOT NULL,
   idEmploye INTEGER NOT NULL,
   PRIMARY KEY(idUser),
   FOREIGN KEY(idEmploye) REFERENCES Employe(idEmploye)
);

CREATE TABLE Type_Produit(
   idType_Produit SERIAL,
   nom VARCHAR(50) NOT NULL,
   description VARCHAR(255),
   PRIMARY KEY(idType_Produit)
);

CREATE TABLE Fournisseur(
   idFournisseur SERIAL,
   nomEntreprise VARCHAR(100) NOT NULL,
   telephone VARCHAR(50) UNIQUE,
   adresse VARCHAR(50) UNIQUE,
   commentaire VARCHAR(255) ,
   PRIMARY KEY(idFournisseur)
);

CREATE TABLE Fournisseur_TypeProduit(
   idFournisseur INTEGER,
   idType_Produit INTEGER,
   PRIMARY KEY(idFournisseur, idType_Produit),
   FOREIGN KEY(idFournisseur) REFERENCES Fournisseur(idFournisseur),
   FOREIGN KEY(idType_Produit) REFERENCES Type_Produit(idType_Produit)
);

CREATE TABLE Client (
   idClient SERIAL,
   nom VARCHAR(30),
   email VARCHAR(100),
   numero INTEGER,
   adresse VARCHAR(100),
   PRIMARY KEY(idClient)
);

CREATE TABLE Unite(
   idUnite SERIAL,
   nom VARCHAR(25) NOT NULL,
   description VARCHAR(255) NOT NULL,
   PRIMARY KEY(idUnite)
);

CREATE TABLE Ingredient(
   idIngredient SERIAL,
   nom VARCHAR(50) NOT NULL,
   idUnite INTEGER NOT NULL,
   PRIMARY KEY(idIngredient),
   FOREIGN KEY(idUnite) REFERENCES Unite(idUnite)
);

CREATE TABLE Stock_Ingredient(
   idStock SERIAL,
   dateMouvement DATE NOT NULL,
   isEntree BOOLEAN NOT NULL,
   prixUnitaire DECIMAL(15,2) NOT NULL,
   quantite NUMERIC(15,2) NOT NULL,
   idIngredient INTEGER NOT NULL,
   PRIMARY KEY(idStock),
   FOREIGN KEY(idIngredient) REFERENCES Ingredient(idIngredient)
);

CREATE TABLE Categorie(
   idCategorie SERIAL,
   nom VARCHAR(50) NOT NULL,
   description VARCHAR(255),
   PRIMARY KEY(idCategorie)
);

CREATE TABLE Parfum(
   idParfum SERIAL,
   nom VARCHAR(50) NOT NULL,
   PRIMARY KEY(idParfum)
);

CREATE TABLE Produit_fini(
   idProduit SERIAL,
   nom VARCHAR(50) NOT NULL,
   prixVente NUMERIC(15,2) NOT NULL,
   description VARCHAR(255) NOT NULL,
   idCategorie INTEGER NOT NULL,
   idParfum INTEGER NOT NULL,
   PRIMARY KEY(idProduit),
   FOREIGN KEY(idCategorie) REFERENCES Categorie(idCategorie),
   FOREIGN KEY(idParfum) REFERENCES Parfum(idParfum)
);

CREATE TABLE Recette(
   idRecette SERIAL,
   idProduit INTEGER NOT NULL,
   qteEstimee INTEGER NOT NULL,
   PRIMARY KEY(idRecette),
   FOREIGN KEY(idProduit) REFERENCES Produit_fini(idProduit)
);

CREATE TABLE Recette_Detail(
   idRecette INTEGER,
   idIngredient INTEGER,
   quantite NUMERIC(15,2) NOT NULL,
   PRIMARY KEY(idRecette, idIngredient),
   FOREIGN KEY(idRecette) REFERENCES Recette(idRecette),
   FOREIGN KEY(idIngredient) REFERENCES Ingredient(idIngredient)
);

CREATE TABLE Fabrication(
   idFabrication SERIAL,
   dateFabrication DATE NOT NULL,
   idRecette INTEGER NOT NULL,
   prixRevient NUMERIC(15,2) NOT NULL,
   PRIMARY KEY(idFabrication),
   FOREIGN KEY(idRecette) REFERENCES Recette(idRecette)
);

CREATE TABLE Vente(
   idVente SERIAL,
   dateVente DATE NOT NULL,
   idProduit INTEGER NOT NULL,
   quantite INTEGER NOT NULL,
   idClient INTEGER NOT NULL,
   PRIMARY KEY(idVente),
   FOREIGN KEY(idProduit) REFERENCES Produit_fini(idProduit),
   FOREIGN KEY(idClient) REFERENCES Client(idClient)
);

CREATE TABLE Produit_conseiller(
   idProduitConseiller SERIAL,
   annee INTEGER,
   idMois INTEGER,
   idProduit INTEGER,
   PRIMARY KEY(idProduitConseiller),
   FOREIGN KEY(idProduit) REFERENCES Produit_fini(idProduit)
);

-- Récupère le stock restant pour chaque ingrédients
CREATE OR REPLACE FUNCTION getStock_Ingredients()
RETURNS TABLE(
   idIngredient INTEGER,
   nom VARCHAR(50),
   stock NUMERIC(15,2)
) AS $$
BEGIN
   RETURN QUERY
   SELECT 
      i.idIngredient,
      i.nom,
      COALESCE(SUM(CASE 
                      WHEN s.isEntree THEN s.quantite
                      ELSE -s.quantite
                   END), 0) AS stock
   FROM 
      Ingredient i
   LEFT JOIN 
      Stock_Ingredient s
   ON 
      i.idIngredient = s.idIngredient
   GROUP BY 
      i.idIngredient;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM getStock_Ingredients();

-- Récupère tout les stocks d'ingredients qui contient une date et une mouvement donnée
CREATE OR REPLACE FUNCTION getIngredientsBy_Criteria(dateM DATE,  isE BOOLEAN)
RETURNS TABLE( 
   idStock INTEGER,
   dateMouvement DATE,
   isEntree BOOLEAN,
   prixUnitaire NUMERIC(15,2),
   quantite NUMERIC(15,2),
   idIngredient INTEGER,
   nomIngredient VARCHAR(50)
) AS $$
BEGIN
   RETURN QUERY
   SELECT 
      s.idStock,
      s.dateMouvement,
      s.isEntree,
      s.prixUnitaire,
      s.quantite,
      s.idIngredient,
      i.nom
   FROM 
      Stock_Ingredient s
   JOIN
      Ingredient i
   ON
      s.idIngredient = i.idIngredient
   WHERE
      (dateM IS NULL OR s.dateMouvement = dateM)
      AND (isE IS NULL OR s.isEntree = isE);
END;
$$ LANGUAGE plpgsql;

SELECT * FROM getIngredientsBy_Criteria(null, null);

-- Récupère tout les fabrication qui contient un produit et un ingrédient donné
CREATE OR REPLACE FUNCTION getFabricationsBy_Criteria(idC INT, idI INT)
RETURNS TABLE(
   idFabrication INT,
   dateFabrication DATE,
   idRecette INTEGER,
   nomProduit VARCHAR(50),
   categorieProduit VARCHAR(50),
   prixRevient NUMERIC(15,2)
) AS $$
BEGIN
   RETURN QUERY
   SELECT 
      f.idFabrication,
      f.dateFabrication,
      f.idRecette,
      p.nom AS nomProduit,
      c.nom AS categorieProduit,
      f.prixRevient
   FROM 
      Fabrication f
   JOIN 
      Recette r
   ON 
      f.idRecette = r.idRecette
   JOIN
      Produit_fini p
   ON
      r.idProduit = p.idProduit
   JOIN
      Categorie c
   ON
      p.idCategorie = c.idCategorie
   JOIN
      Recette_Detail rd
   ON
      r.idRecette = rd.idRecette
   WHERE
      (idC = 0 OR p.idCategorie = idC)  -- Si idC est 0, prendre toutes les catégories
      AND (idI = 0 OR rd.idIngredient = idI)  -- Si idI est 0, prendre tous les ingrédients
   GROUP BY 
      f.idFabrication, p.nom, c.nom
   ORDER BY
      f.idFabrication;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM getFabricationsBy_Criteria(0, 0);

-- Récupère tout les ventes qui contient un categorie et un contenu donné
CREATE OR REPLACE FUNCTION getVentesBy_Criteria(idC INT, idPf INT, dateV DATE)
RETURNS TABLE(
   idVente INTEGER,
   dateVente DATE,
   idProduit INTEGER,
   nomProduit VARCHAR(50),
   categorieProduit VARCHAR(50),
   nomParfum VARCHAR(50),
   idClient INTEGER,
   quantite INTEGER
) AS $$
BEGIN
   RETURN QUERY
   SELECT 
      v.idVente,
      v.dateVente,
      v.idProduit,
      p.nom,
      c.nom,
      pf.nom,
      v.idClient,
      v.quantite
   FROM 
      Vente v
   JOIN 
      Produit_fini p
   ON 
      v.idProduit = p.idProduit
   JOIN 
      Categorie c
   ON 
      p.idCategorie = c.idCategorie
   JOIN
      Parfum pf
   ON
      p.idParfum = pf.idParfum
   WHERE
      (idC = 0 OR p.idCategorie = idC)
      AND (idPf = 0 OR p.idParfum = idPf)
      AND (dateV IS NULL OR v.dateVente = dateV);
END;
$$ LANGUAGE plpgsql;

SELECT * FROM getVentesBy_Criteria(0, 0, null);
