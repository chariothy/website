@ECHO OFF

cd %PROGRAMFILES(x86)%\CWRSYNC\bin
SET HOME=%HOMEDRIVE%%HOMEPATH%

SET CWOLDPATH=%PATH%
SET PATH=%CWRSYNCHOME%\BIN;%PATH%

SET SERVERHOST=10.8.9.88

CLS
color 0a

GOTO MENU
:MENU
ECHO.
ECHO.               =-=-=-=-=项目编译菜单 (Compile Menu)=-=-=-=-=
ECHO.
ECHO.                       1  前后端同时编译 Both
ECHO.
ECHO.                       2  仅编译后端服务 API
ECHO.
ECHO.                       3  仅编译前端服务 VUE
ECHO.
ECHO.                       4  退   出 Quit
ECHO.
ECHO.
ECHO.
echo.                请输入选择项目的序号(Select Menu):
set /p  ID=
if "%id%"=="1"  goto API

if "%id%"=="2" goto API

if "%id%"=="3" goto VUE

IF "%id%"=="4"  goto END
PAUSE

:API
Rem Build jar
echo ###################### Compiling API ######################
cd c:/projects/henry/website
rd /s/q ruoyi-admin\target\classes
call mvn clean package -Dmaven.test.skip=true
if "%errorlevel%"=="1" goto FAIL

echo ###################### Uploading API ######################
rsync.exe -avrHzP --progress /cygdrive/c/projects/henry/website/ruoyi-admin/target/website-admin.jar henry@%SERVERHOST%::website/
if "%errorlevel%"=="1" goto FAIL
pause

Rem Docker compose
echo ###################### Synchronizing docker config
rsync.exe -avrHzP --progress /cygdrive/c/projects/henry/website/nginx.conf henry@%SERVERHOST%::website/
rsync.exe -avrHzP --progress /cygdrive/c/projects/henry/website/docker-compose.yml henry@%SERVERHOST%::website/
pause

Rem restart docker
echo ###################### Restarting docker service
ssh henry@%SERVERHOST% "cd /www/website && docker-compose down --remove-orphans && source ~/.bash_profile && docker-compose up --build -d"

if "%id%"=="2" goto MENU

:VUE
rem Build ui
echo ###################### Compiling VUE ######################
cd c:/projects/henry/website/ruoyi-ui
call npm run build:prod
if "%errorlevel%"=="1" goto FAIL

echo ###################### Uploading VUE ######################
rsync.exe -avrHzP --progress --delete /cygdrive/c/projects/henry/website/ruoyi-ui/dist/ henry@%SERVERHOST%::website/public/
if "%errorlevel%"=="1" goto FAIL
pause

rem Push index
echo ###################### Publishing homepage ######################
cd C:\projects\henry\chariothy.github.io
copy /Y c:\projects\henry\website\ruoyi-ui\dist\index.html
git add .
git commit -m 'build'
git push origin main
pause

goto MENU

:FAIL
echo.
echo.
echo ***************************************************************************************
echo                                   ERROR !!!
echo ***************************************************************************************
goto MENU

:END
cd c:/projects/henry/website