package lab;

import io.vavr.Function0;
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

    public static String getSoutFrom(Runnable runnable) {
        return withProxyForOut(soutExtractor -> {
            runnable.run();
            return soutExtractor.get();
        });
    }

    private static  <T> Tuple2<T, String> getResultAndSoutFrom(Supplier<T> supplier) {
        return withProxyForOut(soutExtractor -> Tuple.of(
                supplier.get(), soutExtractor.get()));
    }

    private static  <T> T withProxyForOut(Function<Supplier<String>, T> soutExtractorMapper) {
        val realOut = System.out;
        val out = new ByteArrayOutputStream();

        Supplier<String> soutExtractor = Function0.of(out::toByteArray)
                .andThen(String::new)
                .andThen(String::intern);

        try (val printStream = new PrintStream(out)) {
            System.setOut(printStream);
            return soutExtractorMapper.apply(soutExtractor);
        } finally {
            System.setOut(realOut);
        }
    }
}
