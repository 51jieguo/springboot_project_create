<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>${basePackage}</groupId>
        <artifactId>${parentName}</artifactId>
        <version>${version}-SNAPSHOT</version>
    </parent>

    <artifactId>${name}</artifactId>
    <version>${version}-SNAPSHOT</version>
    <packaging>jar</packaging>

    <#if jars?? && (jars?size > 0) >
    <dependencies>
        <#list jars as child>
        <dependency>
            <groupId>${child.groupId}</groupId>
            <artifactId>${child.artifactId}</artifactId>
            <#if child.version??>
            <version>${child.version}</version>
            </#if>
        </dependency>
        </#list>
    </dependencies>
    </#if>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>

    </build>
</project>