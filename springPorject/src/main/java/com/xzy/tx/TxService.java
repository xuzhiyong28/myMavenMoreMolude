package com.xzy.tx;

import java.util.List;
import java.util.Map;

public interface TxService {
    public List<Map<String, Object>> queryUser();

    public void updateData();

    public void singleUpdate();

    public void updateByThread();
}
