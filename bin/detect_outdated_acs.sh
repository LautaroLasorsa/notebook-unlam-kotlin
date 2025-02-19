#!/bin/bash

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
            cat $problem | sha256sum > __temp__.sha256
            if diff __temp__.sha256 $name"_completed.sha256" > /dev/null; then
                echo -e "\e[1;32mAC for problem $problem_folder/$problem is up to date\e[0m"
                updated=$((updated + 1))
            else
                echo -e "\e[1;31mAC for problem $problem_folder/$problem is outdated\e[0m"
                outdated=$((outdated + 1))
            fi
            rm __temp__.sha256
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