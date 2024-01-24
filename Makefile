.DEFAULT_GOAL := start

PACKAGES = $(wildcard ./lib/*.jar)

JAVAC_OPTS = -d ./build -cp $(PACKAGES)
JAVAC_CLASSES = $(wildcard ./src/*.java ./src/components/*.java)
compile:
	javac $(JAVAC_OPTS) $(JAVAC_CLASSES)

JAVA_OPTS = -cp ./build:$(PACKAGES)
JAVA_MAIN_CLASS = src.EntryFrame
run:
	java $(JAVA_OPTS) $(JAVA_MAIN_CLASS)
clean:
	rm -rf ./build

start: clean compile run
