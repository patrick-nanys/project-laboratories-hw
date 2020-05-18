@echo off
copy src\*.java out\
copy src\Model\*.java out\
copy src\Controller\*.java out\
copy src\Graphics\*.java out\
cd out
javac *.java
del *.java
cd ..