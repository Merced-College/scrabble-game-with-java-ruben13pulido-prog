public class Word implements Comparable<Word>{
    private String word;
    private String defintion;
    

    public Word() {
        word = "none";
        defintion = "none";
    }
    public Word(String word, String defintion){
        this.word = word;
        this.defintion = defintion;
    }

//getters and setters
     public String getWord() {
        return word;
    }
     public void setWord(String word) {
        this.word = word;
    }
    public String getDefinition() {
        return defintion;
    }
    public void setDefintion(String defintion) {
        this.defintion = defintion;
    }
    @Override
    public String toString() {
        return word + ": " + defintion;
    }
    @Override 
    public int compareTo(Word other) {
        return this.word.compareTo(other.word);
    }
} 
