package pl.training.gzyrek.offer;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.training.gzyrek.offer.model.Offer;
import pl.training.gzyrek.offer.service.OfferService;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class OfferServiceTest {
    private OfferService offerService = new OfferService();
    @Test
    public void shouldPrepareOffersFromInput() throws IOException, URISyntaxException {
        URL res = getClass().getClassLoader().getResource("test.html");
        String input = new String (Files.readAllBytes(Paths.get(Objects.requireNonNull(res).toURI())));
        List<Offer> offers = offerService.prepareOffers(input);

        assertNotNull(offers);
        assertFalse(offers.isEmpty());
        assertEquals(4, offers.size());

        assertEquals(586727107, offers.get(0).getId());
        assertEquals("Felgi 18 cali Honda Hyundai Mitsubishi Kia Nissan 5 x 114.3 Et38",offers.get(0).getName());
        assertEquals(BigDecimal.valueOf(1100), offers.get(0).getPrice());

        assertEquals(589670661, offers.get(1).getId());
        assertEquals("HYUNDAI GALLOPER 00 2.5TDI SILNIK D4BH 150TYŚ FV",offers.get(1).getName());
        assertEquals(BigDecimal.valueOf(2800), offers.get(1).getPrice());

        assertEquals(586767558, offers.get(2).getId());
        assertEquals("Hyundai ix20 Szklany Dach 1.6 CRDi 116KM PDC Klimatronic",offers.get(2).getName());
        assertEquals(BigDecimal.valueOf(24900), offers.get(2).getPrice());

        assertEquals(562405190, offers.get(3).getId());
        assertEquals("Hyundai I30 1.6 CRDI*Lift* ZAMIANA*Po Opłatach*Niemiecki*Bezwypadkowy*SUPER STAN",offers.get(3).getName());
        assertEquals(BigDecimal.valueOf(16900), offers.get(3).getPrice());

    }
    @Test
    public void shouldReturnEmptyListForNonHtmlInput(){
        String input = "Some random string";
        List<Offer> offers = offerService.prepareOffers(input);
        assertNotNull(offers);
        assertTrue(offers.isEmpty());
    }
}
