package com.algorithm.naivebayes;

public class BinaryClassification {

	public double TPRate(double TP,double FN){
		return TP/(FN+TP);
	}
	
	public double FPRate(double FP,double TN){
		return FP/(TN+FP);
	}
	
	public void classify(){
		
	}
}
