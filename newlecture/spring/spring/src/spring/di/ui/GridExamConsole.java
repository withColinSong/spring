package spring.di.ui;

import spring.di.entity.Exam;

public class GridExamConsole implements ExamConsole {

	private Exam exam;
	
	public GridExamConsole(Exam exam) {
		this.exam = exam;
	}

	@Override
	public void print() {
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛"+exam.total()+"弛"+exam.avg()+" 弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		

	}

}
