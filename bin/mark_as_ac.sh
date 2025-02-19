cat $1.kt | sha256sum > $1\_completed.sha256
echo -e "\e[1;32m$1.kt marked as accepted\e[0m"