# 313448888
# israele4

compile: bin
	javac -cp src -d bin src/Factorial.java src/DescribeNumbers.java src/Sort.java

run1:
	java -cp bin Factorial 5

run2:
	java -cp bin DescribeNumbers 12 2 3 -9 8

run3:
	java -cp bin Sort asc 12 2 3 -9 8

check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*.java
 
bin:
	mkdir bin