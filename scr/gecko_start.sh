#!/bin/bash

# vars
coin="btc"
value="44000"

while true; do
	python3 gecko.py -c ${coin} -v ${value} >> /opt/coin-gecko/logs/gecko.${coin}.log
	sleep 300
done
