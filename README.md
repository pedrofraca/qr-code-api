# qr-code-api Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

In order to avoid some of the issues (https://github.com/oracle/graal/issues/4124) to build this might cause due to libraries needed for the image generation, use
docker to build it with the following command.

```shell script
./mvnw clean package -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:21.3-java11 -Dquarkus.native.native-image-xmx=8g
```

## Using docker

```shell script
docker run -p 8080:8080 pedrofraca/qr-gen:latest
```

## Endpoint definition 

```shell script
curl --location --request POST 'http://localhost:8080/generate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "data": "MECARD:N:Doe,John;TEL:13035551212;EMAIL:john.doe@example.com;;"
}'
```

Height and width are optional values, if not send on the request the size of the image will be always 512x512, 
alternatively you can set the size in the request

```shell script
curl --location --request POST 'http://localhost:8080/generate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "data": "MECARD:N:Doe,John;TEL:13035551212;EMAIL:john.doe@example.com;;",
    "height" : 1024,
    "width": 1024
}'
```

## Load testing
If you want to test your deployment you can run the load testing using k6, a default script is included in the load
folder. 

```shell script
k6 run load/script.js
```