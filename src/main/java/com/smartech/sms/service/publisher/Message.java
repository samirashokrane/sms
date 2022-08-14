package com.smartech.sms.service.publisher;

import java.io.Serializable;

public interface Message<T> extends Serializable {
    T getPayload();

}
