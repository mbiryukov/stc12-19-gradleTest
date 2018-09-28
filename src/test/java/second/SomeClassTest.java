package second;

import Second.Helper;
import Second.SomeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SomeClassTest {
    private SomeClass someClass;
    private Helper helper = Mockito.mock(Helper.class);

    @BeforeEach
    void before() {
        someClass = new SomeClass(helper);
    }

    @Test
    void methodWithHelperTest() {
        when(helper.someHelperHethod(anyInt(), anyInt())).thenReturn(1);
        Integer res = someClass.methodWithHelper(1, 2);
        verify(helper).someHelperHethod(11, 7);
        assertEquals(new Integer(1), res);
    }
}
