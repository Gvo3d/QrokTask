QrokTask
YakimovDenis
denis.yakimov89@gmail.com
+380676832268

To start:
gradlew.bat clean bootRun

Authorization can be made from /auth/ controller.
For example:
1) We getting auth model to fill it with data.
localhost:8080/auth/model (GET)
2) We fill the JSON with data.
localhost:8080/auth/ (POST)
JSON:
{
    "username": "user",
    "password": "qwerty"
}
And we get the resulting username if it was correct and auth was successfull.
3) We check the auth.
localhost:8080/auth/ (GET)
And we get the boolean based on our authentication. True - if auth was success or false if not.

There are two users(and only ADMIN has authority to make /author/info/short/{id} ajax).

ListOfMappings:
/authors
/books
/rewards

REST API(if model is needed and model is not full - there will be no update/create):
To make a submit you need to pass into a Postman: localhost:8080+mapping+(optional link/id/whatever else)
Examples:
localhost:8080/authors (GET)
localhost:8080/books (POST) + model
localhost:8080/rewards/2 (DELETE) - for deleting Reward with id=2
localhost:8080/authors/5 (PUT) + model - for updating model with id=5 to new model
localhost:8080/books/model (GET) - to get empty JSON to fill for creating/updating one model

Get empty model (empty JSON with field to fill)
/model

Get All:
basic link (GET METHOD)

Get one:
/{id} (GET METHOD)

Get one:
/full/{id} (GET METHOD) - to get full model (with all fetches) - only for administrators
RewardsController has no full method(rewards has no inner sets with users/books)

Create one:
basic link (POST METHOD) + full model

Update one:
/{id} (PUT METHOD) + full model

Delete one:
/{id} (DELETE METHOD)

When you are updating the entity, there are no reason to change ID because it will not be changed.