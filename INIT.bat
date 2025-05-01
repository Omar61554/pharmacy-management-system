@echo off

:: Base directory
set BASE=pharmacy-management-system

:: Main application
mkdir %BASE%\app
echo. > %BASE%\app\Main.java

:: Controllers
mkdir %BASE%\controller
echo. > %BASE%\controller\BranchController.java
echo. > %BASE%\controller\OrderController.java
echo. > %BASE%\controller\MedicineController.java

:: Models - split into sub-packages
mkdir %BASE%\model\person
echo. > %BASE%\model\person\Person.java
echo. > %BASE%\model\person\Manager.java
echo. > %BASE%\model\person\Pharmacist.java
echo. > %BASE%\model\person\Cashier.java
echo. > %BASE%\model\person\Customer.java
echo. > %BASE%\model\person\OnlineCustomer.java
echo. > %BASE%\model\person\OfflineCustomer.java

mkdir %BASE%\model\product
echo. > %BASE%\model\product\Medicine.java

mkdir %BASE%\model\order
echo. > %BASE%\model\order\Order.java
echo. > %BASE%\model\order\Payment.java

mkdir %BASE%\model\pharmacy
echo. > %BASE%\model\pharmacy\PharmacyBranch.java

:: Services - optionally split by domain
mkdir %BASE%\service\medicine
echo. > %BASE%\service\medicine\MedicineService.java

mkdir %BASE%\service\branch
echo. > %BASE%\service\branch\BranchService.java

mkdir %BASE%\service\order
echo. > %BASE%\service\order\OrderService.java

:: DAO
mkdir %BASE%\dao
echo. > %BASE%\dao\MedicineDAO.java

:: API
mkdir %BASE%\api
echo. > %BASE%\api\MedicineAPIClient.java
echo. > %BASE%\api\MedicineMapper.java
echo. > %BASE%\api\APIMedicineDTO.java

:: GUI
mkdir %BASE%\gui
echo. > %BASE%\gui\AdminDashboard.java
echo. > %BASE%\gui\CashierDashboard.java
echo. > %BASE%\gui\PharmacistDashboard.java

:: Recipe
mkdir %BASE%\recipe
echo. > %BASE%\recipe\Recipe.java

:: Storage
mkdir %BASE%\storage
echo. > %BASE%\storage\Storage.java

:: Utilities
mkdir %BASE%\util
echo. > %BASE%\util\MedicineSorter.java
echo. > %BASE%\util\Validator.java

:: Resources
mkdir %BASE%\resources
echo. > %BASE%\resources\config.properties

echo Project structure created successfully.
