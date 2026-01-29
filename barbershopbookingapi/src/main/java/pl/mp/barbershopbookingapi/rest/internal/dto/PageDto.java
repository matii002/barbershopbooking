package pl.mp.barbershopbookingapi.rest.internal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
@AllArgsConstructor
public class PageDto<T> {
    private List<T> content;

    private CustomPage customPage;

    public PageDto(Page<T> page) {
        this.content = page.getContent();
        this.customPage = new CustomPage(page.getTotalElements(),
                page.getTotalPages(), page.getNumber(), page.getSize());
    }
}
