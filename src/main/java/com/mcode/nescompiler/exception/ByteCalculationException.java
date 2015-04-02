package com.mcode.nescompiler.exception;

/**
 * 
 * Exception thrown when the "calculated bytes" of a Command does 
 * not equal to the actual bytes of the Command.
 * 
 */
public class ByteCalculationException extends RuntimeException {
	public ByteCalculationException(String message) {
		super(message);
	}
}
