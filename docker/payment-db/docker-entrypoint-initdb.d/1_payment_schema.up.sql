USE payment;

DROP Table IF Exists invoices;

CREATE TABLE `invoices`
(
  `payment_id` varchar(36) NOT NULL,
  `order_id` varchar(36) NOT NULL,
  `invoice_status` varchar(10) NOT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;