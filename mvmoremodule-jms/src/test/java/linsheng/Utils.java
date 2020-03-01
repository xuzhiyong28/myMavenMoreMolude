package linsheng;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    private static final ImmutableMap<String, Integer> extTypeSortMap = ImmutableMap.of("User", 5, "Dept", 4, "AO", 3, "TMO", 2, "Other", 1);

    /***
     *
     * * Question1, sort by firstName + lastName + ext,
     * if firstName is the same then sort by lastName and
     * ext, please note lastName and ext can be empty
     * string or null
     * @param extensions
     * @return
     */
    public static List<Extension> sortByName(List<Extension> extensions) {
        if (extensions == null) {
            throw new RuntimeException("extensions is empty");
        } else {
            return extensions
                    .stream()
                    .sorted(
                            Comparator.comparing(Extension::getFirstName, Comparator.naturalOrder())
                                    .thenComparing(Comparator.comparing(Extension::getLastName, Comparator.nullsFirst(String::compareTo)))
                                    .thenComparing(Comparator.comparing(Extension::getExt, Comparator.nullsFirst(String::compareTo)))
                    )
                    .collect(Collectors.toList());
        }
    }


    //Question2
    public static List<Extension> sortByExtType(List<Extension> extensions) {
        if (extensions == null) {
            throw new RuntimeException("extensions is empty");
        } else {
            return extensions
                    .stream()
                    .sorted(Comparator.comparing(Extension::getExtType, (o1, o2) -> {
                        int result = extTypeSortMap.get(o1) - extTypeSortMap.get(o2);
                        return result == 0 ? 0 : (result > 0 ? 1 : -1);
                    }).reversed())
                    .collect(Collectors.toList());
        }
    }

    //Question3
    //sum all sales items by quarter
    //按季度合计所有销售项目
    public static List<QuarterSalesItem> sumByQuarter(List<SaleItem> saleItems) {
        //按照季度规约
        if (saleItems == null) {
            throw new RuntimeException("extensions is empty");
        }
        //初始化4个季度
        Map<Integer, QuarterSalesItem> quarterSalesItemMap = Lists.newArrayList(1, 2, 3, 4)
                .stream()
                .collect(Collectors.toMap(quarter -> quarter, quarter -> new QuarterSalesItem(quarter, 0)));
        saleItems.stream().forEach(saleItem -> {
            QuarterSalesItem quarterSalesItem = quarterSalesItemMap.get(isInQuarter(saleItem));
            quarterSalesItem.setTotal(quarterSalesItem.getTotal() + saleItem.getSaleNumbers());
        });

        return new ArrayList<>(quarterSalesItemMap.values());
    }


    /***
     * Judge which quarter it belongs to
     * @param saleItem
     * @return
     */
    public static int isInQuarter(SaleItem saleItem) {
        int month = saleItem.getMonth();
        Date date = saleItem.getDate();
        if (month == 0) {
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            month = calender.get(Calendar.MONTH) + 1;
        }
        if (1 <= month && month <= 3) {
            return 1;
        }
        if (4 <= month && month <= 6) {
            return 2;
        }
        if (7 <= month && month <= 9) {
            return 3;
        }
        if (10 <= month && month <= 12) {
            return 4;
        }
        return 0;
    }


    //Question4  max all sales items by quarter
    public static List<QuarterSalesItem> maxByQuarter(List<SaleItem> saleItems) {
        Map<Integer, QuarterSalesItem> quarterSalesItemMap = Lists.newArrayList(1, 2, 3, 4)
                .stream()
                .collect(Collectors.toMap(quarter -> quarter, quarter -> new QuarterSalesItem(quarter, 0)));
        //根据季度拆分对象
        Map<Integer, List<SaleItem>> saleItemByQuarterMap = saleItems.stream()
                .collect(Collectors.groupingBy(saleItem -> isInQuarter(saleItem)));

        for (Map.Entry<Integer, List<SaleItem>> entry : saleItemByQuarterMap.entrySet()) {
            //规约合并所有每个季度的值
            double total = entry.getValue().stream().map(saleItem -> saleItem.getSaleNumbers()).reduce(0d, Double::max);
            quarterSalesItemMap.get(entry.getKey()).setTotal(total);
        }
        return new ArrayList<>(quarterSalesItemMap.values());
    }

    //Question5

    /**
     * We have all Keys: 0-9;
     * usedKeys is an array to store all used keys like :[2,3,4];
     * We want to get all unused keys, in this example it would be: [0,1,5,6,7,8,9,]
     */

    public static int[] getUnUsedKeys(int[] allKeys, int[] usedKeys) {

        return Arrays.stream(allKeys)
                .filter(allKey -> !Arrays.stream(usedKeys).anyMatch(userKey -> allKey == userKey))
                .toArray();
    }

}
