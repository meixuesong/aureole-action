package com.tw.barcode;

public interface Command {

    CommandResult apply(String userInput);

}
