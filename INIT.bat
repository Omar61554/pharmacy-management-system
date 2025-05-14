@echo off
REM filepath: b:\omar\semester8\Advanced programming\Project\create_structure.bat

REM Base directory for the project
set BASE_DIR=b:\omar\semester8\Advanced programming\Project

REM Create directories for packages
mkdir "%BASE_DIR%\app"
mkdir "%BASE_DIR%\controller"
mkdir "%BASE_DIR%\model"
mkdir "%BASE_DIR%\service"
mkdir "%BASE_DIR%\dao"
mkdir "%BASE_DIR%\gui"

REM Create Java files
echo package app; > "%BASE_DIR%\app\Main.java"
echo package controller; > "%BASE_DIR%\controller\MedicineController.java"
echo package model; > "%BASE_DIR%\model\Person.java"
echo package model; > "%BASE_DIR%\model\Customer.java"
echo package model; > "%BASE_DIR%\model\PharmacyWorker.java"
echo package model; > "%BASE_DIR%\model\Medicine.java"
echo package model; > "%BASE_DIR%\model\Order.java"
echo package service; > "%BASE_DIR%\service\IMedicineService.java"
echo package service; > "%BASE_DIR%\service\MedicineService.java"
echo package dao; > "%BASE_DIR%\dao\MedicineDAO.java"
echo package gui; > "%BASE_DIR%\gui\AdminPage.java"
echo package gui; > "%BASE_DIR%\gui\ServicePage.java"

REM Notify user
echo All folders and files have been created successfully.
pause