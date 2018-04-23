@setlocal 
call 0_setClasspath.cmd

javac  src\*.java -d .\classes

@endlocal

PAUSE
