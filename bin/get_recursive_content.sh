#!/bin/bash

while IFS= read -r line || [[ -n "$line" ]]; do
    # Eliminar caracteres no visibles (como tabuladores, saltos de línea)
    line=$(echo "$line" | tr -s '[:space:]' ' ')
    
    # Leer cada palabra separada por espacio
    for word in $line; do
        if [[ "${word: -3}" == ".kt" ]]; then
            if [[ -f "$word" ]]; then
                # Leer el contenido del archivo "$word" y pasarlo a get_recursive_content.sh
                get_recursive_content.sh < "$word"
            fi
        else
            echo "$word"
        fi
    done
done
