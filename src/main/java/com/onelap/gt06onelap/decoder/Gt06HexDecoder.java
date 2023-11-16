package com.onelap.gt06onelap.decoder;


import com.onelap.gt06onelap.formula.DataLength;
import com.onelap.gt06onelap.formula.Imei;
import com.onelap.gt06onelap.model.LoginInformation;
import com.onelap.gt06onelap.model.LoginInformationContent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.net.SocketAddress;
import java.util.function.Predicate;

public class Gt06HexDecoder extends Gt06Decoder{
    public static final int LOGIN_INFORMATION = 0x01;
    public static final int POSITIONING_DATA = 0x22;
    public static final int STATUS_INFORMATION = 0x23;
    public static final int STRING_INFORMATION = 0x13;
    public static final int ALARM_DATA = 0x26;
    public static final int COMMAND_INFORMATION_SENT_BY_SERVER_TO_THE_TERMINAL= 0x80;
    private final DataLength DATA_LENGTH = (packetLength)->  packetLength-5;
    private final Imei IMEI = (serialNumber)->  serialNumber.substring(1);
    private final Predicate<Integer> hasTimeZone = (dataLength)-> dataLength > 10;

//    public void sendResponse(Channel channel, boolean extended, int type, int index, ByteBuf content){
//        if (channel != null) {
//            ByteBuf response = Unpooled.buffer();
//            int length = 5 + (content != null ? content.readableBytes() : 0);   //content + 2 + 2 + 1
//            if (extended) {
//                response.writeShort(0x7979);
//                response.writeShort(length);
//            } else {
//                response.writeShort(0x7878);
//                response.writeByte(length);
//            }
//            response.writeByte(type);
//            if (content != null) {
//                response.writeBytes(content);
//                content.release();
//            }
//            response.writeShort(index);
//            response.writeShort(Checksum.crc16(Checksum.CRC16_X25,                      //?
//                    response.nioBuffer(2, response.writerIndex() - 2)));
//            response.writeByte('\r');
//            response.writeByte('\n');
//            channel.writeAndFlush(new NetworkMessage(response, channel.remoteAddress()));  //?
//        }
//    }

    @Override
    public ApiResponse<?> decode(Channel channel, SocketAddress remoteAddress, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        int startBit = byteBuf.readShort();
        int packetLength = byteBuf.readUnsignedByte();
        int number = byteBuf.readUnsignedByte();
        ApiResponse<?> apiResponse;
        switch (number){
            case LOGIN_INFORMATION:
                LoginInformation loginInformation = LoginInformation.getInstance().setStartBit(startBit)
                        .packetLength(packetLength) // sets data length as well
                        .protocolNumber(number)
                        .dataLength(DATA_LENGTH.compute(packetLength))
                        .informationContent(LoginInformationContent.getInstance()
                                .terminalId(IMEI.compute(ByteBufUtil.hexDump(byteBuf.readSlice(8))))
                                .identicationCode(byteBuf.readUnsignedShort())
                                .timeZoneLanguage(hasTimeZone.test(DATA_LENGTH.compute(packetLength))?byteBuf.readUnsignedShort():null)
                                .build())
                        .build();
                apiResponse =  new ApiResponse<>(loginInformation);
                break;
            case ALARM_DATA:

                apiResponse =  new ApiResponse<>(null);
                break;
            case POSITIONING_DATA:
                apiResponse =  new ApiResponse<>(null);
                break;
            case STATUS_INFORMATION:
                apiResponse =  new ApiResponse<>(null);
                break;
            case STRING_INFORMATION:
                apiResponse =  new ApiResponse<>(null);
                break;
            case COMMAND_INFORMATION_SENT_BY_SERVER_TO_THE_TERMINAL:
                apiResponse =  new ApiResponse<>(null);
                break;
            default:
                throw new RuntimeException("no such protocol number exist");
        };
        return  apiResponse;
    }
}
