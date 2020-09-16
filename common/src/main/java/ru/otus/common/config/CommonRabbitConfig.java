package ru.otus.common.config;

public class CommonRabbitConfig {
    public static final String QUEUE_CLIENTS = "clients-queue";
    public static final String QUEUE_CLIENTS_INFO = "clients-info";
    public static final String QUEUE_TAXI = "taxi-queue";
    public static final String QUEUE_TAXI_INFO = "taxi-info";
    public static final String QUEUE_TAXI_START_WORK = "taxi-start-work";
    public static final String QUEUE_TAXI_LOCATION = "taxi-location";
    public static final String QUEUE_TAXI_AROUND = "taxi-around";
    public static final String QUEUE_TAXI_TAKE_ORDER = "taxi-take-order";
    public static final String QUEUE_TAXI_STATUS = "taxi-status";
    public static final String QUEUE_ORDER_REQUEST = "order-request";
    public static final String QUEUE_ORDER_ORDER = "order-order";
    public static final String QUEUE_ORDER_CANCEL = "order-cancel";
    public static final String QUEUE_ROUTE_CALC = "route-calc";
    public static final String QUEUE_GET_CAR_INFO = "get-car-info";
}
