package ru.job4j.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.job4j.domain.*;
import ru.job4j.service.CarService;
import ru.job4j.service.PartsService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carServ;

    @MockBean
    private PartsService partsServ;

    @MockBean
    private FileController file;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetAllCarsThenPageCars() throws Exception {
        given(
                this.carServ.findAll()
        ).willReturn(new Iterable<Car>() {
                         @Override
                         public Iterator<Car> iterator() {
                             List<Car> list = new ArrayList<>();
                             list.add(new Car(
                                     1, "car", new Motor(1), new Transmission(1), new Bodywork(1),
                                     true, new Timestamp(System.currentTimeMillis()), new User(1), ""
                                     )
                             );
                             return list.iterator();
                         }
                     }
        );
        List<Iterable<?>> list = new ArrayList<>();
        List<Object> part = new ArrayList<>();
        part.add(new Motor(1, "motor"));
        list.add(part);
        part.add(new Transmission(1, "trans"));
        list.add(part);
        part.add(new Bodywork(1, "body"));
        list.add(part);
        given(
                this.partsServ.findAllParts()
        ).willReturn(list);
        this.mvc.perform(
                get("/cars").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("index")
        );
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    public void whenPostSiteThenAdd() throws Exception {
        this.mvc.perform(
                post("/with_foto").param("id", "1")
        ).andExpect(
                status().isOk()
        );
        verify(this.carServ, times(1)).findCarsByFotoNotNull();
    }
}
