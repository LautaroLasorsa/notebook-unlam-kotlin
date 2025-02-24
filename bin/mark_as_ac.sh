#!/bin/bash
# Marca un archivo .kt como solución a un problema de prueba. Formato de uso: ./mark_as_ac.sh <archivo> (sin extensión). Genera <archivo>_completed.sha256
# Mark a .kt file as a solution to a test problem. Usage: ./mark_as_ac.sh <file> (without extension). Generates <file>_completed.sha256

get_recursive_content.sh < "$1".kt | sha256sum > "$1"_completed.sha256
echo -e "\e[1;32m$1.kt marked as accepted\e[0m"