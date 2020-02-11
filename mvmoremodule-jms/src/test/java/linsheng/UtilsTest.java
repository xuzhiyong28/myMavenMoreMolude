package linsheng;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UtilsTest {

    List<Extension> testExtensions;
    List<SaleItem> saleItemList;
    int[] allKeys = {1,2,3,4,5,6,7,8,9};
    int[] usedKeys = {2,3,4};

    @Before
    public void before(){
        testExtensions = Lists.newArrayList(
                new Extension("xu", "zhiyong", "180", "Other"),
                new Extension("gao", "yongshun", "190", "AO"),
                new Extension("ao1", "aoC", "100", "User"),
                new Extension("ao5", "aoB", "100", "TMO"),
                new Extension("ao3", "aoB", "110", "Dept"),
                new Extension("ao4", null, "110", "User"),
                new Extension("ao5", null, null, "AO"),
                new Extension("chen", "yixiang", "170", "Dept")
        );
        saleItemList = Lists.newArrayList(
                new SaleItem(1, new Date(), 1000d),
                new SaleItem(1, new Date(), 2000d),
                new SaleItem(2, new Date(), 3000d),
                new SaleItem(6, new Date(), 4000d),
                new SaleItem(3, new Date(), 5000d),
                new SaleItem(4, new Date(), 6000d),
                new SaleItem(2, new Date(), 7000d),
                new SaleItem(4, new Date(), 8000d),
                new SaleItem(5, new Date(), 9000d),
                new SaleItem(0, new Date(), 10000d),
                new SaleItem(8, new Date(), 12000d),
                new SaleItem(11, new Date(), 11000d)
        );
    }

    @Test
    public void sortByNameTest(){
        List<Extension> resultList = Utils.sortByName(testExtensions);
        List<String> firstNameList = resultList.stream().map(extension -> extension.getFirstName()).collect(Collectors.toList());
        String[] firstNameArray = new String[firstNameList.size()];
        firstNameList.toArray(firstNameArray);
        Assert.assertArrayEquals(firstNameArray,new String[]{"ao1","ao3","ao4","ao5","ao5","chen","gao","xu"});
    }

    @Test
    public void sortByExtTypeTest(){
        List<Extension> resultList = Utils.sortByExtType(testExtensions);
        List<String> firstNameList = resultList.stream().map(extension -> extension.getFirstName()).collect(Collectors.toList());
        String[] firstNameArray = new String[firstNameList.size()];
        firstNameList.toArray(firstNameArray);
        Assert.assertArrayEquals(firstNameArray,new String[]{"ao1","ao4","ao3","chen","gao","ao5","ao5","xu"});
    }

    @Test
    public void sumByQuarterTest(){
        List<QuarterSalesItem> quarterSalesItemList = Utils.sumByQuarter(saleItemList);
        Assert.assertEquals(quarterSalesItemList.get(0).getTotal(),28000.0d,0);
        Assert.assertEquals(quarterSalesItemList.get(1).getTotal(),27000.0d,0);
        Assert.assertEquals(quarterSalesItemList.get(2).getTotal(),12000.0d,0);
        Assert.assertEquals(quarterSalesItemList.get(3).getTotal(),11000.0d,0);
    }

    @Test
    public void maxByQuarter(){
        List<QuarterSalesItem> quarterSalesItemList = Utils.maxByQuarter(saleItemList);
        Assert.assertEquals(quarterSalesItemList.get(0).getTotal(),10000.0d,0);
        Assert.assertEquals(quarterSalesItemList.get(1).getTotal(),9000.0d,0);
        Assert.assertEquals(quarterSalesItemList.get(2).getTotal(),12000.0d,0);
        Assert.assertEquals(quarterSalesItemList.get(3).getTotal(),11000.0d,0);
    }

    @Test
    public void getUnUsedKeys(){
        int[] resultArray = Utils.getUnUsedKeys(allKeys,usedKeys);
        Assert.assertArrayEquals(resultArray,new int[]{1,5,6,7,8,9});
    }
}
