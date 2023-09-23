# mail-service

#### How to use this app:

## System requirements
1. A valid gmail account, this account will be used to send emails.
2. A mysql database
3. A RabbitMQ Instance

## Properties
1. Go to https://support.google.com/accounts/answer/185833 to create a Google app password.
2. Open the application.yaml file and change the properties below:

```yaml
spring:
  rabbitmq:
      host: HOST_OF_RABBITMQ
      username: RABBIT_MQ_USERNAME
      password: RABBIT_MQ_PASSWORD
      queue: RABBIT_MQ_QUEUE_NAME
      port: RABBIT_MQ_PORT
  datasource:
    url: jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME
    username: YOUR_DATABASE_USER
    password: YOUR_DATABASE_PASSWORD
  jpa:
    hibernate.ddl-auto: update
    generate-ddl: true
    show-sql: true
  mail:
    host: smtp.gmail.com
    port: 587
    username: A_VALID_GMAIL_ACCOUNT_ADDRESS
    password: A_VALID_GOOGLE_APP_PASSWORD
```

3. To send emails use the curl of the file ./postman/curl.txt

