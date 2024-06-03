package com.batch.example.SpringBatchExample.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Sales {
    private int transactionId;
    private Date date;
    private String productCateg;
    private String productName;
    private int soldUnit;
    private float unitPrice;
    private double totalRevenue;
    private String paymentMethod;
}
