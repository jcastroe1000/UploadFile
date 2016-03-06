
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.MessageContext;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import static org.apache.tomcat.jni.User.username;
import static sun.rmi.transport.TransportConstants.Magic;
import sun.security.x509.AlgorithmId;

public class FileUploadHandler extends HttpServlet  {
    private final String UPLOAD_DIRECTORY = "/home/tono/";
    public String ingredients;
    public String dish_name ;
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                    new DiskFileItemFactory()).parseRequest(request);
                    String inputName = null; 
                    //tomamos el valor de los inputs
                    for(FileItem item : multiparts){
                        if(item.isFormField()){  // Check regular field.
                            inputName = (String)item.getFieldName(); 
                            if(inputName.equalsIgnoreCase("name_dish")){ 
                             dish_name = (String)item.getString(); 
                            }
                        }
                        if(item.isFormField()){  // Check regular field.
                            inputName = (String)item.getFieldName(); 
                            if(inputName.equalsIgnoreCase("ingredients")){ 
                            ingredients= (String)item.getString(); 
                            }
                        }    
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        String mimeType = getServletContext().getMimeType(name);//varificamos que tipo de archivo es
                        if (mimeType.startsWith("image/")) {
                            System.out.println(mimeType);
                            System.out.println("soy imagen");
                            long tamanio    = item.getSize();//tomamos el tamaño de la imagen
                            if(tamanio<=2500000){//valor maximo igual o menor a 2.5 mb
                                System.out.println(tamanio);
                                System.out.println("peso menos o igual de 2500000");
                                System.out.println("The ingredients are:"+ingredients);   
                                System.out.println("UserName is:"+dish_name); 
                                String concat=name+ingredients+dish_name;
                                System.out.println("The Concat is:"+concat); 
                                MessageDigest m=MessageDigest.getInstance("MD5");
                                m.update(concat.getBytes(),0,concat.length());
                                File name2=new File(item.getName());
                                File file2 = new File(new BigInteger(1,m.digest()).toString(64));
                                boolean success = name2.renameTo(file2);
                                item.write( new File(UPLOAD_DIRECTORY + File.separator + file2));
                            }
                            else{
                                System.out.println(tamanio);
                                System.out.println("peso mas de 2500000");
                            }
                        }else{
                            System.out.println("no soy imagen");
                        }
                    }
                }
                request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}