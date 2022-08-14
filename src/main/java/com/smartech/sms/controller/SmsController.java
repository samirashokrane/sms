package com.smartech.sms.controller;

import com.smartech.sms.domain.SmsLog;
import com.smartech.sms.dto.request.SmsRequest;

import com.smartech.sms.dto.response.SmsResponse;
import com.smartech.sms.service.logic.SmsLogService;
import com.smartech.sms.service.publisher.SmsMsgPublisher;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/sms")
@AllArgsConstructor
public class SmsController {


    private final SmsMsgPublisher smsMsgPublisher;
    private final SmsLogService smsLogService;

    /**
     * send sms
     * @param number
     * @param body
     * @return
     */
    @GetMapping("/send/{number}/{body}")
    public ResponseEntity<SmsResponse> send(final @PathVariable("number") String number, final @PathVariable("body") String body) {

        return new ResponseEntity<>(smsMsgPublisher.publish2Queue(SmsRequest.builder().body(body).number(number).build()), HttpStatus.OK);
    }


    /**
     * get all failed sms
     *
     * @return for test
     */
    @Hidden
    @GetMapping("/get/all/failed")
    public ResponseEntity<List<SmsLog>> getAllFailed() {

        return new ResponseEntity<>(smsLogService.getAllFailed(), HttpStatus.OK);
    }
}
