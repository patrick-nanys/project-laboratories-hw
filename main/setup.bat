@echo off
copy src\*.java out\
copy src\Model\*.java out\Model\
copy src\Controller\*.java out\Controller\
copy src\Graphics\*.java out\Graphics\
cd out
javac Main.java
del *.java
del Model\*.java
del Controller\*.java
del Graphics\*.java
cd ..