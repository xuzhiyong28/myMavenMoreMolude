package com.dxc.util;

public interface CallbackTask<R> {
    R execute() throws Exception;
    void OnBack(R r);
    void onException(Throwable t);
}
