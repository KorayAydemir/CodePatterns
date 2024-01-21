.DEFAULT_GOAL := start

compile:
	javac @./javac/options @./javac/classes
run:
	java @./java/options @./java/classes
clean:
	rm -rf ./build

start: clean compile run
