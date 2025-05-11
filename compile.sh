
set -e
echo 
printf "Compile Magda program: %s.magda (Magda parser step)" "$@"
echo
cp Magda/src/$@/$@.magda ./ > tmp.log
rm tmp.log

if [ ! -d "Magda/src/$@/obj" ]; then
    mkdir "Magda/src/$@/obj"
fi

if java Magda.Parser.Parser $@.magda > Magda/src/$@/obj/MagdaProgram.java;then
    rm $@.magda 
else
    rm $@.magda
    exit 1
fi

echo 
printf "Compile Magda program: $@ (Java compiler step)"
echo
javac Magda/src/$@/obj/*.java -Xlint

echo 
echo JOB DONE!
echo 
