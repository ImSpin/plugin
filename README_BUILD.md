# Building the FallenGod Testament Plugin

## Prerequisites

1. **Java 21 or higher** - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
2. **Apache Maven** - Download from [Maven Website](https://maven.apache.org/download.cgi)
3. **Git** (optional) - For version control

## Setup Instructions

### 1. Install Java 21
- Download and install Java 21 JDK
- Verify installation: `java -version`
- Should show version 21.x.x

### 2. Install Maven
- Download Maven from the official website
- Extract to a folder (e.g., `C:\apache-maven-3.9.5` on Windows)
- Add Maven's `bin` directory to your system PATH
- Verify installation: `mvn --version`

### 3. Set Environment Variables (Windows)
```batch
set JAVA_HOME=C:\Program Files\Java\jdk-21
set MAVEN_HOME=C:\apache-maven-3.9.5
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
```

### 4. Set Environment Variables (Linux/Mac)
```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
export MAVEN_HOME=/opt/apache-maven-3.9.5
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
```

## Building the Plugin

### Option 1: Using Build Scripts

**Windows:**
```batch
build.bat
```

**Linux/Mac:**
```bash
chmod +x build.sh
./build.sh
```

### Option 2: Manual Maven Commands

```bash
# Clean previous builds
mvn clean

# Compile and package
mvn package

# Or do both in one command
mvn clean package
```

## Output

After successful build, you'll find:
- `target/testament-1.7.0.jar` - The plugin JAR file
- `target/classes/` - Compiled class files
- `target/maven-archiver/` - Build metadata

## Installing the Plugin

1. Copy `target/testament-1.7.0.jar` to your Paper server's `plugins/` folder
2. Start or restart your server
3. The plugin will create default configuration files
4. Check server console for "FallenGod Testament Plugin has been enabled!"

## Troubleshooting

### Common Issues:

1. **"mvn command not found"**
   - Maven is not installed or not in PATH
   - Install Maven and add to PATH

2. **"JAVA_HOME not set"**
   - Set JAVA_HOME environment variable to your JDK installation

3. **"Unsupported class file major version"**
   - Wrong Java version - ensure you're using Java 21

4. **Build fails with dependency errors**
   - Check internet connection (Maven downloads dependencies)
   - Try `mvn clean` first

### VS Code Setup:

1. Install "Extension Pack for Java" extension
2. Open the project folder in VS Code
3. VS Code should automatically detect the Maven project
4. Use Ctrl+Shift+P → "Java: Rebuild Projects" if needed

### IntelliJ IDEA Setup:

1. Open → Select the project folder
2. IntelliJ should auto-detect the Maven project
3. Wait for indexing to complete
4. Build → Build Project (Ctrl+F9)

## Development Workflow

1. Make code changes
2. Run `mvn clean package` to build
3. Copy new JAR to server plugins folder
4. Restart server to test changes

## Project Structure

```
FallenGodTestament/
├── src/main/java/com/fallengod/testament/
│   ├── FallenGodPlugin.java
│   ├── advancements/
│   ├── altars/
│   ├── commands/
│   └── world/
├── src/main/resources/
│   ├── plugin.yml
│   ├── config.yml
│   ├── altars.yml
│   └── data/fallengod/advancements/
├── pom.xml
└── target/ (generated after build)
```