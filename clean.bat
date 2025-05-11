@echo off
setlocal enabledelayedexpansion

echo Removing all compiled files in the entire project...

:: Delete all .class files recursively in Magda
for /r Magda %%f in (*.class) do del "%%f"

:: Delete MagdaProgram.java in specific folders
del /q "Magda\CompiledCode\MagdaProgram.java"
for /d %%d in (Magda\src\*) do (
    if exist "%%d\obj\MagdaProgram.java" del /q "%%d\obj\MagdaProgram.java"
)

:: Delete generated Java files in Parser and PreParser
del /q "Magda\Parser\*.java"
del /q "Magda\PreParser\*.java"

echo.
echo Removal was successful.
echo.
