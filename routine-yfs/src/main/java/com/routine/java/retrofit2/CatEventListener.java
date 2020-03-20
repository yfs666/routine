package com.routine.java.retrofit2;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.google.common.base.Stopwatch;
import okhttp3.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ok-http 监听器，暂时提供部分的监控，可以通过重写方法继续添加更多的监控，也可以在重写的方法中添加埋点，实现每一步骤的实时监控
 *
 */
public class CatEventListener extends EventListener {

    private Transaction transaction;

    private String url;

    private Stopwatch stopwatch;

    private Stopwatch connectStopwatch;

    private static final String TRANSACTION_TYPE = "studio-url-request";

    private CatEventListener(String url) {
        this.url = url;
    }

    public static CatEventListener of(String url) {
        return new CatEventListener(url);
    }

    public static final Factory LISTENER_FACTORY = call -> CatEventListener.of(call.request().url().url().toString());

    @Override
    public void callStart(Call call) {
        transaction = Cat.newTransaction(TRANSACTION_TYPE, url);
        stopwatch = Stopwatch.createStarted();
    }

    @Override
    public void callEnd(Call call) {
        transaction.setStatus(Transaction.SUCCESS);
        transaction.complete();
    }

    @Override
    public void callFailed(Call call, IOException ioe) {
        Cat.logError(String.format("url {%s}", url), ioe);
        transaction.setStatus(ioe);
        transaction.complete();
    }

    @Override
    public void dnsStart(Call call, String domainName) {
        this.restartStopwatch();
    }

    @Override
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        Cat.logEvent(TRANSACTION_TYPE, url, Transaction.SUCCESS, String.format("dns=%sms", this.stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }


    @Override
    public void secureConnectStart(Call call) {
        this.restartStopwatch();
    }

    @Override
    public void secureConnectEnd(Call call, Handshake handshake) {
        Cat.logEvent(TRANSACTION_TYPE, url, Transaction.SUCCESS, String.format("secureConnect=%sms", this.stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    @Override
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        connectStopwatch = Stopwatch.createStarted();
    }

    @Override
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        Cat.logEvent(TRANSACTION_TYPE, url, Transaction.SUCCESS, String.format("connect=%sms", this.connectStopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    @Override
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException ioe) {
        Cat.logError("connectFailed", ioe);
    }

    @Override
    public void requestHeadersStart(Call call) {
        this.restartStopwatch();
    }

    @Override
    public void requestHeadersEnd(Call call, Request request) {
        Cat.logEvent(TRANSACTION_TYPE, url, Transaction.SUCCESS, String.format("requestHeaders=%sms", this.stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    @Override
    public void requestBodyStart(Call call) {
        this.restartStopwatch();
    }

    @Override
    public void requestBodyEnd(Call call, long byteCount) {
        Cat.logEvent(TRANSACTION_TYPE, url, Transaction.SUCCESS, String.format("requestBody=%sms", this.stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    @Override
    public void requestFailed(Call call, IOException ioe) {
        Cat.logError("requestFailed", ioe);
    }

    @Override
    public void responseHeadersStart(Call call) {
        this.restartStopwatch();
    }

    @Override
    public void responseHeadersEnd(Call call, Response response) {
        Cat.logEvent(TRANSACTION_TYPE, url, Transaction.SUCCESS, String.format("responseHeaders=%sms", this.stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    @Override
    public void responseBodyStart(Call call) {
        this.restartStopwatch();
    }

    @Override
    public void responseBodyEnd(Call call, long byteCount) {
        Cat.logEvent(TRANSACTION_TYPE, url, Transaction.SUCCESS, String.format("responseBody=%sms", this.stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    @Override
    public void responseFailed(Call call, IOException ioe) {
        Cat.logError("responseFailed", ioe);
    }

    private void restartStopwatch() {
        this.stopwatch.reset();
        this.stopwatch.start();
    }

}
