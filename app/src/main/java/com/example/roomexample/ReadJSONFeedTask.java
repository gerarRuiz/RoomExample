package com.example.roomexample;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.SocketTimeoutException;

/**
 * Created by bat_6 on 02/05/2016.
 */
public abstract class ReadJSONFeedTask extends AsyncTask<Object, Void, String> implements ToksWebServicesConnection{

    private SoapSerializationEnvelope envelope;
    private SoapPrimitive resultsRequestSOAP;
    private String carrierString;
    protected String method;
    private final int TIMEOUT = 30000;

    public abstract void onResponseReceived(String jsonResult);


    protected String doInBackground(Object... params) {
        try {
            SoapObject request = (SoapObject) params[0];
            method = (String) params[1];
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            String URL = "http://" + "172.20.239.15" + "/ws_pagomovil/ws_pagomovil.asmx";
            HttpTransportSE transportSE = (method.equals(ENVIA_FACTURA)) ? new HttpTransportSE(URL, TIMEOUT) : new HttpTransportSE(URL, TIMEOUT);
            transportSE.debug = true;

            transportSE.call(NAMESPACE + method, envelope);
            resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
            carrierString = resultsRequestSOAP.toString();

        } catch (SocketTimeoutException socketTimeoutException) {

            carrierString = "timeout";

        } catch (Exception e) {
            carrierString = "  : " + e.getMessage();
            e.printStackTrace();
        }

        return carrierString;
    }

    protected void onPostExecute(String response) {
        String result;

        /*try {
            final byte[] bencr = SecurityUtils.parseByteArray(response);
            final byte[] bllave = SecurityUtils.parseByteArray(MainActivity.llave);
            final byte[] biv = SecurityUtils.parseByteArray(MainActivity.iv);
            result = SecurityUtils.decrypt(bllave, biv, bencr);
            appLog(method + ": " + result);
        } catch (NumberFormatException e) {
            result = response;
        } catch (NullPointerException e) {
            appLog("Sin resultado");
            return;
        }*/

        onResponseReceived(response);
    }
}
