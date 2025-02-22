#!/bin/bash
# Corre un test y guarda los resultados en un archivo. Formato de uso: ./run_test.sh <carpeta> <test> <output> <output_error>
# Run a test and save the results in a file. Usage: ./run_test.sh <folder> <test> <output> <output_error>

if [ "$#" -eq 1 ]; then
    carpeta="$(pwd)/"
    test=$1
    output="output.log"
    output_error="pruebas_fallidas.log"
elif [ "$#" -eq 4 ]; then
    carpeta=$1
    test=$2
    output=$3
    output_error=$4
else
    echo """Posibles usos:
    ./run_test.sh <test>
    ./run_test.sh <carpeta> <test> <output> <output_error>
    """
    exit 1
fi

echo "Corriendo pruebas de $carpeta$test"
echo "Corriendo pruebas de $carpeta$test" >> $output

codigo=$(head -n 1 "$test" | cut -c 3-)
kotlinc $codigo $test -include-runtime -d test.jar

if [ -f test.jar ]; then
    mensaje_error=$(java -jar -ea -Dfile.encoding=UTF-8 -XX:+UseSerialGC -Xss64m -Xms1920m -Xmx1920m test.jar 2>&1 >> $output)
else 
    mensaje_error="No se pudo compilar | Compiling failed"
    echo "$mensaje_error"
fi

if [ ! -z "$mensaje_error" ]; then
    echo -e "\e[1;31mFallo en $carpeta$test\e[0m"
    echo "$test $mensaje_error">>$output_error
fi
        
rm test.jar