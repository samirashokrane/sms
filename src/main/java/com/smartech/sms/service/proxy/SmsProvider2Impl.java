package com.smartech.sms.service.proxy;

import com.smartech.sms.dto.request.SmsRequest;
import com.smartech.sms.dto.enums.MessageStatusEnum;
import com.smartech.sms.dto.enums.SmsProviderEnum;
import com.smartech.sms.dto.response.SmsLogResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional(rollbackFor = Exception.class)
public class SmsProvider2Impl implements SmsProvider2 {

    public SmsLogResponse send(final SmsRequest request) {
        try {
            return SmsLogResponse.builder().number(request.getNumber()).messageStatus(MessageStatusEnum.SUCCESS).build();
        }catch(Exception ex){
            return SmsLogResponse.builder().number(request.getNumber()).messageStatus(MessageStatusEnum.FAIL).build();
        }

    }

    @Override
    public SmsProviderEnum getProvider() {
        return SmsProviderEnum.SMS_PROVIDER2;
    }

    @Override
    public Boolean isUp() {
        return Boolean.TRUE.booleanValue();
    }
}
