--- MASS - Mobile Advanced Shopping Service ---

MASS ist ein ....


--- Build / Deployment ---

Datenbank Server starten
- mvn exec:java -Dexec.classpathScope=runtime -Dexec.mainClass=org.h2.tools.Server -Dexec.args="-tcp -baseDir ./var"

Projekt bauen:
- mvn clean install

Web Server Starten
- mvn jetty:run