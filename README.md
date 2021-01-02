# Microservices Saga impl Eventuate Tram
- Eventuate Tramでは、Sagaをサポートしている
- 永続化層にはJPA/JDBC対応している物のみをサポート
- [Transactional outbox](https://microservices.io/patterns/data/transactional-outbox.html)を採用
  - データベースの更新とメッセージの送信は、データの不整合やバグを回避するために原子性を担保する必要がありそのためのパターン
  - データ格納先のテーブルとOutboxテーブルをトランザクションでまとめている
- [CDC](https://eventuate.io/abouteventuatetram.html)を実装しているため、binlogからメッセージブローカーへデータを送信する動きをすることでイベントを発行している
- メッセージコンシューマのクラッシュなどの対応としてはメッセージコンシューマーはべき等であることを期待し、再度流せるような構成にする必要あり

## 参考リンク
- https://eventuate.io/exampleapps.html
- https://github.com/eventuate-tram/eventuate-tram-sagas-examples-customers-and-orders
- https://eventuate.io/abouteventuatetram.html