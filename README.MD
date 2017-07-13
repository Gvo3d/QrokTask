QrokTask
YakimovDenis
denis.yakimov89@gmail.com
+380676832268

ListOfMappings:
/authors
/books
/rewards

REST API(if model is needed and model is not full - there will be no update/create):
To make a submit you need to pass into a Postman: localhost:8080+mapping+link
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