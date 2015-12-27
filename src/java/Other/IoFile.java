/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.io.*;

/**
 *
 * @author X450JN
 */
public class IoFile {
    private String path;    

    public IoFile(String path) {
        this.path = path;        
    }        
    
    public void writeFile(String content) {
        try {
//            String content = "This is the content to write into file";            
            File file = new File(this.path);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public String readFile() {
        BufferedReader br = null;
        String sCurrentLine;
        String s = null;
        try {
            br = new BufferedReader(new FileReader(path));
            if ((sCurrentLine = br.readLine()) != null) {
                s = sCurrentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return s;
    }
    
    public String[] readFileArr() {
        BufferedReader br = null;
        String sCurrentLine;
        String s[] = null;
        try {
            br = new BufferedReader(new FileReader(path));
            if ((sCurrentLine = br.readLine()) != null) {
                s = sCurrentLine.split(";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return s;
    }
}
