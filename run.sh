#!/bin/bash

# vars
count=0
coin="btc"
value="44000"

while true; do
	date=$(date | xargs | tr ' ' '-' | cut -d"-" -f1,2,3,4)
	((count++))
	python3 gecko.py -c ${coin} -v ${value} && \
	       echo $date >> gecko.${coin}.log
	sleep 60
done
