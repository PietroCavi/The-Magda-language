call javacc\bin\javacc  -OUTPUT_DIRECTORY:"Magda\Parser" Magda\Parser\javacc_source\Parser.jj 

call javac5 Magda/ProgramTree/Declarations/*.java Magda/ProgramTree/MixinExpressions/*.java Magda/ProgramTree/Expressions/*.java Magda/ProgramTree/Instructions/*.java Magda/ProgramTree/LValues/*.java Magda/ProgramTree/*.java Magda/mtj/*.java Magda/Parser/*.java Magda/Compiler/*.java

rem java Magda.Parser.Parser <Example.Magda