package com.org.config.utils;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.org.model.Course;
import com.org.model.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ImageUtil {

    /**
     * @param // file base64编码字符串
     * @param // path 图片路径-具体到文件
     * @return
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     */

    /*
    * 用户-将base64转成图片
    * */
    @SneakyThrows
    public static String Base64ToPic(User user) {

        //获取根目录
        File path = new File(ResourceUtils.getURL("").getPath());
        if(!path.exists()) path = new File("");
        //用上传时间表示文件名防止冲突
        String picName = "/"+ user.getId()+"_"+user.getUsername()+".jpg";

        String src = "/user-8001/src/main/resources/static/head";

            byte[] bs = Base64Utils.decodeFromString(user.getHeadPic());     //base64解码

            String filepath2 = path.getAbsolutePath()+src;          //拼接路径
            /*没有此文件夹就创建*/
            File filePath = new File(path.getAbsolutePath(),src);
            if(!filePath.exists()) {
                filePath.mkdirs();
            }

            //创建文件
            File imageFile = new File(picName);
            if (!imageFile.exists()) {
                imageFile.createNewFile();
            }
            //将二进制写入文件
            OutputStream imageStream = new FileOutputStream(filepath2+picName);
            imageStream.write(bs);
            imageStream.flush();
            imageStream.close();

//            System.out.println("图片保存路径filePath："+filePath);
//            System.out.println("图片完整路径filePath + picName："+filePath + picName);

        return picName;
    }

    /*
     * 将字节数组转成图片
     * */
    @SneakyThrows
    public static Map<String, String> ByteToPic(String Type, byte[] picData, User user) {
        Map<String, String> map = null;                            //给前端的结果

        //获取根目录
        File path = new File(ResourceUtils.getURL("").getPath());
        if(!path.exists()) path = new File("");
        //用上传时间表示文件名防止冲突
        String picName = "/"+user.getEmail()+"_"+user.getUsername()+".jpg";

        String src = null;
        if (Type.equals("face")) {                          //设置图片存放文件夹的路径
            src = "/images/face";
        }else if(Type.equals("head")){
            src = "/images/head";
        }

        String filepath2 = path.getAbsolutePath()+src;          //拼接路径
        /*没有此文件夹就创建*/
        File filePath = new File(path.getAbsolutePath(),src);
        if(!filePath.exists()) {
            filePath.mkdirs();
        }

        //创建文件
        File imageFile = new File(picName);
        if (!imageFile.exists()) {
            imageFile.createNewFile();
        }
        //将二进制写入文件
        OutputStream imageStream = new FileOutputStream(filepath2+picName);
        imageStream.write(picData);
        imageStream.flush();
        imageStream.close();

//        System.out.println("人脸照片保存路径filePath："+filePath);
//        System.out.println("图片完整路径filePath + picName："+filePath + picName);

        map = new HashMap<>();
        map.put("src", src);
        map.put("picName", picName);

        return map;
    }

    /*
     * 管理员-将base64转成图片
     * */
    @SneakyThrows
    public static Map<String, String> Base64ToPicByAdmin(String Type, String base64Data, User user) {
        Map<String, String> map = null;                            //给前端的结果

        //获取根目录
        File path = new File(ResourceUtils.getURL("").getPath());
        if(!path.exists()) path = new File("");
        //用上传时间表示文件名防止冲突
        String picName = "/"+user.getEmail()+"_"+user.getUsername()+".jpg";

        String src = null;
        if (Type.equals("face")) {                          //设置图片存放文件夹的路径
            src = "/images/face";
        }else if(Type.equals("head")){
            src = "/images/head";
        }

        String data = base64Data.split("base64,")[1];//获取base64的图片部分
        byte[] bs = Base64Utils.decodeFromString(data);     //base64解码


        String filepath2 = path.getAbsolutePath()+src;          //拼接路径
        /*没有此文件夹就创建*/
        File filePath = new File(path.getAbsolutePath(),src);
        if(!filePath.exists()) {
            filePath.mkdirs();
        }

        //创建文件
        File imageFile = new File(picName);
        if (!imageFile.exists()) {
            imageFile.createNewFile();
        }
        //将二进制写入文件
        OutputStream imageStream = new FileOutputStream(filepath2+picName);
        imageStream.write(bs);
        imageStream.flush();
        imageStream.close();

        System.out.println("人脸照片保存路径filePath："+filePath);
        System.out.println("图片完整路径filePath + picName："+filePath + picName);

        map = new HashMap<>();
        map.put("src", src);
        map.put("picName", picName);

        return map;
    }

    @SneakyThrows
    public static Map<String, String> Base64ToPic1(String Type, String base64Data, User user) {
        Map<String, String> map = null;                            //给前端的结果

        //获取根目录
        File path = new File(ResourceUtils.getURL("").getPath());
        if(!path.exists()) path = new File("");
        //用上传时间表示文件名防止冲突
        String picName = "/"+user.getEmail()+"_"+user.getUsername()+".jpg";

        String src = null;
        if (Type.equals("face")) {                          //设置图片存放文件夹的路径
            src = "/images/face";
        }else if(Type.equals("head")){
            src = "/images/head";
        }

        String data = base64Data.split("base64,")[1];//获取base64的图片部分
        byte[] bs = Base64Utils.decodeFromString(data);     //base64解码


        String filepath2 = path.getAbsolutePath()+src;          //拼接路径

        File filePath = new File(path.getAbsolutePath(),src);
        if(!filePath.exists()) {
            filePath.mkdirs();
        }

        //创建文件
        File imageFile = new File(picName);
        if (!imageFile.exists()) {
            imageFile.createNewFile();
        }
        //将二进制写入文件
        OutputStream imageStream = new FileOutputStream(filepath2+picName);
        imageStream.write(bs);
        imageStream.flush();
        imageStream.close();

        System.out.println("人脸照片保存路径filePath："+filePath);
        System.out.println("图片完整路径filePath + picName："+filePath + picName);

        map = new HashMap<>();
        map.put("src", src);
        map.put("picName", picName);


        return map;
    }



    public static String multipartFileToFile(MultipartFile file) throws Exception {

        //获取根目录
        File path = new File(ResourceUtils.getURL("classpath:static").getPath());
        if(!path.exists()) path = new File("");
        //System.out.println("path:"+path.getAbsolutePath());

        String fileName = file.getOriginalFilename();
        String src = "/user-8001/src/main/resources/static/file";


        File realPath = new File(path.getAbsolutePath(),src);
        if(!realPath.exists()) {
            realPath.mkdirs();
        }

        File ss = new File(realPath +"/"+ fileName);
        file.transferTo(ss);

        System.out.println("文件保存路径："+realPath);
        System.out.println("文件保存完整路径："+realPath +"/"+ fileName);

        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        //获取文件原本的名字file.getOriginalFilename()

        return fileName;
    }

    /*
    *将图片转成base64
    * */
//    @SneakyThrows
//    public static String PicToBase64(User user) {
//        //获取根目录
//        File path = new File(ResourceUtils.getURL("").getPath());
//        File realPath = new File(path.getAbsolutePath());
//
//        String imagePath = realPath + user.getFaceUrl();//拼接完整路径
//        byte[] data = null;
//        // 读取图片字节数组
//        InputStream in = new FileInputStream(imagePath);
//        data = new byte[in.available()];
//        in.read(data);
//        in.close();                 //关闭流
//        // 对字节数组Base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
//        // 返回Base64编码过的字节数组字符串
//        String base64str = encoder.encode(Objects.requireNonNull(data));
//
//        return base64str;
//    }


}

