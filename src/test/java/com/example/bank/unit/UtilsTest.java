package com.bankservice.bank.unit;

import com.bankservice.bank.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

@Slf4j
public class UtilsTest {

    @Test
    public void testEncryption()  {
        String password = "1234";

        String result = Utils.encryptMd5(password);
        log.info(result);
        Assert.isTrue("81DC9BDB52D04DC20036DBD8313ED055".equals(result), "Password MD5 not same");

    }

}
