set -e

printf "\n"

javacc -OUTPUT_DIRECTORY:"Magda/Parser" Magda/Parser/javacc_source/Parser.jj 
javacc -OUTPUT_DIRECTORY:"Magda/PreParser" Magda/PreParser/javacc_source/PreParser.jj

printf "\n"

#this fixes the two warnings caused by Javacc 
sed -i -e 's/class/final class/g' ./Magda/Parser/ParserTokenManager.java 
sed -i -e 's/class/final class/g' ./Magda/PreParser/PreParserTokenManager.java 

javac Magda/ProgramTree/Declarations/*.java Magda/ProgramTree/MixinExpressions/*.java Magda/ProgramTree/Expressions/*.java Magda/ProgramTree/Instructions/*.java Magda/ProgramTree/LValues/*.java Magda/ProgramTree/*.java Magda/mtj/*.java Magda/PreParser/*.java Magda/PreParser/PreParserStructures/*.java Magda/Parser/*.java Magda/Compiler/*.java -Xlint
