@echo off
echo Building FallenGod Testament Plugin...
echo.

REM Check if Maven is installed
mvn --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven from https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

REM Clean and compile
echo Cleaning previous builds...
mvn clean

echo Compiling and packaging...
mvn package

if %errorlevel% equ 0 (
    echo.
    echo SUCCESS: Plugin built successfully!
    echo JAR file location: target\testament-1.7.0.jar
    echo.
    echo You can now copy this JAR to your server's plugins folder.
) else (
    echo.
    echo ERROR: Build failed. Check the output above for errors.
)

pause