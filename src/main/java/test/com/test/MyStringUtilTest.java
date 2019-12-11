package test.com.test; 

import com.test.MyStringUtil;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/** 
* MyStringUtil Tester. 
* 
* @author dk.xie
* @since <pre>一月 7, 2019</pre> 
* @version 1.0 
*/
@RunWith(PowerMockRunner.class) // 1
@PrepareForTest({ MyStringUtil.class }) // 2
public class MyStringUtilTest {

    @Before
    public void before() {
        PowerMockito.mockStatic(MyStringUtil.class); // 3
    }

    @Test
    public void test() throws IOException {
        PowerMockito.when(MyStringUtil.uppercase("abc")).thenReturn("ABC"); // 4
        assertEquals("ABC", MyStringUtil.uppercase("abc")); // 5
    }


    
        
    } 
