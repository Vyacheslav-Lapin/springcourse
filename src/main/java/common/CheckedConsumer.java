package common;

import java.util.function.Consumer;

public interface CheckedConsumer<T> extends io.vavr.CheckedConsumer<T> {

    static <T> CheckedConsumer<T> of(CheckedConsumer<T> checkedConsumer) {
        return checkedConsumer;
    }

    /**
     * Returns an unchecked consumer that will <em>sneaky throw</em> if an exceptions occurs when applying the consumer.
     *
     * @return a new Consumer that throws a {@code Throwable}.
     */
    default Consumer<T> unchecked() {
        return t -> {
            try {
                accept(t);
            } catch(Throwable ex) {
                CheckedConsumerModule.sneakyThrow(ex);
            }
        };
    }
}

interface CheckedConsumerModule {

    @SuppressWarnings("unchecked")
    static <T extends Throwable> void sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }
}