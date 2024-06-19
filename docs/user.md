# User API Spec


[//]: # (REGISTER USER)
## Register User

- Endpoint : POST /api/users

Request Body : 
```json
{
  "username" : "rendi09",
  "password" : "makan123",
  "name" : "Rendi Jonathan Sibarani"
}
```

Response Body  (Success)
```json
{
  "data" : "OK"
}
``` 

Response Body  (Failed)
```json
{
  "errors" : "Username must not blank, ???"
}
```



[//]: # (LOGIN USER)

## Login User

- Endpoint : POST /api/auth/login

Request Body :
```json
{
  "username" : "rendi09",
  "password" : "makan123"
}
```

Response Body  (Success)
```json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt": 1412431231 // millisecond

  }
}
``` 

Response Body  (Failed)
```json
{
  "errors" : "Username or password wrong"
}
```



[//]: # (GET USER)
## Get User
- Endpoint : GET /api/users/current

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Response Body  (Success)
```json
{
  "data" : {
    "username" : "rendi09",
    "name" : "Rendi Jonathan Sibarani"
  }
}
``` 

Response Body  (Failed)
```json
{
  "errors" : "Unauthorize"
}
```


[//]: # (UPDATE USER)
## Update User

- Endpoint : PATCH /api/users/current

Request Header :
- X-API-TOKEN : Token (Mandatory)


Request Body :
```json
{
  "name" : "Rendi Jonathan Sibarani", //put only if want to update
  "password" : "makan123" //put only if want to update
}
```

Response Body  (Success)
```json
{
  "data" : {
    "username" : "rendi09",
    "name" : "Rendi Jonathan Sibarani"
  }
}
``` 

Response Body  (Failed)
```json
{
  "errors" : "Unauthorize"
}
```



[//]: # (UPDATE USER)
## Logout User

- Endpoint : DELETE /api/auth/logout

Request Header :
- X-API-TOKEN : Token (Mandatory)


Response Body  (Success)
```json
{
  "data" : "OK"
}
``` 