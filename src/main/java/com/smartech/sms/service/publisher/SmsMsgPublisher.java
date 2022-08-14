package com.smartech.sms.service.publisher;

import com.smartech.sms.dto.request.SmsRequest;
import com.smartech.sms.dto.response.SmsResponse;

public interface SmsMsgPublisher {

     SmsResponse publish2Queue(final SmsRequest smsRequest);

}
