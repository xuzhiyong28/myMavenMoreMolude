package com.mvmoremodulePattern.builder.pattern;/**
 * Created by Administrator on 2018-04-28.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-9:08
 * 建造者
 */
public class Builder {
    protected boolean addShallot;
    protected boolean addParsley;
    protected boolean addChili;
    protected boolean addSauerkraut;

    public Builder(){};

    public Builder withShallot(){
        this.addShallot = true;
        return this;
    }

    public Builder withParsley() {
        this.addParsley = true;
        return this;
    }

    public Builder withChili() {
        this.addChili = true;
        return this;
    }

    public Builder withSauerkraut() {
        this.addSauerkraut = true;
        return this;
    }

    public HotDryNoodlesWithBuilder build(){
        return new HotDryNoodlesWithBuilder(this);
    }

}
