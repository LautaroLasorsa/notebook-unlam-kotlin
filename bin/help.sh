#!/bin/bash
# Imprime este mensaje de ayuda
# Print this help message

cd "$NOTEBOOK_KOTLIN_ROOT"/bin

for file in *.sh; do
    echo -e "----------------------------------------"
    echo -e "\033[1mFile: $file\033[0m"
    sed -n '2,3p' "$file"
    echo -e "----------------------------------------"
    
done