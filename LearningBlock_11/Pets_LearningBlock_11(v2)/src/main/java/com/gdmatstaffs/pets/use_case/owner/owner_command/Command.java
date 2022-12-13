package com.gdmatstaffs.pets.use_case.owner.owner_command;

public interface Command
{
    int CHECK_CREDENTIALS = 1;
    int CHECK_TOKEN = 2;
    int CLEAR_TOKEN = 3;
    int CREATE_OWNER = 4;
    int DELETE_OWNER = 5;
    int UPDATE_OWNER_ADDRESS = 6;
    int GET_OWNER = 7;
    int GET_OWNER_LIST = 8;

    Object execute();
}
