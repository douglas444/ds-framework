package br.com.douglas444.dsframework.interceptor;

@FunctionalInterface
public interface ExecuteOrDefault<T, U> {
    U executeOrDefault(T t);
}
