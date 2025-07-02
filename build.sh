#!/bin/bash

echo "Building FallenGod Testament Plugin..."
echo

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed or not in PATH"
    echo "Please install Maven from https://maven.apache.org/download.cgi"
    exit 1
fi

# Clean and compile
echo "Cleaning previous builds..."
mvn clean

echo "Compiling and packaging..."
mvn package

if [ $? -eq 0 ]; then
    echo
    echo "SUCCESS: Plugin built successfully!"
    echo "JAR file location: target/testament-1.7.0.jar"
    echo
    echo "You can now copy this JAR to your server's plugins folder."
else
    echo
    echo "ERROR: Build failed. Check the output above for errors."
    exit 1
fi