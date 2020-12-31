db.createCollection('orders');
db.orders.createIndex(
    {
        item_id: 1,
    },
    {unique: true}
);