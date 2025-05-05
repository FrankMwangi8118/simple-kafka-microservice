package com.codify.Engine;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
@Component
public class PingEngine {
    public boolean isReachable(String host) throws IOException {

        byte[] add=ipToByte(splitIp(host));
        InetAddress address=InetAddress.getByAddress(add);
        return address.isReachable(3000);
    }

    private byte[] ipToByte(String[] splitIp) {
        byte[] ipBytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            int segment = Integer.parseInt(splitIp[i]);
            ipBytes[i] = (byte) segment;
        }
        return ipBytes;
    }

    private String[] splitIp(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            throw new IllegalArgumentException("illegal ip" + ip);
        }
        return parts;
    }
}
