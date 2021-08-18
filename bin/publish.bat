@ECHO OFF
cd %PROGRAMFILES(x86)%\CWRSYNC\bin
SET HOME=%HOMEDRIVE%%HOMEPATH%

SET CWOLDPATH=%PATH%
SET PATH=%CWRSYNCHOME%\BIN;%PATH%

SET SERVERHOST=10.8.9.88

Rem Build jar
echo ###################### Compiling API ######################
cd c:/projects/henry/website
rd /s/q ruoyi-admin\target\classes
call mvn clean package -Dmaven.test.skip=true
if "%errorlevel%"=="1" goto :failed

echo ###################### Uploading API ######################
rsync.exe -avrHzP --progress /cygdrive/c/projects/henry/website/ruoyi-admin/target/website-admin.jar henry@%SERVERHOST%::website/
if "%errorlevel%"=="1" goto :failed
pause

rem Build ui
echo ###################### Compiling VUE ######################
cd c:/projects/henry/website/ruoyi-ui
call npm run build:prod
if "%errorlevel%"=="1" goto :failed

echo ###################### Uploading VUE ######################
rsync.exe -avrHzP --progress --delete /cygdrive/c/projects/henry/website/ruoyi-ui/dist/ henry@%SERVERHOST%::website/public/
if "%errorlevel%"=="1" goto :failed
pause

rem Push index
echo ###################### Publishing homepage ######################
cd C:\projects\henry\chariothy.github.io
copy /Y c:\projects\henry\website\ruoyi-ui\dist\index.html
git add .
git commit -m 'build'
git push origin main
pause

Rem Docker compose
echo ###################### Synchronizing docker config
rsync.exe -avrHzP --progress /cygdrive/c/projects/henry/website/nginx.conf henry@%SERVERHOST%::website/
rsync.exe -avrHzP --progress /cygdrive/c/projects/henry/website/docker-compose.yml henry@%SERVERHOST%::website/
pause

Rem restart docker
echo ###################### Restarting docker service
ssh henry@%SERVERHOST% "cd /www/website && docker-compose down --remove-orphans && source ~/.bash_profile && docker-compose up --build -d"

goto end

:failed
echo.
echo.
echo ***************************************************************************************
echo                                   ERROR !!!
echo ***************************************************************************************
goto end

:end

echo ###################### - FINISHED - ######################
cd c:/projects/henry/website