#!/bin/bash
# Dado el contenido de un archivo, busca archivos .kt y los pasa a get_recursive_content.sh. Explora las dependencias de forma recursiva.
# Given the content of a file, it looks for .kt files and passes them to get_recursive_content.sh. Explores dependencies recursively.

while IFS= read -r line || [[ -n "$line" ]]; do
    # Eliminar caracteres no visibles (como tabuladores, saltos de línea)
    line=$(echo "$line" | tr -s '[:space:]' ' ' | sed 's/\*/\\*/g')
    
    for word in $line ; do
        if [[ "${word: -3}" == ".kt" ]]; then
            if [[ -f "$word" ]]; then
                # Leer el contenido del archivo "$word" y pasarlo a get_recursive_content.sh
                echo -e "\n<--$word\n" >> read_files.log
                cat "$word" >> read_files.log
                get_recursive_content.sh < "$word"
                echo -e "\n$word-->\n" >> read_files.log
                
            fi
        else
            echo "$word"
        fi
    done
done
