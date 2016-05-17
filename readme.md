MuseumNFC2
RFID & Web Serv - LP SIL IDSE

Mon projet permet de lire une carte NFC (ici appelé Spot_NFC) associé à une oeuvre d’art dans une exposition afin d’avoir en retour des informations sur celle-ci pour les visiteurs.
Pour ce projet je voulais d’une part : 
Associer un ID de carte NFC avec des informations d’œuvres d’arts (nom du tableau, description du tableau) tous cela enregistrés dans une BDD SQLite pour la partie gestion (administration).

	Pouvoir consulter les données associées à un Spot_NFC à partir du Web en scannant une carte avec son mobile pour la partie cliente (visiteurs)

La lecture des données renvoyées peuvent se faire en visuel ou audio.

Le projet serait donc constitué d’une partie Serveur/BDD et d’une partie WEB cliente & admin (interface)

Les sources du projet peuvent être récupérées sur GitHub :




Installation :

 -Tous le projet est complet il faut simplement le cloner : 

 	git clone https://github.com/LoicPhenix/MuseumNFC2

 -Installer les libraries :

	Installer Maven
	Installer Jersey-library
	Installer javax.ws.rs
	Installer SQLite-jdbc-3.8.6

Une fois terminé, il faut lancer le serveur pour voir le projet. 
Dans un premier temps, on pourra utiliser PostMan (test de web Services)
