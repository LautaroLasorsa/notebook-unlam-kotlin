#!/bin/bash

shopt -s nullglob

# echo "" > pruebas_fallidas.txt	# Limpiar archivo de pruebas fallidas

rm -f pruebas_fallidas.txt

for carpeta in "$NOTEBOOK_KOTLIN_ROOT"/*/; do
    cd $carpeta
    for test in *_test.kt; do
        run_test.sh "$carpeta" "$test" "../output.txt" "../pruebas_fallidas.txt"
    done
    cd ..
done
