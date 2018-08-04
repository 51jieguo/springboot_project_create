


<#list dbs as db>
spring.data.mongodb.${db}.host=192.168.0.12
spring.data.mongodb.${db}.port=27017
spring.data.mongodb.${db}.database=${db}

</#list>