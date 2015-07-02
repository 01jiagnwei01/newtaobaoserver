START_CP=.
for file in /usr/ppriskruntime/riskcustomer77/lib/*.jar ;
do
  START_CP=$START_CP:$file
done;
echo $START_CP	
java -Xms2048m -Xmx2048m -Xmn600m -Xloggc:/usr/ppriskruntime/riskcustomer77/bin/gclog -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/usr/ppriskruntime/riskcustomer77/bin/dump -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.port=22222 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=192.168.31.7 -XX:+HeapDumpOnOutOfMemoryError -cp $START_CP  -Dsmsapp.basepath=/usr/ppriskruntime/riskcustomer77/bin cn.paypalm.risk.context.ServerMain  >/dev/null 2>&1 &

