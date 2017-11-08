package fundamentals;

import edu.princeton.cs.algs4.In;

public class InputPipeTest {
    public static void main(String[] args) {
        String[] a = In.readStrings();
        for (String s : a) {
            System.out.println(s);
        }
    }
}
// 用于尝试eclipse这个IDE的管道重定向功能
// run - run Configuration - common - input files