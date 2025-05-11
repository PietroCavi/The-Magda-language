set -e
echo 
printf "Compile Magda program: %s.magda (Magda parser step)" "$@"
echo
cp Magda/src/$@/$@.magda ./ > tmp.log
rm tmp.log
java Magda.Parser.Parser $@.magda >Magda/src/$@/obj/MagdaProgram.java
rm $@.magda

echo 
printf "Compile Magda program: $@ (Java compiler step)"
echo
javac Magda/src/$@/obj/*.java -Xlint

echo 
echo JOB DONE!
echo 
