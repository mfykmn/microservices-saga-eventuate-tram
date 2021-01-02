# Microservices Saga impl Eventuate Tram
- [Saga](https://microservices.io/patterns/data/saga.html)を実装
  - 永続化層にはJPA/JDBC対応している物のみをサポート
- [Transactional outbox](https://microservices.io/patterns/data/transactional-outbox.html)を実装
  - データベースの更新とメッセージの送信は、データの不整合やバグを回避するために原子性を担保する必要がありそのためのパターン
  - データ格納先のテーブルとOutboxテーブルをトランザクションでまとめている
- [Transaction log tailing](https://microservices.io/patterns/data/transaction-log-tailing.html)を実装
  - [CDC](https://eventuate.io/abouteventuatetram.html)を実装しているため、binlogからメッセージブローカーへデータを送信する動きをすることでイベントを発行している
- メッセージコンシューマのクラッシュなどの対応としてはメッセージコンシューマーはべき等であることを期待し、再度流せるような構成にする必要あり

## 参考リンク
- https://eventuate.io/exampleapps.html
- https://eventuate.io/abouteventuatetram.html
- https://github.com/eventuate-tram/eventuate-tram-sagas-examples-customers-and-orders
- 関連パターン
  - https://microservices.io/patterns/data/saga.html
  - https://microservices.io/patterns/data/domain-event.html
  - https://microservices.io/patterns/data/transactional-outbox.html
  - https://microservices.io/patterns/data/transaction-log-tailing.html
  - https://microservices.io/patterns/data/polling-publisher.html