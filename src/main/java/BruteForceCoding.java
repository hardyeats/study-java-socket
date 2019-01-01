public class BruteForceCoding {

    private static byte byteVal = 101;
    private static short shortVal = 10001;
    private static int intVal = 100_000_001;
    private static long longVal = 1_000_000_000_001L;

    // 각 자료형을 표현할 때 쓰이는 바이트 개수
    private final static int BSIZE = Byte.BYTES; // 1
    private final static int SSIZE = Short.BYTES; // 2
    private final static int ISIZE = Integer.BYTES; // 4
    private final static int LSIZE = Long.BYTES; // 8

    private final static int BYTEMASK = 0xFF; // byte 값을 부호가 없는 int 값으로 변환할 때

    public static String byteArrayToDecimalString(byte[] bArray) {
        StringBuilder rtn = new StringBuilder();
        for(byte b : bArray) {
            rtn.append(b & BYTEMASK).append("|");
        }
        return rtn.toString();
    }

    public static int encodeIntBigEndian(byte[] dst, long val, int offset, int size) {
        System.out.println("val = " + val);
        for(int i = 0; i < size; i++) {
            byte b = (byte) (val >> ((size - i - 1) * Byte.SIZE));
            System.out.println("message[" + offset + "] = " + b);
            dst[offset++] = b;
        }
        return offset;
    }

    public static long decodeIntBigEndian(byte[] val, int offset, int size) {
        long rtn = 0;
        for(int i = 0; i < size; i++) {
            rtn = (rtn << Byte.SIZE) | ((long) val[offset + i] & BYTEMASK);
        }
        return rtn;
    }

    public static void main(String[] args) {
        byte[] message = new byte[BSIZE + SSIZE + ISIZE + LSIZE];
        // Encode the fields in the target byte array
        int offset = encodeIntBigEndian(message, byteVal, 0, BSIZE);
        offset = encodeIntBigEndian(message, shortVal, offset, SSIZE);
        offset = encodeIntBigEndian(message, intVal, offset, ISIZE);
        encodeIntBigEndian(message, longVal, offset, LSIZE);
        System.out.println("Encoded message: " + byteArrayToDecimalString(message));

        // Decode several fields
        long value = decodeIntBigEndian(message, 0, BSIZE);
        System.out.println("Decoded byte = " + value);
        System.out.println("Same value as byte = " + (byte) value);

        value = decodeIntBigEndian(message, BSIZE, SSIZE);
        System.out.println("Decoded short = " + value);
        System.out.println("Same value as byte = " + (byte) value);


        value = decodeIntBigEndian(message, BSIZE + SSIZE, ISIZE);
        System.out.println("Decoded Int = " + value);
        System.out.println("Same value as byte = " + (byte) value);


        value = decodeIntBigEndian(message, BSIZE + SSIZE + ISIZE, LSIZE);
        System.out.println("Decoded long = " + value);
        System.out.println("Same value as byte = " + (byte) value);


        // Demonstrate dangers of conversion
        offset = 4;
        value = decodeIntBigEndian(message, offset, BSIZE);
        System.out.println("Decoded value (offset " + offset + ", size " + BSIZE + ") = "+ value);
        byte bVal = (byte) decodeIntBigEndian(message, offset, BSIZE);
        System.out.println("Same value as byte = " + bVal);
    }
}
