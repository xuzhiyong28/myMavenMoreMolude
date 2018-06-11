package com.mvmoremodulePattern.builder.nopattern;/**
 * Created by Administrator on 2018-04-28.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-9:05
 * 热干面的配料
 * 这种方式下 加入配料是要按照一定顺序的，容易出错
 */
public class HotDryNoodles {
    private boolean addShallot;
    private boolean addParsley;
    private boolean addChili;
    private boolean addSauerkraut;

    public HotDryNoodles(boolean shallot, boolean parsley, boolean chili, boolean sauerkraut) {
        this.addShallot = shallot;
        this.addParsley = parsley;
        this.addChili = chili;
        this.addSauerkraut = sauerkraut;
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

    public static void main(String agrs[]){
        HotDryNoodles hotDryNoodlesA = new HotDryNoodles(true, true, false, true);
        System.out.println("Customer A wants: " + hotDryNoodlesA);
    }

}
