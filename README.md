# CodingKata


```
#!bash

./gradlew bootRun
```


## Wie benutze ich die API 
* Starte die Anwendung wie oben beschrieben
* Sende eine HTTP-POST oder HTTP-PUT Anfrage an die URL http://localhost:8080/api/rock-paper-scissors/play
* Body der HTTP-Anfrage ist in JSON-Format {"guess": "SCISSOR"} oder {"guess": "ROCK"} oder {"guess": "PAPER"}
* Body der Antwort is {"result":"I_WIN"} => Du hast gewonnen.
* Body der Antwort is {"result":"THE\_OTHER_WINS"} => Computer hat gewonnen.
* Body der Antwort is {"result":"NO_WINNER"} => Computer hat gewonnen.
	

## Schere, Stein, Papier


Die Aufgabe ist Schere, Stein, Papier mit Hilfe der Programmiersprache Java und dem Spring Framework sowie Spring Boot
zu implementieren. Gespielt werden soll mittels REST-Aufrufen gegen den Computer. Die Request- und Response-Bodies
sollen mittels JSON übergeben bzw. entgegengenommen werden.
Entwickle so gewissenhaft, wie du auch Software im Alltag entwickeln würdest.
Regeln

Zwei Personen (in diesem Fall ein Mensch und der Computer) wählen gleichzeitig aus der Menge A einen Gegenstand aus.
A = {Schere, Stein, Papier}

## Gewertet wird nach den normalen Regeln:
* Ziehen beide den gleichen Gegenstand, so gilt dies als unentschieden
* Schere schneidet Papier => Schere gewinnt
* Papier wickelt Stein ein => Papier gewinnt
* Stein ist härter als Schere => Stein gewinnt

## Nicht-funktionale Anforderungen
1. Die zu verwendende Programmiersprache ist Java in der letzten Major-Version.
2. Die Anwendung lässt sich via Apache Maven oder Gradle ohne spezielle Anpassungen erstellen.
3. Die Anwendung ist Self-Contained und lässt sich direkt starten mit: java -jar <application.jar>
4. Stelle sicher, dass das Spiel funktioniert

## Hilfestellungen
1. https://de.wikipedia.org/wiki/Schere,_Stein,_Papier
2. http://spring.io/guides/gs/rest-service/
3. http://martinfowler.com/bliki/TestPyramid.html