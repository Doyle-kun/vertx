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


    public List<Offer> prepareOffers(String input){
        List<Offer> offers = new ArrayList<>();
        Document doc = Jsoup.parse(input);
        Element offersTable = doc.getElementById("offers_table");
        Elements tableBody = offersTable.getElementsByAttribute("data-id");
        for (Element element: tableBody) {
            Offer offer = Offer.builder().id
            offer.setId(Long.valueOf(element.attributes().get("data-id")));
            Elements details = element.getElementsByTag("strong");
            offer.setName(details.get(0).ownText());
            offer.setPrice(new BigDecimal(details.get(1)
                    .ownText()
                    .trim()
                    .replace("zł", "")
                    .replace(" ", "")
                    .replace(",",".")
            ));
            offers.add(offer);
        }
        return offers;
    }
}
