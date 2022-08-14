package com.smartech.sms.service.scheduler;

import com.smartech.sms.domain.SmsLog;
import com.smartech.sms.dto.request.SmsRequest;
import com.smartech.sms.service.logic.SmsLogService;
import com.smartech.sms.service.publisher.SmsMsgPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SmsSendRetry {


    private final SmsLogService smsLogService;

    private final SmsMsgPublisher smsMsgPublisher;


    @Scheduled(initialDelay = 5 * 60 * 1000, fixedRate = 5 * 60 * 1000)
    public synchronized void resend() {
        log.info("get all failed sms........");
        List<SmsLog> lstSmsLog = smsLogService.getAllFailed();
        if (lstSmsLog == null) {
            return;
        }
        lstSmsLog.forEach(
                smsLog -> {

                    SmsRequest smsRequest = SmsRequest.builder().body(smsLog.getBody()).number(smsLog.getNumber()).build();
                    smsMsgPublisher.publish2Queue(smsRequest);
                });
    }
}
