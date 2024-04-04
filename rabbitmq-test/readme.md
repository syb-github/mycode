一、git代码上传至github
git config --global user.name "username"
git config --global user.email "email@qq.com"

生成公钥私钥：
ssh -keygen -t rsa -C "email@qq.com"
将C盘->用户->.ssh文件下生成的id_rsa.pub文件内容粘贴到github->setting->SSH and GPG keys->New SSH key->title->key->add

进入项目目录下执行：
git init 
-> git add . 
-> git commit -m "first commit" 
-> git remote add origin https://github.com/username/project.git 
-> git push -u origin master 

代码就上传到github了


二、redis 
1、报错：ERR Client sent AUTH, but no password is set

redis cmd -》 redis-cli -> config set requirepass "123456" -> auth 123456

三、lua脚本  用于处理redis缓存数据导致原子性
eval "return redis.call('set', KEYS[1], ARGV[1])" 1 name name-value
eval 执行脚本关键字
KEYS[1] 脚本中第一个参数key  lua脚本的下标从1开始  与java不同
ARGV[1] 脚本中第一个参数值
1 代表一个参数





