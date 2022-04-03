#!/bin/bash

E_NOROOT="[!] You must be root."

if [ $(id -u) -ne 0 ]; then
	echo -e "\n${E_NOROOT}\n"
	exit 1
fi

sudo apt-get remove docker docker-engine docker.io containerd runc -y

sudo apt-get update && sudo apt-get upgrade -y

sudo apt-get install ca-certificates curl gnupg lsb-release -y

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg && \echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update && sudo apt-get install docker-ce docker-ce-cli containerd.io -y

sudo docker --version
