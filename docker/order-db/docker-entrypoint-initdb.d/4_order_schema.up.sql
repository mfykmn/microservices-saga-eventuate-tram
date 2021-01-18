USE order;

DROP Table IF Exists orders;

CREATE TABLE `orders`
(
  `order_id` varchar(36) NOT NULL,
  `item_type` varchar(10) NOT NULL,
  `price` decimal NOT NULL,
  `currency` varchar(10) NOT NULL,
  `order_status` varchar(10) NOT NULL,
  `rejection_reason` varchar(10),
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;