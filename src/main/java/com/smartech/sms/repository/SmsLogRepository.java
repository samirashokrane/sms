package com.smartech.sms.repository;

import com.smartech.sms.domain.SmsLog;
import com.smartech.sms.dto.enums.MessageStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsLogRepository extends JpaRepository<SmsLog, Long> {

    List<SmsLog> findByMessageStatus(MessageStatusEnum messageStatusEnum);
}
