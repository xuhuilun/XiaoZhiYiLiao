package com.xuhui.xiaozhi.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(
        chatMemory = "chatMemory",
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel"
)
public interface MemoryChatAssistant {
    @UserMessage("你是我的好朋友，请用东北话回答问题，回答问题的时候适当添加表情符号。{{it}}")
    String chat(String userMessage);

}
