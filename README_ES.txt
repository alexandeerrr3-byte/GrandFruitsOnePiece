GRAND FRUITS: ONE PIECE POWERS
Proyecto fuente para Minecraft Java Edition 1.20.1 + Minecraft Forge 47.4.10
Requiere Java/JDK 17 de 64 bits.

QUÉ INCLUYE
- Gomu Gomu no Mi: puñetazo elástico hacia delante.
- Mera Mera no Mi: explosión de llamas en área.
- Pika Pika no Mi: velocidad, prisa y descarga de luz.
- Gura Gura no Mi: onda de choque.
- Reliquia de Haki: mejora temporal de ataque y resistencia.
- Iconos pixel-art propios y textos en español e inglés.

CÓMO COMPILAR EN WINDOWS
1. Instala Minecraft Java 1.20.1 y Forge para Minecraft 1.20.1.
2. Instala un JDK 17 de 64 bits (no basta con tener solo un Java Runtime).
3. Descomprime esta carpeta.
4. Ejecuta compilar.bat.
5. Si la compilación termina bien, el mod se crea en build\libs\grandfruits-onepiece-1.0.0.jar.
6. Copia ese JAR a %appdata%\.minecraft\mods y abre el perfil de Forge 1.20.1.

El script intenta encontrar Gradle 8.8 en .tools\gradle-8.8\bin\gradle.bat dentro de esta carpeta, en la carpeta superior o en %USERPROFILE%\.tools. También acepta Gradle si está configurado en PATH. Si lo tienes en otra ubicación, abre compilar.bat y cambia la ruta de GRADLE.

USO EN EL JUEGO
- En Creativo, busca las cuatro frutas en la pestaña Comida y bebidas.
- La Reliquia de Haki aparece en la pestaña Combate.
- Sostén uno de los objetos y haz clic derecho para activar el poder.
- Cada poder tiene una recarga. Los objetos son reutilizables en esta versión inicial.

NOTAS
- Este paquete incluye el código fuente y los recursos, no un JAR ya compilado. La compilación necesita descargar Forge/Gradle desde internet, y no fue posible descargar esas dependencias desde el entorno que generó este proyecto.
- Es un proyecto fan-made no oficial. Las mecánicas son una primera versión y todavía no incluye barcos, NPCs, jefes, animaciones 3D ni un sistema de progresión.

COMPILAR AUTOMATICAMENTE EN GITHUB (SIN INSTALAR JAVA NI GRADLE EN TU PC)
1. Entra en https://github.com/new e inicia sesion o crea una cuenta.
2. Crea un repositorio nuevo. Puedes llamarlo GrandFruitsOnePiece.
3. Descomprime este ZIP en tu PC.
4. Dentro de la carpeta GrandFruitsOnePiece, sube TODOS los archivos y carpetas al repositorio (incluida la carpeta .github). Puedes usar Add file > Upload files y arrastrarlos al navegador. Luego confirma con Commit changes.
5. Abre la pestaña Actions del repositorio. El flujo "Compilar mod Grand Fruits" se ejecutara al subir el codigo.
6. Cuando termine correctamente, abre esa ejecucion y descarga el archivo de Artifacts llamado grandfruits-onepiece-jar. Dentro estara grandfruits-onepiece-1.0.0.jar.
7. Copia el JAR a %appdata%\\.minecraft\\mods y abre Minecraft Java 1.20.1 con Forge 47.x.

Nota: la compilacion depende de que el codigo y las dependencias de Forge se resuelvan correctamente. Si aparece una X roja, abre la ejecucion en Actions y comparte el final del registro para corregir el error. GitHub Actions compila el proyecto en sus servidores; no instala el mod automaticamente dentro de tu Minecraft.
