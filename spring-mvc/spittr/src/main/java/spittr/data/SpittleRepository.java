package spittr.data;
import spittr.Spittle;

import java.util.List;

public interface SpittleRepository {
    /**
     *
     * @param max Spittle ID 的最大值
     * @param count Spittle 对象的个数
     * @return
     */
    List<Spittle> findSpittles (long max, int count);

    Spittle findOne(long id);

}
