package xzy.groovy

import groovy.json.JsonSlurper

class Test001 {
    public static void main(def args){
        println "Hello World"
    }

    def cal(String s){
        def slurper = new JsonSlurper()
        def states = slurper.parseText(s)
        def code=states['state']
        return code
    }
}
