-- Les rôles
INSERT INTO role (designation)
VALUES ("Client"),
       ("Ouvrier"),
       ("Administrateur"),
       ("Autre");

-- Les utilisateurs + leur rôle
INSERT INTO user (pseudo, password, role_id)
VALUES ("Céline", "$2y$10$KzsVech8MNTVVcA1bPGrPORQU5kbq3pyEoy2zvxSCLB3hZBV3ZVta", 1),
       ("Fadime", "$2y$10$KzsVech8MNTVVcA1bPGrPORQU5kbq3pyEoy2zvxSCLB3hZBV3ZVta", 2),
       ("Yasmina", "$2y$10$KzsVech8MNTVVcA1bPGrPORQU5kbq3pyEoy2zvxSCLB3hZBV3ZVta", 3),
       ("Autre", "$2y$10$KzsVech8MNTVVcA1bPGrPORQU5kbq3pyEoy2zvxSCLB3hZBV3ZVta", 4),
       ("Ouvrier", "$2y$10$KzsVech8MNTVVcA1bPGrPORQU5kbq3pyEoy2zvxSCLB3hZBV3ZVta", 2),
       ("Flo", "$2y$10$KzsVech8MNTVVcA1bPGrPORQU5kbq3pyEoy2zvxSCLB3hZBV3ZVta", 1);


-- Les chantiers
INSERT INTO chantier (nom, adresse, client_id, ouvrier_id)
VALUES ("MNS", "Avenue de Metz", 1, 2),
       ("IFA", "Avenue du Chantier", 6, 2),
       ("MNSIFA", "Boulevard des travaux", 1, 2);

-- Les tâches
INSERT INTO tache (nom, temps_realisation)
VALUES ("Mettre du parquet", 60),
       ("Refaire la peinture", 30),
       ("Terminer le TP", 100),
       ("Péter les murs", 45);

-- Les consommables
INSERT INTO consommable (nom)
VALUES ("Consommable 1"),
       ("Consommable 2"),
       ("Consommable 3");

-- Les opérations
INSERT INTO operation (nom, date, chantier_id, tache_id, ouvrier_id)
VALUES ("Opération 1", '2024-03-21', 1, 1, 2),
       ("Opération 2", '2024-03-22', 2, 2, 2),
       ("Opération 6", '2024-03-23', 3, 4, 2),
       ("Opération 5", '2024-03-23', 1, 4, 2),
       ("Opération 3", '2024-03-23', 3, 3, 2);

-- -- Les consommables aux tâches
INSERT INTO tache_consommable (tache_id, consommable_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
