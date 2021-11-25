FROM jenkins/jenkins:lts-jdk11

USER root
RUN apt-get update && apt-get install -y lsb-release
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
  https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) \
  signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
  https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli

# No utilizar en un ambiente de producción
ENV JENKINS_USER=admin
ENV JENKINS_PASS=admin
ENV JAVA_OPTS=-Djenkins.install.runSetupWizard=false
###

ADD plugins.txt .
RUN jenkins-plugin-cli -f plugins.txt
ADD ssh-config .
USER root
ADD start.sh .

USER jenkins
ENTRYPOINT [ "/start.sh" ]