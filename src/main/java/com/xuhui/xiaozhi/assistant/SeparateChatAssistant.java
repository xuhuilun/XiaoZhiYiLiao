package com.xuhui.xiaozhi.assistant;


import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory",
        chatMemoryProvider = "chatMemoryProvider"
)
public interface SeparateChatAssistant {
    //    @SystemMessage("你是我的好朋友，请用河南话回答问题,今天是{{current_date}}")

    //    @SystemMessage(fromResource = "date.txt")


    String chat(@MemoryId int memoryId, @UserMessage String userMessage);


    @UserMessage("你是我的好朋友，请用东北话回答问题，回答问题的时候适当添加表情符号。{{message}}")
    String chat2(@MemoryId int memoryId, @V("message") String userMessage);


}
