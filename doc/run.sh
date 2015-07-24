START_CP=.
for file in /home/pprisk/ppriskruntime/riskcustomer66/lib/*.jar ;
do
  START_CP=$START_CP:$file
done;
echo $START_CP	
java -Xms1024m -Xmx1024m -XX:MaxPermSize=256M  -cp $START_CP  -Dsmsapp.basepath=/home/pprisk/ppriskruntime/riskcustomer66/bin cn.paypalm.risk.context.ServerMain  >/dev/null 2>&1 &

