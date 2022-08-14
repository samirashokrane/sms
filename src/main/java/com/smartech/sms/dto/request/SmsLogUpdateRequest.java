package com.smartech.sms.dto.request;

import com.smartech.sms.dto.enums.MessageStatusEnum;
import com.smartech.sms.dto.enums.SmsProviderEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SmsLogUpdateRequest implements Serializable {

    static final long serialVersionUID = 1L;

    Long id;

    MessageStatusEnum messageStatus;

    SmsProviderEnum smsProvider;

}
