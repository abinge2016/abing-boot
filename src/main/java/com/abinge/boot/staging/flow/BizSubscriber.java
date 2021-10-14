package com.abinge.boot.staging.flow;

import java.util.concurrent.Flow;

public class BizSubscriber implements Flow.Subscriber<String> {

    private Flow.Subscription subscription;

    /**
     * Method invoked prior to invoking any other Subscriber
     * methods for the given Subscription. If this method throws
     * an exception, resulting behavior is not guaranteed, but may
     * cause the Subscription not to be established or to be cancelled.
     *
     * <p>Typically, implementations of this method invoke {@code
     * subscription.request} to enable receiving items.
     *
     * @param subscription a new subscription
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    /**
     * Method invoked with a Subscription's next item.  If this
     * method throws an exception, resulting behavior is not
     * guaranteed, but may cause the Subscription to be cancelled.
     *
     * @param item the item
     */
    @Override
    public void onNext(String item) {
        System.out.println("subscriber received data：" + item);
        this.subscription.request(1);
    }

    /**
     * Method invoked upon an unrecoverable error encountered by a
     * Publisher or Subscription, after which no other Subscriber
     * methods are invoked by the Subscription.  If this method
     * itself throws an exception, resulting behavior is
     * undefined.
     *
     * @param throwable the exception
     */
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.subscription.cancel();
    }

    /**
     * Method invoked when it is known that no additional
     * Subscriber method invocations will occur for a Subscription
     * that is not already terminated by error, after which no
     * other Subscriber methods are invoked by the Subscription.
     * If this method throws an exception, resulting behavior is
     * undefined.
     */
    @Override
    public void onComplete() {
        System.out.println("subscriber completed!!");
    }
}
