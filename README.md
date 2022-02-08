#一个项目一个分支, 例如：test_20200804

git init
git checkout -b test_20200804
git remote add origin git@gitlab2.dui88.com:spark/projects.git
git add .
git commit -am 'test'
git push origin test_20200804
