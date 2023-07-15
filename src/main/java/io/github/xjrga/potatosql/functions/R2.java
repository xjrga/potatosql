package io.github.xjrga.potatosql.functions;

public interface R2<X1, X2, Y> {
    Y apply( X1 x1, X2 x2 );
}
