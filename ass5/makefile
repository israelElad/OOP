# 313448888
# israele4

compile: bin
	find src -name "*.java" > sources.txt
	javac -d bin -cp biuoop-1.4.jar: @sources.txt 
jar:
	jar cfm  ass5game.jar ./Manifest.mf  -C bin/ .
run:
	java -jar  ass5game.jar 
bin:
	mkdir bin
