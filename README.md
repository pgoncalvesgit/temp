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
 -   [Actions](#Actions)
     -   [Configure the Actions that an Actuator can perform](#Configure-the-Actions-that-an-Actuator-can-perform)
 -   [Add to the Database](#Add-to-the-Database)
     -   [Add a new Actuator to the Database](#Add-a-new-Actuator-to-the-Database)
     -   [Add a new Description to the Database](#Add-a-new-Description-to-the-Database)
     -   [Add a new Probe to the Database](#Add-a-new-Probe-to-the-Database)
     -   [Add a new Resource to the Database](#Add-a-new-Resource-to-the-Database)
 -   [Keys](#Keys)
     -   [Generate a new Public - Private key pair](#Generate-a-new-Public---Private-key-pair)


# Installation

To build the container, you should run the following command on the worker node:

```
sh build.sh
```

To deploy the pod in the cluster, you should run the following command on the master node:

```
kubectl create -f tma-admin-rest.yaml
```

Keep in mind that you need **curl** installed  on your machine to be able to use the API. However, you can easily install it by typping:
```
sudo apt install curl
```

# Execution

There is an example on how to execute each of the features refered in the beggining in the corresponding section bellow.

# Actions

## Configure the Actions that an Actuator can perform

#### Method - POST

URI:
```
http://localhost:8080/configureactions
```

Example:

```
curl -X POST 'http://localhost:8080/configureactions?actuatorIdString=232' -H 'Content-Type: application/json' -d '{"actions": [{"action": 2,"resourceId" : 2,"configuration": []}]}'
```

### Parameter

|Field|Type|Description|
|--|--|--|
|ActuatorId|String|in a String|
|Json|Json|with the actions configuration|

# Add to the Database

## Add a new Actuator to the Database

#### Method - POST

URI:

```
http://localhost:8080/addactuator
```

Example:

```
curl -X POST http://localhost:8080/addactuator -H 'content-type: multipart/form-data;' -F file=@/C:/Users/Alex/Downloads/test/public2 -F address=2
```

### Parameter

|Field|Type|Description|
|--|--|--|
|address|String|of the actuator|
|file|File|with the public key|

### Success 200

|Field|Type|Description|
|--|--|--|
|actuatorId|int|Actuator ID of the brand new created actuator.|

## Add a new Description to the Database

#### Method - POST

URI:

```
http://localhost:8080/adddescription
```

Example:

```
curl -X POST http://localhost:8080/adddescription -H 'Content-Type: application/json' -d '{"dataType": "2","descriptionName": "2","unit" : "3"}'
```

### Parameter

|Field|Type|Description|
|--|--|--|
|Json|Json|with the description configuration|

### Success 200

|Field|Type|Description|
|--|--|--|
|DescriptionId|int|Description ID of the brand new created description|

## Add a new Probe to the Database

#### Method - POST

URI:

```
http://localhost:8080/addprobe
```

Example:

```
curl -X POST http://localhost:8080/addprobe -H 'Content-Type: application/json' -d '{"probeName": "2","password": "2","salt" : "3","token" : "3","tokenExpiration" : "3"}'
```

### Parameter

|Field|Type|Description|
|--|--|--|
|Json|Json|with the Probe configuration|

### Success 200

|Field|Type|Description|
|--|--|--|
|ProbeId|int|Probe ID of the brand new created probe|

## Add a new Resource to the Database

#### Method - POST

URI:

```
http://localhost:8080/addresource
```

Example:

```
curl -X POST http://localhost:8080/addresource -H 'Content-Type: application/json' -d '{"name": "teste","type": "aqui","address": "221.31"}'
```

### Parameter


|Field|Type|Description|
|--|--|--|
|ProbeId|int|with the Resource configuration|

### Success 200

|Field|Type|Description|
|--|--|--|
|ResourceId|int|Resource ID of the brand new created Resource|

# Keys

## Generate a new Public - Private key pair

#### Method - POST

URI:

```
http://localhost:8080/generatekeys
```

Example:

```
something
```

### Parameter

|Field|Type|Description|
|--|--|--|
|Id|Number|Users unique ID|


### Success 200


|Field|Type|Description|
|--|--|--|
|PublicKey|String|Public Key of the User|
|PrivateKey|String|Private Key of the User|
