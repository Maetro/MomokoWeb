#!/usr/bin/env bash

sudo apt-get update
sudo apt-get install -y nginx

netstat -anltp | grep LISTEN

sudo apt-get install -y mysql-server

cd /vagrant
mysqladmin -u root -proot create momokobd
mysql -u root -proot momokobd < mysql/start_BD.sql

sudo nano /etc/mysql/mysql.conf.d/mysqld.cnf
#comment out
#bind-address = 127.0.0.1 
mysql -u root -proot -e "CREATE USER 'local'@'%' IDENTIFIED BY 'root';"
mysql -u root -proot -e "GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root';"
mysql -u root -proot -e  "FLUSH PRIVILEGES;"

sudo -s 
service mysql restart
exit

sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update -y
sudo apt-get install -y oracle-java8-installer
cd /opt/
sudo wget http://www-eu.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
sudo tar -xvzf apache-maven-3.3.9-bin.tar.gz
sudo mv apache-maven-3.3.9 maven
sudo rm apache-maven-3.3.9-bin.tar.gz

sudo nano /etc/profile.d/mavenenv.sh

#Add the following lines:
M2_HOME=/opt/maven
PATH=$PATH:$M2_HOME/bin
export M2_HOME
export PATH
#END

#sudo -s
chmod +x /etc/profile.d/mavenenv.sh
source /etc/profile.d/mavenenv.sh
#exit


curl -sL https://deb.nodesource.com/setup_9.x | sudo -E bash -
sudo apt-get install -y nodejs
sudo apt-get install -y npm
sudo npm install pm2@latest -g

cd /home/backend
mvn clean install -DskipTests

cd /home/frontend
sudo npm install -g @angular/cli
sudo npm install