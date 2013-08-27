package com.white.pesit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ServerCode {

    static String toReturn;
    static String serverLink = "http://pesseacm.org/nfcid/";

    public static String serverInteract(final String file, final String send)
            throws IOException, InterruptedException {
        toReturn = null;
        Thread thread = new Thread() {
            public void run() {
                try {
                    toReturn = serverInteract2(file, send);
                } catch (IOException e) {

                    e.printStackTrace();
                    System.out.println("Server Code.java : Exception");
                }
            }
        };
        thread.start();
        thread.join();
        return toReturn;

    }
    
    public static String newLink(final String send)
            throws IOException, InterruptedException {
        toReturn = null;
        System.out.println("In new link");
        Thread thread = new Thread() {
            public void run() {
                try {
                    toReturn = serverInteract_newlink(send);
                } catch (IOException e) {

                    e.printStackTrace();
                    System.out.println("Server Code.java : Exception");
                }
            }
        };
        thread.start();
        thread.join();
        return toReturn;

    }
    public static String serverInteract_newlink(String send)
            throws IOException {

        
        String res = "";
        
        System.out.println("In server interact new link");
        URL call_server = new URL(send);
        System.out.println("SEND IS "+send);
        URLConnection yc;
        BufferedReader in;
        while (res.equalsIgnoreCase("")) {
            yc = call_server.openConnection();
            in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            //inputLine = in.readLine();
            while ((inputLine = in.readLine()) != null) {
                res = res+inputLine;
                System.out.println(res);

            }
            in.close();
        }
    

        return res;
    }
    public static String serverInteract2(String file,String send)
            throws IOException {

        send = serverLink + file;
        String res = "";
         send=send.replaceAll(" ", "~");
        System.out.println(send);
        URL call_server = new URL(send);
        URLConnection yc;
        BufferedReader in;
        while (res.equalsIgnoreCase("")) {
            yc = call_server.openConnection();
            in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            //inputLine = in.rea
            while ((inputLine = in.readLine()) != null) {
                res = res+"$"+inputLine;
                System.out.println(res);

            }
            in.close();
        }
         System.out.println(res +"result final");

        return res;
    }

    public final static boolean isInternetOn(Context context) {
        ConnectivityManager connec = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
                || connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING
                || connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING
                || connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
                || connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {

            Toast.makeText(context, "No Internet Connection Found...",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
}