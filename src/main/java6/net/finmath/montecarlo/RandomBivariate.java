package net.finmath.montecarlo;

import net.finmath.stochastic.RandomBivariateInterface;
import net.finmath.stochastic.RandomVariableInterface;
import org.apache.commons.math3.linear.DefaultRealMatrixChangingVisitor;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;


import java.util.Iterator;
import java.util.List;

/**
 * Created by os on 06/05/14.
 */
public class RandomBivariate implements RandomBivariateInterface {

    private RealMatrix data;

    private double filtrationTime = 0.0;

    @Override
    public RandomBivariateInterface sin() {
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
                      {

                return FastMath.sin(value);
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public RandomBivariateInterface cos() {
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {

                return FastMath.cos(value);
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public RandomBivariateInterface exp() {
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {

                return FastMath.exp(value);
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public RandomBivariateInterface log() {
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {

                return FastMath.log(value);
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public RandomBivariateInterface sqrt() {
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {

                return FastMath.sqrt(value);
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public RandomBivariateInterface squared() {
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {

                return value * value;
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public RandomBivariateInterface invert() {
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return 1.0 / value;
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public RandomBivariateInterface abs() {
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return FastMath.abs(value);
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public double getFiltrationTime() {
        return filtrationTime;
    }

    @Override
    public RandomBivariateInterface cap(double cap) {
        final double mcap = cap;
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return FastMath.min(mcap,value);
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public RandomBivariateInterface floor(double floor) {
        final double mfloor = floor;
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return FastMath.max(mfloor, value);
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public RandomBivariateInterface add(double value) {
        return new RandomBivariate(filtrationTime, data.scalarAdd(value));
    }

    @Override
    public RandomBivariateInterface sub(double value) {
        return new RandomBivariate(filtrationTime, data.scalarAdd(-value));
    }

    @Override
    public RandomBivariateInterface mult(double value) {
        return new RandomBivariate(filtrationTime, data.scalarMultiply(value));
    }

    @Override
    public RandomBivariateInterface div(double value) {
        return new RandomBivariate(filtrationTime, data.scalarMultiply(1.0/value));
    }

    @Override
    public RandomBivariateInterface pow(double exponent) {
        final double mexponent = exponent;
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return Math.pow(value, mexponent);
            }
        });
        return new RandomBivariate(filtrationTime,rm);
    }

    @Override
    public RandomBivariateInterface add(RandomBivariateInterface randomVariable) {
        return new RandomBivariate(Math.max(filtrationTime, randomVariable.getFiltrationTime())
                , data.add(randomVariable.getRealMatrix()));
    }

    @Override
    public RandomBivariateInterface sub(RandomBivariateInterface randomVariable) {
        return new RandomBivariate(Math.max(filtrationTime, randomVariable.getFiltrationTime())
                , data.subtract(randomVariable.getRealMatrix()));
    }

    @Override
    public RandomBivariateInterface mult(RandomBivariateInterface randomVariable) {
        final RandomBivariateInterface rb = randomVariable;
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return rb.getRealMatrix().getEntry(row,column) * value;
            }
        });
        return new RandomBivariate(Math.max(filtrationTime, randomVariable.getFiltrationTime()),rm);
    }

    @Override
    public RandomBivariateInterface div(RandomBivariateInterface randomVariable) {
        final RandomBivariateInterface rb = randomVariable;
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return value / rb.getRealMatrix().getEntry(row,column) ;
            }
        });
        return new RandomBivariate(Math.max(filtrationTime, randomVariable.getFiltrationTime()),rm);
    }

    @Override
    public RandomBivariateInterface cap(RandomBivariateInterface cap) {
        final RandomBivariateInterface rb = cap;
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return FastMath.min(value ,rb.getRealMatrix().getEntry(row,column)) ;
            }
        });
        return new RandomBivariate(Math.max(filtrationTime, cap.getFiltrationTime()),rm);
    }

    @Override
    public RandomBivariateInterface floor(RandomBivariateInterface floor) {
        final RandomBivariateInterface rb = floor;
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return FastMath.max(value, rb.getRealMatrix().getEntry(row, column)) ;
            }
        });
        return new RandomBivariate(Math.max(filtrationTime, floor.getFiltrationTime()),rm);
    }

    @Override
    public RandomBivariateInterface accrue(RandomBivariateInterface rate, final double periodLength) {
        final RandomBivariateInterface rb = rate;
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return value * (1 + rb.getRealMatrix().getEntry(row,column) * periodLength) ;
            }
        });
        return new RandomBivariate(Math.max(filtrationTime, rate.getFiltrationTime()),rm);
    }

    @Override
    public RandomBivariateInterface discount(RandomBivariateInterface rate, final double periodLength) {
        final RandomBivariateInterface rb = rate;
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return value / (1 + rb.getRealMatrix().getEntry(row,column) * periodLength) ;
            }
        });
        return new RandomBivariate(Math.max(filtrationTime, rate.getFiltrationTime()),rm);
    }

    @Override
    public RandomBivariateInterface barrier(final RandomBivariateInterface trigger, final RandomBivariateInterface valueIfTriggerNonNegative, RandomBivariateInterface valueIfTriggerNegative) {
        double newTime = Math.max(filtrationTime, trigger.getFiltrationTime());
        newTime = Math.max(newTime, valueIfTriggerNonNegative.getFiltrationTime());
        newTime = Math.max(newTime, valueIfTriggerNegative.getFiltrationTime());

        // Less capable than RV implementation

        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return trigger.getRealMatrix().getEntry(row,column) >= 0.0 ?
                        valueIfTriggerNonNegative.getRealMatrix().getEntry(row,column)
                        : valueIfTriggerNonNegative.getRealMatrix().getEntry(row,column);
            }
        });

        return new RandomBivariate(newTime,rm);

    }

    @Override
    public RandomBivariateInterface barrier(RandomBivariateInterface trigger, RandomBivariateInterface valueIfTriggerNonNegative, double valueIfTriggerNegative) {
        final double val = valueIfTriggerNegative;
        // TODO faster
        RealMatrix rm = this.getRealMatrix().copy();
        rm.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {
            @Override
            public double visit(int row, int column, double value)
            {
                return val ;
            }
        });

        return this.barrier(trigger, valueIfTriggerNonNegative, new RandomBivariate(valueIfTriggerNonNegative.getFiltrationTime(), rm));

    }

    private class RowIterator implements Iterator<RandomVariableInterface> {

        private int currentRow;
        private RealMatrix realMatrix;

        public RowIterator() {
            currentRow = 0;
            this.realMatrix = data;
        }

        @Override
        public boolean hasNext() {
            return currentRow < realMatrix.getRowDimension();
        }

        @Override
        public RandomVariableInterface next() {
            RandomVariableInterface el = new RandomVariable(filtrationTime,data.getRow(currentRow));
            currentRow++;
            return el;
        }
    }

    private class ColumnIterator implements Iterator<RandomVariableInterface> {

        private int currentColumn;
        private RealMatrix realMatrix;

        public ColumnIterator() {
            currentColumn = 0;
            this.realMatrix = data;
        }

        @Override
        public boolean hasNext() {
            return currentColumn < realMatrix.getColumnDimension();
        }

        @Override
        public RandomVariableInterface next() {
            RandomVariableInterface el = new RandomVariable(filtrationTime,data.getColumn(currentColumn));
            currentColumn++;
            return el;
        }
    }


    private class Rows implements Iterable<RandomVariableInterface> {
        @Override
        public RowIterator iterator() {

            return new RowIterator();
        }
    }

    private class Columns implements Iterable<RandomVariableInterface> {
        @Override
        public ColumnIterator iterator() {
            return new ColumnIterator();
        }
    }


    @Override
    public Rows rows() {
        return new Rows();
    }

    @Override
    public Columns columns() {
        return new Columns();
    }

    @Override
    public RealMatrix getRealMatrix() {
        return data;
    }

    public RandomBivariate(double filtrationTime, RealMatrix rm)
    {
        this.filtrationTime = filtrationTime;
        this.data = rm;
    }

    public RandomBivariate(double filtrationTime, List<RandomVariableInterface> vectors, Boolean columns)
    {
        double filtration = filtrationTime;
        int vectorLength = vectors.get(0).size();
        int cols = columns ? vectors.size() : vectorLength;
        int rows = columns ? vectorLength : vectors.size();
        RealMatrix rm = MatrixUtils.createRealMatrix(rows,cols);
        int ii = 0;
        for(RandomVariableInterface rv : vectors)
        {
            filtration = Math.max(filtration,rv.getFiltrationTime());
            if(columns) {
                rm.setColumn(ii,rv.getRealizations());
            } else {
                rm.setRow(ii,rv.getRealizations());
            }

            ii++;

        }
        this.filtrationTime = filtration;
        this.data = rm;
    }


}
