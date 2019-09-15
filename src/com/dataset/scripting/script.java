package com.dataset.scripting;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import com.datascan.xml.ExtractXML;
import com.datascan.xml.Info;
import com.impl.Constants;

public class script {
private String pathD = null;
private String outputPathD = null;

	public script(String path,String outputPath) throws SQLException, SAXException, IOException, ParserConfigurationException {
		this.pathD = path;
		this.outputPathD = outputPath;
		init();
	}
	
	private void init() throws SQLException, SAXException, IOException, ParserConfigurationException{
		File f = new File(pathD);
		File[] f_files = f.listFiles();
		for(File F:f_files){
			if(F.isFile()){
				File output = new File(outputPathD+F.getName());
			String absPath = F.getAbsolutePath();
			String fileName = F.getName();
			System.out.println("FileName="+fileName);
			ExtractXML extract = new ExtractXML();
			extract.extact(absPath);
			Info infoObj = extract.getInfoObj();
			String match_type = infoObj.getMatch_type();
			if(match_type.equalsIgnoreCase("ODI")){
				FileUtils.copyFile(F, output);
			}
			}
		}
	}
	
	public static void main(String[] args) throws SQLException, SAXException, IOException, ParserConfigurationException {
		String path = System.getProperty(Constants.system_dir);
		path = path+"//"+Constants.datasetName+"//cricsheet-xml-master//data";
		String outputPath = System.getProperty(Constants.system_dir)+"//"+Constants.datasetName+"//cricsheet-xml-master//data//ODI//";
//		script sc = new script(path,outputPath);
		
		File f = new File(outputPath);
		File[] files = f.listFiles();
		for(File F:files){
			String absPath = F.getAbsolutePath();
			ExtractXML extract = new ExtractXML();
			extract.extact(absPath);
			Info infoObj = extract.getInfoObj();
			String potm = infoObj.getPlayerOfMatch();
			if(potm.equalsIgnoreCase("SPD Smith") && potm!=null){
				System.out.println(F.getName());
			}
		}
	}
}
