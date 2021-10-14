package com.abinge.boot.staging.flow;

import java.util.concurrent.SubmissionPublisher;

public class FlowTest {
    public static void main(String[] args) throws Exception {

        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        BizProcessor processor = new BizProcessor();
        BizSubscriber subscriber = new BizSubscriber();
        //增加中间过滤
        publisher.subscribe(processor);
        //最终消费处理
        processor.subscribe(subscriber);

        publisher.submit(-111);
        publisher.submit(111);
        publisher.submit(222);
        publisher.submit(333);
        publisher.submit(444);

        publisher.close();

        Thread.currentThread().join(1000);

    }
}
