package xzy.java8.forkjoin;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinMapDoTask extends RecursiveTask<Map<String, Integer>> {

    private static final int TASK_COUNT = 5000; //按照5000个拆分

    private Map<String, Integer> myMap;

    public ForkJoinMapDoTask(Map<String, Integer> myMap) {
        this.myMap = myMap;
    }


    @Override
    protected Map<String, Integer> compute() {

        if (myMap.keySet().size() <= TASK_COUNT) {
            Map<String, Integer> resultMap = Maps.newHashMap();
            for (Map.Entry<String, Integer> entry : myMap.entrySet()) {
                resultMap.put(entry.getKey(), entry.getValue() + 10);
            }
            return resultMap;
        } else {
            List<Map<String,Integer>> mapList = ForkJoinMapDoTask.mapChunk(myMap,2);
            ForkJoinMapDoTask leftTask = new ForkJoinMapDoTask(mapList.get(0));
            leftTask.fork();
            ForkJoinMapDoTask rightTask = new ForkJoinMapDoTask(mapList.get(1));
            rightTask.fork();
            Map<String, Integer> lfetMap = leftTask.join();
            Map<String, Integer> rightMap = rightTask.join();
            Map<String, Integer> resultMap = Maps.newHashMap();
            resultMap.putAll(lfetMap);
            resultMap.putAll(rightMap);
            return resultMap;
        }
    }


    public static <k, v> List<Map<k, v>> mapChunk(Map<k, v> chunkMap, int batch) {
        List<Map<k,v>> resultList = Lists.newArrayList();
        if(MapUtils.isEmpty(chunkMap)){
            return Lists.newArrayList();
        }
        int keyLength = chunkMap.keySet().size();
        int pageSize = keyLength % batch == 0 ? (keyLength / batch) : (keyLength / batch + 1);
        //初始化数据
        for(int i = 0 ; i < batch; i ++){
            resultList.add(Maps.newHashMap());
        }
        int i = 0, count = 0;
        for(Map.Entry<k,v> entry : chunkMap.entrySet()){
            resultList.get(i).put(entry.getKey(),entry.getValue());
            count++;
            if(count % pageSize == 0){
                i++;
            }
        }
        return resultList;
    }

}
