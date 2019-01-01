import org.junit.Test;

import java.util.Random;

public class ByteTest {

    private final int BYTEMASK = 0b1111_1111;

    @Test
    public void testByteMaskAndTypeCasting() {

        final int ARRAY_SIZE = 8;


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

    @Test
    public void convertIntToByte() {

        int[] testNumbers = { -1, 0, 126, 127, 128, 129, 256, 257 };

        for(int number : testNumbers) {
            byte b = (byte) number;
            System.out.printf("%4d | %32s | %4d \n", number, Integer.toBinaryString(number), b);
            System.out.printf("%4d | %32s |\n", b & BYTEMASK, Integer.toBinaryString(b & BYTEMASK));
            System.out.println();
        }
    }

    @Test
    public void shiftOperator() {
        int a = 200;
        System.out.println(getBinaryString(a));

        int b = a >> 2;
        System.out.println(getBinaryString(b));

        int c = a << 2;
        System.out.println(getBinaryString(c));
    }

    private String getBinaryString(int number) {
        return Integer.toBinaryString(number);
    }
}
