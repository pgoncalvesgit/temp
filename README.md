## UC Meeting (Nuno, Zé, Rui, Paulo):

  - #### Components Validation - In Progress
    * Message sent to Ignacio today 
 - #### Updated version of Deliverable 3.5 - In Progress
   * Analyze, Dashboard?
   * Rui is in charge of it
 - #### Final deployment of TMA (decide the environment, ...) - To Do (Discussion with Amanda pending)
 - #### Integration with Dell (Probe and Quality Models) - In Progress
   * Probes pending (waiting for Dell)
   * Quality Model - To be tested
     - Quick fixes pending
   * [Actuator ](https://github.com/eubr-atmosphere/tma-framework-e/tree/new/cloudea-actuator/development/actuators/cloudea-actuator) - In Progress
 - #### Support to Lemonade probes (Walter/UFMG) - In Progress
   * Not sure if we will need to implement something
 - #### Final Demo - To Do (Decide what quality models to be used)
   * Use the resource or performance quality models
   * Atuação: scale and send mail
 - #### [Java Probe JMX](https://github.com/eubr-atmosphere/tma-framework-m/pull/67) - Discussion between Rui and Nuno pending
   * The current solution is overkill. It should be a boolean instead of the current implementation.
   * Remove JUnique and use a boolean that is checked in an if statement.
 - #### TMA release (including API) - To Do
   * To be done during the meeting next week
   * (API needs to be pushed to GitHub)
   * Change the "Description" in the dashboard header to "Measurement/event"
   * Salt must be generated using Argon
   * Find bootstrap for the dashboard
 - #### Script to add the probes, descriptions (maybe through Admin GUI) - as it was discussed during 28th RTB - To Do
   * Add an higher level file
   * Can use the API
 - #### Integration with Unicamp Dashboard - In Progress
   * Waiting for their feedback
   * Last messages on Thursday
 - #### Paper - Framework, Artifact - Decide what to do
   * Stand by for now
 - #### Allow planning to handle plans with more than one action - To Do
 - #### Threat Model - To Do
   * Authentication, Fogbow


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
 -   [API Calls](#API-Calls)
	  -  [Introduction](#Introduction)
      -  [Actions](#Actions)
	       -   [Configure the Actions that an Actuator can perform](#Configure-the-Actions-that-an-Actuator-can-perform)
      *   [Add to the Database](#Add-to-the-Database)
	       -   [Add a new Actuator to the Database](#Add-a-new-Actuator-to-the-Database)
	       -   [Add a new Description to the Database](#Add-a-new-Description-to-the-Database)
	       -   [Add a new Probe to the Database](#Add-a-new-Probe-to-the-Database)
	       -   [Add a new Resource to the Database](#Add-a-new-Resource-to-the-Database)
      *   [Keys](#Keys)
	       -   [Generate a new Public - Private key pair](#Generate-a-new-Public---Private-key-pair)
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

# API Calls

## Introduction
	
Every call has the same base documentation. Model:

#### Method - HTTP Method
URI:
```
URI here
```

Model:
Parameters - every necessary parameter name will be here
Body - every variable that goes inside the body will be here
```
curl command with variable names
```

Example:

```
curl command with examples instead of variable names
```

### Input

A short explanation of the input, normally with a table like this:

|Key|Type|Description|Example|
|--|--|--|--|
| | | |
| | | |

### Success numbers & Error numbers

The numbers will be the possible status code the can be returned.

In this API, 201 is the only status code associated with a sucessfull call.

When the call is unsucessfull however, the status code can either be 400, in case something is wrong with the input syntax, 415, in case something is wrong with the input type and 500 in case something is wrong with the server (Database error).

If the error is 404, it means the URI is not associated with any API Call.
If the error is 405, it means the HTTP Method is not allowed to the URI that the call was made.

Every API Call will return a Json with this base configuration:

|Key|Type|Value description|
|--|--|--|
|specificMessage|String|Specific message about the response|
|message|String|Generic message about the response|
|status|String|Status of the HTTP Request|
- - -

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
|actions|Array with Jsons|Array with all the actions|[{"action": 2,"resourceId" : 2,"configuration": []}]|
|action|String|An action we want to add|do something|
|resourceId|String|String with the ID of the resource|2|
|configuration|Array|Array of configurations to add to the action|[{"key":"conf","value":"3"},{"key":"conf","value":"3"}]|
|key|String|key of a configuration|"conf"|
|value|String|value of a configuration|"2"|

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
|dataType|String|Type of the data|String|
|descriptionName|String|Name of the description|Desc|
|unit|String|Unit of the description|a|

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
|probeName|String|Name of the probe|probe|
|password|String|Password of the probe|elCe12w21e21e|
|salt|String|salt of the password|123123213|
|token|String|token of the probe|3|
|tokenExpiration|String|Expiration of the Probe|3|{"probeName": "2","password": "2","salt" : "3","token" : "3","tokenExpiration" : "3"}|

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
|name|String|name of the resource|new resource|
|type|String|type of the resource|String|
|address|String|address of the resource|127.0.0.1|

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

#### Method - GET

URI:

```
http://localhost:8080/generatekeys
```

Model:
```
curl -X GET http://localhost:8080/generatekeys --output file
```

Example:

```
curl -X GET http://localhost:8080/generatekeys --output home/user/file.zip
```

### Input

The file will be the directory where you want your file to be saved. For example home/user/file.zip


### Success 201

If the call is sucessfull the status code will be 201 which means the keys were created. You will have a zip file in the directory you specified. If you unzip it, you will get 2 files, one called publicKey and another called privateKey, which hold the public key and the private key inside, respectively.

### Error 500

If the call is unsucessfull the status code will be 500, it is due to an error while creating the keys or while creating the zip file.

## Implementation Details

To implement this API it was used [SpringBoot](https://spring.io/projects/spring-boot)  with [log4j](https://logging.apache.org/log4j/2.x/) to help with the logging.
- - -
- - -
