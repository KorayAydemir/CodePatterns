.DEFAULT_GOAL := start

ifeq ($(OS),Windows_NT)
    detected_OS := Windows
else
    detected_OS := $(shell uname)
endif

ifeq ($(detected_OS),Windows)
	SEP = ;
	RMDIR = rmdir /s /q
else
	SEP = :
	RMDIR = rm -rf
endif

PACKAGES = $(wildcard ./lib/*.jar)

JAVAC_OPTS = -d ./build -cp $(PACKAGES)
JAVAC_CLASSES = $(wildcard ./src/*.java ./src/components/*.java ./src/pages/*.java)
compile:
	javac $(JAVAC_OPTS) $(JAVAC_CLASSES)

JAVA_OPTS = -cp ./build$(SEP)$(PACKAGES)
JAVA_MAIN_CLASS = src.EntryFrame
run:
	$(info $(detected_OS))
	java $(JAVA_OPTS) $(JAVA_MAIN_CLASS)
clean:
	$(RMDIR) ./build

start: clean compile run
