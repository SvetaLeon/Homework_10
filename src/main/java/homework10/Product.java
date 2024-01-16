package homework10;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@ToString
public class Product {
    private final String type;
    private final double price;
    private final boolean discount;
    private final LocalDateTime dateOfAddition;
}
