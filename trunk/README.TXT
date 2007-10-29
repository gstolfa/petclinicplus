Maven2
======
mvn install
mvn eclipse:eclipse
mvn jetty:run-war


Eclipse Integration
===================

Test-Umgebungen
---------------
- Tomcat 5.5.29 + Eclipse 3.3 + Europa + JDK 1.5  -> Tests erfolgreich (siehe unten)
- Tomcat 6.0.14 + Eclipse 3.3 + Europa + JDK 1.5  -> Tests nicht erfolgreich (Probleme mit EL)

Integration von Jetty über Maven-Plugin funktioniert mit Einschränkungen.
Der Server lässt nicht nicht wieder beenden. Abhilfe kann TCPView schaffen.
Konfiguration:
- Open External Tools Dialog: Maven-Aufruf mit jetty:run-war.
  Als Umgebungsoptionen setzen: MAVEN_OPTS=-Xms256m -Xmx256m -XX:PermSize=256m -XX:MaxPermSize=256m 
                                           -Xdebug -Xnoagent -Djava.compiler=NONE 
                                           -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n
- Debug Remote Application (Localhost, Port 4000) 
                                          

On-the-fly-Änderungen am Java Code oder FTL-Templates
-----------------------------------------------------
Folgende Konfiguration läßt die Änderung an Java-Code und
FTL-Dateien einer laufenden Anwendung zu, ohne dass diese neu
deployt werden muss.

1. Preferences > Server > Installed Runtimes: Tomcat 5.5 konfigurieren.
2. Project > Properties > Target Runtimes: Tomcat-Runtime markieren.
3. Project > Properties > Server: Tomcat-Runtime markieren.
4. Project > Properties > J2EE Module Dependencies: Alles markieren
   WICHTIG:
   - Unter Tomcat 5.5 Häkchen bei log4j und commons-logging entfernen.
     (Befindet sich commons logging im WEB-INF/lib-Verzeichnis kommt es beim Neuladen des
     Web Application Kontexts zur NullPointerException)
   - Unter Tomcat 6 müssen alle Einträge markiert sein.
5. Project > Properties > Web Project Settings: Gewünschten Kontext-Namen einstellen.
6. Servers View öffnen und prüfen, ob Anwendung deployt wird.
7. Projekt markieren + rechte Maustaste > Debug As > Debug on Server





