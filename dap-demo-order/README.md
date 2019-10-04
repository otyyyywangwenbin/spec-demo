
curl -XPOST -H"Content-Type:application/json" http://localhost:8081/api/orders -d '{"id":"order-01","orderDetail":"这是订单01","user":{"id":"user-01"}}'




curl -XGET http://localhost:8081/api/orders/order-01

curl -XGET http://localhost:8080/api/users/page-query?page=1&size=1&sort=createdDate,desc

curl -XDELETE http://localhost:8080/api/users/90107a03-60ea-4ffc-a034-2a4072cde53a


