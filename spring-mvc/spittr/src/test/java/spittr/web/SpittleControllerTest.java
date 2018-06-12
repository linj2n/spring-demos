package spittr.web;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.Spittle;
import spittr.data.SpittleRepository;


public class SpittleControllerTest  {
//    @Test
//    public void shouldShowPagedSpittles () throws Exception {
//        // 创建 50 Spittle 数据
//        List<Spittle> expectedSpittles = createSpittleList(50);
//        SpittleRepository mockRepository = mock(SpittleRepository.class);
//
//
//
//    }
    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i=0; i < count; i++) {
            spittles.add(new Spittle("Spittle " + i, new Date()));
        }
        return spittles;
    }

}