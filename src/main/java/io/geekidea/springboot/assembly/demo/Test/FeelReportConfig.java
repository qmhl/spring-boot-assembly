//package io.geekidea.springboot.assembly.demo.Test;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @Author wangnan122
// * @Date 2022/9/26 6:47 下午
// */
//@Component
//@Slf4j
//public class FeelReportConfig implements InitializingBean {
//
////    private final static String FILE_DIR = "/export/App/feel/monitor/1.txt";
//    private final static String FILE_DIR = "//export//App//feel//monitor//1.txt";
//
//    private static final String path1 = "http://storage.jd.local/rule/header.png";
//    private static final String path2 = "http://storage.jd.local/rule/rank-1.png";
//    private static final String path3 = "http://storage.jd.local/rule/rank-2.png";
//    private static final String path4 = "http://storage.jd.local/rule/rank-3.png";
//
//    public static void main(String[] args) throws Exception {
//        File file = new File(FILE_DIR);
//        // 创建文件夹 避免文件夹不存在异常
//        createFile(file.getPath());
//        List<String> pnglist = Arrays.asList(path1, path2, path3, path4);
//        pnglist.forEach(e-> {
//            try {
//                download(e,"header.png",FILE_DIR);
//            } catch (Exception ex) {
//                log.error("写入文件异常！",ex);
//            }
//
//        });
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        File file = new File(FILE_DIR);
//        // 创建文件夹 避免文件夹不存在异常
//        createFile(file.getPath());
//        List<String> pnglist = Arrays.asList(path1, path2, path3, path4);
//        pnglist.forEach(e-> {
//            try {
//                download(e,"header.png",FILE_DIR);
//            } catch (Exception ex) {
//                log.error("写入文件异常！",ex);
//            }
//
//        });
//
//    }
//    public static void download(String urlString, String filename, String savePath) throws Exception {
//        // 构造URL
//        URL url = new URL(urlString);
//
//        // 打开连接
//
//        URLConnection con = url.openConnection();
//
//        //设置请求超时为5s
//
//        con.setConnectTimeout(5 * 1000);
//
//        // 输入流
//
//        InputStream is = con.getInputStream();
//
//        // 1K的数据缓冲
//
//        byte[] bs = new byte[1024];
//
//        // 读取到的数据长度
//
//        int len;
//
//        // 输出的文件流
//
//        File sf = new File(savePath);
//
//        if (!sf.exists()) {
//
//            sf.mkdirs();
//
//        }
//
//        OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
//
//        // 开始读取
//
//        while ((len = is.read(bs)) != -1) {
//
//            os.write(bs, 0, len);
//
//        }
//
//        // 完毕，关闭所有链接
//
//        os.close();
//
//        is.close();
//
//    }
//
//
//    /**
//     * 创建文件夹和临时文件
//     *
//     * @param filePath
//     * @return
//     * @throws Exception
//     */
//    private static File createFile(String filePath) throws Exception {
//        File esfile = new File(filePath);
//        File parentFile = esfile.getParentFile();
//        if (!parentFile.exists() && !parentFile.mkdirs()) {
//            log.warn("创建文件夹失败！{}", parentFile.getAbsolutePath());
//        }
//        if (!esfile.createNewFile()) {
//            log.warn("创建结果文件失败！{}", filePath);
//        }
//        return esfile;
//    }
//
//}
