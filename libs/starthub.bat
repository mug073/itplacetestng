@echo off 
start/min "cmd" java -jar selenium-server-standalone-2.53.0.jar -role hub

start/min "cmd" java -jar selenium-server-standalone-2.53.0.jar -role webdriver -hub http://localhost:4444/grid/register -port 5556 -browser browserName=chrome -Dwebdriver.chrome.driver=chromedriver.exe

start/min "cmd" java -jar selenium-server-standalone-2.53.0.jar -role webdriver -hub http://localhost:4444/grid/register