package net.finmath.tests.montecarlo;

import net.finmath.montecarlo.RandomBivariate;
import net.finmath.montecarlo.RandomVariable;
import net.finmath.stochastic.RandomBivariateInterface;
import net.finmath.stochastic.RandomVariableInterface;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by os on 06/05/14.
 */
public class RandomBivariateTests {
    @Test
    public void testRandomVariableDeterministc() {

        List<RandomVariableInterface> spass = new ArrayList<RandomVariableInterface>();

        // Create a random variable with a constant
        RandomVariableInterface randomVariable = new RandomVariable(2.0);
        spass.add(randomVariable);

        // Perform some calculations
        randomVariable = randomVariable.mult(2.0);
        spass.add(randomVariable);
        randomVariable = randomVariable.add(1.0);
        spass.add(randomVariable);
        randomVariable = randomVariable.squared();
        spass.add(randomVariable);
        randomVariable = randomVariable.sub(4.0);
        spass.add(randomVariable);
        randomVariable = randomVariable.div(7.0);
        spass.add(randomVariable);

        RandomBivariateInterface bv = new RandomBivariate(0.0,spass,true);

        for(RandomVariableInterface rvi : bv.columns())
        {
            Assert.assertTrue(rvi.getVariance() == 0.0);
            Assert.assertTrue(rvi.getAverage() > 0.0);
        }

        for(RandomVariableInterface rvi : bv.rows())
        {
            Assert.assertTrue(rvi.getVariance() == 0.0);
            Assert.assertTrue(rvi.getAverage() > 0.0);
        }

    }
}
