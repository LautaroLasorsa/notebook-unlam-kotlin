gen_ac_mark.sh < "$1".k1 | sha256sum > "$1"_completed.sha256
echo -e "\e[1;32m$1.kt marked as accepted\e[0m"