# 如何从ruoyi上游更新版本

### 步骤都可以在idea中完成，这里仅做记录
### 在可视化界面中完成代码合并

1. 拷贝sql/ry_yyyymmdd.sql到gen目录下（因为merge之后旧的sql会删除）
2. git fetch upstream
3. git checkout master
4. **git merge upstream/master** （<i>这一步需要在idea中完成: git => merge => upstream/master</i>）
5. 将ry_yyyymmdd.sql应用到数据库中
   1. 可能不在merge中，因为没有冲突
   2. 备份数据库，docker exec mysql bash /dump/cp-website-test.sh
   2. 在新的sql文件上右键，Compare with... gen/ry.sql 查看从上次来的更新
   3. 更改表结构时记录ALTER，用于flyway
6. 将表结构更改记录到schema/sql中新建文件Vyymmdd__upgrade_ruoyi.sql中
7. 发布项目schema
7. 运行dkre flyway
8. bin/publish.bat