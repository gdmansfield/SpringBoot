package com.gdmatstaffs.pets.exception;

public class OwnerIdsDoNotMatchException extends RuntimeException
{
    public OwnerIdsDoNotMatchException(int ownerId, int petOwnerId)
    {
        super("The owner id " + ownerId + " does not match the pet owner's id " + petOwnerId);
    }
}
