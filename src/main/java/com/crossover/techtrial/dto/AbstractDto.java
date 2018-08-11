/*
 */
package com.crossover.techtrial.dto;

/**
 *
 * @author Ali Imran
 * @param <E>
 */
public abstract class AbstractDto<E> {

    public abstract String validate();
    
    public abstract E getEntity();
}
