## MASS - Mobile Advanced Shopping Service

Das Projekt **MASS** ist im fünften Semester im Studiengang Medieninformatik an der Fachhochschule Köln, Campus Gummersbach, im Modul *Entwicklungsprojekt interaktiver Systeme* entstanden. MASS ist eine Java-Enterprise Anwendung, die

* das Eintragen von Angeboten ermöglicht,
* eine GCM-Registrierung vom Android Geräten entgegennimmt,
* das Abonnieren von verschiedenen Topics über REST anbietet und
* mithilfe eines Message-Brokers die Angebote an die jeweiligen Abonnenten verteilt.

### Build und Deployment

#### Datenbank Server starten
* mvn exec:java -Dexec.classpathScope=runtime -Dexec.mainClass=org.h2.tools.Server -Dexec.args="-tcp -baseDir ./var"

#### Projekt bauen:
* mvn clean install

#### Web Server Starten
* mvn jetty:run
