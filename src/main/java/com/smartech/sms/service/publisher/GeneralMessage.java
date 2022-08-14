package com.smartech.sms.service.publisher;

import org.springframework.util.Assert;

import java.io.Serializable;

public class GeneralMessage<T> implements Message<T>, Serializable {

    private static final long serialVersionUID = 1L;

    private final T payload;

    public GeneralMessage(T payload) {
        Assert.notNull(payload, "Payload must not be null");
        this.payload = payload;
    }

    @Override
    public T getPayload() {
        return payload;
    }


}
