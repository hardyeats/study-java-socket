import org.junit.Test;

import java.util.Random;

public class ByteTest {


    @Test
    public void testByteMaskAndTypeCasting() {


        final int ARRAY_SIZE = 8;
        final int BYTEMASK = 0b1111_1111;


        Random r = new Random();

        int[] intArray = new int[ARRAY_SIZE];
        int[] intArrayWithByteMask = new int[ARRAY_SIZE];
        byte[] byteArray = new byte[ARRAY_SIZE];

        for(int i = 0; i < ARRAY_SIZE; i++) {
            int number = r.nextInt();
            intArray[i] = number;
            intArrayWithByteMask[i] = number & BYTEMASK;
            byteArray[i] = (byte) number;
        }

        for(int i = 0; i < ARRAY_SIZE; i++) {
            System.out.println(
                String.format("%11d (int, 10) | %32s (int, 2) | %4d (byte, 10)", intArray[i], Integer.toBinaryString(intArray[i]), byteArray[i])
            );
            System.out.println(
                String.format("%11d (int, 10) | %32s (int, 2) | %4d (byte, 10)", intArrayWithByteMask[i], Integer.toBinaryString(intArrayWithByteMask[i]), (byte) intArrayWithByteMask[i])
            );
            System.out.println();
        }
    }
}
