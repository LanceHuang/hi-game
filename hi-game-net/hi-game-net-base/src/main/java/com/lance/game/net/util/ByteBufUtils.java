package com.lance.game.net.util;

import io.netty.buffer.ByteBuf;

/**
 * @author Lance
 */
public class ByteBufUtils {

    public static byte readByte(ByteBuf buffer) {
        return buffer.readByte();
    }

    public static short readShort(ByteBuf buffer) {
        return buffer.readShort();
    }

    public static int readInt(ByteBuf buffer) {
        return zigZagToInt(readRawVarInt32(buffer));
    }

    public static long readLong(ByteBuf buffer) {
        return buffer.readLong(); // todo
    }

    public static void writeByte(ByteBuf buffer, byte value) {
        buffer.writeByte(value);
    }

    public static void writeShort(ByteBuf buffer, short value) {
        buffer.writeShort(value);
    }

    public static void writeInt(ByteBuf buffer, int value) {
        writeRawVarInt32(buffer, intToZigZag(value));
    }

    public static void writeLong(ByteBuf buffer, long value) {
        buffer.writeLong(value); // todo
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
     * 从buffer读取32位整数
     */
    private static int readRawVarInt32(ByteBuf buffer) {
        fastpath:
        {
            int readerIndex = buffer.readerIndex();
            int x;
            if ((x = buffer.readByte()) >= 0) { // 第一个字节0开头
                return x;
            } else if ((x ^= (buffer.readByte() << 7)) < 0) { // 第2个字节0开头
                x ^= (~0 << 7); // 异或还原数据
            } else if ((x ^= (buffer.readByte() << 14)) >= 0) {
                x ^= (~0 << 7) ^ (~0 << 14);
            } else if ((x ^= (buffer.readByte() << 21)) < 0) {
                x ^= (~0 << 7) ^ (~0 << 14) ^ (~0 << 21);
            } else {
                int y = buffer.readByte();
                x ^= y << 28;
                x ^= (~0 << 7) ^ (~0 << 14) ^ (~0 << 21) ^ (~0 << 28);
                if (y < 0
                        && buffer.readByte() < 0
                        && buffer.readByte() < 0
                        && buffer.readByte() < 0
                        && buffer.readByte() < 0
                        && buffer.readByte() < 0) { // todo
                    buffer.readerIndex(readerIndex); // 重置readerIndex
                    break fastpath;
                }
            }
            return x;
        }
        return (int) readRawVarInt64SlowPath(buffer);
    }

    private static long readRawVarInt64SlowPath(ByteBuf buffer) { // todo
        long result = 0;
        for (int shift = 0; shift < 64; shift += 7) {
            final byte b = readByte(buffer);
            result |= (long) (b & 0x7F) << shift;
            if ((b & 0x80) == 0) {
                return result;
            }
        }
        throw new RuntimeException();
    }

    /**
     * 将32位整数，每7位写入buffer
     */
    private static void writeRawVarInt32(ByteBuf buffer, int value) {
        while (true) {
            if ((value & ~0x7F) == 0) { // 判断8~32位还有没有数据
                writeByte(buffer, (byte) value);
                return;
            } else {
                writeByte(buffer, (byte) ((value & 0x7F) | 0x80)); // 取最低7位，并在高位补1
                value >>>= 7; // 高位补0
            }
        }
    }
}
