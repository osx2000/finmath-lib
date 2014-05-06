package net.finmath.stochastic;

/**
 * Created by os on 06/05/14.
 */
public interface RandomUnaryOperationInterface<T> {
    /**
     * Applies x &rarr; min(x,cap) to this random variable.
     * @param cap The cap.
     * @return New random variable with the result of the function.
     */
    T cap(double cap);

    /**
     * Applies x &rarr; max(x,floor) to this random variable.
     * @param floor The floor.
     * @return New random variable with the result of the function.
     */
    T floor(double floor);

    /**
     * Applies x &rarr; x + value to this random variable.
     * @param value The value to add.
     * @return New random variable with the result of the function.
     */
    T add(double value);

    /**
     * Applies x &rarr; x - value to this random variable.
     * @param value The value to subtract.
     * @return New random variable with the result of the function.
     */
    T sub(double value);

    /**
     * Applies x &rarr; x * value to this random variable.
     * @param value The value to multiply.
     * @return New random variable with the result of the function.
     */
    T mult(double value);

    /**
     * Applies x &rarr; x / value to this random variable.
     * @param value The value to divide.
     * @return New random variable with the result of the function.
     */
    T div(double value);

    /**
     * Applies x &rarr; pow(x,exponent) to this random variable.
     * @param exponent The exponent.
     * @return New random variable with the result of the function.
     */
    T pow(double exponent);

    /**
     * Applies x &rarr; x+randomVariable to this random variable.
     * @param randomVariable A random variable (compatible with this random variable).
     * @return New random variable with the result of the function.
     */
    T add(T randomVariable);

    /**
     * Applies x &rarr; x-randomVariable to this random variable.
     * @param randomVariable A random variable (compatible with this random variable).
     * @return New random variable with the result of the function.
     */
    T sub(T randomVariable);

    /**
     * Applies x &rarr; x*randomVariable to this random variable.
     * @param randomVariable A random variable (compatible with this random variable).
     * @return New random variable with the result of the function.
     */
    T mult(T randomVariable);

    /**
     * Applies x &rarr; x/randomVariable to this random variable.
     * @param randomVariable A random variable (compatible with this random variable).
     * @return New random variable with the result of the function.
     */
    T div(T randomVariable);

    /**
     * Applies x &rarr; min(x,cap) to this random variable.
     * @param cap The cap. A random variable (compatible with this random variable).
     * @return New random variable with the result of the function.
     */
    T cap(T cap);

    /**
     * Applies x &rarr; max(x,floor) to this random variable.
     * @param floor The floor. A random variable (compatible with this random variable).
     * @return New random variable with the result of the function.
     */
    T floor(T floor);

    /**
     * Applies x &rarr; x * (1.0 + rate * periodLength) to this random variable.
     * @param rate The accruing rate. A random variable (compatible with this random variable).
     * @param periodLength The period length
     * @return New random variable with the result of the function.
     */
    T accrue(T rate, double periodLength);

    /**
     * Applies x &rarr; x / (1.0 + rate * periodLength) to this random variable.
     * @param rate The discounting rate. A random variable (compatible with this random variable).
     * @param periodLength The period length
     * @return New random variable with the result of the function.
     */
    T discount(T rate, double periodLength);

    /**
     * Applies x &rarr; (trigger &ge; 0 ? valueIfTriggerNonNegative : valueIfTriggerNegative)
     * @param trigger The trigger. A random variable (compatible with this random variable).
     * @param valueIfTriggerNonNegative The value used if the trigger is greater or equal 0
     * @param valueIfTriggerNegative The value used if the trigger is less than 0
     * @return New random variable with the result of the function.
     */
    T barrier(T trigger, T valueIfTriggerNonNegative, T valueIfTriggerNegative);

    /**
     * Applies x &rarr; (trigger &ge; 0 ? valueIfTriggerNonNegative : valueIfTriggerNegative)
     * @param trigger The trigger. A random variable (compatible with this random variable).
     * @param valueIfTriggerNonNegative The value used if the trigger is greater or equal 0
     * @param valueIfTriggerNegative The value used if the trigger is less than 0
     * @return New random variable with the result of the function.
     */
    T barrier(T trigger, T valueIfTriggerNonNegative, double valueIfTriggerNegative);


}
