package com.hieplp.url.statistic.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hieplp.url.common.constants.auth.PasswordKey;
import com.hieplp.url.common.constants.auth.TokenKey;
import com.hieplp.url.common.util.FileUtil;
import com.hieplp.url.common.util.RsaUtil;
import com.hieplp.url.statistic.config.ConfigInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

@Slf4j
@RequiredArgsConstructor
public class RsaModule extends AbstractModule {

    private final ConfigInfo configInfo;

    @Provides
    @Singleton
    @Named(TokenKey.PRIVATE)
    public PrivateKey getTokenPrivateKey() throws Exception {
        log.info("Start get token private key");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(FileUtil.getBytes(configInfo.getTokenConfig().getPrivateKey()));
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    @Provides
    @Singleton
    @Named(TokenKey.PUBLIC)
    public PublicKey getTokenPublicKey() throws Exception {
        log.info("Start get token public key");
        return RsaUtil.getPublicKey(getTokenPrivateKey());
    }

    @Provides
    @Singleton
    @Named(PasswordKey.PRIVATE)
    public PrivateKey getPasswordPrivateKey() throws Exception {
        // FIXME: This is a bug. The password private key is not used in this service.
        //        Return null to avoid injector cannot find the key.
        return null;
    }
}
