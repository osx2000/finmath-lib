package net.finmath.stochastic;

/**
 * Created by os on 06/05/14.
 */
public interface RandomNullaryOperationInterface<T> {
    T sin();
    T cos();
    T exp();
    T log();
    T sqrt();
    T squared();
    T invert();
    T abs();

    /**
     * Returns the filtration time.
     *
     * @return The filtration time.
     */
    double getFiltrationTime();
}
