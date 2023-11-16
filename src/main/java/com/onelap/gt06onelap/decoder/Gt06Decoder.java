package com.onelap.gt06onelap.decoder;

import com.onelap.gt06onelap.enums.DecoderType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import java.net.SocketAddress;

public abstract class Gt06Decoder {

    public static Gt06Decoder getDecoder(DecoderType type){
        Gt06Decoder decoder;
        switch (type){
            case HEX:
                decoder = new Gt06HexDecoder();
                break;
            default:
                throw new RuntimeException("no such decoder exist");
        }
        return decoder;
    }
    public  abstract  ApiResponse<?> decode(Channel channel, SocketAddress remoteAddress, Object msg);

}
