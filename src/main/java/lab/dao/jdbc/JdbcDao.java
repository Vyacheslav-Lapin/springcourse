package lab.dao.jdbc;

import common.CheckedConsumer;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.val;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public interface JdbcDao {
    static PreparedStatementCreator getPreparedStatementCreator(String sql, Object... args) {
        return connection -> {

            val preparedStatement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);

            Consumer<Tuple2<Integer, ?>> prepareObject =
                    CheckedConsumer.of((Tuple2<Integer, ?> value) ->
                            preparedStatement.setObject(value._1, value._2)).unchecked();

            IntStream.range(0, args.length)
                    .mapToObj(i -> Tuple.of(i + 1, args[i]))
                    .forEach(prepareObject);

            return preparedStatement;
        };
    }

    static long executeAndGetKey(Consumer<KeyHolder> keyHolderMapper) {
        val keyHolder = new GeneratedKeyHolder();
        keyHolderMapper.accept(keyHolder);
        return keyHolder.getKey().longValue();
    }
}
