import java.util.*;
import java.text.*;
import java.io.*;

public class LoginState extends WarehouseState{

	private static final int MANAGER_LOGIN = 0;
	private static final int CLERK_LOGIN = 1;
	private static final int USER_LOGIN = 2;
	private static final int EXIT = 3;


	private static LoginState instance;

	private LoginState(){
		super();
	}

	public static LoginState instance(){
		if(instance == null){
			instance = new LoginState();
		}
		return instance;
	}

	public void run(){
		System.out.println("In login state.\n");
	}
}