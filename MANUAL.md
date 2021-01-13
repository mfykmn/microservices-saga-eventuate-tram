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
    user@host: ~ $ curl -X POST http://0.0.0.0:10000/api/order/v1/orders -H 'Content-type: application/json' -d '{
      "item_type": "SMARTPHONE",
      "price": 706,
      "currency": "USD"
    }'
    {"order_id":"d132414b-426f-456c-805d-27c2d1b3bb6d","order_status":"CREATED"}

    user@host: ~ $ curl -X GET http://0.0.0.0:10000/api/order/v1/orders/d132414b-426f-456c-805d-27c2d1b3bb6d
    {"order_id":"d132414b-426f-456c-805d-27c2d1b3bb6d","order_status":"APPROVED","item_type":"SMARTPHONE","price":706,"currency":"USD"}
    ```
- Payment ServiceをCLIで利用する
    ```ShellSession
    user@host: ~ $ curl -X GET http://0.0.0.0:10001/api/payment/v1/invoices/591bb73e-e537-4c48-9859-9950c46f5374
    {"payment_id":"591bb73e-e537-4c48-9859-9950c46f5374","order_id":"d132414b-426f-456c-805d-27c2d1b3bb6d","invoice_status":"PAID"}
    ```

### MySQL
- CLIを利用する
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram $ docker-compose exec mysql bash
    root@266dd6efb58d:/# mysql -u user -p
    mysql> use order;
    mysql> select * from orders;
    +--------------------------------------+------------+-------+----------+--------------+------------------+
    | order_id                             | item_type  | price | currency | order_status | rejection_reason |
    +--------------------------------------+------------+-------+----------+--------------+------------------+
    | d132414b-426f-456c-805d-27c2d1b3bb6d | SMARTPHONE |   706 | USD      | APPROVED     | NULL             |
    +--------------------------------------+------------+-------+----------+--------------+------------------+
    1 row in set (0.00 sec)

    mysql> use payment;
    mysql> select * from invoices;
    +--------------------------------------+--------------------------------------+----------------+
    | payment_id                           | order_id                             | invoice_status |
    +--------------------------------------+--------------------------------------+----------------+
    | 591bb73e-e537-4c48-9859-9950c46f5374 | d132414b-426f-456c-805d-27c2d1b3bb6d | PAID           |
    +--------------------------------------+--------------------------------------+----------------+
    1 row in set (0.00 sec)
    ```

### Kafka
- Kafka Topic確認
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram $ docker-compose exec kafka bash
    [appuser@fb71f61a1291 ~]$ kafka-topics --list --bootstrap-server kafka:29092
    com.example.sagas.sagas.createorder.CreateOrderSaga-reply
    paymentService
    ```
- Kafka Produce
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram $ docker-compose exec kafka bash
    [appuser@fb71f61a1291 ~]$ kafka-console-producer \
      --broker-list localhost:29092 \
      --topic com.example.sagas.sagas.createorder.CreateOrderSaga-reply
    > test1
    > test2
    ``
- Kafka Consume
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate-tram $ docker-compose exec kafka bash
    [appuser@fb71f61a1291 ~]$ kafka-console-consumer \
      --bootstrap-server kafka:29092 \
      --topic paymentService \
      --from-beginning
    {"payload":"{}","headers":{"commandreply_saga_id":"00000176f13b31a2-3a96de13129a0000","DATE":"Mon, 11 Jan 2021 11:35:58 GMT","reply_outcome-type":"SUCCESS","commandreply__destination":"paymentService","commandreply_reply_to":"com.example.sagas.sagas.createorder.CreateOrderSaga-reply","commandreply_type":"com.example.sagas.sagas.createorder.ReserveInvoiceCommand","DESTINATION":"com.example.sagas.sagas.createorder.CreateOrderSaga-reply","commandreply_saga_type":"com.example.sagas.sagas.createorder.CreateOrderSaga","reply_type":"com.example.sagas.sagas.createorder.InvoiceReserved","reply_to_message_id":"00000176f13b324a-3a96de13129a0000","ID":"00000176f13b357a-3a96de13129a0000"}}
    ```
