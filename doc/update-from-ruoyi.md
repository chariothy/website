# 如何从ruoyi上游更新版本

### 步骤都可以在idea中完成，这里仅做记录

1. 记下sql/ry_yyyymmdd.sql的日期
2. git fetch upstream
3. git checkout master
4. **git merge upstream/master**
5. 将ry_yyyymmdd.sql应用到数据库中
   1. 可能不在merge中，因为没有冲突
   2. 用gitdiff查看从上次来的更新
   3. 更改表结构时记录ALTER
6. 将表结构更改记录到flyway的sql中
7. 运行dkre flyway
8. bin/publish.bat

## 尤其第三步需要在idea中完成，.

在可视化界面中完成代码合并

git => merge => upstream/master