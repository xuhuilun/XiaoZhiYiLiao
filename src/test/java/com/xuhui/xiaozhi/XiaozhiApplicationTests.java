package com.xuhui.xiaozhi;

import com.xuhui.xiaozhi.assistant.Assistant;
import com.xuhui.xiaozhi.assistant.MemoryChatAssistant;
import com.xuhui.xiaozhi.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class XiaozhiApplicationTests {

    @Autowired
    private MemoryChatAssistant assistant;

    @Autowired
    private SeparateChatAssistant separateAssistant;

    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Autowired
    private MemoryChatAssistant memoryChatAssistant;

    @Test
    public void testChatMemory5() {
        String answer1 = separateChatAssistant.chat2(1, "我是环环");
        System.out.println(answer1);

    }




}
