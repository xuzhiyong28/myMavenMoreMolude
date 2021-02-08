package xzy.designPattern.proxy;

public class SubjectImpl implements SubjectInterface{
    @Override
    public String doSomething(String arg1, Integer arg2) {
        System.out.println("========SubjectImpl-doSomething============");
        return null;
    }
}
