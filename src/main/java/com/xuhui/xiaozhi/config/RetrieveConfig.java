package com.xuhui.xiaozhi.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@Configuration
public class RetrieveConfig {
        @Bean
        ContentRetriever contentRetrieverXiaoZhi() {
                // 使用FileSystemDocumentLoader读取指定目录下的知识库文档
                // 并使用默认的文档解析器对文档进行解析
                Document document1 = null;
                Document document2 = null;
                Document document3 = null;
                try {
                        String path1 = Paths.get(getClass().getClassLoader().getResource("knowledge/医院信息.md").toURI())
                                        .toString();
                        document1 = FileSystemDocumentLoader.loadDocument(path1);
                        String path2 = Paths.get(getClass().getClassLoader().getResource("knowledge/科室信息.md").toURI())
                                        .toString();
                        document2 = FileSystemDocumentLoader.loadDocument(path2);
                        String path3 = Paths.get(getClass().getClassLoader().getResource("knowledge/神经内科.md").toURI())
                                        .toString();
                        document3 = FileSystemDocumentLoader.loadDocument(path3);
                } catch (NullPointerException | URISyntaxException e) {
                        throw new RuntimeException("知识库文件未找到或路径格式错误，请检查resources/knowledge目录下的文件是否存在，且无特殊字符", e);
                }

                List<Document> documents = Arrays.asList(document1, document2, document3);

                // 使用内存向量存储
                InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

                // 使用默认的文档分割器
                EmbeddingStoreIngestor.ingest(documents, embeddingStore);
                // 从嵌入存储（EmbeddingStore）里检索和查询内容相关的信息
                return EmbeddingStoreContentRetriever.from(embeddingStore);
        }
}
