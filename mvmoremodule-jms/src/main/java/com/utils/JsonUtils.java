package com.utils;/**
 * Created by Administrator on 2018-10-14.
 */


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhiyong
 * @createDate 2018-10-14-18:37
 */
public class JsonUtils {
    /**
     * 将json转成object
     *
     * @param jsonString
     * @param tr
     * @param <T>
     * @return
     */
    public static <T> T readJsonToObject(String jsonString, TypeReference<T> tr) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        } else {
            try {
                return (T) objectMapper.readValue(jsonString, tr);
            } catch (Exception e) {

            }
        }
        return null;
    }

    public static boolean compareMap(Map<String, Object> leftMap, Map<String, Object> rightMap) {

        MapDifference<String, Object> difference = Maps.difference(leftMap, rightMap);
        //获取所有不同点
        Map<String, MapDifference.ValueDifference<Object>> differenceMap = difference.entriesDiffering();
        for (Iterator diffIterator = differenceMap.entrySet().iterator(); diffIterator.hasNext();) {
            Map.Entry entry = (java.util.Map.Entry) diffIterator.next();

            MapDifference.ValueDifference<Object> valueDifference = (MapDifference.ValueDifference<Object>) entry.getValue();
            System.out.println("left: " + valueDifference.leftValue());
            System.out.println("right: " + valueDifference.rightValue());
            //处理结果是否为map,则递归执行比较规则
            if (valueDifference.leftValue() instanceof Map && valueDifference.rightValue() instanceof Map) {
                boolean equal = compareMap((Map<String, Object>) valueDifference.leftValue(), (Map<String, Object>) valueDifference.rightValue());
                if (!equal) {
                    return false;
                }
            }
            //如果处理结果为list，则通过list方式处理  - 若list中值相同，但是顺序不同，则认为两个list相同
            if (valueDifference.leftValue() instanceof List && valueDifference.rightValue() instanceof List) {
                boolean equal = ((List) valueDifference.leftValue()).containsAll((List) valueDifference.rightValue());
                if (!equal) {
                    return false;
                }
            }
            //如果处理最终结果为字符串,则停止比较
            if ((valueDifference.leftValue() instanceof String) && (valueDifference.rightValue() instanceof String)) {
                return false;
            }
        }
        //若B中有A中不存在的值，则认为不同
        Map<String, Object> onlyOnRightMap = difference.entriesOnlyOnRight();
        if (onlyOnRightMap != null && !onlyOnRightMap.isEmpty()) {
            return false;
        }
        return true;
    }

}
