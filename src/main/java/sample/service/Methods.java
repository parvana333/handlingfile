package sample.service;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Methods {

    public String findRNN(File file, String RNN) throws IOException {
        String transacId="";
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(RNN)) {
                transacId=line.substring(0,9);
                break;
            }
        }
        bufferedReader.close();
        return transacId;
    }

    public List<String> findId(File file,String transacId) throws IOException {
        List<String> list=new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line=null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(transacId)) {
               list.add(line);
            }
        }
        bufferedReader.close();
        return list;
    }

     public void createDoc(List<String> list, String directory) throws IOException {
         XWPFDocument document=new XWPFDocument();
         XWPFParagraph paragraph=document.createParagraph();
         XWPFRun run=paragraph.createRun();
         String filename="filtered.docx";
         File file=new File(directory);
         if(!file.exists()){
             file.mkdirs();
             System.out.println("entered");
         }
         FileOutputStream fileOutputStream=new FileOutputStream(new File(directory+filename));
         for(String l:list){
             run.setText(l);
             System.out.println("here");
         }
         document.write(fileOutputStream);
         fileOutputStream.close();
     }

}
