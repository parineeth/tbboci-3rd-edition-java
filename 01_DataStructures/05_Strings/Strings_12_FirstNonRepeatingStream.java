/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class StreamParam {
    public final int NUM_CHARS = 256;

    /*If we have seen the character 2 or more times in the stream,
    then isRepeated[character] = true*/
    public boolean[] isRepeated;

    /*For every character, we maintain the position of its first
    occurence. If the character has not yet occured in stream, we store -1 */
    public int[] firstPos; 

    public char firstUnique; /*the first unique character in the stream*/
    public int curPos;  /*the current position in the stream*/

    StreamParam() {
        isRepeated = new boolean[NUM_CHARS]; /*Initial value is False*/
        firstPos = new int[NUM_CHARS];

        for (int i = 0; i < NUM_CHARS; ++i) {
            firstPos[i] = -1;
        }
        firstUnique = '#';
        curPos = 0;
    }
}




class Strings_12_FirstNonRepeatingStream {

    public static final int MAX_STRING_LENGTH = 100;
    public static final int MAX_POS = 1000000;
    public static final int NUM_CHARS = 256;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    /*
    StreamParam: contains the parameters for processing the stream
    curChar: indicates the current character in the stream
    Returns: first unique character in the stream if it exists, # otherwise
    */
    public static char firstUniqueInStream(StreamParam p, char curChar) {
        if (p.firstPos[curChar] == -1) {
            /*We are seeing the character for the first time in the stream.
            So update its first position*/
            p.firstPos[curChar] = p.curPos;

            /*If there are no unique characters in the stream, then make 
            this the first unique character*/
            if (p.firstUnique == '#') {
                p.firstUnique = curChar;
            }

            p.curPos += 1;
            return p.firstUnique;
        }

        /*We have already seen this character before*/
        p.isRepeated[curChar] = true;

        /*If the current character is the first unique character in the stream,
        then we need to replace it with next unique character*/
        if (p.firstUnique == curChar) {
    
            /*Find the first character that occurs only once in stream*/
            int smallestPos = MAX_POS;
            p.firstUnique = '#';
            for (int i = 0; i < NUM_CHARS; ++i) {
                if (p.isRepeated[i] == false && p.firstPos[i] != -1
                   && p.firstPos[i] < smallestPos) {
                    smallestPos = p.firstPos[i];
                    p.firstUnique = (char)i;
                }
            }
        }

        p.curPos += 1;
        return p.firstUnique;
    }


    public static void test(char[] str1, char[] expectedResult)     {
        StreamParam p = new StreamParam();

        System.out.print(str1);

        System.out.print(" => ");

        int i = 0;
        for (char c : str1) {
            char result = firstUniqueInStream(p, c);

            if (result != expectedResult[i])
                handleError();

            if (result == 0) 
                System.out.print(" ");
            else        
                System.out.print(result);

            ++i;
        }

        System.out.println("");
    }



    public static void main(String[] args)  {

        test("a".toCharArray(), "a".toCharArray());

        test("aaabcdeb".toCharArray(), "a##bbbbc".toCharArray());

        System.out.println("Test passed");
    }

}
