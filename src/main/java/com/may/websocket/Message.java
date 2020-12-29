package com.may.websocket;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class Message {
    private String name;
    private String content;
    private Date sendDate;
}
