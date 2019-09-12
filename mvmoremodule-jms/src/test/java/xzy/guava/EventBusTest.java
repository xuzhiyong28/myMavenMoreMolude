package xzy.guava;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/***
 * guava提供的观察者模式
 */
public class EventBusTest {


    public static void main(String[] args){
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        eventBus.register(new SimpleListener2());
        eventBus.post("Simple Event");
    }



    static class SimpleListener {
        @Subscribe
        public void doAction(final String event) {
            System.out.println(event + "_1");
        }
    }

    static class SimpleListener2{
        @Subscribe
        public void doAction(final String event) {
            System.out.println(event + "_2");
        }
    }
}
