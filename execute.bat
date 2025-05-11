@echo off
@echo.
@echo Execute %1 Magda program
@echo.
xcopy /Q /V /Y Magda\src\%1\obj\*.* .\Magda\CompiledCode > tmp.log
del tmp.log
java Magda.CompiledCode.MagdaProgram
@echo.
