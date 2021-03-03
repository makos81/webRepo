call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browserStart
echo Error running script runcrud.bat

:browserStart
start chrome
if "%ERRORLEVEL%" == "0" goto getTask
echo Error during starting chrome

:getTask
start "" "http://localhost:8080/crud/v1/task/getTasks"

:endTask
echo Running webpage finished