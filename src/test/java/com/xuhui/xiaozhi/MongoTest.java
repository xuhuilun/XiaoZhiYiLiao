package com.xuhui.xiaozhi;


import com.xuhui.xiaozhi.entity.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class MongoTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoTemplate() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("hi");
        mongoTemplate.insert(chatMessages);
    }
}
