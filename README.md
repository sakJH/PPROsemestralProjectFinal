# PPROsemestralProjectFinal
RecenzentiUHK - Spring boot Movie review website

Name: Recezenti UHK

## Used Technologies
- Java
- Spring Framework
- MySQL
- Docker (MySQL Server)

## IDEA version
Intellij IDEA ULTIMATE version 2022.2.3
Init setting
- Sprting Initializr
  -- Lang. -> Java
  -- Type -> Maven Project

Project structure
- JDK -> Oracle OpenJDK 19.0.1
- Language -> 19

## Description
Semestrální projekt na predmět Pokročilé progmování (PPRO) na FAKULTĚ INFORMATIKY A MANAGEMENTU NA UNIVERZITE V HRADCI KRÁLOVÉ


# Docker a DB

## Docker MySQL configuration script:

docker run --name uhk -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql

## IDEA DB setup
1. Database
2. Create new Data source MySQL
3. Database -> Right click -> Creat Schema "uhk"
4. Run "RecenzentiUhk2Application" (it create DB tables)
5. Run Script on uhk schema "import.sql" (import data to database)


# Git
Final version
https://github.com/sakacj/PPROsemestralProjectFinal

Old version
https://github.com/sakacj/PPROsemestralProject
