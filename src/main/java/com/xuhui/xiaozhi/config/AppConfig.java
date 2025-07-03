package com.xuhui.xiaozhi.config;

import com.xuhui.xiaozhi.constant.EnvironmentConst;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 伦旭辉
 * @date 2025/06/25 15:56
 **/
@Configuration
public class AppConfig {
    @Bean
    public QwenChatModel qwenChatModel() {
        return QwenChatModel.builder()
                .apiKey(System.getenv(EnvironmentConst.QwenAPI))
                .modelName("qwen-max")
                .build();
    }
}
