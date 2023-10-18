package ris58h.antlrkit.generator;

import java.io.IOException;

public interface IOCallback<T> {
    void accept(T value) throws IOException;
}
