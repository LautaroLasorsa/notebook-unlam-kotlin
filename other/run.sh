kotlinc "$1".kt -include-runtime -d "$1".jar
for x in "$1"*.in; do
    echo ARCHIVO: $x
    cat $x
    echo ===============
    java -jar "$1".jar < $x
    echo ===============
done | tee -a $1.print