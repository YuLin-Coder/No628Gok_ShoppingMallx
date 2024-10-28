package cn.gok.util;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 解析文件的工具类，用于文件上传操作
 */
public class FileUploadUtil {
	/**
	 * 这个函数的功能是获取前端的数据集合，将文件打包成File以便后续操作
	 * @param request
	 * @param servletContext
	 * @return
	 */
	public static List<FileItem> getRequsetFileItems(
			HttpServletRequest request,ServletContext servletContext){
		
		//判断enctype属性是否为multipart/form-data
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			String str="javax.servelet.context.tempdir";
            File repository=(File) servletContext.getAttribute(str);
            factory.setRepository(repository);
            ServletFileUpload upload=new ServletFileUpload(factory);
            try {
                return upload.parseRequest(request);
            }catch (FileUploadException e) {
                e.printStackTrace();
                return null;
            }
		}
		return null;
	}
	
	/**
	 * 保存文件
	 * @param fileItem    		要被保存的文件
	 * @param savePath      	指定的保存目录路径
	 * @param fileName      	要保存的文件名
	 * @return      			保存成功返回true,否则返回false
	 */
	public static boolean saveFile(FileItem fileItem,String savePath,String fileName) {        
        File file = new File(savePath);
        if(!file.exists()) {
        	file.mkdirs();
        }
        File uploadFile	= new File(savePath+File.separator+fileName);
        try{
        	fileItem.write(uploadFile);
            return true;
        }catch(Exception e){
        	e.printStackTrace();
        }
        return false;
    }
	
	/**
	 *这个函数的功能是获取当前时间点与1970年的间隔秒数
	 */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }
    
    /**
     * 这个函数的功能是得到新的图片名称
     * @param fileSuffix  图片的后缀
     * @return
     */
     public static String getImgNewName(String fileSuffix) {
         Date date=new Date();
         int second=getSecondTimestamp(date);
         String fileName=String.valueOf(second)+"."+fileSuffix;
         return fileName;
     }
     
     
	   /**
	    * 获取文件后缀名， 只要jpg或者png格式
	    * @param item
	    * @return  	如果是jpg，则返回jpg
	    * 		        如果png，则返回png
	    * 		        否则返回null
	    */
	 public static String getFileSuffix(FileItem item) {
	     String fileFullName=item.getName();
	     File fileInfo=new File(fileFullName);
	     String suffix = fileInfo.getName().substring(fileInfo.getName().lastIndexOf(".") + 1);
	     if(suffix.equals("jpg") ||suffix.equals("png") ) {
	         return suffix;
	     }
	     return null;        
	 }
	 
	 
	 
}
