package io.slurm.cources.currency.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.slurm.cources.currency.client.HttpCurrencyDateRateClient;
import io.slurm.cources.currency.schema.ValCurs;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class CbrService {

    private final Cache<LocalDate, Map<String, BigDecimal>> cache;

    private HttpCurrencyDateRateClient client;

    public CbrService(HttpCurrencyDateRateClient client) {
        cache = CacheBuilder.newBuilder().build();
        this.client = client;
    }

    public BigDecimal requestByCurrencyCode(String code) {
        try {
            return cache.get(LocalDate.now(), this::callAllByCurrentDate).get(code);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, BigDecimal> callAllByCurrentDate() {
        var xml = client.requestByDate(LocalDate.now());
        ValCurs response = unmarshall(xml);
        return response.getValute().stream().collect(Collectors.toMap(ValCurs.Valute::getCharCode, item -> parseWithLocale(item.getValue())));
    }

    private ValCurs unmarshall(String xml) {
        try (StringReader reader = new StringReader(xml)) {
            JAXBContext content = JAXBContext.newInstance(ValCurs.class);
            return (ValCurs) content.createUnmarshaller().unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private BigDecimal parseWithLocale(String currency) {
        try {
            double v = NumberFormat.getNumberInstance(Locale.getDefault()).parse(currency).doubleValue();
            return BigDecimal.valueOf(v);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
