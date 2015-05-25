package com.jamesfen.lucene;

import java.io.File;  
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;  
import org.apache.lucene.analysis.standard.StandardAnalyzer;  
import org.apache.lucene.document.Document;  
import org.apache.lucene.document.Field.Store;  
import org.apache.lucene.document.StringField;  
import org.apache.lucene.document.TextField;  
import org.apache.lucene.index.IndexWriter;  
import org.apache.lucene.index.IndexWriterConfig;  
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;  
import org.apache.lucene.store.FSDirectory;  
import org.apache.lucene.util.Version;  


public class IndexFile {
	protected static String[] ids={"1", "2"};  
	  
    protected static String[] content={"Amsterdam has lost of add  cancals", "i love  add this girl"};  
  
    protected static String[] city={"Amsterdam", "Venice"};  
  
    private static Directory dir;  
  
    /** 
     * 初始添加文档 
     * @throws Exception 
     */  
    public static void main(String[] args) throws Exception {
    	String pathFile="C://lucene/index";  
        dir=FSDirectory.open(new File(pathFile));  
        IndexWriter writer=getWriter();  
        for(int i=0; i < ids.length; i++) {  
            Document doc=new Document();  
            doc.add(new StringField("id", ids[i], Store.YES));  
            doc.add(new TextField("content", content[i], Store.YES));  
            doc.add(new StringField("city", city[i], Store.YES));  
            writer.addDocument(doc);  
        }  
        System.out.println("init ok?");  
        writer.close(); 
    }
    
	/** 
     * 获得IndexWriter对象 
     * @return 
     * @throws Exception 
     */  
    public static IndexWriter getWriter() throws Exception {  
        Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_40);  
        IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_40, analyzer);  
        return new IndexWriter(dir, iwc);  
    } 
}
