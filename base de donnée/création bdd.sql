--1)
create table Tweet 
(
    idTweet NUMBER(7) PRIMARY KEY NOT NULL,
    texteTweet VARCHAR(280),                         --le nombre max de caract�re pour un tweet
    dateTweet DATE NOT NULL,
    nbFav NUMBER(7) NOT NULL,
    nbRT NUMBER(7) NOT NULL,
    urlTweet VARCHAR(150) NOT NULL,
    arobaseProfil VARCHAR(15) NOT NULL               --le nombre max de caract�re pour un arobase twitter 
);
--------------------------------------------------------------------------------------------------
--2)
create table Profil
(
    arobaseProfil VARCHAR(15) PRIMARY KEY NOT NULL,  --le nombre max de caract�re pour un arobase twitter 
    pseudo VARCHAR(50) NOT NULL,                     --le pseudo peut contenir maximum 50 caract�res
    biographie VARCHAR(160),                        --le nombre max de caract�re pour une biographie sur twitter
    nbFollowing NUMBER(8),
    nbFollowers NUMBER(8),
    localisation  VARCHAR(30),
    dateCompte DATE,
    urlCompte VARCHAR(150) NOT NULL
);
--------------------------------------------------------------------------------------------------
--3)
create table ObserveProfil
(
    arobaseCompte VARCHAR(15) PRIMARY KEY NOT NULL,  --le nombre max de caract�re pour un arobase twitter 
    pseudo VARCHAR(50) NOT NULL,                     --le pseudo peut contenir maximum 50 caract�res
    biographie VARCHAR(160) NOT NULL,
    nbFollowing NUMBER(8),
    nbFollowers NUMBER(8),
    urlProfil VARCHAR(150) NOT NULL
);
--------------------------------------------------------------------------------------------------
--4)
create table estSuiviPar
(
    idEstSuiviPar NUMBER(9) PRIMARY KEY NOT NULL,
    arobaseProfil VARCHAR(15) NOT NULL,  
    arobaseCompte VARCHAR(15) NOT NULL
);
--------------------------------------------------------------------------------------------------
--5)
create table Suit
(
    idSuit NUMBER(9) PRIMARY KEY NOT NULL,
    arobaseProfil VARCHAR(15) NOT NULL,
    arobaseCompte VARCHAR(15) NOT NULL
);
-----------------------------------------------------------------------------------------------------------------------

--ajout des diff�rentes foreign keys:

--ajouter foreign key 
ALTER TABLE Tweet ADD CONSTRAINT FK_TweetOwner FOREIGN KEY (arobaseProfil) REFERENCES Profil(arobaseProfil);

ALTER TABLE estSuiviPar ADD CONSTRAINT FK_CompteSuiviPar FOREIGN KEY (arobaseCompte) REFERENCES ObserveProfil(arobaseCompte);

ALTER TABLE estSuiviPar ADD CONSTRAINT FK_ProfilSuiviPar FOREIGN KEY (arobaseProfil) REFERENCES Profil(arobaseProfil);

ALTER TABLE Suit ADD CONSTRAINT FK_CompteSuit FOREIGN KEY (arobaseCompte) REFERENCES ObserveProfil(arobaseCompte);

ALTER TABLE Suit ADD CONSTRAINT FK_ProfilSuit FOREIGN KEY (arobaseProfil) REFERENCES Profil(arobaseProfil);


SELECT * FROM Profil;

-----------------------------------------------------------------------------------------------------------------------

--insertion exemple profil
INSERT INTO Profil VALUES('duskorm','duskorm','Voici ma biographie TWITTER.',TO_DATE('12-2020','MM-YYYY'),'https://twitter.com/duskorm');


--insertion exemple tweet
INSERT INTO Tweet VALUES(1,
'Eh j'ai pas compris Twitter c'est vraiment de la merde t... WEEEU WEEEU et non je ne suis pas un mec paum� sur Twitter, ceci est une descente de police.
BIEN JOU� JOE',
TO_DATE('05-01-2022','DD-MM-YYYY'),0,0,
'https://twitter.com/duskorm/status/1478638997970767874',
'duskorm');


-----------------------------------------------------------------------------------------------------------------------

--affichage des 2 tables Profil et Tweet

SELECT * FROM Profil;

SELECT * FROM Tweet;

-----------------------------------------------------------------------------------------------------------------------

--on met cette partie pour ceux qui ont l'ancienne version de la BDD

--ajout colonne nbFollower dans Profil
ALTER TABLE Profil ADD nbFollower NUMBER(8);

--ajout collone nbFollowing dans Profil
ALTER TABLE Profil ADD nbFollowing NUMBER(8);

-----------------------------------------------------------------------------------------------------------------------

--suppression des donn�es dans les 2 tables Profil et Tweet

DELETE FROM Profil;

DELETE FROM Tweet;


