package com.onelap.gt06onelap;

import com.onelap.gt06onelap.decoder.Gt06Decoder;
import com.onelap.gt06onelap.enums.DecoderType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;

@SpringBootTest
class Gt06decoderApplicationTests {

	@Test
	void contextLoads() {


		byte[] hexValues = {
				0x78, 0x78,
				0x11,
				0x01,

				0x01, 0x23 ,0x45 ,0x67,
				(byte)0x89 ,0x01 ,0x23 ,0x45,              // terminal id
				0x01, 0x18,								  // identification code
				0x32, 0x00,                               // zone language          + 8:30

				0x00, 0x01,
				(byte)0x8C, (byte) 0xDD,
				0x0D, 0x0A
		};
		ByteBuf byteBuf = Unpooled.copiedBuffer(hexValues);
		Gt06Decoder decoder = Gt06Decoder.getDecoder(DecoderType.HEX);
		decoder.decode(null,null,byteBuf);


	}

}
