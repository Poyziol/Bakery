@echo off
setlocal

@REM Définir les dossiers des .classes et les librairies
set "bin=D:\Documents\S5\Mme Baovola\Projet S5-S3\Gestion_Boulangerie\bin"
set "lib=D:\Documents\S5\Mme Baovola\Projet S5-S3\Gestion_Boulangerie\lib"

@REM Définir leur destinataire
set "bin-dir=D:\Documents\S5\Mme Baovola\Projet S5-S3\Gestion_Boulangerie\webapps\WEB-INF\classes"
set "lib-dir=D:\Documents\S5\Mme Baovola\Projet S5-S3\Gestion_Boulangerie\webapps\WEB-INF\lib"

REM Définir le dossier source et le dossier de destination
set "source=D:\Documents\S5\Mme Baovola\Projet S5-S3\Gestion_Boulangerie\webapps"
set "destination=C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\gestion_boulangerie"

REM Vérifier si le dossier source existe
if not exist "%source%" (
    echo Le dossier source "%source%" n'existe pas.
    exit /b 1
)

REM Supprimer le dossier de destination s'il existe déjà
if exist "%destination%" (
    echo Le dossier source "%source%" existe déjà
    rmdir "%destination%"
)
echo Initialisation du déstination terminé
mkdir "%destination%"

@REM Copier les librairies et les classes dans webapps
xcopy "%bin%\*" "%bin-dir%\" /E /H /C /I
xcopy "%lib%\*" "%lib-dir%\"

REM Copier le contenu du dossier source vers le dossier de destination
xcopy "%source%\*" "%destination%\" /E /H /C /I

echo Copie terminée avec succès.
pause
