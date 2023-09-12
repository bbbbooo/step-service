package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.question.ReqQuestionDTO;
import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import com.example.stepbackend.repository.QuestionRepository;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class QuestionControllerTest {

    @Autowired
    private QuestionController myController;

    private WebApplicationContext context;

    private MockMvc mockMvc;

    private WireMockServer wireMockServer;

    @Autowired
    private QuestionRepository questionRepository;

    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
        restTemplate = new RestTemplate();
    }

    @AfterEach
    void tearDown() {
        // WireMock 서버 종료
        wireMockServer.stop();
    }

    @Test
    void testMockServerResponseWithWebClient() {
        // MockServer에서 응답을 설정합니다.
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/data"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("Hello, World!")
                        .withHeader("Content-Type", MediaType.TEXT_PLAIN_VALUE)));

        // 실제 서버 대신 MockServer에 요청을 보냅니다.
        String mockServerUrl = "http://localhost:" + wireMockServer.port() + "/data";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(mockServerUrl, String.class);

        // 응답을 확인합니다.
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Hello, World!", responseEntity.getBody());
    }

    @Test
    @DisplayName("문제 응답")
    void testReadQuestion() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/data"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("{\"main\":\"As a system for transmitting specific factual information without any distortion or ambiguity, the sign system of honey-bees would probably win easily over human language every time. However, language offers something more valuable than mere information exchange. Because the meanings of words are not invariable and because understanding always involves interpretation, the act of communicating is always a joint, creative effort. Words can carry meanings beyond those consciously intended by speakers or writers because listeners or readers bring their own perspectives to the language they encounter. Ideas expressed imprecisely may be more intellectually stimulating for listeners or readers than simple facts. The fact that language is not always reliable for causing precise meanings to be generated in someone else’s mind is a reflection of its powerful strength as a medium for creating new understanding. It is the inherent ambiguity and adaptability of language as a meaning-making system that makes the relationship between language and thinking so special.\"," +
                                  "\"answer\":\"4\"," +
                                  "\"subject\":\"다음 빈칸에 들어갈 말로 가장 적절한 것을 고르시오.\"," +
                                  "\"class\":\"blank\"," +
                                  "\"view1\":\"be selected for general purposes\"," +
                                  "\"view2\":\"meet the requirements of your inquiry\"," +
                                  "\"view3\":\"be as high as possible for any occasion\"," +
                                  "\"view4\":\"be applied to new technology by experts\"," +
                                  "\"view5\":\"rely exclusively upon satellite information\"" +
                                  "}")
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE))
        );

        String mockServerUrl = "http://localhost:" + wireMockServer.port() + "/data";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(mockServerUrl, String.class);

        String classification;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseEntity.getBody().toString());
            classification = (String) jsonObject.get("class");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // 응답을 확인합니다.
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("blank", classification);
    }
}