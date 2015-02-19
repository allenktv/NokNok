package com.kbear.noknok.events;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by allen on 2/18/15.
 */
public final class EventProducer {
    private static final Bus BUS = new Bus(ThreadEnforcer.ANY);
    private static final Bus MAIN_BUS = new Bus(ThreadEnforcer.MAIN);

    private EventProducer() {}

    /**
     * This instance is used if dealing with events strictly on MAIN THREAD
     * @return - Bus instance that can run only on MAIN thread
     */
    public static Bus getInstance() {
        return MAIN_BUS;
    }

    /**
     * This instance is used if dealing with events on ANY THREAD
     * @return - Bus instance that can run on ANY thread
     */
    public static Bus getAsyncInstance() {
        return BUS;
    }

    public static void register(Object obj) {
        MAIN_BUS.register(obj);
        BUS.register(obj);
    }

    public static void unregister(Object obj) {
        MAIN_BUS.unregister(obj);
        BUS.unregister(obj);
    }
}
