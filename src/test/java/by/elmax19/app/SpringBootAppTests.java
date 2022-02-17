package by.elmax19.app;

import by.elmax19.app.context.AppContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SpringBootAppTests {
	AppContext appContext = new AppContext();

	@Test
	void contextLoads() {
		System.out.println("Context loading test");
	}

	@Test
	void classPathBeanTest() {
		Assert.isTrue(appContext.getClassPathContext().containsBean("classPathBean"), "ClassPathXmlApplicationContext should contains classPathBean");
		System.out.println("ClassPathXmlApplicationContext contains classPathBean");
	}

	@Test
	void fileSystemBeanTest() {
		Assert.isTrue(appContext.getFileSystemContext().containsBean("fileSystemBean"), "FileSystemXmlApplicationContext should contains fileSystemBean");
		System.out.println("FileSystemXmlApplicationContext contains fileSystemBean");
	}

	@Test
	void freeBeanTest() {
		//todo Test, that checks which Context contains freeBean
	}
}
