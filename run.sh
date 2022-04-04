#!/bin/bash

# vars
D_SUCCESS="[+] STATE: UP"
# Errors
E_NOROOT="[!] You must be root to run this script."

if [ $(id -u) -ne 0 ]; then
	echo
	echo ${E_NOROOT}
	echo
else
	sudo docker-compose up -d --build && \
		echo 
		echo ${D_SUCCESS}
		echo
fi
