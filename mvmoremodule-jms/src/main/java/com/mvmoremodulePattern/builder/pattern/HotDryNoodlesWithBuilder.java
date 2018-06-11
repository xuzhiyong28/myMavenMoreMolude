package com.mvmoremodulePattern.builder.pattern;/**
 * Created by Administrator on 2018-04-28.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-9:07
 * 热干面
 */
public class HotDryNoodlesWithBuilder {
    private boolean addShallot;
    private boolean addParsley;
    private boolean addChili;
    private boolean addSauerkraut;

    public HotDryNoodlesWithBuilder(Builder builder) {
        this.addShallot = builder.addShallot;
        this.addParsley = builder.addParsley;
        this.addChili = builder.addChili;
        this.addSauerkraut = builder.addSauerkraut;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("A bowl of hot-dry noodles has:");

        if (this.addShallot) {
            builder.append("葱花.");
        }

        if (this.addParsley) {
            builder.append("香菜.");
        }

        if (this.addChili) {
            builder.append("辣椒.");
        }

        if (this.addSauerkraut) {
            builder.append("酸菜.");
        }

        return builder.toString();
    }




}
