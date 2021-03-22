package xzy.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Test {
    @org.junit.Test
    public void test0() {
        try {
            String script = "";
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("groovy");
            //Bindings bindings = engine.createBindings();
            //bindings.put("message", "1");
            //bindings.put("miao", "2");
            //String k = engine.eval(script, bindings).toString();
            engine.eval(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test1() {
        GroovyClassLoader classLoader = new GroovyClassLoader();
        String script = "def cal(int a, int b){\n" +
                "    return a+b\n" +
                "}";
        Class groovyClass = classLoader.parseClass(script);
        try {
            Object[] param = {8, 7};
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            int result = (int) groovyObject.invokeMethod("cal", param);
            System.out.println(result);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void test3() {
        GroovyClassLoader classLoader = new GroovyClassLoader();
        String script = "    def cal(String s){\n" +
                "        def slurper = new JsonSlurper()\n" +
                "        def states = slurper.parseText(s)\n" +
                "        def code=states['state']\n" +
                "        return code\n" +
                "    }";
        Class groovyClass = classLoader.parseClass(script);
        try {
            Object[] param = {"{\"state\":{\"name\":\"fulei.yang\",\"age\":\"18\"}}"};
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            String result = (String) groovyObject.invokeMethod("cal", param);
            System.out.println(result);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
