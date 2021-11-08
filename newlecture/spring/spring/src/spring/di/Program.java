package spring.di;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.di.entity.Exam;
import spring.di.entity.NewlecExam;
import spring.di.ui.ExamConsole;

public class Program {
	
	public static void main(String[] args) {

		// ApplicationContext => IoC
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring/di/setting.xml");
	
		NewlecExam ex = (NewlecExam) context.getBean("exam");
		System.out.println(ex.total());

		
	}
}
