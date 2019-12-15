package io.walda.producer.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private Integer id;
    private String from;
    private String to;
    private String text;
    private Timestamp timestamp;

}
