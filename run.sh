#!/bin/bash

# vars
D_SUCCESS="[+] Dockerfile built, container running."
# Errors
E_NOROOT="[!] You must be root to run this script."

if [ $(id -u) -ne 0 ]; then
	echo
	echo ${E_NOROOT}
	echo
else
	sudo docker build . --tag "coin-gecko-img:1.0" && \
		sudo docker run -d --name "gecko-script" -p 7000:7000 coin-gecko-img:1.0 && \
		echo 
		echo ${D_SUCCESS}
		echo
fi
