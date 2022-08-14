package com.smartech.sms.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SmsRequest  implements Serializable {

     static final long serialVersionUID = 1L;

     String number;

     String body;

}
