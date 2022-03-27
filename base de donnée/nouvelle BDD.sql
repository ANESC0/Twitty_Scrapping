    --1)
create table Tweet 
(
    urlTweet VARCHAR(150) PRIMARY KEY NOT NULL,
    texteTweet VARCHAR(280),                         --le nombre max de caract?re pour un tweet
    dateTweet DATE NOT NULL,
    nbFav NUMBER(7) NOT NULL,
    nbRT NUMBER(7) NOT NULL,
    arobaseProfil VARCHAR(15) NOT NULL               --le nombre max de caract?re pour un arobase twitter 
);
--------------------------------------------------------------------------------------------------
--2)
create table Profil
(
    arobaseProfil VARCHAR(15) PRIMARY KEY NOT NULL,  --le nombre max de caract?re pour un arobase twitter 
    pseudo VARCHAR(50) NOT NULL,                     --le pseudo peut contenir maximum 50 caract?res
    biographie VARCHAR(160),                        --le nombre max de caract?re pour une biographie sur twitter
    nbFollowing NUMBER(8),
    nbFollowers NUMBER(8),
    localisation  VARCHAR(30),
    dateCompte DATE,
    urlCompte VARCHAR(150) NOT NULL
);

--------------------------------------------------------------------------------------------------
--4)
create table Suit
(
    idSuit NUMBER(9) PRIMARY KEY NOT NULL,
    Suivi VARCHAR(15) NOT NULL,
    Suiveur VARCHAR(15) NOT NULL
);
-----------------------------------------------------------------------------------------------------------------------

--ajout des diff?rentes foreign keys:

--ajouter foreign key 
ALTER TABLE Tweet ADD CONSTRAINT FK_TweetOwner FOREIGN KEY (arobaseProfil) REFERENCES Profil(arobaseProfil);

ALTER TABLE Suit ADD CONSTRAINT FK_ProfilSuivi FOREIGN KEY (Suivi) REFERENCES Profil(arobaseProfil);

ALTER TABLE Suit ADD CONSTRAINT FK_ProfilQuiSuit FOREIGN KEY (Suiveur) REFERENCES Profil(arobaseProfil);


SELECT * FROM Suit;

-----------------------------------------------------------------------------------------------------------------------

--insertion exemple profil
--INSERT INTO Profil VALUES('duskorm','duskorm','Voici ma biographie TWITTER.',1,1,'Région IV-A (Calabarzon), Répu',TO_DATE('12-2020','MM-YYYY'),'https://twitter.com/duskorm');
--INSERT INTO Profil VALUES('CMCnd','Cnd','Papa d@1PVcs.',861,14000,'Paris',TO_DATE('12-2011','MM-YYYY'),'https://twitter.com/CMCnd');


--insertion exemple tweet
--INSERT INTO Tweet VALUES('https://twitter.com/CMCnd/status/1498950338057064448?s=20=U2RA3p_5cAMwnmr20y-fxA','Tweet random du CND, 
--ca va vous?',TO_DATE('02-03-2022','DD-MM-YYYY'),26,1,'CMCnd');
--INSERT INTO Tweet VALUES('https://twitter.com/duskorm/status/1480980921847320584','Anesco is gay',TO_DATE('11-01-2022','DD-MM-YYYY'),0,0,'duskorm');
--InSERT INTO Tweet VALUES('https://twitter.com/duskorm/status/1478638997970767874?s=20=kZgBhKX5hZlyfxC7hxe-Ew','Eh jai pas compris Twitter cest vraiment de la merde t... WEEEU WEEEU et non je ne suis pas un mec paumé sur Twitter, ceci est une descente de police.
--BIEN JOUÉ JOE',TO_DATE('05-01-2022','DD-MM-YYYY'),0,0,'duskorm');


-----------------------------------------------------------------------------------------------------------------------

--affichage des 2 tables Profil et Tweet

--SELECT * FROM Profil;

--SELECT * FROM Tweet;

--SELECT * FROM Suit;


-----------------------------------------------------------------------------------------------------------------------

--suppression des donn?es dans les 2 tables Profil et Tweet

--DELETE FROM Profil;

--DELETE FROM Tweet;
