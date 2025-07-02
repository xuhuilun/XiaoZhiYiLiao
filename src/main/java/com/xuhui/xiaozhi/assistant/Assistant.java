package com.xuhui.xiaozhi.assistant;


import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * @author 伦旭辉
 * @date 2025/06/25 16:02
 **/
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel"
)
public interface Assistant {
    String chat(String userMessage);
}
