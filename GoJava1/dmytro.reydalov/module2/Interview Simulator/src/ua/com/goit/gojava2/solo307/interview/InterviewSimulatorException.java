package ua.com.goit.gojava2.solo307.interview;

public class InterviewSimulatorException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message;

	public InterviewSimulatorException() {
		super();
	}

	public InterviewSimulatorException(String arg0) {
		super(arg0);
	}

	public String getText() {
		return message;
	}
}
