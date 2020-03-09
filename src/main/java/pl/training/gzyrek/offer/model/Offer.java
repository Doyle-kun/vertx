package pl.training.gzyrek.offer.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Offer {
    private Long id;
    private String name;
    private BigDecimal price;

}
