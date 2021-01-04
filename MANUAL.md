# Manual
それぞれ別のターミナルで行う
- docker-composeによる環境構築
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram $ docker-compose -f docker-compose.yml up
    ```
- Order Service実行
    gradle build実行後に
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram/order-service $ ./gradlew bootRun
    ```
- Payment Service実行
  gradle build実行後に
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram/payment-service $ ./gradlew bootRun
    ```
- Shipment Service実行
  gradle build実行後に
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram/shipment-service $ ./gradlew bootRun
    ```

## Usage
### App
- Order ServiceをCLIで利用する
    ```ShellSession
    user@host: ~ $ curl -X POST http://0.0.0.0:10000/orders -H 'Content-type: application/json' -d '{
      "item_type": "SMARTPHONE",
      "price": 706,
      "currency": "USD"
    }'
    {"order_id":"4cd40458-4b8a-4408-b9bd-bfac4b758749","order_status":"CREATED"}

    user@host: ~ $ curl -X GET http://0.0.0.0:10000/orders/4cd40458-4b8a-4408-b9bd-bfac4b758749
    {"order_id":"4cd40458-4b8a-4408-b9bd-bfac4b758749","order_status":"CREATED","item_type":"SMARTPHONE","price":706,"currency":"USD"}
    ```
- Payment ServiceをCLIで利用する
    ```ShellSession
    user@host: ~ $ curl -X GET http://0.0.0.0:10001/payments/4cd40458-4b8a-4408-b9bd-bfac4b758749
    ```
- Shipment ServiceをCLIで利用する
    ```ShellSession
    user@host: ~ $ curl -X GET http://0.0.0.0:10002/shipments/4cd40458-4b8a-4408-b9bd-bfac4b758749
    ```

### MySQL
- CLIを利用する
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram $ docker-compose exec order-db bash
    root@266dd6efb58d:/# mysql -u user -p
    ```

### Kafka
- Confluent Control Centerを開く
    ```ShellSession
    user@host: ~ $ open http://0.0.0.0:9021
    ```
- Kafka CLIを利用する
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram $ docker-compose exec broker bash
    [appuser@fb71f61a1291 ~]$ kafka-console-producer --broker-list localhost:9092 --topic com.example.sagas.sagas.createorder.CreateOrderSaga-reply
    > test1
    > test2
    ```
- Kafka ConsumerをCLIから利用する
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram $ docker-compose exec broker bash
    [appuser@fb71f61a1291 ~]$ kafka-console-consumer --bootstrap-server=localhost:29092 --topic com.example.sagas.sagas.createorder.CreateOrderSaga-reply  --group com.example.sagas.sagas.createorder.CreateOrderSaga-consumer
    ```
