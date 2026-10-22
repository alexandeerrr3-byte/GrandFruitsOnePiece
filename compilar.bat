@echo off
setlocal EnableExtensions
cd /d "%~dp0"
echo ============================================
echo   Grand Fruits - One Piece Powers
 echo   Minecraft 1.20.1 / Forge / Java 17
 echo ============================================
echo.

if defined JAVA_HOME if exist "%JAVA_HOME%\bin\java.exe" set "PATH=%JAVA_HOME%\bin;%PATH%"
where java >nul 2>nul
if errorlevel 1 (
  echo ERROR: No encuentro Java. Instala un JDK 17 de 64 bits y configura JAVA_HOME.
  pause
  exit /b 1
)
for /f "tokens=3" %%V in ('java -version 2^>^&1 ^| findstr /i "version"') do set "JAVA_VERSION=%%~V"
if not defined JAVA_VERSION (
  echo ERROR: No pude leer la version de Java.
  java -version
  pause
  exit /b 1
)
echo Java detectado: %JAVA_VERSION%
if not "%JAVA_VERSION:~0,2%"=="17" (
  echo.
  echo ERROR: Este proyecto necesita JDK 17 para compilar Forge 1.20.1.
  echo El Java actual no parece ser la version 17.
  echo Configura JAVA_HOME para que apunte a tu carpeta de JDK 17 y vuelve a ejecutar este archivo.
  echo Ejemplo: C:\Program Files\Eclipse Adoptium\jdk-17...
  pause
  exit /b 1
)

set "GRADLE="
if exist "%~dp0.tools\gradle-8.8\bin\gradle.bat" set "GRADLE=%~dp0.tools\gradle-8.8\bin\gradle.bat"
if not defined GRADLE if exist "%~dp0..\.tools\gradle-8.8\bin\gradle.bat" set "GRADLE=%~dp0..\.tools\gradle-8.8\bin\gradle.bat"
if not defined GRADLE if exist "%USERPROFILE%\.tools\gradle-8.8\bin\gradle.bat" set "GRADLE=%USERPROFILE%\.tools\gradle-8.8\bin\gradle.bat"
if not defined GRADLE if defined GRADLE_HOME if exist "%GRADLE_HOME%\bin\gradle.bat" set "GRADLE=%GRADLE_HOME%\bin\gradle.bat"
if not defined GRADLE (
  where gradle >nul 2>nul
  if not errorlevel 1 set "GRADLE=gradle"
)
if not defined GRADLE (
  echo ERROR: No encuentro Gradle 8.8.
  echo Pon tu carpeta gradle-8.8 dentro de .tools, junto a esta carpeta o en %%USERPROFILE%%\.tools.
  echo Tambien puedes configurar GRADLE_HOME o anadir Gradle al PATH.
  pause
  exit /b 1
)
echo Gradle detectado: %GRADLE%
echo.
echo Iniciando compilacion. La primera vez necesita internet para descargar Forge y sus dependencias.
call "%GRADLE%" --no-daemon build
if errorlevel 1 (
  echo.
  echo La compilacion fallo. Revisa el mensaje de error anterior.
  echo Para Forge 1.20.1 confirma que el JDK activo sea Java 17 y que tengas conexion a internet.
  pause
  exit /b 1
)
echo.
echo COMPILACION CORRECTA. Archivo generado en:
echo %~dp0build\libs\grandfruits-onepiece-1.0.0.jar
echo Copialo a %%appdata%%\.minecraft\mods.
pause
