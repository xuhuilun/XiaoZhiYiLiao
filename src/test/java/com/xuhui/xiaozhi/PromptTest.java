package com.xuhui.xiaozhi;

import com.xuhui.xiaozhi.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void test() {
        String content = separateChatAssistant.chat(3, "我是谁");
        System.out.println(content);


    }
}
