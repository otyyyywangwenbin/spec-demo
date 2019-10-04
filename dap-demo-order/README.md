curl -XPOST -H"Content-Type:application/json" http://localhost:8081/api/products -d '{"id":"product-01","name":"product-01","quantity":100,"price":100}'
curl -XPOST -H"Content-Type:application/json" http://localhost:8081/api/products -d '{"id":"product-02","name":"product-02","quantity":100,"price":100}'



curl -XPOST -H"Content-Type:application/json" http://localhost:8081/api/orders -d '{"id":"order-01","orderNo":"order-01","user":{"id":"user-01"},"items":[{"product":{"id":"product-01"}}]}'


curl -XPOST -H"Content-Type:application/json" http://localhost:8081/api/orders -d '{"id":"user-03","name":"user-03","age":27}'

curl -XGET http://localhost:8080/api/users/90107a03-60ea-4ffc-a034-2a4072cde53a

curl -XGET http://localhost:8080/api/users/page-query?page=1&size=1&sort=createdDate,desc

curl -XDELETE http://localhost:8080/api/users/90107a03-60ea-4ffc-a034-2a4072cde53a


