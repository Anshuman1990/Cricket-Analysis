package com.scorepredictor;

import java.util.Random;

public class dummy {
public static void main(String[] args) {
	System.out.println(dummy.get_num());
}

public static int get_num(){
	return 20+(int) (Math.random()*30);
}
}
