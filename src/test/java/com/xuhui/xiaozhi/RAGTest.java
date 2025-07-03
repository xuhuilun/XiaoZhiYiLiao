package com.xuhui.xiaozhi;


import com.alibaba.dashscope.tokenizers.QwenTokenizer;
import com.xuhui.xiaozhi.constant.EnvironmentConst;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RAGTest {


    @Test
    public void testParsePDF() {
        Document document = FileSystemDocumentLoader.loadDocument(
                "D:\\GoogleDownload\\中科大-伦旭辉-个人简历.pdf",
                new ApachePdfBoxDocumentParser()
        );
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        DocumentByParagraphSplitter documentByParagraphSplitter = new DocumentByParagraphSplitter(
                300,
                30,
                new HuggingFaceTokenizer()
        );

        EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .documentSplitter(documentByParagraphSplitter)
                .build()
                .ingest(document);

    }
}
