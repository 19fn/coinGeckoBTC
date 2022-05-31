package com.endava.endavel.batatasquad;

import com.endava.endavel.batatasquad.domain.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.List;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class HotelControllerTest {
    @LocalServerPort
    private int port = 8080;
    @Autowired
    private TestRestTemplate restTemplate;

    public HotelControllerTest() {
    }

    private String getHotelURL() {
        return "http://localhost:" + this.port + "/api/v1.0/hotel/";
    }
/*
    @Test
    @Order(1)
    public void saveHotels() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel");
        hotel.setDescription("Descripcion re larga");
        hotel.setAddress("Calle Falsa 123");
        hotel.setPricePerDay(10000.0);
        hotel.setStars(4);
        hotel.setContId(null);
        hotel.setDestId(null);

        HttpEntity<Hotel> request = new HttpEntity(hotel);
        ResponseEntity<Hotel> response = this.restTemplate.postForEntity(this.getHotelURL(), request, Hotel.class, new Object[0]);
        Hotel hotelInResponse = (Hotel) response.getBody();
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(hotelInResponse.getId());
    }

    @Test
    @Order(2)
    public void getAllHotels() {
        ResponseEntity<List<Hotel>> response = this.restTemplate.exchange(this.getHotelURL(), HttpMethod.GET, (HttpEntity) null, new ParameterizedTypeReference<List<Hotel>>() {
        }, new Object[0]);
        List<Hotel> hotelInResponse = (List) response.getBody();
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(0, hotelInResponse.size());
    }
*/
}