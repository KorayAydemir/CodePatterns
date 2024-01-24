.DEFAULT_GOAL := start

ifeq ($(OS),Windows_NT)
    detected_OS := Windows
else
    detected_OS := $(shell uname)
endif

ifeq ($(detected_OS),Windows)
	JAVA_OPTS = -cp ./build;$(PACKAGES)
	SHELL = cmd.exe
	CLEAN_COMMAND = if exist build rmdir /s /q build
else
	JAVA_OPTS = -cp ./build:$(PACKAGES)
    	SHELL = /bin/bash
	CLEAN_COMMAND = rm -rf build
endif

PACKAGES = $(wildcard ./lib/*.jar)

JAVAC_OPTS = -d ./build -cp $(PACKAGES)
JAVAC_CLASSES = $(wildcard ./src/*.java ./src/components/*.java ./src/pages/*.java)
compile:
	javac $(JAVAC_OPTS) $(JAVAC_CLASSES)

JAVA_MAIN_CLASS = src.EntryFrame
run:
	java $(JAVA_OPTS) $(JAVA_MAIN_CLASS)
clean:
	$(CLEAN_COMMAND)

start: clean compile run
