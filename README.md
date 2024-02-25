# Personal data encryption service
____
## Описания репозитория
Этот репозитория содержит opensource code для реализации сервиса по шифрованию персональных данных(контактный номер телефона,электронная почта)
____
## Задачи сервиса
+ Принимать входящие данные и обрабатывать их
+ Обработать данные и записать в приватные поля "numberOfPhone" и "email" в классе "Text"
+ Обработанные данные сохранить(для этого была реализована база данных postgreSQL)
+ Вернуть данные в формате json
____
## Используемые технологии
+ Код написан языке Java с помощью фреймворка Spring с рассширением Spring Boot
+ База данных  postgreSQL
____
## HTTP-запросы
+ GET http://localhost:8080/api/v1/text - показать все сохраненные данные
+ POST http://localhost:8080/api/v1/text/save_information - сохранить данные
+ POST http://localhost:8080/api/v1/text/find_phone_number_email - зашифровать входные данные и сохранить
+ GET http://localhost:8080/api/v1/text/{{text}} - найти данные по запрошенной информации
+ DELETE http://localhost:8080/api/v1/text/delete_text/{{id}} - удалить данные по id
____
## Анализатор кода
[Sonar Cloud](https://sonarcloud.io/summary/overall?id=Perceva1e_projectWebJava)
