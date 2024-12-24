clear.jar: clear.kt
	kotlinc clear.kt -include-runtime -d clear.jar
	
clear: clear.jar
	java -jar clear.jar

commit:
	make clear
	git add .
	git commit -m "$(MENSAJE)"
	git push

test:
	bash test.sh