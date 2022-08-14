package com.smartech.sms.mapper;

import com.smartech.sms.domain.SmsLog;
import com.smartech.sms.dto.request.SmsLogRegisterRequest;
import com.smartech.sms.dto.response.SmsResponse;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class SmsLogMapper {

    public static final SmsLogMapper INSTANCE = new SmsLogMapper();

    public SmsLog initial(final SmsLogRegisterRequest request) {
        return SmsLog.builder()
                .body(request.getBody())
                .number(request.getNumber())
                .sendTime(LocalDateTime.now())
                .messageStatus(request.getMessageStatus())
                .build();

    }

    public SmsResponse getResponse(final SmsLog smsLog) {
        return SmsResponse.builder()
                .id(smsLog.getId())
                .messageStatus(smsLog.getMessageStatus())
                .number(smsLog.getNumber())
                .build();
    }
}
