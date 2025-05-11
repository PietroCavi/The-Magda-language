set -e
printf "Removing all compiled files in the entire project"

find Magda -type f -name "*.class" -delete
find Magda/CompiledCode -type f -name "MagdaProgram.java" -delete
find Magda/src/*/obj -type f -name "MagdaProgram.java" -delete

echo 
printf "Removal was successful"
echo
