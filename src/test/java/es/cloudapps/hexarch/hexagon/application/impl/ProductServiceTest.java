package es.cloudapps.hexarch.hexagon.application.impl;

import es.cloudapps.hexarch.hexagon.application.ProductServicePort.*;
import es.cloudapps.hexarch.hexagon.domain.exception.NotFoundException;
import es.cloudapps.hexarch.hexagon.domain.model.Product;
import es.cloudapps.hexarch.hexagon.domain.services.ProductRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository repository;

    private ProductService productService;

    @Before
    public void setUp() {
        this.repository = mock(ProductRepository.class);
        this.productService = new ProductService(repository);
    }

    @Test
    public void shouldCreateANewProduct() {
        RegisterNewReq req = new RegisterNewReq();
        req.name = "Test product";

        Product product = mock(Product.class);
        when(product.id()).thenReturn(1);
        when(product.name()).thenReturn("Test product");

        when(repository.save(any())).thenReturn(product);

        RegisterNewResp resp = productService.registerNew(req);

        verify(repository,times(1)).save(any());

        assertNotNull(resp);
        assertThat(resp.name, is(equalTo("Test product")));
    }

    @Test
    public void shouldRemoveAnExistingProduct() {
        RemoveReq req = new RemoveReq();
        req.id = 1;

        Product product = mock(Product.class);
        when(product.id()).thenReturn(1);

        when(repository.get(1)).thenReturn(Optional.of(product));

        RemoveResp resp = productService.remove(req);

        verify(repository,times(1)).remove(product);

        assertNotNull(resp);
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailWhenRemovingANonExistingProduct() {
        RemoveReq req = new RemoveReq();
        req.id = 1;

        when(repository.get(any())).thenReturn(Optional.empty());

        productService.remove(req);
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailWhenSearchingANonExistingProduct() {
        FindReq req = new FindReq();
        req.id = 1;

        when(repository.get(any())).thenReturn(Optional.empty());

        productService.find(req);
    }

    @Test
    public void shouldReturnAllProducts() {
        FindAllReq req = new FindAllReq();

        Product p1 = mock(Product.class);
        Product p2 = mock(Product.class);
        Product p3 = mock(Product.class);

        when(repository.all()).thenReturn(Arrays.asList(p1,p2,p3));

        FindAllResp resp = productService.findAll(req);

        assertThat(resp.items.size(), is(equalTo(3)));
    }

}
