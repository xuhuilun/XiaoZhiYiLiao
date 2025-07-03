package com.xuhui.xiaozhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChatFrom {
    private String message;
    private Long memoryId;
}
