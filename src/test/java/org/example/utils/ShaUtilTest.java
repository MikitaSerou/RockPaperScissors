package org.example.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShaUtilTest {

    @Test
    public void KeyByteSizeTest() throws DecoderException {
        String key = ShaUtil.generateAndGetKey();
        byte[] b = Hex.decodeHex(key);
        assertEquals(16, b.length);
    }

    @Test
    public void encodeMoveTest() {
        String encodedMove = ShaUtil.getEncodedMove("BD9B5544739FCE7359C267E734E380A2", "rock");
        assertEquals("faac40c71b4b12bf0ef5556eeb7c06925d5ae405d447e006bb8a06565338d411", encodedMove);

        String encodedMove2 = ShaUtil.getEncodedMove("20fe2978afb3f1a0ca9ea47f91900ca7", "hello");
        assertEquals("1fca2c3ef3331b82e7f861a95018ef3015947628bb521ce314ca63b6e7e51c04", encodedMove2);

        String encodedMove3 = ShaUtil.getEncodedMove("sdfgdgsdfgdffgsdfgsdfgsdg",
                " ");
        assertEquals("367433a99937014b6d5c122eeb11ee6ecc6f60e6a360a56af580e1ba30b69d90", encodedMove3);
    }

    @Test
    public void encodeMoveNotPassTest() {
        String encodedMove = ShaUtil.getEncodedMove("asgsfdgdsdgdfgsdfdgs", "rock");
        assertNotEquals("faac40c71b4b12bf0ef5556eeb7c06925d5ae405d447e006bb8a06565338d411", encodedMove);

        String encodedMove2 = ShaUtil.getEncodedMove("20fe2978afb3f1a0ca9ea47f91900ca7", "hi");
        assertNotEquals("1fca2c3ef3331b82e7f861a95018ef3015947628bb521ce314ca63b6e7e51c04", encodedMove2);

        String encodedMove3 = ShaUtil.getEncodedMove("sdfgdgsdfgdffgsdfgsdfgsdg",
                " ");
        assertNotEquals("367433a99937014b6d5sdfecc6f60e6a360a56af580e1ba30b69d90", encodedMove3);
    }

    @Test
    public void emptyKeyTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ShaUtil.getEncodedMove("", "hello"));
        String expectedMessage = "Empty key";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
