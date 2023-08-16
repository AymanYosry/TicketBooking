package com.poc.booking.dao;

import lombok.Data;

import java.io.Serializable;

@Data
public class DaoResponse implements Serializable {
    private String status;
    private String statusMessage;
    private Object result;
}
