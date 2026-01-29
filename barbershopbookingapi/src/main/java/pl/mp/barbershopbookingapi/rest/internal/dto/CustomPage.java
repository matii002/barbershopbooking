package pl.mp.barbershopbookingapi.rest.internal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomPage {
    Long totalElements;
    int totalPages;
    int number;
    int size;
}
