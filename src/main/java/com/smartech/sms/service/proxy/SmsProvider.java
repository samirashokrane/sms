package com.smartech.sms.service.proxy;

import com.smartech.sms.dto.request.SmsRequest;
import com.smartech.sms.dto.enums.SmsProviderEnum;
import com.smartech.sms.dto.response.SmsLogResponse;

public interface SmsProvider {

     SmsLogResponse send(final SmsRequest smsRequest);

     SmsProviderEnum getProvider();

     Boolean isUp();
}
