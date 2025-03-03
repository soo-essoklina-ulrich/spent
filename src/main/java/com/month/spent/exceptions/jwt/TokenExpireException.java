package com.month.spent.exceptions.jwt;

public class TokenExpireException extends RuntimeException{
    public TokenExpireException (String msg){
        super(msg);
    }
}
