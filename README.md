# playground-graphql

* Start the Mountebank REST server stubs with `docker-compose up`

* Start the application with `./gradlew bootrun`

* Open `http://localhost:8080/graphiql`

* Paste and execute this query:
```
{
  users {
    name
    books {
      title
      comments {
        comment
        user {
          name
          email
          location
          secret {
            secret1
            secret2
          }
        }
      }
    }
  }
}
```

* You can also use curl:
```
curl -v -X POST http://localhost:8080/graphql \
-H "Content-Type: application/json" \
-d '{"query":"{\n  users {\n    name\n    books {\n      title\n      comments {\n        comment\n        user {\n          name\n          email\n          location\n          secret {\n            secret1\n            secret2\n          }\n        }\n      }\n    }\n  }\n}\n","variables":null,"operationName":null}"}'
```