package interfaces;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;

public class CharGen implements Readable{

    private static final char[] seq = "aCharSeq".toCharArray();
    
    private int count;
    
    public CharGen(int count){
        this.count=count;
    }
    
    @Override
    public int read(CharBuffer cb) throws IOException {
        if(count-- ==0) return -1;
        int n=seq.length;
        for(int i=0;i<n;i++){
            cb.append(seq[i]);
        }
        cb.append(" ");
        return 0;
    }
    
    public static void main(String[] args){
        Scanner s = new Scanner(new CharGen(8));
        while(s.hasNext()){
            System.out.println(s.next());
        }
        
    }

}
