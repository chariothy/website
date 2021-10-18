# 如何从ruoyi上游更新版本

### 步骤都可以在idea中完成，这里仅做记录

1. git fetch upstream
2. git checkout master
3. git merge upstream/master
4. 将ry_yyyymmdd.sql应用到数据库中
5. 将表结构更改记录到flyway中
6. bin/publish.bat

## 尤其第三步需要在idea中完成，在可视化界面中完成代码合并