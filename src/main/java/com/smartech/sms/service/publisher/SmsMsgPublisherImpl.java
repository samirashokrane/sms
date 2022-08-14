package com.smartech.sms.service.publisher;

import com.smartech.sms.dto.enums.MessageStatusEnum;
import com.smartech.sms.dto.request.SmsRequest;
import com.smartech.sms.dto.response.SmsResponse;
import com.smartech.sms.service.logic.SmsLogService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SmsMsgPublisherImpl implements SmsMsgPublisher {


    private final MessageSender messageSender;

    public SmsResponse publish2Queue(final SmsRequest smsRequest) {
        GeneralMessage<SmsRequest> generalMessage = new GeneralMessage<SmsRequest>(smsRequest);

        log.info("publish2Queue ...............");
        messageSender.sendMessage(generalMessage);

        return SmsResponse.builder().messageStatus(MessageStatusEnum.PUBLISH).build();
    }
}
