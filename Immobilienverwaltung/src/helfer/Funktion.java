package helfer;

@FunctionalInterface
public interface Funktion<T, R> {
    R apply(T t);
}