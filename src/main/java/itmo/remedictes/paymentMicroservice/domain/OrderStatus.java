package itmo.remedictes.paymentMicroservice.domain;

public enum OrderStatus {
    COLLECTING,
    PAYED,
    SHIPPING,
    COMPLETE,
    FAILED,
    CANCELLED
}
