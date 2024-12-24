#!/bin/bash

shopt -s nullglob

echo "" > pruebas_fallidas.txt	# Limpiar archivo de pruebas fallidas

for carpeta in ./*/; do
    for test in $carpeta*_test.kt; do
        echo "Corriendo pruebas de $test"
        codigo=${test%_test.kt}.kt
        kotlinc $codigo $test -include-runtime -d test.jar
        mensaje_error=$(java -jar test.jar 2>&1 > /dev/null)

        if [ ! -z "$mensaje_error" ]; then
            echo "$test $mensaje_error">>pruebas_fallidas.txt
        fi
        
        rm test.jar
    done
done