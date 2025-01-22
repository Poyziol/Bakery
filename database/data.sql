-- INSERT INTO Fonction (nom, description) VALUES 
--     ('Admin', 'Proprietaire de la boulangerie'),
--     ('Boulanger', 'Preparation des pains et viennoiseries'),
--     ('Patissier', 'Realisation des gateaux et desserts'),
--     ('Aide-boulanger', 'Assistance au boulanger dans ses taches'),
--     ('Caissier', 'Accueil et vente des produits aux clients'),
--     ('Manager', 'Supervision des employes et gestion du magasin'),
--     ('Livreur', 'Livraison des commandes aux clients'),
--     ('Chef de production', 'Planification et supervision de la production');

-- INSERT INTO Employe (nom, prenoms, dateNaissance, adresse, telephone, idFonction) VALUES 
--     ('RABEZAKARISON', 'Miangaly', '2004-09-23', 'IPT 127 Antanety Bemasoandro', '0386667260', 1),
--     ('RANDRIA', 'John', '1987-03-15', 'XW 17 OI Andranomena', '0342512387', 2),
--     ('RAKOTO', 'Manampy', '1992-11-02', 'IT 22 X Antanimena', '0385102612', 2),
--     ('RAHARIJAONA', 'Feno', '1990-05-12', 'Lot 45 CD Ambohipo', '0321425369', 3),
--     ('ANDRIAMIHANTA', 'Noro', '1985-08-25', 'Lot 12 BC Ambolokandrina', '0345129876', 5);

-- INSERT INTO Users (email, motDePasse, idEmploye) VALUES 
--     ('nancy@gmail.com', 'nancy*5_2728', 1);

-- INSERT INTO Type_Produit (nom, description) VALUES 
--     ('Fournitures de base', 'Ingredients essentiels comme la farine, le sucre, et le sel.'),
--     ('Produits de levee et de fermentation', 'Levure, levain et autres agents pour faire lever les pates.'),
--     ('Produits laitiers et œufs', 'Lait, beurre, creme, et œufs utilises en boulangerie et patisserie.'),
--     ('Chocolats et confiseries', 'Chocolat, pralines, et decorations sucrees.'),
--     ('Fruits et purees de fruits', 'Fruits frais, confits, ou en puree pour les garnitures.'),
--     ('Materiel et emballage', 'Emballages comme boîtes a gateaux et materiel de boulangerie.'),
--     ('Produits de decoration', 'Pate d amande, colorants, et autres elements decoratifs.'),
--     ('Boissons', 'Produits comme le cafe, le the ou les boissons artisanales.'),
--     ('Produits bio', 'Ingredients certifies biologiques et respectueux de l environnement.'),
--     ('epices et arômes', 'Arômes et epices comme la vanille, la cannelle ou la fleur d oranger.');

-- INSERT INTO Fournisseur (nomEntreprise, telephone, adresse, commentaire) VALUES 
--     ('Farina Supplies', '0321234567', 'Lot II D 23, Ambanidia, Antananarivo', 'Fournisseur principal de farine et levure'),
--     ('Sweet World', '0347654321', 'Lot 18 B, Ambohidahy, Fianarantsoa', 'Fournisseur de sucre et chocolats'),
--     ('Lait Delices', '0331122334', 'Lot 5 A, Maroala, Mahajanga', 'Produits laitiers de haute qualite'),
--     ('Fruit Passion', '0327654321', 'Lot 3 C, Ankirihiry, Toamasina', 'Fruits frais et purees naturelles'),
--     ('Materiel Pro', '0341239876', 'Lot 7 D, Mandaniresaka, Antsirabe', 'Fournitures et emballages professionnels');

-- INSERT INTO Fournisseur_TypeProduit (idFournisseur, idType_Produit) VALUES 
--     (1, 1), -- Farina Supplies fournit des fournitures de base
--     (1, 2), -- Farina Supplies fournit des produits de levee
--     (2, 4), -- Sweet World fournit des chocolats
--     (2, 7), -- Sweet World fournit des produits de decoration
--     (2, 10), -- Sweet World fournit des epices
--     (3, 3), -- Lait Delices fournit des produits laitiers
--     (3, 10), -- Lait Delices fournit des produits bio
--     (4, 5), -- Fruit Passion fournit des fruits
--     (4, 9), -- Fruit Passion fournit des epices et arômes
--     (5, 6); -- Materiel Pro fournit des materiels et emballages.

INSERT INTO Unite (nom, description) VALUES
    ('Kg', 'Kilogrammes - utiliser pour les matieres solides comme la farine ou le sucre'),
    ('g', 'Grammes - utiliser pour de petites quantites comme le sel ou la levure'),
    ('L', 'Litres - utiliser pour les liquides comme le lait ou l huile'),
    ('mL', 'Millilitres - utiliser pour les liquides en petites quantites'),
    ('Piece', 'Unite - utiliser pour les articles comptes individuellement comme les œufs ou les pains');

INSERT INTO Ingredient (nom, idUnite) VALUES
    -- Matieres solides
    ('Farine', 1), -- Kg
    ('Sucre', 1), -- Kg
    ('Sel', 2), -- g
    ('Levure seche', 2), -- g
    ('Levure fraiche', 2), -- g
    ('Beurre', 1), -- Kg
    ('Chocolat en poudre', 2), -- g
    ('Fruits secs', 2), -- g

    -- Liquides
    ('Lait', 3), -- L
    ('Eau', 3), -- L
    ('Huile vegetale', 3), -- L
    ('Extrait de vanille', 4), -- mL
    ('Aromes alimentaires', 4), -- mL

    -- Articles comptes individuellement
    ('Œufs', 5), -- Piece
    ('Fruits frais', 5), -- Piece

    -- Autres ingredients
    ('Creme patissiere', 3), -- L
    ('Chantilly', 3), -- L
    ('Pate d amande', 1), -- Kg
    ('Confiture', 3), -- L
    ('Glace royale', 3); -- L

INSERT INTO Stock_Ingredient (dateMouvement, isEntree, prixUnitaire, quantite, idIngredient) VALUES
    -- Farine (Entrees et sorties)
    ('2025-01-01', TRUE, 1.20, 50, 1), -- Achat de 50 Kg de farine a 1.20 €/Kg
    ('2025-01-05', FALSE, 1.20, 10, 1), -- Utilisation de 10 Kg de farine

    -- Sucre
    ('2025-01-02', TRUE, 1.50, 25, 2), -- Achat de 25 Kg de sucre a 1.50 €/Kg
    ('2025-01-06', FALSE, 1.50, 5, 2), -- Utilisation de 5 Kg de sucre

    -- Sel
    ('2025-01-03', TRUE, 0.01, 500, 3), -- Achat de 500 g de sel a 0.01 €/g
    ('2025-01-07', FALSE, 0.01, 200, 3), -- Utilisation de 200 g de sel

    -- Lait
    ('2025-01-04', TRUE, 0.90, 20, 9), -- Achat de 20 L de lait a 0.90 €/L
    ('2025-01-08', FALSE, 0.90, 5, 9), -- Utilisation de 5 L de lait

    -- Œufs
    ('2025-01-05', TRUE, 0.25, 120, 13), -- Achat de 120 œufs a 0.25 €/piece
    ('2025-01-09', FALSE, 0.25, 30, 13), -- Utilisation de 30 œufs

    -- Creme patissiere
    ('2025-01-10', TRUE, 3.00, 10, 17), -- Achat de 10 L de creme patissiere a 3.00 €/L
    ('2025-01-12', FALSE, 3.00, 3, 17); -- Utilisation de 3 L de creme patissiere

INSERT INTO Categorie (nom, description) VALUES
    ('Pain', 'Produits a base de farine, levure et eau, comme les baguettes ou les pains speciaux'),
    ('Viennoiseries', 'Produits riches en beurre ou en pate feuilletee, comme les croissants et pains au chocolat'),
    ('Patisseries', 'Desserts sucres elabores, comme les tartes, eclairs et mille-feuilles'),
    ('Sandwichs', 'Produits sales composes de pain garni, comme les paninis ou les sandwiches baguette'),
    ('Snacks', 'Petits encas sucres ou sales, comme les cookies, brownies ou quiches'),
    ('Gateaux', 'Gateaux d anniversaire ou de reception, comme les entremets ou layer cakes'),
    ('Boissons', 'Produits liquides vendus, comme les cafes, thes ou jus de fruits'),
    ('Produits sans gluten', 'Produits adaptes aux regimes sans gluten, comme certains pains ou biscuits'),
    ('Produits bio', 'Produits fabriques avec des ingredients issus de l agriculture biologique'),
    ('Produits saisonniers', 'Produits specifiques a des periodes de l annee, comme les galettes des rois ou buches de Noel');

INSERT INTO Parfum (nom) VALUES
    -- Parfums sucres
    ('Vanille'),
    ('Chocolat'),
    ('Fraise'),
    ('Framboise'),
    ('Citron'),
    ('Orange'),
    ('Noix de coco'),
    ('Caramel'),
    ('Pistache'),
    ('Amande'),
    ('Miel'),
    ('Cafe'),

    -- Parfums sales
    ('Fromage'),
    ('Jambon'),
    ('Poulet'),
    ('Thon'),
    ('Tomate'),
    ('Basilic'),
    ('Herbes de Provence'),
    ('epinards'),
    ('Champignon'),
    ('Oignon'),
    ('Poivre'),
    ('Saumon fume'),

    -- Parfum Nature
    ('Nature');

INSERT INTO Produit_fini (nom, prixVente, description, idCategorie, idParfum) VALUES 
    -- Pains
    ('Baguette', 1.00, 'Pain classique a la forme allongee, croustillant a l exterieur', 1, 25),  -- Nature
    ('Pain de Campagne', 2.50, 'Pain rond a la croute epaisse et a la mie alveolee', 1, 25),  -- Nature
    ('Pain Complet', 2.80, 'Pain a base de farine complete pour un gout plus rustique', 1, 25),  -- Nature
    ('Pain de Mie', 2.00, 'Pain doux a la mie fine et aeree', 1, 25),  -- Nature

    -- Viennoiseries
    ('Pain au Chocolat', 1.80, 'Pate feuilletee fourree de chocolat', 2, 2),  -- Chocolat
    ('Pain au Raisin', 2.00, 'Viennoiserie a base de pate levee garnie de raisins secs et creme patissiere', 2, 3),  -- Fraise
    ('Chausson aux Pommes', 2.20, 'Pate feuilletee garnie de compote de pommes', 2, 4),  -- Framboise
    ('Croissant Vanille', 1.50, 'Croissant feuillete avec une touche de vanille', 2, 1),  -- Vanille
    ('Croissant Chocolat', 1.60, 'Croissant fourre au chocolat', 2, 2),  -- Chocolat
    ('Croissant Fraise', 1.70, 'Croissant garni d une creme a la fraise', 2, 3),  -- Fraise
    ('Croissant Framboise', 1.80, 'Croissant fourre a la framboise', 2, 4),  -- Framboise
    ('Croissant Nature', 1.50, 'Croissant nature sans garniture', 2, 25),  -- Nature

    -- Patisseries
    ('Eclair au Chocolat', 3.00, 'Choux fourre a la creme au chocolat et glace', 3, 2),  -- Chocolat
    ('Tarte au Citron', 3.50, 'Pate sablee garnie de creme au citron et meringue', 3, 5),  -- Citron
    ('Mille-feuille', 4.00, 'Pate feuilletee avec une creme patissiere entre les couches', 3, 1),  -- Vanille
    ('Paris-Brest', 4.50, 'Pate a choux fourree a la creme pralinee', 3, 7),  -- Noix de coco

    -- Sandwichs
    ('Sandwich Jambon-Beurre', 3.50, 'Sandwich compose de jambon, beurre et baguette', 4, 14),  -- Jambon
    ('Panini Poulet', 4.00, 'Sandwich chaud avec du poulet, fromage et legumes', 4, 15),  -- Poulet
    ('Sandwich au Fromage', 3.00, 'Sandwich avec fromage frais, legumes et pain de campagne', 4, 13),  -- Fromage

    -- Gateaux
    ('Gateau au Chocolat', 5.00, 'Gateau moelleux au chocolat', 5, 2),  -- Chocolat
    ('Tarte aux Fruits', 5.50, 'Tarte garnie de fruits frais de saison', 5, 11),  -- Miel
    ('Buche de Noel', 6.00, 'Buche traditionnelle de Noel avec une creme au beurre', 5, 12),  -- Cafe

    -- Boissons
    ('Cafe', 2.00, 'Cafe chaud, expresso ou long', 6, 12),  -- Cafe
    ('The', 2.20, 'The vert, noir ou parfume', 6, 11),  -- Miel
    ('Jus d Orange', 2.50, 'Jus d orange frais presse', 6, 6),  -- Orange

    -- Produits sans gluten
    ('Pain sans Gluten', 3.00, 'Pain fabrique sans gluten, ideal pour les personnes intolerantes', 1, 25),  -- Nature
    ('Cookie sans Gluten', 2.50, 'Cookie sucre a base de farine sans gluten', 5, 2),  -- Chocolat

    -- Produits bio
    ('Pain Bio', 3.20, 'Pain fabrique avec de la farine bio', 1, 25),  -- Nature
    ('Tarte Bio aux Fruits', 4.00, 'Tarte bio garnie de fruits frais de saison', 3, 11),  -- Miel

    -- Produits saisonniers
    ('Galette des Rois', 6.50, 'Galette traditionnelle a la frangipane pour l epiphanie', 5, 13),  -- Fromage
    ('Buche de Noel', 6.00, 'Buche traditionnelle de Noel avec une creme au beurre', 5, 12);  -- Cafe

-- Table Recette (Exemples pour quelques produits finis)
INSERT INTO Recette (idProduit, qteEstimee) VALUES
    (1, 10),
    (2, 8),
    (3, 6),
    (4, 12),
    (5, 15),
    (6, 10),
    (7, 10),
    (8, 12),
    (9, 14),
    (10, 10),
    (11, 12),
    (12, 10);

-- Table Recette_Detail (Exemples d'ingrédients pour quelques produits)
INSERT INTO Recette_Detail (idRecette, idIngredient, quantite) VALUES
    (1, 1, 1.5),  -- Baguette - Farine (Kg)
    (1, 3, 0.02), -- Baguette - Sel (g)
    (1, 9, 0.2),  -- Baguette - Lait (L)
    (2, 1, 2.0),  -- Pain de Campagne - Farine (Kg)
    (2, 3, 0.03), -- Pain de Campagne - Sel (g)
    (2, 9, 0.25), -- Pain de Campagne - Lait (L)
    (3, 1, 2.5),  -- Pain Complet - Farine (Kg)
    (3, 2, 0.1),  -- Pain Complet - Sucre (Kg)
    (3, 9, 0.3),  -- Pain Complet - Lait (L)
    (4, 1, 2.0),  -- Pain de Mie - Farine (Kg)
    (4, 3, 0.02), -- Pain de Mie - Sel (g)
    (4, 9, 0.2),  -- Pain de Mie - Lait (L)
    (5, 1, 0.6),  -- Pain au Chocolat - Farine (Kg)
    (5, 2, 0.05), -- Pain au Chocolat - Sucre (Kg)
    (5, 3, 0.01), -- Pain au Chocolat - Sel (g)
    (5, 9, 0.1),  -- Pain au Chocolat - Lait (L)
    (5, 16, 0.25), -- Pain au Chocolat - Creme patissiere (L)
    (6, 1, 0.8),  -- Pain au Raisin - Farine (Kg)
    (6, 2, 0.05), -- Pain au Raisin - Sucre (Kg)
    (6, 3, 0.015), -- Pain au Raisin - Sel (g)
    (6, 9, 0.15), -- Pain au Raisin - Lait (L)
    (6, 16, 0.15), -- Pain au Raisin - Creme patissiere (L)
    (7, 1, 0.6),  -- Chausson aux Pommes - Farine (Kg)
    (7, 2, 0.05), -- Chausson aux Pommes - Sucre (Kg)
    (7, 9, 0.1),  -- Chausson aux Pommes - Lait (L)
    (7, 16, 0.3), -- Chausson aux Pommes - Creme patissiere (L)
    (8, 1, 0.4),  -- Croissant Vanille - Farine (Kg)
    (8, 2, 0.03), -- Croissant Vanille - Sucre (Kg)
    (8, 9, 0.1),  -- Croissant Vanille - Lait (L)
    (8, 16, 0.15), -- Croissant Vanille - Creme patissiere (L)
    (9, 1, 0.45),  -- Croissant Chocolat - Farine (Kg)
    (9, 2, 0.035), -- Croissant Chocolat - Sucre (Kg)
    (9, 9, 0.12),  -- Croissant Chocolat - Lait (L)
    (9, 16, 0.2),  -- Croissant Chocolat - Creme patissiere (L)
    (10, 1, 0.4),  -- Croissant Fraise - Farine (Kg)
    (10, 2, 0.03), -- Croissant Fraise - Sucre (Kg)
    (10, 9, 0.1),  -- Croissant Fraise - Lait (L)
    (10, 16, 0.15), -- Croissant Fraise - Creme patissiere (L)
    (11, 1, 0.45), -- Croissant Framboise - Farine (Kg)
    (11, 2, 0.03), -- Croissant Framboise - Sucre (Kg)
    (11, 9, 0.12), -- Croissant Framboise - Lait (L)
    (11, 16, 0.18), -- Croissant Framboise - Creme patissiere (L)
    (12, 1, 0.5),  -- Croissant Nature - Farine (Kg)
    (12, 9, 0.15), -- Croissant Nature - Lait (L)
    (12, 3, 0.01); -- Croissant Nature - Sel (g)

INSERT INTO Fabrication (dateFabrication, idRecette, prixRevient) VALUES
    ('2025-01-10', 1, 5.50), -- Fabrication de la recette 1 pour le Baguette avec un prix de revient de 5,50 €
    ('2025-01-11', 2, 3.80), -- Fabrication de la recette 2 pour le Pain de Campagne avec un prix de revient de 3,80 €
    ('2025-01-12', 3, 4.00), -- Fabrication de la recette 3 pour le Pain Complet avec un prix de revient de 4,00 €
    ('2025-01-13', 4, 6.20), -- Fabrication de la recette 4 pour le Pain de Mie avec un prix de revient de 6,20 €
    ('2025-01-14', 5, 7.10), -- Fabrication de la recette 5 pour le Pain au Chocolat avec un prix de revient de 7,10 €
    ('2025-01-15', 6, 3.50), -- Fabrication de la recette 6 pour le Pain au Raisin avec un prix de revient de 3,50 €
    ('2025-01-16', 7, 2.10),  -- Fabrication de la recette 7 pour le Chausson aux Pommes avec un prix de revient de 2,10 €
    ('2025-01-17', 8, 1.90),  -- Fabrication de la recette 8 pour le Croissant Vanille avec un prix de revient de 1,90 €
    ('2025-01-18', 9, 2.00),  -- Fabrication de la recette 9 pour le Croissant Chocolat avec un prix de revient de 2,00 €
    ('2025-01-19', 10, 1.80), -- Fabrication de la recette 10 pour le Croissant Fraise avec un prix de revient de 1,80 €
    ('2025-01-20', 11, 1.90), -- Fabrication de la recette 11 pour le Croissant Framboise avec un prix de revient de 1,90 €
    ('2025-01-21', 12, 1.70); -- Fabrication de la recette 12 pour le Croissant Nature avec un prix de revient de 1,70 €

INSERT INTO Client (nom , email ,numero , adresse) VALUES
    ('Jean Dupont', 'jean@gmail.com' , 0345123456 , 'Lot 12 AB Ambohipo'),
    ('Marie Martin', 'martin@gmail.com' , 0341837232 ,  'Lot 15 AB Itaosy'),
    ('John Dylan', 'dylan@gmail.com' , 0341823256 ,  'Lot 17 AB Andoharanofotsy'),
    ('Harry Claude', 'harry@gmail.com' , 0341205612 ,  'Lot 18 CZ Itaosy'),
    ('Kiady Mihaja', 'kiady@gmail.com' , 0342514826 ,  'Lot 4 YI Andoharanofotsy');

INSERT INTO Vente (dateVente, idProduit, quantite, idClient) VALUES
    ('2025-01-01', 1, 50 , 2),  -- Vente de 50 Baguettes
    ('2025-01-02', 2, 30 , 1),  -- Vente de 30 Pains de Campagne
    ('2025-01-03', 3, 40 , 1),  -- Vente de 40 Pains Complets
    ('2025-01-04', 4, 20 , 3),  -- Vente de 20 Pains de Mie
    ('2025-01-05', 5, 25 , 3),  -- Vente de 25 Pains au Chocolat
    ('2025-01-06', 6, 35 , 3),  -- Vente de 35 Pains au Raisin
    ('2025-01-07', 7, 30 , 1),  -- Vente de 30 Chaussons aux Pommes
    ('2025-01-08', 8, 50 , 1),  -- Vente de 50 Croissants Vanille
    ('2025-01-09', 9, 40 , 1),  -- Vente de 40 Croissants Chocolat
    ('2025-01-10', 10, 60 , 1), -- Vente de 60 Croissants Fraise
    ('2025-01-11', 11, 45 , 1), -- Vente de 45 Croissants Framboise
    ('2025-01-12', 12, 55 , 1), -- Vente de 55 Croissants Nature
    ('2025-01-13', 13, 20 , 1), -- Vente de 20 Eclairs au Chocolat
    ('2025-01-14', 14, 15 , 1), -- Vente de 15 Tartes au Citron
    ('2025-01-15', 15, 10 , 1), -- Vente de 10 Mille-feuilles
    ('2025-01-16', 16, 12 , 2), -- Vente de 12 Paris-Brest
    ('2025-01-17', 17, 25 , 2), -- Vente de 25 Sandwichs Jambon-Beurre
    ('2025-01-18', 18, 20 , 2), -- Vente de 20 Paninis Poulet
    ('2025-01-19', 19, 18 , 2), -- Vente de 18 Sandwichs au Fromage
    ('2025-01-20', 20, 30 , 2), -- Vente de 30 Gateaux au Chocolat
    ('2025-01-21', 21, 22 , 1), -- Vente de 22 Tartes aux Fruits
    ('2025-01-22', 22, 25 , 1), -- Vente de 25 Buches de Noel
    ('2025-01-23', 23, 40 , 1), -- Vente de 40 Cafes
    ('2025-01-24', 24, 35 , 1), -- Vente de 35 Thes
    ('2025-01-25', 25, 50 , 1), -- Vente de 50 Jus d'Orange
    ('2025-01-26', 26, 30 , 2), -- Vente de 30 Pains sans Gluten
    ('2025-01-27', 27, 40 , 3), -- Vente de 40 Cookies sans Gluten
    ('2025-01-28', 28, 25 , 2), -- Vente de 25 Pains Bio
    ('2025-01-29', 29, 35 , 2), -- Vente de 35 Tartes Bio aux Fruits
    ('2025-01-30', 30, 20 , 3), -- Vente de 20 Galettes des Rois
    ('2025-01-31', 31, 22 , 1); -- Vente de 22 Buches de Noel
