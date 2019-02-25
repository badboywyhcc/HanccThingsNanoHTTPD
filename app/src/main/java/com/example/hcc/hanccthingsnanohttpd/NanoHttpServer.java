package com.example.hcc.hanccthingsnanohttpd;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.IOException;
import java.util.HashMap;

public class NanoHttpServer extends NanoHTTPD {
    public NanoHttpServer(int port) {
        super(port);
    }

    public NanoHttpServer(String hostname, int port) {
        super(hostname, port);
    }

    /**
     * Override this to customize the server.
     * <p/>
     * <p/>
     * (By default, this returns a 404 "Not Found" plain text error response.)
     *
     * @param session The HTTP session
     * @return HTTP response, see class Response for details
     */
    @Override
    public Response serve(IHTTPSession session) {
        Response response;
        if (session.getMethod().name().equalsIgnoreCase("get")){
            System.out.println(session.getUri());

            try {
                session.parseBody(new HashMap<String, String>());//获取请求参数之前需要调一下这个方法
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ResponseException e) {
                e.printStackTrace();
            }
            response = new Response(Response.Status.OK, "text/plain; charset=UTF-8", "{\"code\":0,\"data\":\"将要发送9200广播\" }");
        }else{
            response = new Response(Response.Status.OK, "text/plain; charset=UTF-8", "{\"code\":1,\"data\":\"not found\" }");
        }

        //response = new Response(Response.Status.OK, "text/plain; charset=UTF-8", "{\"code\":1,\"data\":\"not found\" }");
        return response;
    }


    //获取IP地址
    public static String getLocalIpStr(Context context){
        WifiManager MyWiFiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo=(WifiInfo)MyWiFiManager.getConnectionInfo();
        return  intToIpAddr(wifiInfo.getIpAddress());
    }

    private static String intToIpAddr(int ip){
        return (ip & 0xFF)+"."
                + ((ip>>8)&0xFF) + "."
                + ((ip>>16)&0xFF) + "."
                + ((ip>>24)&0xFF);
    }
}
