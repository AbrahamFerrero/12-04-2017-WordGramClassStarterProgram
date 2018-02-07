import java.util.*;
/**
 * Class created as part of Assignment 2. Please read the readme.txt file for more info.
 * 
 * @author (Abraham Ferrero) 
 * @version (11/DIC/2017)
 */
public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order){
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
		for(int k=start; k < words.length - myOrder; k++) {
			WordGram wg = new WordGram(words,k,myOrder);
			if(wg.equals(target)) {
				return k;
			}
		}
		return -1;
	}
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos =0;
        while(pos < myText.length){
            int start = indexOf(myText,kGram,pos);
            if (start == -1){
               break;
            }
            if (start >= myText.length-1){
               break;  
            }
            String next = myText[start+myOrder];
            follows.add(next);
            pos = start+1;
        }
        return follows;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
}
