FROM  openjdk as builder
WORKDIR /opt/app
COPY pom.xml ./

