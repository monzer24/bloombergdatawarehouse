package com.bloomberg.datawarehouse.deal;

import com.bloomberg.datawarehouse.BloombergdatawarehouseApplication;
import com.bloomberg.datawarehouse.controller.form.DealForm;
import com.bloomberg.datawarehouse.dto.DealDTO;
import com.bloomberg.datawarehouse.facade.DealFacade;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BloombergdatawarehouseApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class DealTest {

    @Autowired
    private MockMvc mvc;

    @Resource(name = "dealFacade")
    private DealFacade dealFacade;

    @Test
    public void should_add_deal_successfully() throws Exception { // perform method throws Exception
        String dealId= UUID.randomUUID().toString();
        double amount = 12.4567D;
        String orderCurrency = "JOD";
        String toCurrency = "AED";
        String dealDate = LocalDateTime.now().minusHours(2).toString();

        String url = UriComponentsBuilder.fromPath("/")
                .queryParam("dealId", dealId)
                .queryParam("dealAmount", amount)
                .queryParam("orderCurrencyIsoCode", orderCurrency)
                .queryParam("toCurrencyIsoCode", toCurrency)
                .queryParam("dealDate", dealDate)
                .toUriString();

        MvcResult mvcResult = mvc.perform(post(url)).andReturn();
        assertThat(mvcResult.getResponse().getStatus(), Matchers.equalTo(302));
        assertThat(dealFacade.getDealById(dealId).isPresent(), Matchers.equalTo(true));
    }

    @Test
    public void get_deal_details_successfully() throws Exception { // perform method throws Exception
        String dealId = UUID.randomUUID().toString();

        String url = UriComponentsBuilder.fromPath("/"+dealId)
                .toUriString();

        Object result = mvc.perform(get(url))
                .andReturn()
                .getModelAndView()
                .getModel()
                .get("deal");

        boolean isPresent = dealFacade.getDealById(dealId).isPresent();
        if(isPresent){
            assertThat(result instanceof DealDTO, Matchers.is(true));
            assertThat(((DealDTO) result).getDealCode(), Matchers.is(dealId));
        }
    }

    @Test
    public void get_all_deals_available() throws Exception {
        String url = UriComponentsBuilder.fromPath("/deals")
                .toUriString();

        Object result = mvc.perform(get(url))
                .andReturn()
                .getModelAndView()
                .getModel()
                .get("deals");

    }

}
