curl -XPOST -H"Content-Type:application/json" http://localhost:8080/api/users -d '{"id":"user-01","name":"user-01","age":25}'
curl -XPOST -H"Content-Type:application/json" http://localhost:8080/api/users -d '{"id":"user-02","name":"user-02","age":26}'
curl -XPOST -H"Content-Type:application/json" http://localhost:8080/api/users -d '{"id":"user-03","name":"user-03","age":27}'

curl -XGET http://localhost:8080/api/users/90107a03-60ea-4ffc-a034-2a4072cde53a

curl -XGET http://localhost:8080/api/users/page-query?page=1&size=1&sort=createdDate,desc

curl -XDELETE http://localhost:8080/api/users/90107a03-60ea-4ffc-a034-2a4072cde53a


