package br.com.bootcampjava.exceptions;

public class DAOException extends Exception{
	public DAOException(){}
	public DAOException(Throwable t){
		super(t);
	}
	public DAOException(Throwable t, String message){
		super(message, t);
	}
	public DAOException(String message){
		super(message);
	}
}


