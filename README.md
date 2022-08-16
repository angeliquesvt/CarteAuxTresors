# Carte aux trésors


#### Exercice pour CarbonIT  
Créer une carte à partir d'un fichier et écrire les résultats dans un nouveau fichier tout en respectant les contraintes de l'exercice.

## Prérequis
Maven & Java 11

## Lancement du projet:
Aller dans le dossier du projet et installer les dépendances:
```
mvn clean install  
```
Pour exécuter le projet, aller dans le dossier target et lancer la commande suivante ***en passant le chemin du fichier à lire en paramètre***:
```
java -jar LaCarteAuxTresors-1.0-SNAPSHOT.jar [fichierAlire]
```
Le résultat sera affiché dans le dossier target du projet dans GameResult.txt 

## Tests
Les tests d'intégrations se situent dans le dossier src/integration-tests
Les tests unitaires se situent dans le dossier src/test

## Architecture
Dans src/java :
* Models représentent les objets du jeu

* Service représentent les classes permettant d'effectuer des actions : 

`* CarteAuxTresorsFileService: Lecture et écriture de fichier `

`* CarteAuxTresorsPlayService Démarrer le jeu`

### 

## Langage:
Java 11  
Junit 5