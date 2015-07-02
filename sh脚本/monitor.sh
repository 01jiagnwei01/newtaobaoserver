#/bin/sh
. /etc/rc.d/init.d/functions
RISK_LOG=/usr/ppriskruntime/riskcustomer66/riskCheck.log
touch $RISK_LOG
echo "log create success!"
while true;
do
    date >> $RISK_LOG
    echo "Start checking........." >>$RISK_LOG
    ps -fe |grep "riskcustomer66" |grep -v "grep" >/dev/null
if [ "$?" == "0" ]; 
 then
   echo ">>>risk is running,donothing"
   echo ">>>>risk is runing,donothing" >>$RISK_LOG
   echo_success
else
   echo ">>>risk is deaded,run it" >>$RISK_LOG
   echo_failure
   cd /usr/ppriskruntime/riskcustomer66/bin
   ./run.sh >/dev/null
   if [ "$?" == "0" ]; then
      echo ">>risk reset success......"
      echo ">>risk reset sucess.......">>$RISK_LOG
   else
      echo ">>risk reset failure....."
      echo ">>risk reset failure.....">>$RISK_LOG
      cd /usr/ppriskruntime/riskcustomer66/bin
     ./run.sh >/dev/null
   fi
fi
sleep 30
done
