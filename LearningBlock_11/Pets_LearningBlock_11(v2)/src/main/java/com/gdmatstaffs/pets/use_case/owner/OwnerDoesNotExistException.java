package com.gdmatstaffs.pets.use_case.owner;

public class OwnerDoesNotExistException extends RuntimeException
{
    public OwnerDoesNotExistException(int ownerId)
    {
        super("There is no owner with id " + ownerId);
    }
}
