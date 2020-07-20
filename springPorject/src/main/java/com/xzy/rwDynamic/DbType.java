package com.xzy.rwDynamic;

public enum DbType {
    MASTER("dataSourceMaster"),
    SLAVE("dataSourceSlave");
    private String type;
    DbType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
