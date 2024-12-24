clear.jar: clear.kt
	kotlinc clear.kt -include-runtime -d clear.jar
	
commit: clear.jar
	java -jar clear.jar
	git add .
	git commit -m "$(MENSAJE)"
	git push

test:
	bash test.sh