package com.xuhui.xiaozhi.assistant;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(
        chatMemory = "chatMemory",
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel"
)
public interface MemoryChatAssistant {
    String chat(String userMessage);
}
