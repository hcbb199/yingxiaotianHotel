package cn.neteast;

import cn.neteast.yxtHotel.pojo.TbOrder;
import cn.neteast.yxtHotel.service.OrderService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring*.xml")
public class test1 {
   /* @Autowired
    public OrderService orderService;

    public void test11() {
        List<TbOrder> all = orderService.findAll();
        if (all != null) {
            for (TbOrder tbOrder : all) {
                System.out.println(tbOrder.getAdminId());

            }
        }
    } */
}
