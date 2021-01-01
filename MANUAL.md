# Manual
## docker-composeによる環境構築

```ShellSession
user@host: ~/workspace/microservice-sage-eventuate $ docker-compose -f docker-compose.yml up
```

## Usage
### App
- Service実行
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate $ ./gradlew bootRun
    ```
- Order ServiceをCLIで利用する
    ```ShellSession
    user@host: ~ $ curl -X GET http://0.0.0.0:10000/orders/23c0ef28-a8c3-40e4-8721-1c3c9f4e284f
    user@host: ~ $ curl -X POST http://0.0.0.0:10000/orders
    ```

### MySQL
- CLIを利用する
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate $ docker-compose exec db bash
    root@266dd6efb58d:/# mysql -u user -p
    ```

### Kafka
- Confluent Control Centerを開く
    ```ShellSession
    user@host: ~ $ open http://0.0.0.0:9021
    ```
- Kafka CLIを利用する
    ```ShellSession
    user@host: ~/workspace/microservice-sage-eventuate $ docker-compose exec broker bash
    [appuser@fb71f61a1291 ~]$ kafka-console-producer --broker-list localhost:9092 --topic hoge
    > test1
    > test2
    ```