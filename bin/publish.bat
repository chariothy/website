@ECHO OFF
cd %PROGRAMFILES(x86)%\CWRSYNC\bin
SET HOME=%HOMEDRIVE%%HOMEPATH%

SET CWOLDPATH=%PATH%
SET PATH=%CWRSYNCHOME%\BIN;%PATH%

rem Build ui
cd c:/projects/henry/website/ruoyi-ui
npm run build:prod

rsync.exe -avrHzP --progress --delete /cygdrive/c/projects/henry/website/ruoyi-ui/dist/static/ henry@10.8.9.88::website/public/static/
pause

rem Push index
cd C:\projects\henry\chariothy.github.io
copy /Y c:\projects\henry\website\ruoyi-ui\dist\index.html
git add .
git commit -m 'build'
git push origin main

Rem Build jar
cd c:/projects/henry/website
mvn clean package -Dmaven.test.skip=true
rsync.exe -avrHzP --progress /cygdrive/c/projects/henry/website/ruoyi-admin/target/website-admin.jar henry@10.8.9.88::website/
pause

Rem Docker compose
rsync.exe -avrHzP --progress /cygdrive/c/projects/henry/website/docker-compose.yml henry@10.8.9.88::website/
pause

Rem restart docker
ssh henry@10.8.9.88 "cd /www/website && docker-compose restart admin"