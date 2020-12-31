# Manual
## docker-composeによる環境構築

```ShellSession
user@host: ~/workspace/microservice-sage $ docker-compose -f docker-compose.yml up
```

## Usage
### App
- Order ServiceをCLIで利用する
    ```ShellSession
    user@host: ~ $ curl -X GET http://0.0.0.0:10000/orders/84bf789c-f486-4ad6-8c5c-4ba243fe333b
    user@host: ~ $ curl -X POST http://0.0.0.0:10000/orders
    ```
### MongoDB
- Mongo Expressを開く
    ```ShellSession
    user@host: ~ $ open http://0.0.0.0:8888
    ```
- Mongo CLIを利用する
    ```ShellSession
    user@host: ~/workspace/microservice-sage $ docker-compose exec db bash
    root@266dd6efb58d:/# mongo admin -u root -p
    ```

### Kafka
- Confluent Control Centerを開く
    ```ShellSession
    user@host: ~ $ open http://0.0.0.0:9021
    ```
- Kafka CLIを利用する
    ```ShellSession
    user@host: ~/workspace/microservice-sage $ docker-compose exec broker bash
    [appuser@fb71f61a1291 ~]$ kafka-console-producer --broker-list localhost:9092 --topic hoge
    > test1
    > test2
    ```