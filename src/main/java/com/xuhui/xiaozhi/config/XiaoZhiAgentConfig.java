package com.xuhui.xiaozhi.config;


import com.xuhui.xiaozhi.store.MongoChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiaoZhiAgentConfig {

    @Autowired
    private MongoChatMemoryStore chatMemoryStore;



    @Bean
    ChatMemoryProvider chatMemoryProviderXiaozhi(){
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(chatMemoryStore).build();
    }



}
