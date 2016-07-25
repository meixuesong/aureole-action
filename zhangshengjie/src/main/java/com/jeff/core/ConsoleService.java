package com.jeff.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jeff on 7/17/16.
 */
public class ConsoleService {
    public int getMainChoice(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int res=-1;
        try{
            res = Integer.parseInt(br.readLine());
        }catch(Exception nfe){
            System.err.println("Invalid Format!");
        }
        return res;
    }

    public String getSubInput(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String res="";
        try{
            res = br.readLine();
            while(res.length()!= RandomUtils.SEQ_LENGTH){
                res = br.readLine();
            }

        }catch(Exception nfe){
            System.err.println("Invalid Format!");
        }
        return res;
    }
}
