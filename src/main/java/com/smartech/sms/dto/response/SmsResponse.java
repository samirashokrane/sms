package com.smartech.sms.dto.response;

import com.smartech.sms.dto.enums.MessageStatusEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SmsResponse implements Serializable {

     static final long serialVersionUID = 1L;

    Long id ;

    MessageStatusEnum messageStatus;

    String number;
}
