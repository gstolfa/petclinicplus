@echo off
set CATALINA_HOME=c:\Users\Lars\Tools\apache-tomcat-6.0
set PATH=%CATALINA_HOME%\bin;%PATH%
call mvnjdk5 clean install
copy /Y target\pcp.war %CATALINA_HOME%\webapps
del /S /F %CATALINA_HOME%\webapps\pcp
cd %CATALINA_HOME%\bin
call startup
