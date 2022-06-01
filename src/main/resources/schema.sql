create table if not exists membre (
    numMembre bigint auto_increment primary key,
    statut varchar(255) not null ,
    nom varchar(255) not null ,
    prenom varchar(255) not null,
    mail varchar(255) not null ,
    mdp varchar(255) not null,
    villeRes varchar(255) not null,
    paysRes varchar(255) not null,
    niveau int not null ,
    statutPaiement varchar(255) not null,
    statutInscription varchar(255) not null,
    aptitudeMedicale varchar(255) not null
);
