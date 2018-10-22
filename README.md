# Data Integration

# Steps

## RestClient

| Field            | Type   | Accept variable? | Description                         |
|------------------|--------|------------------|-------------------------------------|
| type             | string | N                | RestClient                          |
| url              | string | Y                | http://localhost:8080               |
| headers          | object | Y                | {   "Authorization": "Bearer ..." } |
| httpMethod       | string | N                | GET, POST, PUT, DELETE              |
| applicationType  | string | N                | JSON                                |
| bodyVariable     | string | Y                |                                     |
| responseVariable | string | Y                |                                     |

Example:

```
{
	"type": "RestClient",
	"url": "http://localhost:8080",
	"headers": {
		"Authorization": "Bearer xxx"
	},
	"httpMethod": "POST",
	"applicationType": "JSON",
	"bodyVariable": "bodyVar",
	"responseVariable": "responseVar"
}
```
## JsonInput

| Field            | Type   | Accept variable? | Description                         |
|------------------|--------|------------------|-------------------------------------|
| type             | string | N                |                                     |
| inputVariable    | string | Y                |                                     |
| outputVariable   | string | Y                |                                     |
| fields	       | array  | N                |                                     |
| fields.name      | string | Y                |                                     |
| fields.path      | string | N                |                                     |
| fields.type      | string | N                |                                     |

Example:

```
{
	"type": "JsonInput",
	"inputVariable": "inputVar",
	"outputVariable": "outputVar",
	"fields": [
		{
			"name": "id",
			"path": "$..id",
			"type": "STRING"
		}, {
			"name": "name",
			"path": "$..name",
			"type": "STRING"
		}
	]
}
```

## JsonOutput

| Field              | Type   | Accept variable? | Description                         |
|--------------------|--------|------------------|-------------------------------------|
| type               | string | N                |                                     |
| inputVariable      | string | Y                |                                     |
| outputVariable     | string | Y                |                                     |
| array		         | boolean| N                |                                     |
| arrayElementName   | string | Y                |                                     |
| fields	         | array  | N                |                                     |
| fields.name	     | string | Y                |                                     |
| fields.elementName | string | Y                |                                     |

Example:

```
{
	"type": "JsonOutput",
	"inputVariable": "inputVar",
	"outputVariable": "outputVar",
	"array": false,
	"arrayElementName": null,
	"fields": [
		{
			"name": "name",
			"elementName": "name"
		}
	]
}
```

## JoinTable

| Field              | Type   | Accept variable? | Description                         |
|--------------------|--------|------------------|-------------------------------------|
| type               | string | N                |                                     |
| leftTable      	 | string | Y                |                                     |
| rightTable     	 | string | Y                |                                     |
| outputVariable	 | string | Y                |                                     |
| arrayElementName   | string | Y                |                                     |
| keysLeftTable      | array  | Y                |                                     |
| keysRightTable     | array  | Y                |                                     |
| joinType           | string | N                |                                     |

Example:

```
{
	"type": "JoinTable",
	"leftTable": "leftTableVar",
	"rightTable": "rightTableVar",
	"outputVariable": "outputVar",
	"keysLeftTable": ["id"],
	"keysRightTable": ["id"],
	"joinType": "INNER"
}
```

## MongoDBOutput

| Field              | Type   | Accept variable? | Description                         |
|--------------------|--------|------------------|-------------------------------------|
| type               | string | N                |                                     |
| host      	     | string | Y                |                                     |
| port     	         | number | Y                |                                     |
| database	         | string | Y                |                                     |
| collection         | string | Y                |                                     |
| inputVariable      | string | Y                |                                     |
| fields             | array  | N                |                                     |
| fields.name        | string | Y                |                                     |
| fields.path        | string | Y                |                                     |

Example:

```
{
	"type": "MongoDBOutput",
	"host": "localhost",
	"port": 27017,
	"database": "myDatabase",
	"collection": "myCollection",
	"inputVariable": "inputVar",
	"fields": [
		{
			"name": "name",
			"path": ""
		}
	]
}
```