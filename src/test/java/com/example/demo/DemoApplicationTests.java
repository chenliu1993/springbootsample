package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
// import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.service.PostService;
import com.example.demo.util.SystemUtil;

import com.example.demo.domain.PostEntity;

// import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

	// @Autowired
	// private UserService demoUserService;

	@Autowired
	private PostService demoPostService;

	@Test
	public void TestPostSave() {
		// AnnotationConfigApplicationContext context = new
		// AnnotationConfigApplicationContext(AspectConfig.class);
		// demoPostService = context.getBean(PostService.class);

		// This is the demo postEntity
		PostEntity testPostEntity = new PostEntity();
		testPostEntity.setAuthor("dummy");
		testPostEntity.setContent("why are you looking?");
		testPostEntity.setId(Long.valueOf(1000));
		testPostEntity.setTheme("Japanese is too hard to learn!!");

		demoPostService.save(testPostEntity);

		System.out.println("=======================================");
	}

	@Test
	public void TestTranslatePath() {
		String linuxPath = "/c/test/file";
		System.out.println(SystemUtil.isWindows());
		System.out.println(SystemUtil.translateIntoWindowsPath(linuxPath));
		System.out.println("=======================================");
	}

	public static void main(String[] args) {
		System.out.println("test");
	}

}
