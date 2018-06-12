package spittr.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class HomeControlerTest {
    @Test
    public void testHomePage() throws Exception {
        HomeControler homeControler = new HomeControler();

        MockMvc mockMvc = standaloneSetup(homeControler).build();       // 搭建 MockMvc
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));       // 对 "/" 执行 GET 请求，预期得到 home 视图
    }

}