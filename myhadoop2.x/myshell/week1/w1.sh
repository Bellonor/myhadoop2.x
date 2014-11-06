#! /bin/bash
# echo "ala" |sudo -S  mkdir /var/backups/etc.$today
today=`date +%Y%m%d`
yesterday=`date +%Y%m%d --date="-1 day"`
if [ ! -d /var/backups/etc.$today ] 
then 
    mkdir /var/backups/etc.$today
    echo 'in'
    cp -r /etc/*  /var/backups/etc.$today/
    
    same = diff -aBr /var/backups/etc.$today /var/backups/etc.$yesterday
    echo $same
    if [ $same -eq 0 ]
    then
       rm -R /var/backups/etc.$yesterday
    fi
fi
echo '22'
if [ ! -d /var/backups/etc.$yesterday ]
then
	exit
fi
if [  -d /var/backups/etc.$today ] 
then 
	same=`diff -aBr /var/backups/etc.$today /var/backups/etc.$yesterday 2> /dev/null | wc -l`
    #same=0;
    echo $same
    if [ $same -eq 0 ]
 then 
	echo 'yes'
 fi
    
    if [ $same -eq 0 ]
    then
       rm -R /var/backups/etc.$yesterday
     
    fi
fi
	