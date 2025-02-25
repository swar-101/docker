@echo off
REM Build Product Service
echo Building Product Service...
cd product-service
docker build -t product-service:dev .
cd ..

REM Build Order Service
echo Building Order Service...
cd order-service
docker build -t order-service:dev .
cd ..

REM Build Payment Service
echo Building Payment Service...
cd payment-service
docker build -t payment-service:dev .
cd ..

REM Build User Service
echo Building User Service...
cd user-service
docker build -t user-service:dev .
cd ..


echo ***************************************************************************************
echo Batch process ended. 

echo CHECK FOR ERRORS! If you see red lines, RESOLVE or REPORT. 
echo ***************************************************************************************

echo If NOT  
pause
exit
