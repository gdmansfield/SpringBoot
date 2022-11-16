package com.gdmatstaffs.pets.owner.command;

public interface Command
{
    int CHECK_CREDENTIALS = 1;
    int CREATE_OWNER = 2;
    int DELETE_OWNER = 3;
    int UPDATE_OWNER_ADDRESS = 4;
    int GET_OWNER = 5;
    int GET_OWNER_LIST = 6;

    Object execute();
}
