# Microservices Saga impl Eventuate Tram
Eventuate Tramでは、Sagaをサポートしている

永続化層にはJPA/JDBC対応している物をサポート、これの理由としては

データ格納とメッセージブローカーへのメッセージングの原子性を担保するためのパターンとして[Transactional outbox](https://microservices.io/patterns/data/transactional-outbox.html)を採用

データ格納先のテーブルとOutboxテーブルをトランザクションでまとめている

また、Eventuate Tramでは[CDC](https://eventuate.io/abouteventuatetram.html)を実装しているため、binlogからメッセージブローカーへデータを送信する動きをすることでイベントを発行している

## 参考リンク
- https://eventuate.io/exampleapps.html
- https://github.com/eventuate-tram/eventuate-tram-sagas-examples-customers-and-orders
- https://eventuate.io/abouteventuatetram.html