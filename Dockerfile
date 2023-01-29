FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

RUN  microdnf install git \
    && microdnf install boost-devel boost \
    && microdnf install gcc-c++ automake  make
RUN   git clone https://github.com/critter-mj/akochan.git \
    && cd akochan  \
    && cd ai_src \
    && make -f Makefile_Linux \
    && cd .. \
    && make -f Makefile_Linux \
    && cp -r  system.exe setup_mjai.json params /  \
    && cp libai.so  /lib

ENTRYPOINT ["java","-jar","/app.jar"]