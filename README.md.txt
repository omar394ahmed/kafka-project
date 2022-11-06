 
NOTES :-

1- pre-requisite to setup this env :-
      - windows OS or run equivilant commands related to your OS .  
      - JAVA 8 .
      - kafka  . 
      - intellij preferred .


2- Setup steps 
     
    first of all make sure either run the following commands from this 
       dir :- {home-path}kafka\kafka_2.13-3.3.1\bin\windows 
                    OR 
       Add kafka to your envirnment_variables 

    1- run zookeeper with this command :- zookeeper-server-start.bat  C:\kafka\kafka_2.13-3.3.1\config\zookeeper.properties .

    2- run kafka : - kafka-server-start.bat C:\kafka\kafka_2.13-3.3.1\config\server.properties . 
    
    3- create topics : -  kafka-topics.bat --bootstrap-server localhost:9092 --topic {topicname }--create  --partitions 1 --replication-factor 1 .
    
          topic names :- firstTopic , smallAmountTopic , bigAmountTopic .

    4- important notes : - 

        - please find those attributes on your config files on this dir C:\kafka\kafka_2.13-3.3.1\config
         
             1- on server file : - 
                        listeners=PLAINTEXT://localhost:9092 ( default )
                        advertised.listeners=PLAINTEXT://localhost:9092 ( default)
            
            2- zookeeper file : -  clientPort=2181 ( default )
   

   5 - clone this repo 

   6- run all four project 

   7- make postman request  https://www.getpostman.com/collections/31f06d751b38acdb5695

    
  FINAL_NOTES 
       YOU CAN FIND SAMPLE OF DUMMY DATA I HAVE USED TO TEST THIS PROJECT WITH FILE NAME USERS   


 