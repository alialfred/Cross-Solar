package com.crossover.techtrial.controller;

import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import java.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * PanelControllerTest class will test all APIs in PanelController.java.
 *
 * @author Crossover
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PanelControllerTest {

    MockMvc mockMvc;

    @Mock
    private PanelController panelController;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(panelController).build();
    }

    @Test
    public void testPanelShouldBeRegistered() throws Exception {
        HttpEntity<Object> panel = getHttpEntity(
                "{\"serial\": \"2323232323232323\", \"longitude\": \"54.123232\","
                + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
        ResponseEntity<Panel> response = template.postForEntity("/api/register", panel, Panel.class);
        Assert.assertEquals(202, response.getStatusCode().value());
    }

    @Test
    public void testPanelShouldNotBeRegistered() throws Exception {
        HttpEntity<Object> panel = getHttpEntity(
                "{\"serial\": \"232323\", \"longitude\": \"54.123232\","
                + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
        ResponseEntity<Panel> response = template.postForEntity("/api/register", panel, Panel.class);
        Assert.assertEquals(400, response.getStatusCode().value());
    }

    @Test
    public void testHourlyElectricityShouldBeCreate() throws Exception {
        HourlyElectricity electricity = new HourlyElectricity();
        electricity.setGeneratedElectricity(125L);
        electricity.setReadingAt(LocalDateTime.now());
        HttpEntity<Object> he = getHttpEntity(electricity);
        ResponseEntity<HourlyElectricity> response = template.postForEntity("/api/panels/{panel-serial}/hourly", he, HourlyElectricity.class, "1234567890123456");
        Assert.assertEquals(202, response.getStatusCode().value());
    }

    @Test
    public void testHourlyElectricityShouldNotBeCreate() throws Exception {
        HourlyElectricity electricity = new HourlyElectricity();
        electricity.setGeneratedElectricity(125L);
        electricity.setReadingAt(LocalDateTime.now());
        HttpEntity<Object> he = getHttpEntity(electricity);
        ResponseEntity<HourlyElectricity> response = template.postForEntity("/api/panels/{panel-serial}/hourly", he, HourlyElectricity.class, "0");
        Assert.assertEquals(400, response.getStatusCode().value());
    }

    @Test
    public void testHourlyElectricityGet() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/panels/{panel-serial}/hourly", String.class, "1234567890123456");
        Assert.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void testDailyElectricity() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/panels/{panel-serial}/daily", String.class, "1234567890123456");
        Assert.assertEquals(200, response.getStatusCode().value());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }
}
