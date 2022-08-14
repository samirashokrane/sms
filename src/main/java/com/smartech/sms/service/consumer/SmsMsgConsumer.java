package com.smartech.sms.service.consumer;


import com.smartech.sms.dto.request.SmsRequest;

public interface SmsMsgConsumer {

    void receive(final SmsRequest smsRequest);

}
