package id.lagrangian.gauge.service;

import id.lagrangian.gauge.exception.LastUpdateFindException;
import id.lagrangian.gauge.model.ExchangeRates;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class IndonesianExchangeService {

    public ExchangeRates latest() throws IOException, LastUpdateFindException {
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setBase("IDR");
        exchangeRates.setDate(Date.valueOf(findLateUpdate()));
        exchangeRates.setRates(findExchangeRates());
        return exchangeRates;
    }

    public Map<String, Double> findExchangeRates() throws IOException {
        Document doc = Jsoup.connect("https://www.bi.go.id/en/moneter/informasi-kurs/transaksi-bi/Default.aspx").get();
        Map<String, Double> result = new HashMap<>();
        Elements rateTable = doc.select("#main-content .table1 tbody tr");
        // remove header
        rateTable.remove(0);
        rateTable.forEach(element -> {
            Elements rateRow = element.getElementsByTag("td");
            if (rateRow.size() >=4 ) {
                String currency = rateRow.get(0).text();
                Double value = Double.valueOf(rateRow.get(1).text().replace(",", ""));
                Double shell = Double.valueOf(rateRow.get(2).text().replace(",", ""));
                Double buy = Double.valueOf(rateRow.get(3).text().replace(",", ""));
                result.put(currency, (shell + buy) / (2 * value));
            }
        });
        return result;
    }

    public LocalDate findLateUpdate() throws LastUpdateFindException, IOException {
        Document doc = Jsoup.connect("https://www.bi.go.id/en/moneter/informasi-kurs/transaksi-bi/Default.aspx").get();
        Elements elements = doc.select(".floatR span");
        if (!elements.get(0).text().equals("Last Update")) {
            throw new LastUpdateFindException(LastUpdateFindException.NOT_FOUND);
        } else {
            LocalDate localDate = LocalDate.now();
            try {
                localDate = LocalDate.parse(elements.get(1).text());
            } catch (Exception e) {
                throw new LastUpdateFindException(LastUpdateFindException.PARSE_FAILED);
            } finally {
                return localDate;
            }
        }
    }

}