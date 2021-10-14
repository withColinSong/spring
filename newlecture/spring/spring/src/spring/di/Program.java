package spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.di.ui.ExamConsole;

public class Program {
	public static void main(String[] args) {
		
		/* 스프링에게 지시하는 방법으로 코드를 변경
		  Exam exam = new NewlecExam(); 
		  ExamConsole console = new GridExamConsole(exam);
		  
		  console.setExam(exam);
		  ExamConsole console = ?;
		 */		
		
		// ApplicationContext => IoC
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring/di/setting.xml");
		
		// 1. 이름명
		ExamConsole console = (ExamConsole) context.getBean("console");
		// 2. 자료형명(타입명)
//		ExamConsole console = context.getBean(ExamConsole.class);
		console.print();
		
	}
}
