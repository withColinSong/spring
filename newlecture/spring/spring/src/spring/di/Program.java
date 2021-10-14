package spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.di.ui.ExamConsole;

public class Program {
	public static void main(String[] args) {
		
		/* ���������� �����ϴ� ������� �ڵ带 ����
		  Exam exam = new NewlecExam(); 
		  ExamConsole console = new GridExamConsole(exam);
		  
		  console.setExam(exam);
		  ExamConsole console = ?;
		 */		
		
		// ApplicationContext => IoC
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring/di/setting.xml");
		
		// 1. �̸���
		ExamConsole console = (ExamConsole) context.getBean("console");
		// 2. �ڷ�����(Ÿ�Ը�)
//		ExamConsole console = context.getBean(ExamConsole.class);
		console.print();
		
	}
}
