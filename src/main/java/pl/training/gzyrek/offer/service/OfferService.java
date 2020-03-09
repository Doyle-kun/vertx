package pl.training.gzyrek.offer.service;

import pl.training.gzyrek.offer.model.Offer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OfferService {


    public List<Offer> prepareOffers(String input) {
        List<Offer> offers = new ArrayList<>();
        Document doc = Jsoup.parse(input);
        Element offersTable = doc.getElementById("offers_table");
        Elements tableBody = offersTable.getElementsByAttribute("data-id");
        tableBody.forEach(element -> {
            Elements details = element.getElementsByTag("strong");
            offers.add(Offer.builder()
                    .id(Long.valueOf(element.attributes().get("data-id")))
                    .name(details.get(0).ownText())
                    .price(new BigDecimal(details.get(1)
                            .ownText()
                            .trim()
                            .replace("zł", "")
                            .replace(" ", "")
                            .replace(",", ".")
                    )).build());
        });
        return offers;
    }
}
