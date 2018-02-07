import java.util.*;
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }
    
    //These methods are part of assignment 1 requirements:
    public int length(){
        //This method will return an int variable which is the length of the array
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // This method is a typical toString method to print a wordgram in this case.
        for (int i=0; i < length(); i++){
            ret =  ret + myWords[i] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        //equals method adapted to our wordgram project. 
        WordGram other = (WordGram) o;
        //If is not the same length, return false.
        if(this.length() != other.length()){
            return false;
        }
        //If is not the same bunch of words, return false.
        for(int i=0; i<length(); i++){
            if(!myWords[i].equals(other.wordAt(i))){
                return false;
            }
        }
        //Else, return true.
        return true;
    }

    public WordGram shiftAdd(String word) {	       
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        String[] newWords = new String[this.length()];
        for (int i=0;i<newWords.length-1;i++) {
        	newWords[i] = this.wordAt(i+1);
        }
        newWords[newWords.length-1] = word;
        WordGram out = new WordGram(newWords, 0, newWords.length);
        return out;
    }
    //End of assignment 1 by Abraham Ferrero.
    
    public int hashCode(){
        return this.toString().hashCode();
    }
}