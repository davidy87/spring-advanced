package hello.advanced.app.v5;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.callback.LogTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final LogTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new LogTemplate(trace);
    }

    public void save(String itemId) {
        template.execute("OrderRepository.save()", () -> {
            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("예외 발생!");
            }
            sleep();
            return null;
        });
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
