#FROM ubuntu:latest as intermediate
#
#RUN apt-get update && apt-get install -y git
#
#ARG SSH_KEY
#
#RUN mkdir /root/.ssh/
#
#RUN echo "${SSH_KEY}" > /root/.ssh/id_rsa
#
#RUN chmod 600 /root/.ssh/id_rsa
#
#RUN ssh-keyscan gitlab.stackroute.in > /root/.ssh/known_hosts
#
#RUN git clone git@gitlab.stackroute.in:ananyo.sen/movie-rentals.git && cd movie-rentals && git checkout dev

FROM maven:latest

#COPY --from=intermediate /movie-rentals /movie-rentals/

COPY "${pwd}"/ /movie-rentals/

RUN ls /movie-rentals/


#RUN cd /movie-rentals/ && mvn package

EXPOSE 8080

CMD cd /movie-rentals/ && mvn package && mvn spring-boot:run -DskipTests