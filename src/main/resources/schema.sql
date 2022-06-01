create table if not exists membre (
    num_membre bigint auto_increment primary key,
    statut varchar(255) not null ,
    nom varchar(255) not null ,
    prenom varchar(255) not null,
    mail varchar(255) not null ,
    mdp varchar(255) not null,
    ville_res varchar(255) not null,
    pays_res varchar(255) not null,
    niveau int not null ,
    statut_paiement varchar(255) not null,
    statut_inscription varchar(255) not null,
    aptitude_medicale varchar(255) not null
);
