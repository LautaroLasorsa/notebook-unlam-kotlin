#!/bin/bash
# Busca los problemas de prueba donde la solución aceptada no coincida con la versión actual de la solución o de las dependencias.
# Looks for test problems where the accepted solution does not match the current version of the solution or dependencies.

updated=0
outdated=0
missing=0

for problem_folder in "$NOTEBOOK_KOTLIN_ROOT"/*/*_test_problems; do
    cd "$problem_folder"
    for problem in *.kt; do
        name=$(basename $problem .kt)
        if [ ! -f $name"_completed.sha256" ]; then
            echo -e "\e[1;31mAC for problem $problem_folder/$problem = $name\\_completed.sha256 is missing\e[0m"
            missing=$((missing + 1))
        else
            curr_hash=$(get_recursive_content.sh < $problem | sha256sum)
            ac_hash=$(cat $name"_completed.sha256")
            echo "curr_hash: $curr_hash"
            echo "ac_hash: $ac_hash"
        #    get_recursive_content.sh < $problem 
            if [[ "$curr_hash" == "$ac_hash" ]]; then
                echo -e "\e[1;32mAC for problem $problem_folder/$problem is up to date\e[0m"
                updated=$((updated + 1))
            else
                echo -e "\e[1;31mAC for problem $problem_folder/$problem is outdated\e[0m"
                outdated=$((outdated + 1))
            fi
        fi
    done
done;

echo "----------------------------------------"
echo -e "\e[1;32mUpdated: $updated\e[0m"
echo -e "\e[1;31mOutdated: $outdated\e[0m"
echo -e "\e[1;31mMissing: $missing\e[0m"
echo "----------------------------------------"

if [ $outdated -gt 0 ] || [ $missing -gt 0 ]; then
    exit 1
fi