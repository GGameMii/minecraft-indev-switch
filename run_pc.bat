@echo off
setlocal
cd /d "%~dp0"

set "JAVA_CMD=java"
if exist "C:\Program Files\Zulu\zulu-8\bin\java.exe" (
    set "JAVA_CMD=C:\Program Files\Zulu\zulu-8\bin\java.exe"
)

echo ========================================================
echo Iniciar Minecraft Indev (Multiplayer + Controlos Embutidos)
echo ========================================================
echo Podes ligar qualquer comando USB / Bluetooth (Xbox, PS4/5, Switch Pro Controller)
echo.

"%JAVA_CMD%" -Djava.library.path=lib/natives -cp "minecraft_indev_clean.jar;lib/*" net.minecraft.client.Minecraft

pause
