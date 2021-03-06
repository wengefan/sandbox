package security;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import org.junit.Test;

public class SHA256Test {

    @Test
    public void test_hash_one_block_message() throws Exception {
        byte[] src = "abc".getBytes();
        byte[] actual = SHA256.hash(src);
        byte[] expected =
            bytes(
                0xba7816bf,
                0x8f01cfea,
                0x414140de,
                0x5dae2223,
                0xb00361a3,
                0x96177a9c,
                0xb410ff61,
                0xf20015ad);
        assertThat(actual, is(expected));
    }

    @Test
    public void test_hash_multi_block_message() throws Exception {
        byte[] src = "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq".getBytes();
        byte[] actual = SHA256.hash(src);
        byte[] expected =
            bytes(
                0x248d6a61,
                0xd20638b8,
                0xe5c02693,
                0x0c3e6039,
                0xa33ce459,
                0x64ff2167,
                0xf6ecedd4,
                0x19db06c1);
        assertThat(actual, is(expected));
    }

    @Test
    public void test_hash_long_message() throws Exception {
        byte[] src = new byte[1_000_000];
        Arrays.fill(src, (byte) 'a');
        byte[] actual = SHA256.hash(src);
        byte[] expected =
            bytes(
                0xcdc76e5c,
                0x9914fb92,
                0x81a1c7e2,
                0x84d73e67,
                0xf1809a48,
                0xa497200e,
                0x046d39cc,
                0xc7112cd0);
        assertThat(actual, is(expected));
    }

    private static byte[] bytes(int... is) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i : is) {
            for (int j = 0; j < 4; j++) {
                out.write((i >>> (24 - j * 8)) & 0xff);
            }
        }
        return out.toByteArray();
    }
}
