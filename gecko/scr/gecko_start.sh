#!/bin/bash

# vars
count=0
coin="btc"
value="44000"

while [ $count -le 3 ]; do
	((count++))
	python3 gecko.py -c ${coin} -v ${value} >> /opt/coin-gecko/logs/gecko.${coin}.log
	sleep 300
done
