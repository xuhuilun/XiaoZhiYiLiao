package com.xuhui.xiaozhi;


import com.alibaba.dashscope.tokenizers.QwenTokenizer;
import com.xuhui.xiaozhi.constant.EnvironmentConst;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.*;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RAGTest {

    @Autowired
    private EmbeddingModel embeddingModel;

    @Autowired
    private EmbeddingStore embeddingStore;


    @Test
    public void test() {
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
        EmbeddingStoreIngestor
                .builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .build()
                .ingest(documents);
    }

    @Test
    public void embeddingSearch() {
//提问，并将问题转成向量数据
        Embedding queryEmbedding = embeddingModel.embed("你最喜欢的运动是什么？").content();
//创建搜索请求对象
        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1) //匹配最相似的一条记录
//.minScore(0.8)
                .build();

//根据搜索请求 searchRequest 在向量存储中进行相似度搜索
        EmbeddingSearchResult<TextSegment> searchResult =
                embeddingStore.search(searchRequest);
//searchResult.matches()：获取搜索结果中的匹配项列表。
//.get(0)：从匹配项列表中获取第一个匹配项
        EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);
//获取匹配项的相似度得分
        System.out.println(embeddingMatch.score()); // 0.8144288515898701
//返回文本结果
        System.out.println(embeddingMatch.embedded().text());
    }

    @Test
    public void testPineconeEmbeded() {
//将文本转换成向量
        TextSegment segment1 = TextSegment.from("我喜欢羽毛球");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
//存入向量数据库
        embeddingStore.add(embedding1, segment1);
        TextSegment segment2 = TextSegment.from("今天天气很好");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);
    }
    @Test
    public void testEmbeddingModel() {

        Response<Embedding> embed = embeddingModel.embed("你好");
        System.out.println("向量维度：" + embed.content().vector().length);
        System.out.println("向量输出：" + embed.toString());
    }

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
