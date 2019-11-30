
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MyFunc 
{
    public ImageIcon resizePic(String picPath,byte[] BLOBpic,int w,int h)
    {
        ImageIcon I;
        if(picPath!= null)
        {
            I = new ImageIcon(picPath);
        }
        else 
        {
            I = new ImageIcon(BLOBpic);
        }
        Image img = I.getImage().getScaledInstance(w,h, Image.SCALE_SMOOTH);
        ImageIcon mypic = new ImageIcon(img);
        return mypic;
     }
    public String browseImage(JLabel lb1)
    {
        String path="";
        JFileChooser filec = new JFileChooser();
        filec.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        //file extension
        FileNameExtensionFilter fileFilter = new  FileNameExtensionFilter("Images","jpg","png");
        filec.addChoosableFileFilter(fileFilter);
        int filestate = filec.showSaveDialog(null);
        
        //if the user select a file 
        if(filestate==JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = filec.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            //imagePath = path;
            //display the image in label using resize image
            //jLabelPic.setIcon(new ImageIcon(path));
            lb1.setIcon(resizePic(path,null,lb1.getWidth(),lb1.getHeight()));
            
        }//if user cancel
        else if(filestate==JFileChooser.CANCEL_OPTION)
        {
            System.out.println("No Image Selected");
        }
        return path;
    }
}
