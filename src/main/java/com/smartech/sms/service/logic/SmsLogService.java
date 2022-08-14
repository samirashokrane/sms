package com.smartech.sms.service.logic;

import com.smartech.sms.domain.SmsLog;
import com.smartech.sms.dto.request.SmsLogRegisterRequest;
import com.smartech.sms.dto.request.SmsLogUpdateRequest;
import com.smartech.sms.dto.response.SmsResponse;

import java.util.List;

public interface SmsLogService {

    SmsResponse smsLogRegister(SmsLogRegisterRequest smsLogRequest);

    void smsLogUpdate(SmsLogUpdateRequest smsLogUpdateRequest);

    List<SmsLog> getAllFailed();
}
