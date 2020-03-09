package pl.training.gzyrek.offer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.training.gzyrek.offer.OfferVerticle;
import pl.training.gzyrek.offer.model.Offer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OfferService {
    private static Logger logger = LoggerFactory.getLogger(OfferService.class);

    public List<Offer> prepareOffers(String input) {
        List<Offer> offers = new ArrayList<>();
        logger.debug("Parsing input from: " + input);
        Document doc = Jsoup.parse(input);
        logger.debug("Parsed document: " + doc);
        Element offersTable = doc.getElementById("offers_table");
        logger.debug("Got offers table: " + offersTable);
        if(offersTable!=null) {
            Elements tableBody = offersTable.getElementsByAttribute("data-id");
            logger.debug("Got table content: " + tableBody);
            tableBody.forEach(element -> {
                Elements details = element.getElementsByTag("strong");
                Offer.OfferBuilder offer = Offer.builder()
                        .id(Long.valueOf(element.attributes().get("data-id")))
                        .name(details.get(0).ownText());
                        if (details.size()>1) {
                        offer.price(new BigDecimal(details.get(1)
                                    .ownText()
                                    .trim()
                                    .replace("zł", "")
                                    .replace(" ", "")
                                    .replace(",", ".")
                            ));
                        }
                offers.add(offer.build());
            });
        }
        logger.debug("Produced list of offers: " + offers.toString());
        return offers;
    }
}
