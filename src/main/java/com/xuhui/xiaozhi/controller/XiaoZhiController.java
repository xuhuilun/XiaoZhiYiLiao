package com.xuhui.xiaozhi.controller;


import com.xuhui.xiaozhi.assistant.XiaoZhiAgent;
import com.xuhui.xiaozhi.entity.ChatFrom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "硅谷小智")
@RequestMapping("/xiaozhi")
@RestController
public class XiaoZhiController {
    @Autowired
    private XiaoZhiAgent xiaoZhiAgent;

    @Operation(summary = "talk")
    @PostMapping("/chat")
    public String talk(@RequestBody ChatFrom chatFrom) {
        return xiaoZhiAgent.chat(chatFrom.getMemoryId(), chatFrom.getMessage());
    }

}
