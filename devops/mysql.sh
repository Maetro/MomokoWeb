#!/usr/bin/env bash


cd /vagrant
mysqladmin -u root -proot create momokobd

mysql -u root -proot momokobd < mysql/start_BD.sql