package com.lance.game.net.util;

import com.google.protobuf.CodedOutputStream;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

/**
 * @author Lance
 */
public class ByteBufUtils {

    public static boolean readBoolean(ByteBuf buf) {
        return readInt(buf) == 1;
    }

    public static byte readByte(ByteBuf buf) {
        return buf.readByte();
    }

    public static byte[] readBytes(ByteBuf buf, int dataLength) {
        byte[] data = new byte[dataLength];
        buf.readBytes(data);
        return data;
    }

    public static short readShort(ByteBuf buf) {
        return buf.readShort();
    }

    public static int readInt(ByteBuf buf) {
        return zigZagToInt(readRawVarInt32(buf));
    }

    public static long readLong(ByteBuf buf) {
        return buf.readLong(); // todo
    }

    public static float readFloat(ByteBuf buf) {
        return buf.readFloat();
    }

    public static double readDouble(ByteBuf buf) {
        return buf.readDouble();
    }

    public static String readString(ByteBuf buf) {
        int dataLength = readInt(buf);
        byte[] data = readBytes(buf, dataLength);
        return new String(data, StandardCharsets.UTF_8);
    }

    public static void writeBoolean(ByteBuf buf, boolean value) {
        writeByte(buf, (byte) (value ? 1 : 0));
    }

    public static void writeByte(ByteBuf buf, byte value) {
        buf.writeByte(value);
    }

    public static void writeBytes(ByteBuf buf, byte[] value) {
        buf.writeBytes(value);
    }

    public static void writeShort(ByteBuf buf, short value) {
        buf.writeShort(value);
    }

    public static void writeInt(ByteBuf buf, int value) {
        writeRawVarInt32(buf, intToZigZag(value));
    }

    public static void writeLong(ByteBuf buf, long value) {
        buf.writeLong(value); // todo
    }

    public static void writeFloat(ByteBuf buf, float value) {
        buf.writeFloat(value);
    }

    public static void writeDouble(ByteBuf buf, double value) {
        buf.writeDouble(value);
    }

    public static void writeString(ByteBuf buf, String value) {
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        writeInt(buf, data.length);
        writeBytes(buf, data);
    }

    /**
     * 将32位整数转换成ZigZag编码
     */
    private static int intToZigZag(int n) {
        return (n << 1) ^ (n >> 31);
    }

    /**
     * 将ZigZag编码转换成32位整数
     */
    private static int zigZagToInt(int n) {
        return (n >>> 1) ^ -(n & 1);
    }

    /**
     * 从buf读取32位整数
     */
    private static int readRawVarInt32(ByteBuf buf) {
        fastpath:
        {
            int readerIndex = buf.readerIndex();
            int x;
            if ((x = buf.readByte()) >= 0) { // 第一个字节0开头
                return x;
            } else if ((x ^= (buf.readByte() << 7)) < 0) { // 第2个字节0开头
                x ^= (~0 << 7); // 异或还原数据
            } else if ((x ^= (buf.readByte() << 14)) >= 0) {
                x ^= (~0 << 7) ^ (~0 << 14);
            } else if ((x ^= (buf.readByte() << 21)) < 0) {
                x ^= (~0 << 7) ^ (~0 << 14) ^ (~0 << 21);
            } else {
                int y = buf.readByte();
                x ^= y << 28;
                x ^= (~0 << 7) ^ (~0 << 14) ^ (~0 << 21) ^ (~0 << 28);
                if (y < 0
                        && buf.readByte() < 0
                        && buf.readByte() < 0
                        && buf.readByte() < 0
                        && buf.readByte() < 0
                        && buf.readByte() < 0) {
                    buf.readerIndex(readerIndex); // 重置readerIndex
                    break fastpath;
                }
            }
            return x;
        }
        return (int) readRawVarInt64SlowPath(buf);
    }

    private static long readRawVarInt64SlowPath(ByteBuf buf) { // todo
        long result = 0;
        for (int shift = 0; shift < 64; shift += 7) {
            final byte b = readByte(buf);
            result |= (long) (b & 0x7F) << shift;
            if ((b & 0x80) == 0) {
                return result;
            }
        }
        throw new RuntimeException();
    }

    /**
     * 将32位整数，每7位写入buf
     */
    private static void writeRawVarInt32(ByteBuf buf, int value) {
        while (true) {
            if ((value & ~0x7F) == 0) { // 判断8~32位还有没有数据
                writeByte(buf, (byte) value);
                return;
            } else {
                writeByte(buf, (byte) ((value & 0x7F) | 0x80)); // 取最低7位，并在高位补1
                value >>>= 7; // 高位补0
            }
        }
    }

    public static int computeBoolean(boolean value) {
        return 1;
    }

    public static int computeByte(byte value) {
        return 1;
    }

    public static int computeShort(short value) {
        return 2;
    }

    public static int computeInt(int value) {
        return computeRawVarint32Size(intToZigZag(value));
    }

    public static int computeLong(long value) {
        // todo
        return 8;
    }

    private static int computeRawVarint32Size(int value) {
        if ((value & (0xffffffff << 7)) == 0) {
            return 1;
        }
        if ((value & (0xffffffff << 14)) == 0) {
            return 2;
        }
        if ((value & (0xffffffff << 21)) == 0) {
            return 3;
        }
        if ((value & (0xffffffff << 28)) == 0) {
            return 4;
        }
        return 5;
    }

    public static int computeFloat(float value) {
        return 4;
    }

    public static int computeDouble(double value) {
        return 8;
    }

    public static int computeString(String value) {
        return CodedOutputStream.computeStringSizeNoTag(value);
    }
}
