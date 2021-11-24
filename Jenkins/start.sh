#!/bin/bash

if [ ! -f /var/jenkins_home/.ssh/id_rsa ]; then
    yes | ssh-keygen -t rsa -q -N "" -f /var/jenkins_home/.ssh/id_rsa
fi
cp -f ssh-config /var/jenkins_home/.ssh/config

chmod +x mvnw
/usr/local/bin/jenkins.sh