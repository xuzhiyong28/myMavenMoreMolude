package xzy.leetCode.hash;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.*;

/***
 * 哈希一致性算法
 * https://mp.weixin.qq.com/s?__biz=MzI2MTk1NDY0Mw==&mid=2247483768&idx=1&sn=fee0eda48ea283de79d787387ae5d89e&chksm=ea53cc44dd24455248ce8ce49aefa2d707b61041a221322dcba86bdeeb4295f0c61be2c40f70&scene=21#wechat_redirect
 * https://cloud.tencent.com/developer/article/1648654
 */
public class ConsistentHashLoadBalanceTest {
    private TreeMap<Long, String> realNodes = new TreeMap();

    public static void main(String[] args) throws IOException {
        test2();
    }


    public static void test1() throws IOException {
        ExecutorService executors = Executors.newFixedThreadPool(50);
        //String channels = "216.vip.com;216.vipshop.com;3rd-app.vipstatic.com;3rd.vipstatic.com;8.vip.com;a.appsimg.com;a.vimage1.com;a.vimage2.com;a.vimage3.com;a.vimage4.com;a.vpimg1.com;a.vpimg2.com;a.vpimg3.com;a.vpimg4.com;a1.vimage1.com;a2.vimage1.com;a3.vimage1.com;a4.vimage1.com;act.vimage2.com;act.vipshop.com;act.vpimg2.com;agr.vpalstatic.com;ap.appvipshop.com;ap.vip.com;api.union.vipshop.com;app-ssl.vipstatic.com;app.appvipshop.com;app.vip.com;app.vipshop.com;app.vipstatic.com;appsimgtest.8686c.com;b.appsimg.com;b.vpimg1.com;bak.appsimg.com;bak.vipstatic.com;baoxian-act.vipstatic.com;baoxian.vipstatic.com;beauty.vip.com;beauty.vipstatic.com;bfact.vpalstatic.com;bootstrap.vipstatic.com;brand.vip.com;brand.vipshop.com;brandadmin.vip.com;bx.vipstatic.com;c.vpalstatic.com;c.vpimg1.com;c1.vipstatic.com;c2.vipstatic.com;c3.vipstatic.com;c4.vipstatic.com;captcha.vipstatic.com;cart.vip.com;cart.vipshop.com;category.vip.com;category.vipstatic.com;ccp.vipstatic.com;checkout-ssl.vip.com;checkout.vip.com;checkout.vipshop.com;click.union.vipshop.com;client.vip.com;cmc.vip.com;common.vip.com;compass.vis.vip.com;cs_mst.vipstatic.com;ctx-test.vip.com;d.vpimg1.com;day.vip.com;day.vipshop.com;defend-ssl.vipstatic.com;defend.vip.com;detail.vip.com;dypricing.vipstatic.com;edu.vip.com;err.vip.com;fashion.vip.com;fashion.vipshop.com;fcs.vipstatic.com;fe-mp-data.vipstatic.com;files.vip.com;fund.vipstatic.com;g.appsimg.com;g.vpimg1.com;gold-repo.vipstatic.com;grcredit.vipstatic.com;gw.vipapis.com;h.vipstatic.com;h2.appsimg.com;h2.vipstatic.com;h2a.appsimg.com;h2a.vipstatic.com;h2b.appsimg.com;h2b.vipstatic.com;h2bak.appsimg.com;h2bak.vipstatic.com;h5-jz.vipstatic.com;h5-ssl.vip.com;h5.vip.com;h5.vipstatic.com;h5rsc-ssl.vipstatic.com;h5rsc.vipstatic.com;hey-api.vip.com;hey-sns.vip.com;hey.vip.com;hhc-api.vip.com;hhc-sapi.vip.com;home.vip.com;huwai-api.vip.com;i.vip.com;i.vipshop.com;img-space.vip.com;img.pjbest.com;img.vtd.vip.com;img1.vip.com;img1.vipshop.com;img2.vip.com;img2.vipshop.com;img3.vip.com;img3.vipshop.com;img4.vip.com;img4.vipshop.com;imgads.vpalstatic.com;imgvtd.8686c.com;in.appvipshop.com;in.vip.com;jf.vip.com;jf.vipshop.com;jr.vipstatic.com;jrapp.vipstatic.com;jrcoupon.vip.com;jrcoupon.vipstatic.com;jrhd.vipstatic.com;jujia-api.vip.com;kid.vip.com;lc-baoxian.vipstatic.com;lc.vipstatic.com;lcco.vip.com;lefeng-api.vip.com;lefeng-sapi.vip.com;lefeng-static-ssl.vipstatic.com;licai.vip.com;list.vip.com;loan.vipstatic.com;login.vpalstatic.com;lux.vip.com;lux.vipshop.com;lux.vipstatic.com;m-static.weipinfin.com;ma.appvipshop.com;ma.vip.com;main.vpalstatic.com;mapi-cart.appvipshop.com;mapi-i.appvipshop.com;mapi-shop.appvipshop.com;mapi-shop.vip.com;mapi-stock.appvipshop.com;mapi-user.appvipshop.com;mapi.appvipshop.com;mapi.vip.com;mapi.vipshop.com;mapp.vipfashion.com;mar.appvipshop.com;mar.vip.com;mar.vipshop.com;mbx.vipstatic.com;mc.vpalstatic.com;mcp.vip.com;mcp.vipstatic.com;member-ssl.vipstatic.com;member.vipstatic.com;mfund.vipstatic.com;mi.vpalstatic.com;mip-api.vip.com;mip-sapi.vip.com;mjr.vipstatic.com;mjrcoupon.vipstatic.com;mjrhd.vipstatic.com;mlc.vipstatic.com;mlogin.vpalstatic.com;mlq.vipstatic.com;mpaccount.vipstatic.com;mpic.vimage2.com;mpic2.vimage1.com;mpic4.vimage1.com;ms.vipstatic.com;ms.vpimg1.com;ms2-m.vipstatic.com;ms2.vimage1.com;msp.vip.com;mst-cdn.vip.com;mst.appvipshop.com;mst.vip.com;mst.vipstatic.com;mxfd.vipstatic.com;mxjd.vipstatic.com;mzt.appvipshop.com;n.myopen.vip.com;n.vip.com;nov-admin.vip.com;nov-admin.vipstatic.com;npay-pc.vipstatic.com;npay.vipstatic.com;open.vip.com;order.vip.com;ota.vip.com;ota.vipstatic.com;p1.vip.com;p1.vipshop.com;p2.vip.com;p2.vipshop.com;paiquan.vip.com;pay-static.vip.com;pay.vip.com;pay.vipshop.com;pay.vipstatic.com;payplus.vipstatic.com;pdc-img.vipstatic.com;pfs-vis.vip.com;pfs-vis.vipstatic.com;pg.vimage3.com;pg.vpimg3.com;pic1.vip.com;pic1.vipshop.com;pms.vip.com;reco.vipshop.com;reco.vipstatic.com;resys.vip.com;s.vpalstatic.com;s2-app.vipstatic.com;s2-m.vipstatic.com;s2.vimage2.com;s2.vip.com;s2.vipshop.com;s2.vipstatic.com;s2.vpalstatic.com;s3.vpalstatic.com;safe.vip.com;sapi.appvipshop.com;sapi.vip.com;sc-thor.vip.com;sc-vulcan.vip.com;sc.appvipshop.com;sc.vip.com;school.vip.com;search.vip.com;seo.vip.com;seo.vipshop.com;sh.vpalstatic.com;share.vip.com;shop-ssl.vipstatic.com;shop.vip.com;shop.vipfashion.com;shop.vipshop.com;shop.vipstatic.com;size.vip.com;size.vipshop.com;sp.vip.com;sp.vipshop.com;sssh.vip.com;static.pjbest.com;stock.vip.com;stock.vipshop.com;store-ses.vipstatic.com;store-smc.vipstatic.com;store-sof.vipstatic.com;store.vipstatic.com;t.vip.com;tqt.vip.com;trip.vipstatic.com;tuan.vip.com;ugc.vipstatic.com;upic.vimage1.com;user.vipstatic.com;v.vimage4.com;v.vpimg4.com;vad.vipstatic.com;vcf-vc.vipstatic.com;vdo.vipstatic.com;vendor-mapp.vipfashion.com;vf-lc.vipstatic.com;vf-passport.vip.com;vfm-passport.vip.com;vfq.vipstatic.com;vfs.vip.com;vipluxsa.vipstatic.com;vipma.net;vipvideowcs.8686c.com;vipwcs.8686c.com;vis.vip.com;vis.vipstatic.com;visapp.vip.com;viva.vipstatic.com;vop-vis.vipstatic.com;vpal.vipstatic.com;vpos.vipstatic.com;vpsp.vipstatic.com;vtd.8686c.com;vtdimg.vipstatic.com;w2.vip.com;w2.vipshop.com;wapi.vip.com;weixin-api.vip.com;weixin-static.vip.com;weixin.vipstatic.com;wi.vip.com;wi.vipshop.com;wjr.vipstatic.com;ws-a.appsimg.com;ws-b.appsimg.com;ws-h2.appsimg.com;ws.appsimg.com;ws_a.appsimg.com;ws_upic.vimage1.com;www.vip.com;www.vipfashion.com;wyd.vipstatic.com;xapi.vip.com;xsapi.vip.com;xup.vipstatic.com;xupload-ssl.vip.com;xupload.vip.com;y.vip.com;y.vipshop.com;yw-sapi.vip.com;zp.vip.com;zp.vipshop.com;zuida-api.vip.com;zuida-sapi.vip.com";
        //List<String> channelList = Lists.newArrayList(StringUtils.split(channels,";"));
        List<String> channelList = FileUtils.readLines(new File("D:\\channel.txt"));
        List<Integer> hashList = Lists.newLinkedList();
        System.out.println("域名长度 = " + channelList.size());

        List<Future<List<Integer>>> futures = Lists.newArrayList();
        for (List<String> strings : Lists.partition(channelList, 100000)) {
            Future<List<Integer>> future = executors.submit(new Callable<List<Integer>>() {
                @Override
                public List<Integer> call() throws Exception {
                    List<Integer> tempHashList = Lists.newArrayList();
                    for (String channel : strings) {
                        if (!tempHashList.contains(FNV1_32_Hash(channel))) {
                            tempHashList.add(FNV1_32_Hash(channel));
                        }
                    }
                    return tempHashList;
                }
            });
            futures.add(future);
        }
        futures.forEach(listFuture -> {
            try {
                hashList.addAll(listFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("hash长度 = " + hashList.size());
    }

    /***
     * 采用HashMap的hash方法计算100W域名之后的散列程度和耗时
     * @throws IOException
     */
    public static void test2() throws IOException {
        ExecutorService executors = Executors.newFixedThreadPool(50);
        List<String> channelList = FileUtils.readLines(new File("D:\\channel.txt"));
        ConcurrentHashMap<Integer, Integer> conMap = new ConcurrentHashMap<>();
        for (int i = 0; i <= 15; i++) {
            conMap.put(i, 0);
        }
        List<Future> futures = Lists.newArrayList();
        for (List<String> strings : Lists.partition(channelList, 100000)) {
            Future submit = executors.submit(() -> {
                for (String channel : strings) {
                    int index = HASH_MAP(channel);
                    conMap.put(index, conMap.get(index) + 1);
                }
            });
            futures.add(submit);
        }
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println(conMap);
    }


    /***
     * 采用FNV1_32_Hash 哈希计算100W域名之后的散列程度和耗时
     * @throws IOException
     */
    public static void test4() throws IOException {
        ExecutorService executors = Executors.newFixedThreadPool(50);
        List<String> channelList = FileUtils.readLines(new File("D:\\channel.txt"));
        ConcurrentHashMap<Integer, Integer> conMap = new ConcurrentHashMap<>();
        for (int i = 0; i <= 15; i++) {
            conMap.put(i, 0);
        }
        List<Future> futures = Lists.newArrayList();
        for (List<String> strings : Lists.partition(channelList, 100000)) {
            Future submit = executors.submit(() -> {
                for (String channel : strings) {
                    int index = FNV1_32_Hash(channel) % 16;
                    conMap.put(index, conMap.get(index) + 1);
                }
            });
            futures.add(submit);
        }
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println(conMap);
    }

    /***
     * FNV1_32_Hash算法
     * @param key
     * @return
     */
    public static Integer FNV1_32_Hash(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    public static Integer HASH_MAP(String key) {
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        return (16 - 1) & hash;
    }


}
