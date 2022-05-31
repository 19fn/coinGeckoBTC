package com.endava.endavel.batatasquad;

import com.endava.endavel.batatasquad.domain.Hotel;
import com.endava.endavel.batatasquad.persistance.HotelRepository;
import com.endava.endavel.batatasquad.services.HotelServiceImpl;
import com.endava.endavel.batatasquad.vo.HotelVO;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class HotelServiceTest {

    @MockBean
    private HotelRepository hotelRepository;

    @Autowired
    private HotelServiceImpl hotelService;
    private static Hotel hotel;
    private static Hotel hotelStatic;
    private static final List<Hotel> hotelList = new ArrayList<>();

    public HotelServiceTest() {
    }

    @BeforeAll
    public static void setUp() {
        hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Hotel 1");
        hotel.setDescription("Description 1");
        hotel.setAddress("Address 1");
        hotel.setPricePerDay(10000.0);
        hotel.setDestId(null);
        hotel.setContId(null);
        hotel.setStars(5);

        hotelList.add(hotel);
    }

    @DisplayName("Create a Static Hotel")
    private static void createStaticHotel() {
        hotelStatic = new Hotel();
        hotelStatic.setId(2);
        hotelStatic.setName("Hotel 2");
        hotelStatic.setDescription("Description 2");
        hotelStatic.setAddress("Address 2");
        hotelStatic.setPricePerDay(10000.0);
        hotelStatic.setDestId(null);
        hotelStatic.setContId(null);
        hotelStatic.setStars(3);

        hotelList.add(hotelStatic);
        assertNotNull(hotelStatic);
    }

    @Test
    @Order(1)
    @DisplayName("Check Implementation Injection")
    public void checkImplInjection() {
        assertNotNull(this.hotelService);
    }

    @Test
    @Order(2)
    @DisplayName("Create a Hotel")
    public void createHotel() {
        createStaticHotel();
        when(this.hotelRepository.save(ArgumentMatchers.any(Hotel.class))).thenReturn(hotelStatic);
        HotelVO hotelVO = new HotelVO();

        hotelVO.setId(hotelStatic.getId());
        hotelVO.setName(hotelStatic.getName());
        hotelVO.setDescription(hotelStatic.getDescription());
        hotelVO.setAddress(hotelStatic.getAddress());
        hotelVO.setPricePerDay(hotelStatic.getPricePerDay());
        // hotelVO.setDestId(hotelStatic.getDestId().getId());
        // hotelVO.setContId(hotelStatic.getContId().getId());
        hotelVO.setStars(hotelStatic.getStars());

        Hotel hotel1 = this.hotelService.createHotel(hotelVO);

        assertEquals(hotel1.getId(), hotelVO.getId());
    }

    @Test
    @Order(3)
    @DisplayName("Hotel By ID")
    public void getHotelById() {
        when(this.hotelRepository.findHotelById(hotel.getId())).thenReturn(hotel);
        Hotel hotel1 = this.hotelService.findHotelById(hotel.getId());
        assertEquals(hotel, hotel1);
    }

    @Test
    @Order(4)
    @DisplayName("Hotel By Name")
    public void getHotelByName() {
        when(this.hotelRepository.findHotelByName(hotel.getName())).thenReturn(hotel);
        Hotel hotel1 = this.hotelService.findHotelByName(hotel.getName());
        assertEquals(hotel, hotel1);
    }

    @Test
    @Order(5)
    @DisplayName("Hotel By Address")
    public void getHotelByAddress() {
        when(this.hotelRepository.findHotelByAddress(hotel.getAddress())).thenReturn(hotel);
        Hotel hotel1 = this.hotelService.findHotelByAddress(hotel.getAddress());
        assertEquals(hotel, hotel1);
    }


    @Test
    @Order(6)
    @DisplayName("All Hotels By Stars")
    public void getAllHotelsByStars() {
        when(this.hotelRepository.findHotelsByStars(5)).thenReturn(hotelList);
        List<Hotel> hotels = this.hotelService.findHotelsByStars(5);
        verify(hotelRepository).findHotelsByStars(5);
    }

    @Test
    @Order(7)
    @DisplayName("All Hotels By DestId")
    public void getAllHotelsByDestId() {
        when(this.hotelRepository.findHotelsByDestId(null)).thenReturn(hotelList);
        List<Hotel> hotels = this.hotelService.findHotelsByDestId(null);
        verify(hotelRepository).findHotelsByDestId(null);
    }

    @Test
    @Order(8)
    @DisplayName("All Hotels By Price Per Day")
    public void getAllHotelsByPricePerDay() {
        when(this.hotelRepository.findHotelsByPricePerDay(10000.00)).thenReturn(hotelList);
        List<Hotel> hotels = this.hotelService.findHotelsByPricePerDay(10000.00);
        verify(hotelRepository).findHotelsByPricePerDay(10000.00);
    }

    @Test
    @Order(9)
    @DisplayName("All Hotels")
    public void getAllHotels() {
        when(this.hotelRepository.findAll()).thenReturn(hotelList);
        List<Hotel> hotelList1 = this.hotelService.findHotels();
        assertEquals(hotelList1.size(), hotelList.size());
    }

}