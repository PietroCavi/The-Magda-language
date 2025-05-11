set -e
echo
printf "Execute %s Magda program\n\n" "$@"
cp Magda/src/$@/obj/*.* ./Magda/CompiledCode > tmp.log
rm tmp.log
java Magda.CompiledCode.MagdaProgram
echo
