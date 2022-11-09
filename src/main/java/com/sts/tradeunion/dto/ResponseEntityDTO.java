package com.sts.tradeunion.dto;

import com.sts.tradeunion.entities.AbstractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;


/**
 * Отправляется в качестве request на клиент как страница. Содержит информацию о контенте страницы, общем количестве эелементов, и общем количестве страниц.
 *
 * @param <T> - Класс отправлямой в ответе сущности (DTO)
 * @param <S> - Класс, параметризующий принимаемый объект класса {@link Page} (Entity)
 */
public class ResponseEntityDTO<T extends AbstractEntity, S extends AbstractDTO> {

    private ModelMapper modelMapper;

    private List<S> data;
    private long totalElements;
    private int totalPages;

    public ResponseEntityDTO(Page<T> page, Class<S> entity) {
        modelMapper = new ModelMapper();
        data = new ArrayList<>();
        page.getContent().forEach(element -> data.add(modelMapper.map(element,entity)));
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
    }

    public List<S> getData() {
        return data;
    }

    public void setData(List<S> data) {
        this.data = data;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

