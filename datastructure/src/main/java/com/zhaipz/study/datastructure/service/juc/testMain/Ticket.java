package com.zhaipz.study.datastructure.service.juc.testMain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaipz
 * @ClassName: Ticket
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2021/8/26 17:39
 */
@Data
@Slf4j
public class Ticket {
    public int tickets;

    public Ticket(int tickets) {
        this.tickets = tickets;
    }

    public void saleTicket(){
        log.info("线程" + Thread.currentThread().getName() + "卖出一张票,剩余票数：" + tickets--);

    }
}
