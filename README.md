Enne projekti seadistamist veenduge, et teil on installitud järgmine:
Java 21 - Spring Boot taustaprogrammi käitamiseks
Gradle 8.x - Backendi ehitamiseks
PostgreSQL 15.x või uuem - andmebaasi jaoks
Node.js 18.x või uuem - Vue.js kasutajaliidese käitamiseks
npm 8.x või uuem - kaasas Node.js kasutajaliidese haldamiseks
Git - hoidla kloonimiseks
Kaasaegne veebibrauser
IntelliJ IDEA

Hoidla kloonimine:
git clone https://github.com/JyriSem/flightreservation.git

PostgreSQL-i andmebaasi seadistamine:
CREATE DATABASE flightdb;

src/main/resources/application.properties:
asendage andmebaasi kasutajanimi ja parool

Installi sõltuvused / bash:
./gradlew build

Käivitage Spring Boot "flightreservation" rakendus enne Vite/Vue kasutajaliidest,
veenduge andmebaasi kättesaadavuses.
