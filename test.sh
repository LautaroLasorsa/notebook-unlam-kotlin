#!/bin/bash

shopt -s nullglob

# echo "" > pruebas_fallidas.txt	# Limpiar archivo de pruebas fallidas

rm -f pruebas_fallidas.txt

for carpeta in ./*/; do
    cd $carpeta
    for test in *_test.kt; do
        echo "Corriendo pruebas de $carpeta$test"
        codigo=$(head -n 1 "$test" | cut -c 3-)
        kotlinc $codigo $test -include-runtime -d test.jar
        mensaje_error=$(java -jar test.jar 2>&1 > /dev/null)

        if [ ! -z "$mensaje_error" ]; then
            echo "$test $mensaje_error">>../pruebas_fallidas.txt
        fi
        
        rm test.jar
    done
    cd ..
done
