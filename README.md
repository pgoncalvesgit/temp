# TMA Admin Rest API

This project allows you to:

-   Generate public-private key pair to be used in the encryption process;
-   Add a new actuator to the database;
-   Add a new resource to the database;
-   Add a new description to the database;
-   Add a new probe to the database;
-   Configure the actions that an actuator can perform (check details on  [Message Format for Actions Registration](https://github.com/eubr-atmosphere/tma-framework-k/tree/master#message-format-for-actions-registration)).


# Index

 -   [Installation](#Installation)
 -   [Execution](#Execution)
 -   [HTTP Methods](#HTTP-Methods)
      *   [Actions](#Actions)
         --  [Configure the Actions that an Actuator can perform](#Configure-the-Actions-that-an-Actuator-can-perform)
      *   [Add to the Database](#Add-to-the-Database)
        --   [Add a new Actuator to the Database](#Add-a-new-Actuator-to-the-Database)
        --   [Add a new Description to the Database](#Add-a-new-Description-to-the-Database)
        --   [Add a new Probe to the Database](#Add-a-new-Probe-to-the-Database)
        --   [Add a new Resource to the Database](#Add-a-new-Resource-to-the-Database)
      *   [Keys](#Keys)
        --   [Generate a new Public - Private key pair](#Generate-a-new-Public---Private-key-pair)
 -   [Implementation Details](#Implementation-Details)


# Installation

To build the container, you should run the following command on the worker node:

```
sh build.sh
```

To deploy the pod in the cluster, you should run the following command on the master node:

```
kubectl create -f tma-admin-rest.yaml
```

# Execution

There is an example on how to execute each of the features refered in the beggining in the corresponding section bellow.

# HTTP Methods

## Actions

### Configure the Actions that an Actuator can perform
- - -

#### Method - POST

URI:
```
http://localhost:8080/configureactions
```

Model:
Parameter - actuatorId.
Body - json;
```
curl -X POST 'http://localhost:8080/configureactions?actuatorIdString=actuatorId' -H 'Content-Type: application/json' -d 'json'
```

Example:

```
curl -X POST 'http://localhost:8080/configureactions?actuatorIdString=232' -H 'Content-Type: application/json' -d '{"actions": [{"action": 2,"resourceId" : 2,"configuration": []}]}'
```

### Input

The actuatorId needs to be a String with the ID of the actuator which we want to configure (example: 232).

The following table shows some information about the json configuration.

|Key|Type|Description|Example|
|--|--|--|--|
|actions|Array with Jsons|Array with all the actions|TODO|
|action|String|TODO|TODO|
|resourceId|String|String with the ID of the resource|TODO|
|configuration|Array|TODO|TODO|
|key|String|TODO|TODO|
|value|String|TODO|TODO|{"actions": [{"action": 2,"resourceId" : 2,"configuration": []}]}|

### Success 201 & Error 400, 500

The 3 possible status are 201, 400 and 500. The json that is returned will always have the same keys. In the table bellow there is some information about each key.

|Key|Type|Value description|
|--|--|--|
|specificMessage|String|Specific message about the response|
|message|String|Generic message about the response|
|status|String|Status of the HTTP Request|
- - -
## Add to the Database

### Add a new Actuator to the Database
- - -
#### Method - POST

URI:

```
http://localhost:8080/addactuator
```

Model:
Body: file and actuatorAddress
```
curl -X POST http://localhost:8080/addactuator -H 'content-type: multipart/form-data;' -F file=@file -F address=actuatorAddress
```

Example:

```
curl -X POST http://localhost:8080/addactuator -H 'content-type: multipart/form-data;' -F file=@/home/user/publickey -F address=127.0.0.1
```

### Input
The following table shows some information about the input.
|Input|Type|Description|Example|
|--|--|--|--|
|actuatorAddress|String|Address of the Actuator which we want to add|127.0.0.1|
|file|File|File with the public key|/home/user/publickey

### Success 201 & Error 400, 415, 500

The 4 possible status are 201, 400, 415 and 500. The key actuatorId will only exist if the status returned is 201. In the table bellow there is some information about each key.

|Key|Type|Value description|
|--|--|--|
|specificMessage|String|Specific message about the response|
|message|String|Generic message about the response|
|status|String|Status of the HTTP Request|
||||
|actuatorId|int|Actuator ID of the brand new created actuator. (Only exists if the status returned is 201)|

- - -
### Add a new Description to the Database
- - -
#### Method - POST

URI:

```
http://localhost:8080/adddescription
```

Model:
Body: json
```
curl -X POST http://localhost:8080/adddescription -H 'Content-Type: application/json' -d 'json'
```

Example:

```
curl -X POST http://localhost:8080/adddescription -H 'Content-Type: application/json' -d '{"dataType": "2","descriptionName": "2","unit" : "3"}'
```

### Input

The following table shows some information about the json configuration.

|Key|Type|Description|Example|
|--|--|--|--|
|dataType|String|TODO|TODO|
|descriptionName|String|TODO|TODO|
|unit|String|TODO|TODO|{"dataType": "2","descriptionName": "2","unit" : "3"}|

### Success 201 & Error 400, 500

The 3 possible status are 201, 400, and 500. The key descriptionId will only exist if the status returned is 201. In the table bellow there is some information about each key.

|Field|Type|Description|
|--|--|--|
|specificMessage|String|Specific message about the response|
|message|String|Generic message about the response|
|status|String|Status of the HTTP Request|
||||
|descriptionId|String|Description ID of the brand new created description. (Only exists if the status returned is 201)|

## Add a new Probe to the Database

#### Method - POST

URI:

```
http://localhost:8080/addprobe
```

Model:
Body: json
```
curl -X POST http://localhost:8080/addprobe -H 'Content-Type: application/json' -d 'json'
```

Example:

```
curl -X POST http://localhost:8080/addprobe -H 'Content-Type: application/json' -d '{"probeName": "2","password": "2","salt" : "3","token" : "3","tokenExpiration" : "3"}'
```

### Input

The following table shows some information about the json configuration.

|Key|Type|Description|Example|
|--|--|--|--|
|probeName|String|TODO|TODO|
|password|String|TODO|TODO|
|salt|String|TODO|TODO|
|token|String|TODO|TODO|
|tokenExpiration|String|TODO|TODO|{"probeName": "2","password": "2","salt" : "3","token" : "3","tokenExpiration" : "3"}|

### Success 201 & Error 400, 500

The 3 possible status are 201, 400, and 500. The key probeId will only exist if the status returned is 201. In the table bellow there is some information about each key.

|Field|Type|Description|
|--|--|--|
|specificMessage|String|Specific message about the response|
|message|String|Generic message about the response|
|status|String|Status of the HTTP Request|
||||
|probeId|String|Probe ID of the brand new created probe. (Only exists if the status returned is 201)|

## Add a new Resource to the Database

#### Method - POST

URI:

```
http://localhost:8080/addresource
```

Model:
Body: json
```
curl -X POST http://localhost:8080/addresource -H 'Content-Type: application/json' -d 'json'
```

Example:

```
curl -X POST http://localhost:8080/addresource -H 'Content-Type: application/json' -d '{"name": "teste","type": "aqui","address": "221.31"}'
```

### Input

The following table shows some information about the json configuration.

|Key|Type|Description|Example|
|--|--|--|--|
|name|String|TODO|TODO|
|type|String|TODO|TODO|
|address|String|TODO|TODO|{"name": "teste","type": "aqui","address": "221.31"}|

### Success 201 & Error 400, 500

The 3 possible status are 201, 400, and 500. The key resourceId will only exist if the status returned is 201. In the table bellow there is some information about each key.

|Field|Type|Description|
|--|--|--|
|specificMessage|String|Specific message about the response|
|message|String|Generic message about the response|
|status|String|Status of the HTTP Request|
||||
|resourceId|String|Resource ID of the brand new created Resource. (Only exists if the status returned is 201)|

# Keys

## Generate a new Public - Private key pair

#### Method - POST

URI:

```
http://localhost:8080/generatekeys
```

Model:
```
something
```

Example:

```
something
```

### Parameter

|Field|Type|Description|
|--|--|--|
|?|?|?|


### Success 201 & Error 400, 500


|Field|Type|Description|
|--|--|--|
|specificMessage|String|Specific message about the response|
|message|String|Generic message about the response|
|status|String|Status of the HTTP Request|
||||
|PublicKey|String|Public Key of the User|
|PrivateKey|String|Private Key of the User|


## Implementation Details

To implement this API it was used [SpringBoot](https://spring.io/projects/spring-boot)  with [log4j](https://logging.apache.org/log4j/2.x/) to help with the logging.
