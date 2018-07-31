


<#list dbs as db>
spring.data.mongodb.${db}.host=172.20.10.10
spring.data.mongodb.${db}.port=27017
spring.data.mongodb.${db}.database=${db}

</#list>