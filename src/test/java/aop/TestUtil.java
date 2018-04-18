package aop;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Function;
import java.util.function.Supplier;

@UtilityClass
public class TestUtil {

    public String getFromOut(Runnable runnable) {
        return withProxyForOut(byteArraySupplyer -> {
            runnable.run();
            return new String(byteArraySupplyer.get()).intern();
        });
    }

    public <T> Tuple2<T, String> getFromOut(Supplier<T> supplier) {
        return withProxyForOut(byteArraySupplyer -> Tuple.of(
                supplier.get(),
                new String(byteArraySupplyer.get()).intern()));
    }

    private <T> T withProxyForOut(Function<Supplier<byte[]>, T> mapper) {
        val realOut = System.out;
        val out = new ByteArrayOutputStream();
        try (val printStream = new PrintStream(out)) {
            System.setOut(printStream);
            return mapper.apply(out::toByteArray);
        } finally {
            System.setOut(realOut);
        }
    }
}
