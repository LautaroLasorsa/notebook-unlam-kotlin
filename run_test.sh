carpeta=$1
test=$2
output=$3

echo "Corriendo pruebas de $carpeta$test"
codigo=$(head -n 1 "$test" | cut -c 3-)
kotlinc $codigo $test -include-runtime -d test.jar
mensaje_error=$(java -jar -ea test.jar 2>&1 > ../output.txt)

if [ ! -z "$mensaje_error" ]; then
    echo -e "\e[1;31mFallo en $carpeta$test\e[0m"
    echo "$test $mensaje_error">>$output
fi
        
rm test.jar