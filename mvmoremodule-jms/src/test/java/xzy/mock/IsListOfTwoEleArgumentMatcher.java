package xzy.mock;

import org.mockito.ArgumentMatcher;

import java.util.List;

public class IsListOfTwoEleArgumentMatcher implements ArgumentMatcher<List> {
    @Override
    public boolean matches(List list) {
        return list.size() == 2;
    }
}
