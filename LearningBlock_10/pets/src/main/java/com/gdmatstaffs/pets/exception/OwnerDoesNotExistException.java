package com.gdmatstaffs.pets.exception;

public class OwnerDoesNotExistException extends RuntimeException
{
    public OwnerDoesNotExistException(int ownerId)
    {
        super("There is no owner with id " + ownerId);
    }
}
