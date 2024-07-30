package org.lafresca.lafrescabackend.Models;

public enum OrderStatus {
    PENDING,
    PREPARING,
    READY, // on delivery queue or on waiter queue
    DELIVERING, // on delivery (this is only for online orders)
    DELIVERED // delivered
}
