package com.jeff.core;

import com.jeff.shell.Command;

/**
 * Created by jeff on 7/17/16.
 */
public class State {
    private String sequence;
    private String input;
    private String output;
    private int count;
    private Command next;
    private String result="ONGOING";

    public State() {
    }

    public State(String sequence, String input, String output, int count, Command next) {
        this.sequence = sequence;
        this.input = input;
        this.output = output;
        this.count = count;
        this.next = next;
    }

    public void reset(){
        this.sequence=null;
        this.input=null;
        this.output=null;
        this.count=6;
        this.next=null;
        this.result="ONGOING";
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void decCount(){
        this.count--;
    }

    public boolean gameOver(){
        return this.count <= 0;
    }

    public Command getNext() {
        return next;
    }

    public void setNext(Command next) {
        this.next = next;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
