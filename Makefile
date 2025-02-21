bin/clear.jar: bin/clear.kt
	kotlinc bin/clear.kt -include-runtime -d clear.jar
	
clear: bin/clear.jar
	java -jar bin/clear.jar

commit:
	make clear
	git add .
	git commit -m "$(MENSAJE)"
	git push

test:
	bash test.sh