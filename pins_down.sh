 #!/bin/bash

PORTS=`sudo raspi-gpio get | grep func=INPUT | awk '{ print $2 }' | tr -d : | grep [0-2][0-7]`

for PORT in $PORTS
do
	echo "Setting port $PORT to output low..."
	sudo raspi-gpio set $PORT op
done

PORTS=`sudo raspi-gpio get | grep func=OUTPUT | grep level=1 | awk '{ print $2 }' | tr -d : | grep [0-2][0-7]`

for PORT in $PORTS
do
        echo "Setting port $PORT to low..."
        sudo raspi-gpio set $PORT dl
done

