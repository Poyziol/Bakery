INSERT INTO Fonction (nom, description) VALUES 
    ('Admin', 'Propriétaire de la boulangerie'),
    ('Boulanger', 'Préparation des pains et viennoiseries'),
    ('Pâtissier', 'Réalisation des gâteaux et desserts'),
    ('Aide-boulanger', 'Assistance au boulanger dans ses tâches'),
    ('Caissier', 'Accueil et vente des produits aux clients'),
    ('Manager', 'Supervision des employés et gestion du magasin'),
    ('Livreur', 'Livraison des commandes aux clients'),
    ('Chef de production', 'Planification et supervision de la production');

INSERT INTO Employe (nom, prenoms, dateNaissance, adresse, telephone, idFonction) VALUES 
    ('RABEZAKARISON', 'Miangaly', '2004-09-23', 'IPT 127 Antanety Bemasoandro', '0386667260', 1),
    ('RANDRIA', 'John', '1987-03-15', 'XW 17 OI Andranomena', '0342512387', 2),
    ('RAKOTO', 'Manampy', '1992-11-02', 'IT 22 X Antanimena', '0385102612', 2);

INSERT INTO Users (email, motDePasse, idEmploye) VALUES 
    ('nancy@gmail.com', 'nancy*5_2728', 1);

INSERT INTO Type_Produit (nom, description) VALUES 
    ('Fournitures de base', 'Ingrédients essentiels comme la farine, le sucre, et le sel.'),
    ('Produits de levée et de fermentation', 'Levure, levain et autres agents pour faire lever les pâtes.'),
    ('Produits laitiers et œufs', 'Lait, beurre, crème, et œufs utilisés en boulangerie et pâtisserie.'),
    ('Chocolats et confiseries', 'Chocolat, pralines, et décorations sucrées.'),
    ('Fruits et purées de fruits', 'Fruits frais, confits, ou en purée pour les garnitures.'),
    ('Matériel et emballage', 'Emballages comme boîtes à gâteaux et matériel de boulangerie.');
    -- ('Produits de décoration', 'Pâte d'amande, colorants, et autres éléments décoratifs.'),
    -- ('Boissons', 'Produits comme le café, le thé ou les boissons artisanales.'),
    -- ('Produits bio', 'Ingrédients certifiés biologiques et respectueux de l'environnement.'),
    -- ('Épices et arômes', 'Arômes et épices comme la vanille, la cannelle ou la fleur d'oranger.');

INSERT INTO Fournisseur (nomEntreprise, telephone, adresse, commentaire) VALUES 
    ('Farina Supplies', '0321234567', 'Lot II D 23, Ambanidia, Antananarivo', 'Fournisseur principal de farine et levure'),
    ('Sweet World', '0347654321', 'Lot 18 B, Ambohidahy, Fianarantsoa', 'Fournisseur de sucre et chocolats'),
    ('Lait Délices', '0331122334', 'Lot 5 A, Maroala, Mahajanga', 'Produits laitiers de haute qualité'),
    ('Fruit Passion', '0327654321', 'Lot 3 C, Ankirihiry, Toamasina', 'Fruits frais et purées naturelles'),
    ('Matériel Pro', '0341239876', 'Lot 7 D, Mandaniresaka, Antsirabe', 'Fournitures et emballages professionnels');

INSERT INTO Fournisseur_TypeProduit (idFournisseur, idType_Produit) VALUES 
    (1, 1), -- Farina Supplies fournit des fournitures de base
    (1, 2), -- Farina Supplies fournit des produits de levée
    (2, 4), -- Sweet World fournit des chocolats
    -- (2, 10), -- Sweet World fournit des épices
    (3, 3), -- Lait Délices fournit des produits laitiers
    (4, 5), -- Fruit Passion fournit des fruits
    (5, 6); -- Matériel Pro fournit des matériels et emballages.

INSERT INTO Unite (nom, description) VALUES 
    ('kg', 'Kilogramme'),
    ('l', 'Litre'),
    ('nb', 'Pièce');

INSERT INTO Ingredient (nom, idUnite) VALUES 
    ('Farine', 1),
    ('Levure', 1),
    ('Sucre', 1);

INSERT INTO Stock_Ingredient (dateMouvement, isEntree, prixUnitaire, quantite, idIngredient) VALUES 
    ('2024-01-01', true, 2.5, 50, 1), -- Entrée : Farine
    ('2024-01-01', true, 1.0, 20, 2), -- Entrée : Levure
    ('2024-01-01', true, 0.8, 30, 3), -- Entrée : Sucre
    ('2024-01-15', false, 2.5, 10, 1), -- Sortie : Farine
    ('2024-01-15', false, 1.0, 5, 2); -- Sortie : Levure

INSERT INTO Recette (qteEstimee) VALUES 
    (10), -- Recette pour 10 unités de produit
    (20); -- Recette pour 20 unités de produit

INSERT INTO Recette_Detail (idRecette, idIngredient, quantite) VALUES 
    (1, 1, 2.0), -- Recette 1 : 2 kg de farine
    (1, 2, 0.05), -- Recette 1 : 0.05 kg de levure
    (1, 3, 0.5), -- Recette 1 : 0.5 kg de sucre
    (2, 1, 3.0), -- Recette 2 : 3 kg de farine
    (2, 2, 0.08); -- Recette 2 : 0.08 kg de levure

INSERT INTO Categorie (nom, description) VALUES 
    ('Pains', 'Produits de boulangerie de base comme les baguettes, pains complets, etc.'),
    ('Viennoiseries', 'Produits feuilletés ou briochés comme les croissants et pains au chocolat.'),
    ('Pâtisseries', 'Desserts raffinés comme les éclairs, mille-feuilles et tartes.'),
    ('Produits salés', 'Snacks salés comme les quiches, sandwichs et pizzas.'),
    ('Gâteaux personnalisés', 'Gâteaux créés sur commande pour des occasions spéciales.'),
    ('Boissons', 'Boissons variées comme café, thé, et jus.'),
    ('Confiseries', 'Bonbons, chocolats, et autres sucreries.'),
    ('Produits bio ou sans gluten', 'Produits adaptés aux besoins spécifiques, bio ou sans gluten.');

INSERT INTO Produit_fini (nom, prixRevient, prixVente, description, idCategorie, idRecette) VALUES 
    ('Baguette classique', 0.30, 1.00, 'Pain classique français', 1, 1),
    ('Croissant', 0.50, 1.50, 'Viennoiserie feuilletée au beurre', 2, 1),
    ('Tarte aux fruits', 1.50, 4.00, 'Pâtisserie garnie de fruits frais', 3, 2),
    ('Quiche Lorraine', 2.00, 6.00, 'Tarte salée garnie de lardons', 4, 2),
    ('Gâteau anniversaire', 5.00, 15.00, 'Gâteau personnalisé pour les occasions spéciales', 5, 2);
