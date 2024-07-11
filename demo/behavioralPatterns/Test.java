package behavioralPatterns;

public class Test {
    public static void main(String[] args) {

    }
    String solve(String s){
        int i;
        int j;
        int ML  = 0;
        int ansI = 0;
        int ansJ = 0;
        for (i = 0;i < s.length();i++){
            for (j = 0;i - j>=0 && i+j < s.length();j++){
                if(s.charAt(i-j) != s.charAt(i+j)){
                    break;
                }
            }
            if(j*2+1 > ML){
                ansI = i-j;
                ansJ = i+j;
            }
        }
        for (i = 0;i < s.length()-1;i++){
            for (j = 0;i - j >= 0 && i-j+1 < s.length();i++){
                if(s.charAt(i-j) != s.charAt(i+j+1)){
                    break;
                }
            }
            if(j*2+2 > ML){
                ansI = i-j;
                ansJ = i+j+2;
            }
        }
        return s.substring(ansI,ansJ);
    }
}
