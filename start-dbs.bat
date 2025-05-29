@echo off
SET PGUSER=postgres
SET PGPASSWORD=sameer

REM Create Databases if they do not exist
echo Creating databases if not exists...

REM AutoDb
psql -U %PGUSER% -h localhost -tc "SELECT 1 FROM pg_database WHERE datname = 'AutoDb'" | findstr -q 1 || psql -U %PGUSER% -h localhost -c "CREATE DATABASE \"AutoDb\""

REM HomeDb
SET PGPASSWORD=password
psql -U %PGUSER% -h localhost -tc "SELECT 1 FROM pg_database WHERE datname = 'HomeDb'" | findstr -q 1 || psql -U %PGUSER% -h localhost -c "CREATE DATABASE \"HomeDb\""

REM SellerDb
psql -U %PGUSER% -h localhost -tc "SELECT 1 FROM pg_database WHERE datname = 'SellerDb'" | findstr -q 1 || psql -U %PGUSER% -h localhost -c "CREATE DATABASE \"SellerDb\""

REM FinalLifeInsurance
SET PGPASSWORD=manager
psql -U %PGUSER% -h localhost -tc "SELECT 1 FROM pg_database WHERE datname = 'FinalLifeInsurance'" | findstr -q 1 || psql -U %PGUSER% -h localhost -c "CREATE DATABASE \"FinalLifeInsurance\""

REM Optional - PetDb (if needed later)
REM psql -U %PGUSER% -h localhost -tc "SELECT 1 FROM pg_database WHERE datname = 'PetDb'" | findstr -q 1 || psql -U %PGUSER% -h localhost -c "CREATE DATABASE \"PetDb\""

echo All required databases created.

REM You can now run services manually from IntelliJ
pause
