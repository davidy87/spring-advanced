package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class LogTemplate {

    private final LogTrace trace;

    public LogTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String message, Callback<T> callback) {
        TraceStatus status = null;

        try {
            status = trace.begin(message);
            T result = callback.call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
