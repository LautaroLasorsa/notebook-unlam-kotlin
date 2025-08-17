#!/bin/bash
# Corre todos los tests automatizados y guarda las pruebas fallidas en pruebas_fallidas.log y los outputs en output.log
# Run all automated tests and save the failed tests in pruebas_fallidas.log and the outputs in output.log

shopt -s nullglob

rm -f pruebas_fallidas.log

for carpeta in "$NOTEBOOK_KOTLIN_ROOT"/*/; do
    cd "$carpeta"
    for test in *_test.kt; do
        run_test.sh "$carpeta" "$test" "../output.log" "../pruebas_fallidas.log"
    done
    cd ..
done
