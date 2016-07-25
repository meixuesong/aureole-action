package com.jeff.core;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jeff on 7/17/16.
 */
public class GuessService {
    public String tryGuess(String sequence,String guess){
        int aNum=0,bNum=0;
        Set<Character> seqSet=new HashSet<>();
        for(int i=0;i<RandomUtils.SEQ_LENGTH;i++){
            seqSet.add(sequence.charAt(i));
            if(sequence.charAt(i) == guess.charAt(i)){
                aNum++;
            }
        }
        for(int i=0;i<RandomUtils.SEQ_LENGTH;i++){
            if(seqSet.contains(guess.charAt(i))){
                bNum++;
            }
        }
        bNum-=aNum;

        return String.format("%dA%dB",aNum,bNum);
    }
}
