#!/bin/bash

# vars
count=0
# coin
c1="btc"
c2="eth"
c3="sol"
value="44000"

while [ $count -lt 3 ]; do
	((count++))
	python3 gecko.py -c ${c1} -c ${c2} -c ${c3} >> /opt/coin-gecko/logs/gecko.coin.log
	sleep 300
done
