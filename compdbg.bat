@echo off
@echo.
@echo Compile Magda program: %1.magda (Magda parser step)
@echo.
xcopy /Q /V /Y Magda\src\%1\%1.magda .\ 
java Magda.Parser.Parser %1.magda >Magda\src\%1\obj\MagdaProgram.java

@echo.
@echo Compile Magda program: %1 (Java compiler step)
@echo.
call javac5 Magda\src\%1\obj\*.java 

@echo.
@echo JOB DONE!
@echo.
