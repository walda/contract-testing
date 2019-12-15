package io.walda.consumer.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDto {

    private Timestamp timestamp;
    private MessageDto message;
}
