Бэкэнд сервиса "Заказ такси"

Состоит из 5 отдельно запускаемых Spring Boot приложений:

    apigateway
    client
    taxi
    route
    taxiorder

Модуль common общий, прежде чем собирать/запускать приложения, необходимо собрать этот модуль и установить его в локальный maven репозиторий: 
    
    mvn install

Приложения apigateway, client, taxi и taxiorder требуют подключения к базе данных Postgres 12.
Каждому из этих приложений требуется своя одноимённая схема в БД taxi. 


Обмен данными идёт через брокер собщений RabbitMQ, запущенный локально в контейнере командой

    docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
