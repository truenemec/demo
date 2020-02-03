package com.demo.productservice.category;

import com.demo.productservice.AbstractIT;
import com.demo.productservice.category.dto.CategoryModel;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryControllerIT extends AbstractIT {

    private static final String BASE_API_URL = "/api/v1/category/";

    @Override
    public String getApiPath() {
        return BASE_API_URL;
    }

    @Test
    public void shouldProperlyHandleGetById() {
        CategoryModel categoryDto = CategoryModel.builder()
                .categoryId(1L)
                .name("root")
                .parentId(null)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<CategoryModel> request = new HttpEntity<>(headers);


        ResponseEntity<CategoryModel> result = getRestTemplate().exchange(getUrl() + categoryDto.getCategoryId(),
                HttpMethod.GET, request, CategoryModel.class);
        assertThat(result.getBody()).isEqualToComparingFieldByField(categoryDto);
    }

    @Test
    public void shouldProperlyHandleSave() {
        CategoryModel categoryDto = CategoryModel.builder()
                .name("test")
                .parentId(1L)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<CategoryModel> request = new HttpEntity<>(categoryDto, headers);

        ResponseEntity<CategoryModel> saved = getRestTemplate().postForEntity(getUrl(), request, CategoryModel.class);
        assertThat(saved.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(saved.getBody()).isEqualToIgnoringGivenFields(categoryDto, "categoryId");
    }

    @Test
    public void shouldNotProperlyHandleSaveWithIncorrectParent() {
        CategoryModel categoryDto = CategoryModel.builder()
                .name("test")
                .parentId(99L)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<CategoryModel> request = new HttpEntity<>(categoryDto, headers);

        ResponseEntity<CategoryModel> result = getRestTemplate().postForEntity(getUrl(), request, CategoryModel.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void shouldProperlyHandleUpdate() {
        CategoryModel categoryDto = CategoryModel.builder()
                .name("test")
                .parentId(1L)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<CategoryModel> request = new HttpEntity<>(categoryDto, headers);


        CategoryModel saved = getRestTemplate().postForObject(getUrl(), request, CategoryModel.class);
        CategoryModel categoryDtoUpdated = saved.toBuilder().name("new_name").build();
        ResponseEntity<CategoryModel> res = getRestTemplate().exchange(getUrl() + saved.getCategoryId(), HttpMethod.PUT,
                new HttpEntity<>(categoryDtoUpdated, headers), CategoryModel.class);
        assertThat(res.getBody()).isEqualToComparingFieldByField(categoryDtoUpdated);
    }

    @Test
    public void shouldProperlyHandleDelete() {
        CategoryModel categoryDto = CategoryModel.builder()
                .name("test")
                .parentId(1L)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<CategoryModel> request = new HttpEntity<>(categoryDto, headers);

        CategoryModel saved = getRestTemplate().postForObject(getUrl(), request, CategoryModel.class);
        getRestTemplate().delete(getUrl() + saved.getCategoryId());
        ResponseEntity<CategoryModel> result = getRestTemplate().getForEntity(getUrl() + saved.getCategoryId(), CategoryModel.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
