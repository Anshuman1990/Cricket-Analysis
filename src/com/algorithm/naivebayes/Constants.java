/**
 * 
 */
package com.algorithm.naivebayes;

public interface Constants {
String csv = "csv";
String txt = "txt";
String xml = "xml";
String Tab = "\t";
String comma = ",";
String semicolon = ";";
String regex = "[$&+:;=?@#|'<>.^*()%!-\"\\s]";
String TP = "True Positive";
String FP = "False Positive";
String TN = "True Negative";
String FN = "False Negative";
String features = "runScored,wicketTaken,catches";


public <T extends Object> T cleanData(T data)throws Exception;

}
