package org.example.steps.pages;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.mapping.Jackson2Mapper;
import org.example.clients.JsonUserMockClient;
import org.example.clients.XmlUserMockClient;
import org.example.models.Envelope;
import org.example.models.UserData;
import org.example.models.UserScore;
import org.example.pages.UserScorePage;

import java.util.List;

public class UserScorePageSteps {

    @Inject
    private UserScorePage userScorePage;

    @Inject
    private JsonUserMockClient jsonUserMockClient;

    @Inject
    private XmlUserMockClient xmlUserMockClient;

    @Если("Я открываю страницу с оценкой пользователя {int}")
    public void open(int id) {
        userScorePage.open("score", String.valueOf(id));
    }

    @Тогда("Оценка пользователя {int} актуальна JSON")
    public void checkScoreJson(int id) {
        userScorePage.compareUserScoreTo(jsonUserMockClient.getUserScore(id).extract().as(UserScore.class));
    }

    @Тогда("Оценка пользователя {int} актуальна XML")
    public void checkScoreXml(int id) {
        userScorePage.compareUserScoreTo(
                xmlUserMockClient.getUserScore(id).extract()
                        .as(Envelope.class, new Jackson2Mapper((t, s) -> new XmlMapper()))
                        .getBody()
                        .getUserGetOneResponse()
                        .getUserScore());
    }

}
