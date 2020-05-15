package me.hltj.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Convenient Utils for Vert.x Future
 */
public final class FutureUtils {

    /**
     * convert a callback style Vert.x call to Future result style
     *
     * @param consumer callback style Vert.x call
     * @param <T>      the type parameter of the AsyncResult
     * @return the Future
     */
    public static <T> Future<T> futurize(Consumer<Handler<AsyncResult<T>>> consumer) {
        Promise<T> promise = Promise.promise();
        consumer.accept(promise);
        return promise.future();
    }

    /**
     * if a Future succeed with null, map it with the default value
     *
     * @param future the Future
     * @param v0     the default value
     * @param <T>    the type parameter of the Future
     * @return the result Future
     */
    public static <T> Future<T> defaultWith(Future<T> future, T v0) {
        return future.map(x -> x == null ? v0 : x);
    }

    /**
     * if a Future succeed with null, map it with the default value
     *
     * @param future   the Future
     * @param supplier a supplier to get the default value
     * @param <T>      the type parameter of the Future
     * @return the result Future
     */
    public static <T> Future<T> defaultWith(Future<T> future, Supplier<T> supplier) {
        throw new RuntimeException("unimplemented");
    }

    /**
     * if a Future failed or succeed with null, replace it with a Future that succeed with the default value
     *
     * @param future the Future
     * @param v0     the default value
     * @param <T>    the type parameter of the Future
     * @return the result Future
     */
    public static <T> Future<T> fallbackWith(Future<T> future, T v0) {
        return defaultWith(future.otherwise(v0), v0);
    }

    /**
     * if a Future failed or succeed with null, replace it with a Future that succeed with the default value
     *
     * @param future   the Future
     * @param function a function to get the default value
     * @param <T>      the type parameter of the Future
     * @return the result Future
     */
    public static <T> Future<T> fallbackWith(Future<T> future, Function<Optional<Throwable>, T> function) {
        throw new RuntimeException("unimplemented");
    }

    /**
     * if a Future failed or succeed with null, replace it with a Future that succeed with the default value
     *
     * @param future   the Future
     * @param mapper   a function to get the default value on failure
     * @param supplier a function to get the default value for replacing null
     * @param <T>      the type parameter of the Future
     * @return the result Future
     */
    public static <T> Future<T> fallbackWith(Future<T> future, Function<Throwable, T> mapper, Supplier<T> supplier) {
        throw new RuntimeException("unimplemented");
    }
}
