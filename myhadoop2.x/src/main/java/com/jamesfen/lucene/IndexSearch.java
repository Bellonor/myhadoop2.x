package com.jamesfen.lucene;
import java.io.File;  
import java.io.IOException;

import org.apache.lucene.document.Document;  
import org.apache.lucene.index.DirectoryReader;  
import org.apache.lucene.index.IndexReader;  
import org.apache.lucene.index.Term;  
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;  
import org.apache.lucene.search.ScoreDoc;  
import org.apache.lucene.search.TermQuery;  
import org.apache.lucene.search.TopDocs;  
import org.apache.lucene.store.Directory;  
import org.apache.lucene.store.FSDirectory;
public class IndexSearch {
	public static void main(String[] args) throws IOException, ParseException {
		 String filePath="C://lucene/index";  
	        Directory dir=FSDirectory.open(new File(filePath));  
	        IndexReader reader=DirectoryReader.open(dir);  
	        IndexSearcher searcher=new IndexSearcher(reader);  
	        Term term=new Term("content", "add");  
	        TermQuery query=new TermQuery(term);  
	        TopDocs topdocs=searcher.search(query, 5);  
	        ScoreDoc[] scoreDocs=topdocs.scoreDocs;  
	        System.out.println("查询结果总数---" + topdocs.totalHits+"最大的评分--"+topdocs.getMaxScore());  
	        for(int i=0; i < scoreDocs.length; i++) {  
	            int doc = scoreDocs[i].doc;  
	            Document document = searcher.doc(doc);  
	            System.out.println("content===="+document.get("content"));  
	            System.out.println("id--" + scoreDocs[i].doc + "---scors--" + scoreDocs[i].score+"---index--"+scoreDocs[i].shardIndex);  
	        }  
	        reader.close();  
	}
	
}
