package spittr.web;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


public class HomeControllerTest {
    @Test
    public void testHomePage() throws Exception {
        HomeController homeController = new HomeController();

        MockMvc mockMvc = standaloneSetup(homeController).build();       // 1.
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));       // 2.
    }
}