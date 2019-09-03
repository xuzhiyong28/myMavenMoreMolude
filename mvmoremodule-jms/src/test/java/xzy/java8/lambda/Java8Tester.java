package xzy.java8.lambda;

public class Java8Tester {

    public static void main(String[] args) {
        //类型声明
        MathOperation mathOperation = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };
        MathOperation mathOperationLambda1 = ((a, b) -> a + b);
        MathOperation mathOperationLambda2 = ((a, b) -> {
            return a - b > 0 ? a : b;
        });
        MathOperation mathOperationLambda3 = (int a, int b) -> a / b;


        GreetingService greetingService = new GreetingService() {
            @Override
            public void sayMessage(String message) {
                System.out.println(message);
            }
        };

        GreetingService greetingServiceLambda1 = (message -> System.out.println(message));

        Java8Tester java8Tester = new Java8Tester();
        //选中代码 按住Alt+回车可以换成lambda表达式
        java8Tester.operate(1, 2, new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        });
        java8Tester.operate(1,2,((a, b) -> a + b));

        Converter<Integer,String> converter = new Converter<Integer, String>() {
            @Override
            public void convert(int i) {
                System.out.println(i);
            }
        };
        Converter<Integer,String> converter2 = i -> System.out.println(i);


    }


    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    interface Converter<T1,T2>{
        void convert(int i);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

}
