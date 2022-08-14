package com.smartech.sms.service.consumer;

import com.smartech.sms.config.ActiveMQConfiguration;

import com.smartech.sms.dto.request.SmsLogRegisterRequest;
import com.smartech.sms.dto.request.SmsLogUpdateRequest;
import com.smartech.sms.dto.request.SmsRequest;
import com.smartech.sms.dto.enums.MessageStatusEnum;
import com.smartech.sms.dto.enums.SmsProviderEnum;
import com.smartech.sms.dto.response.SmsLogResponse;
import com.smartech.sms.dto.response.SmsResponse;
import com.smartech.sms.exception.ResourceNotFoundException;
import com.smartech.sms.service.logic.SmsLogService;
import com.smartech.sms.service.proxy.SmsProvider;
import com.smartech.sms.service.proxy.SmsProvider1;
import com.smartech.sms.service.proxy.SmsProvider2;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SmsMsgConsumerImpl implements SmsMsgConsumer {


    private SmsProvider smsProvider = null;

    private final SmsLogService smsLogService;

    private final SmsProvider1 smsProvider1;

    private final SmsProvider2 smsProvider2;


    @JmsListener(destination = ActiveMQConfiguration.SMS_QUEUE)
    public void receive(SmsRequest smsRequest) {

        log.info("before send...register sms log with request...{}", smsRequest.toString());
        SmsResponse smsResponse = smsLogService.smsLogRegister(SmsLogRegisterRequest.builder()
                .number(smsRequest.getNumber())
                .body(smsRequest.getBody())
                .messageStatus(MessageStatusEnum.PUBLISH)
                .build());

        smsProvider = getSmsProvider();

        if (smsProvider != null) {
            log.info("receive...with provider...{}", smsProvider.toString());
            SmsLogResponse response = smsProvider.send(smsRequest);

            log.info("after send...register sms log with request...{}", smsRequest.toString());
            smsLogService.smsLogUpdate(
                    SmsLogUpdateRequest.builder()
                            .messageStatus(response.getMessageStatus())
                            .smsProvider(smsProvider.getProvider())
                            .id(smsResponse.getId())
                            .build());
        } else {
            smsLogService.smsLogUpdate(SmsLogUpdateRequest.builder()
                    .messageStatus(MessageStatusEnum.FAIL)
                    .smsProvider(SmsProviderEnum.NONE)
                    .build());
        }

    }


    private SmsProvider getSmsProvider() {

        if (smsProvider1.isUp().booleanValue()) {
            log.info("Sms Provider 1 is up...");
            return smsProvider1;
        } else {
            if (smsProvider2.isUp().booleanValue()) {
                log.info("Sms Provider 2 is up...");
                return smsProvider2;
            } else {
                // TODO: 8/14/2022  send email for system's admin
                //send email for admin system
                return null;
            }
        }
    }
}
