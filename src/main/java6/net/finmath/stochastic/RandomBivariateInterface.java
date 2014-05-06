package net.finmath.stochastic;

import net.finmath.montecarlo.RandomBivariate;
import net.finmath.montecarlo.RandomVariable;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Iterator;

/**
 * Created by os on 06/05/14.
 */
public interface RandomBivariateInterface extends RandomNullaryOperationInterface<RandomBivariateInterface>,RandomUnaryOperationInterface<RandomBivariateInterface> {
    Iterable<RandomVariableInterface> rows();

    Iterable<RandomVariableInterface> columns();

    RealMatrix  getRealMatrix();

    RandomVariableInterface getRow(int i);

    RandomVariableInterface getColumn(int i);

}
