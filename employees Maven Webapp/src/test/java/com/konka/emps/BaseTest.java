package com.konka.emps;


import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:beans.xml","classpath:shiro.xml"})
public class BaseTest {
@Before
public void init(){
	
}
@After
public void after(){

}
}
