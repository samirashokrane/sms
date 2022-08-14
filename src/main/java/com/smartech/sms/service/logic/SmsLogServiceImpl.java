package com.smartech.sms.service.logic;

import com.smartech.sms.domain.SmsLog;
import com.smartech.sms.dto.request.SmsLogRegisterRequest;
import com.smartech.sms.dto.request.SmsLogUpdateRequest;
import com.smartech.sms.dto.enums.MessageStatusEnum;
import com.smartech.sms.dto.response.SmsResponse;
import com.smartech.sms.exception.ResourceNotFoundException;
import com.smartech.sms.mapper.SmsLogMapper;
import com.smartech.sms.repository.SmsLogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SmsLogServiceImpl implements SmsLogService {

    private final SmsLogRepository smsLogRepository;


    public SmsResponse smsLogRegister(SmsLogRegisterRequest smsLogRequest) {

        log.info("smsRequest...{}", smsLogRequest.toString());
        SmsLog smsLog = smsLogRepository.save(SmsLogMapper.INSTANCE.initial(smsLogRequest));
        return SmsLogMapper.INSTANCE.getResponse(smsLog);

    }

    public void smsLogUpdate(SmsLogUpdateRequest smsLogUpdateRequest) {

        SmsLog smsLog = smsLogRepository.findById(smsLogUpdateRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found SMSLog with id =" + smsLogUpdateRequest.getId().toString()));

        smsLog.setMessageStatus(smsLogUpdateRequest.getMessageStatus());
        smsLog.setProvider(smsLogUpdateRequest.getSmsProvider());
        smsLog = smsLogRepository.save(smsLog);
        log.info("success update sms log ...{}", smsLog);
    }


    public List<SmsLog> getAllFailed() {
        List<SmsLog> smsLogList = smsLogRepository.findByMessageStatus(MessageStatusEnum.FAIL);
        log.info("smsLog List...{}", smsLogList);
        return smsLogList;
    }

}
