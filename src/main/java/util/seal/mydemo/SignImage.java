package util.seal.mydemo;

/**
 * @author 画图工具
 * @packageName util
 * @date 2019/12/25 15:57
 * @Description
 * @Versin 1.0
 */

import lombok.*;
import lombok.experimental.Accessors;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SignImage {
    private static Logger logger = LoggerFactory.getLogger(SignImage.class);
    private static final String FILE_PATH = "/opt/uploads/";
    private static final String TARGET_FILE_PATH = "uploads/";
    public static final String ENT_BACKGROUND_PATH = "uploads/signature/model/ent_back.png";
    public static final String USER_BACKGROUND_PATH = "uploads/signature/model/user_back.png";
    private static final String IMAGE_SUFFIX = ".png";

    /**
     * @param name    String 个人名字
     *                String 签名日期
     *                图片高度
     * @param pngname String png图片名
     * @return
     */
    public static String createSignTextImg(
            String name, //
            String pngname, boolean date, boolean isPreview) {
        int width = 225;
        int height = 90;
        FileOutputStream out = null;
        //背景色
        Color bgcolor = Color.WHITE;
        //字色
        Color fontcolor = Color.RED;
        Font signerNameFont = new Font("宋体", Font.BOLD, 12);
        Font othorTextFont = new Font("宋体", Font.BOLD, 10);
        String filepath = "";
        try { // 宽度 高度
            BufferedImage bimage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = bimage.createGraphics();
            // 背景透明
            /*bimage = g.getDeviceConfiguration().createCompatibleImage(width,height,Transparency.TRANSLUCENT);
            g.dispose();
            g = bimage.createGraphics();*/

            // 设置透明度
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1f));
            // 背景色
//            g.setColor(bgcolor);
            // 画一个矩形
            graphics2D.fillRect(0, 0, width, height);
            // 去除锯齿(当设置的字体过大的时候,会出现锯齿)
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Color color = new Color(255, 0, 0, 180);
            graphics2D.setColor(color);
            graphics2D.fillRect(0, 0, 8, height);
            graphics2D.fillRect(0, 0, width, 8);
            graphics2D.fillRect(0, height - 8, width, height);
            graphics2D.fillRect(width - 8, 0, width, height);

            // 字的颜色
            graphics2D.setColor(fontcolor);
            // 字体字形字号
            graphics2D.setFont(signerNameFont);
            FontMetrics fm = FontDesignMetrics.getMetrics(signerNameFont);
            int font1_Hight = fm.getHeight();
            int strWidth = fm.stringWidth(name);
            int y = 35;
            int x = (width - strWidth) / 2;
            // 在指定坐标除添加文字
            graphics2D.setStroke(new BasicStroke(1));
            graphics2D.drawString(name, x, y);
            // 字体字形字号
            graphics2D.setFont(othorTextFont);
            String format = "";
            if (date) {
                //获得当前时间
                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                format = ldt.format(dtf);
            }
            strWidth = fm.stringWidth(format);
            x = (width - strWidth) / 2;
            // 在指定坐标除添加文字
            graphics2D.setStroke(new BasicStroke(1));
            graphics2D.drawString(format, x, y + font1_Hight);

            // 透明度设置结束
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            graphics2D.dispose();

            // 指定输出文件
            if (isPreview) {
                filepath = "uploads/files/preview/signet/" + format;
            } else {
                filepath = "uploads/files/signet/" + format;
            }

            File file = new File(filepath);
            file.mkdirs();
            filepath = filepath + "/" + pngname;
            out = new FileOutputStream(filepath);
            BufferedImage bufferedImage = alphaProcess(bimage);
            ImageIO.write(bufferedImage, "png", out);
            out.flush();
            bufferedImage.flush();
        } catch (Exception e) {
            logger.error("createSignTextImg_" + e.getMessage(), e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return filepath;
    }

    /**
     * 获取的是磁盘路径
     *
     * @param realname
     * @return
     */
    public static String getSignatureImage(String realname) {
        String replace = null;
        try {
            String signTextImg = SignImage.createSignTextImg(realname, realname + ".png", true, false);
            replace = signTextImg.replace(TARGET_FILE_PATH, FILE_PATH);
        } catch (Exception e) {
            logger.error("getSignatureImage_" + e.getMessage(), e);
        }
        return replace;
    }

    private static void transparent() throws IOException {
        int width = 400;
        int height = 300;
// 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
// 获取Graphics2D
        Graphics2D g2d = image.createGraphics();

// ----------  增加下面的代码使得背景透明  -----------------
//        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
//        g2d.dispose();
//        g2d = image.createGraphics();
// ----------  背景透明代码结束  -----------------
//        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.5f));

        // 画一个矩形
        g2d.fillRect(0, 0, width, height);
        // 去除锯齿(当设置的字体过大的时候,会出现锯齿)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = new Color(255, 0, 0);
        g2d.setColor(color);
        g2d.fillRect(0, 0, 8, height);
        g2d.fillRect(0, 0, width, 8);
        g2d.fillRect(0, height - 8, width, height);
        g2d.fillRect(width - 8, 0, width, height);
// 画图
        g2d.setColor(new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(10));
        g2d.drawString("name", 100, 100);
//释放对象
        g2d.dispose();
        BufferedImage bufferedImage = alphaProcess(image);
// 保存文件
        ImageIO.write(bufferedImage, "png", new File("d:/test/test.png"));
        bufferedImage.flush();
    }

    /**
     * 处理透明度
     */
    private static BufferedImage alphaProcess(BufferedImage bufferedImage) {
        //获取源图像的宽高
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        System.out.println(width + " " + height);
        //实例化一个同样大小的图片，并将type设为 BufferedImage.TYPE_4BYTE_ABGR，支持alpha通道的rgb图像
        BufferedImage resImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        double grayMean = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufferedImage.getRGB(i, j);
                int r = (0xff & rgb);
                int g = (0xff & (rgb >> 8));
                int b = (0xff & (rgb >> 16));
                //这是灰度值的计算公式
                grayMean += (r * 0.299 + g * 0.587 + b * 0.114);
            }
        }
        //计算平均灰度
        grayMean = grayMean / (width * height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufferedImage.getRGB(i, j);
                //一个int是32位,java中按abgr的顺序存储，即前8位是alpha，最后8位是r，所以可以通过下面的方式获取到rgb的值
                int r = (0xff & rgb);
                int g = (0xff & (rgb >> 8));
                int b = (0xff & (rgb >> 16));
                double gray = (r * 0.299 + g * 0.587 + b * 0.114);
                //如果灰度值大于之前求的平均灰度值，则将其alpha设为0，下面准确写应该是rgb = r + (g << 8) + (b << 16) ＋ （0 << 24）;
                if (gray > grayMean) {
                    rgb = r + (g << 8) + (b << 16);
                }
                resImage.setRGB(i, j, rgb);
            }
        }
        //ok，返回的就是将浅色背景设为透明的BufferedImage了，可以用灰度化里提到的方式写成文件
        return resImage;
    }

    /**
     * 解析base64图
     * @param image64Str
     * @param imgName
     * @return [0]文件路径 [1]文件名
     */
//    public static String[] getSourceImageByBase64(String image64Str, String imgName) {
//        String imageEncode = image64Str.substring(image64Str.indexOf(",") + 1);
//        if (StringUtils.isBlank(imageEncode)) {
//            return null;
//        }
//        OutputStream out = null;
//        try {
//            // Base64解码图片
////            byte[] decode = Base64.decodeBase64(imageEncode);
//            byte[] decode = Base64Utils.decodeFromString(imageEncode);
//            int length = decode.length;
//            for (int i = 0; i < length; ++i) {
//                if (decode[i] < 0) {
//                    decode[i] += 256;
//                }
//            }
//
//            //获取当前日期和时间
//            LocalDateTime ldt = LocalDateTime.now();
//            //使用系统提供的格式来格式化：2017-11-04
//            String strDate = ldt.format(DateTimeFormatter.ISO_DATE);
//            String filePath = "signature/" + strDate + "/source/";
//            // TODO 文件名改为随机
//            String fileName = System.nanoTime() + "-" + imgName + IMAGE_SUFFIX;
//            //         String fileName = System.currentTimeMillis() + "_" + RandomStringUtils.randomNumeric(6) + imgName.substring(imgName.lastIndexOf("."));
//            String dirPath = TARGET_FILE_PATH + filePath;
//            File dirFile = new File(dirPath);
//            if (!dirFile.exists()) {
//                dirFile.mkdirs();
//            }
//            out = new FileOutputStream(new File(dirFile, fileName));
//            out.write(decode);
//            out.flush();
//            return new String[]{dirPath + fileName, fileName};
//        } catch (Exception e) {
//            logger.error("getSourceImageByBase64_" + e.getMessage(), e);
//        } finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                logger.error("getSourceImageByBase64_" + e.getMessage(), e);
//            }
//        }
//        return null;
//    }

    /**
     * 生成用户原章
     *
     * @param name
     * @return 文件路径
     */
    public static String createUserSourceChapter(String name) {
        String filePath = "signature";
        String fileName = System.nanoTime() + "-" + name + IMAGE_SUFFIX;
        String dirPath = TARGET_FILE_PATH + filePath;
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String totalFileName = dirPath + fileName;
        createChapter(totalFileName, name);
        return totalFileName;
    }

//    /**
//     * 生成用户原章
//     * @param name
//     * @return 文件base64
//     */
//    public static String createUserSourceChapterBase64(String name) {
//        if (StringUtils.isBlank(name)) {
//            throw new RestFailedOperationException(RestErrorCode.CHAPTER_NAME_DOES_NOT);
//        }
////        return Base64.encodeBase64String(createChapterByteArray(name));
//        return Base64Utils.encodeToString(createChapterByteArray(name));
//    }

    /**
     * 画章
     *
     * @param name 章里面的名字
     * @return 字节数组
     */
    private static byte[] createChapterByteArray(String name) {
        BufferedImage bi = getSourceBufferedImage(name);
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            ImageIO.write(bi, "png", output);
            bi.flush();
            return output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 画章
     *
     * @param path 保存路径
     * @param name 章里面的名字
     */
    private static void createChapter(String path, String name) {
//        checkCreateChapterData(path, name);
        BufferedImage bi = getSourceBufferedImage(name);
        try {
            ImageIO.write(bi, "png", new File(path));
            bi.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 画章
     *
     * @param name 章里面的名字
     */
    private static BufferedImage getSourceBufferedImage(String name) {
        int height = 36;
        int fontSize = 36;
        int width = fontSize * name.length();
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2 = bi.createGraphics();
        g2.setBackground(Color.WHITE);
        g2.setPaint(Color.BLACK);
        g2.clearRect(0, 0, width, height);
        Font font = new Font("黑体", Font.PLAIN, fontSize);
        // 去除锯齿(当设置的字体过大的时候,会出现锯齿)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(font);
        g2.drawString(name, 0, fontSize - 4);
        g2.dispose();
        return resizeImage(110, 36, bi);
    }

//    /**
//     * 画章, 不用
//     * @param path 保存路径
//     * @param number 章里面的名字
//     */
//    private static void createCardNumberImage(String path, String number, String name) {
//        checkCreateChapterData(path, number);
//        // 一个字宽高 60 * 60
//        int fontSize = 18;
//        int height = fontSize + 4;
//        int width1 = number.length() * fontSize;
//        int width2 = name.length() * fontSize;
//        int width = width1 + width2 + 2;
//        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
//        Graphics2D g2 = bi.createGraphics();
//        g2.setBackground(Color.WHITE);
//        g2.setPaint(new Color(222,222,222));
//        g2.clearRect(0, 0, width, height);
//        Font font = new Font("arial", Font.PLAIN, fontSize);
//        // 去除锯齿(当设置的字体过大的时候,会出现锯齿)
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setFont(font);
//        g2.drawString(number,0, fontSize - 2);
//        g2.setFont(new Font("黑体", Font.PLAIN, fontSize));
//        g2.drawString(name,width1 + 1, fontSize - 2);
//        g2.dispose();
//        try {
//            BufferedImage bufferedImage = resizeImage(127, 9, bi);
//            ImageIO.write(bufferedImage, "png", new File(path));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private static void checkCreateChapterData(String path, String name) {
//        if (StringUtils.isBlank(name)) {
//            throw new RestFailedOperationException(RestErrorCode.CHAPTER_NAME_DOES_NOT);
//        }
//        if (StringUtils.isBlank(path)) {
//            throw new RuntimeException("章路径没有");
//        }
//    }

    public static String overlapImage(String backgroundPath, String chapterPath, String message, String message02, String outPutPath) {
        logger.debug("===>overlapImage:backgroundPath:" + backgroundPath);
        logger.debug("===>overlapImage:chapterPath:" + chapterPath);
        logger.debug("===>overlapImage:message:" + message);
        logger.debug("===>overlapImage:message02:" + message02);
        logger.debug("===>overlapImage:outPutPath:" + outPutPath);
        try {
            //设置图片大小
//            BufferedImage background = resizeImage(618,1000, ImageIO.read(new File("这里是背景图片的路径！")));
            BufferedImage background = getOverlapImageBufferedImage(backgroundPath, chapterPath, message, message02);
            ImageIO.write(background, "png", new File(outPutPath));
            background.flush();
        } catch (Exception e) {
            logger.error("===>overlapImage:e.getMessage()" + e.getMessage(), e);
        }
        return null;
    }

    public static byte[] overlapImageByte(String backgroundPath, String chapterPath, String message, String message02) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bytes = null;
        logger.debug("===>overlapImage:backgroundPath:" + backgroundPath);
        logger.debug("===>overlapImage:chapterPath:" + chapterPath);
        logger.debug("===>overlapImage:message:" + message);
        logger.debug("===>overlapImage:message02:" + message02);
        try {
            BufferedImage background = getOverlapImageBufferedImage(backgroundPath, chapterPath, message, message02);

            byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(background, "png", byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
            background.flush();
        } catch (Exception e) {
            logger.error("overlapImageByte_" + e.getMessage(), e);
        } finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                logger.error("overlapImageByte_" + e.getMessage(), e);
            }
        }
        return bytes;
    }

    private static BufferedImage getOverlapImageBufferedImage(String backgroundPath, String chapterPath, String message, String message02) throws IOException {
        //设置图片大小
//            BufferedImage background = resizeImage(618,1000, ImageIO.read(new File("这里是背景图片的路径！")));
        BufferedImage background = ImageIO.read(new File(backgroundPath));
        BufferedImage chapter = ImageIO.read(new File(chapterPath));
//            BufferedImage chapter = ImageIO.read(new File(chapterPath));
        //String message = "扫描下方二维码，欢迎大家添加我的淘宝返利机器人，居家必备，省钱购物专属小秘书！";
        Graphics2D g = background.createGraphics();
        g.setColor(new Color(216, 216, 216));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (message02 != null) {
//            chapter = resizeImage(126,34,ImageIO.read(new File(chapterPath)));
//            chapter = resizeImageByThumbnailator(chapterPath, 126, 34);
            g.setFont(new Font("arial", Font.PLAIN, 11));
            g.setPaint(new Color(0, 0, 0, 64));//阴影颜色
            int x = 20, y = 189;
            g.drawString(message, x, y);//先绘制阴影
            g.drawString(message, x, y);
            g.setFont(new Font("黑体", Font.PLAIN, 11));
            g.setPaint(new Color(0, 0, 0, 90));//阴影颜色
            x = 145;
            g.drawString(message02, x, y);//先绘制阴影
            g.drawString(message02, x, y);
        } else {
            int x = 28, y = 190;
//            chapter = resizeImage(115,115,ImageIO.read(new File(chapterPath)));
//            chapter = resizeImageByThumbnailator(chapterPath, 115, 115);
            g.setFont(new Font("arial", Font.PLAIN, 12));
            g.setPaint(new Color(0, 0, 0, 64));//阴影颜色
            g.drawString(message, x, y);//先绘制阴影
            g.drawString(message, x, y);
        }
        //在背景图片上添加图片
        int x = (background.getWidth() - chapter.getWidth()) / 2;
        int y = (background.getHeight() - chapter.getHeight()) / 2 - 5;
        /*Ellipse2D.Double shape = new Ellipse2D.Double(x, y, 120d, 120d);
        g.setClip(shape);*/
        g.drawImage(chapter, x, y, chapter.getWidth(), chapter.getHeight(), null);
        int alpha = 0;
        for (int j1 = background.getMinY(); j1 < background.getHeight(); j1++) {
            for (int j2 = background.getMinX(); j2 < background.getWidth(); j2++) {
                int rgb = background.getRGB(j2, j1);
                int R = (rgb & 0xff0000) >> 16;
                int G = (rgb & 0xff00) >> 8;
                int B = (rgb & 0xff);
                if (((255 - R) < 50) && ((255 - G) < 50) && ((255 - B) < 50)) {
                    rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                }
                background.setRGB(j2, j1, rgb);
            }
        }
        g.dispose();
        return background;
    }

    private static void resizeImage(String outPath, String inPath) throws IOException {
        BufferedImage chapter = resizeImage(115, 115, ImageIO.read(new File(inPath)));
        ImageIO.write(chapter, "png", new File(outPath));
    }

    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.fillRect(0, 0, x, y);
        graphics.drawImage(
                bfi.getScaledInstance(x, y, Image.SCALE_AREA_AVERAGING), 0, 0, null);
        return bufferedImage;
    }

    public static byte[] genChapterImageByte(String backImage, String message, String message02, String chapterPath) {
        return overlapImageByte(backImage, chapterPath, message, message02);
    }

    /**
     * @param backImage
     * @param message
     * @param message02
     * @param chapterPath
     * @param chapterName
     * @param type        类型 系统 自定义
     * @return 字符串数组，第一个文件路径，第二个文件名
     */
//    public static String[] genChapterImage(String backImage, String message, String message02, String chapterPath, String chapterName, Byte type) {
//        //获取当前日期和时间
//        LocalDateTime ldt = LocalDateTime.now();
//        //使用系统提供的格式来格式化：2017-11-04
//        String strDate = ldt.format(DateTimeFormatter.ISO_DATE);
//        String filepath = TARGET_FILE_PATH + "signature/" + strDate + "/signature/";
//        File file = new File(filepath);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        String filename = System.nanoTime() + "-" + chapterName + IMAGE_SUFFIX;
//        String outPutPath = filepath + filename;
//        String newChapterPath = TARGET_FILE_PATH + "signature/" + strDate + "/signature/" + "middle" + filename;
//        if (SignatureTypeEnum.SELF_EDIT_CHAPTER.matches(type)) {
//            ImageUtils.transferAlpha2File(chapterPath, newChapterPath);
//            overlapImage(backImage, newChapterPath, message, message02, outPutPath);
//            // 清理掉中间图
//            File file1 = new File(newChapterPath);
//            file1.deleteOnExit();
//        }else {
//            overlapImage(backImage, chapterPath, message, message02, outPutPath);
//        }
//        return new String[]{outPutPath, filename};
//    }
    public static void resizeImageByThumbnailator(String of, String to, int width, int height) {
        try {
            Thumbnails.of(of)
                    .size(width, height)
                    .outputQuality(1f)
                    .toFile(to);
        } catch (IOException e) {
            logger.error("resizeImageByThumbnailator_" + e.getMessage(), e);
        }
    }

    public static BufferedImage resizeImageByThumbnailator(String of, int width, int height) throws IOException {
        return Thumbnails.of(of)
                .size(width, height)
                .asBufferedImage();
    }

    public static void testCreateUserChapter(String sourcePath, String backPath, String id1, String name, String outputPath) {
        createChapter(sourcePath, name);
        overlapImage(backPath, sourcePath, id1, "/" + name, outputPath);
    }
    //

    /**
     * 创建私人章的方法
     *
     * @param drawStrName         输入的姓名 1- 18 位
     * @param filePathAndFileName E:\test2\grp.png
     * @param drawStrSpecial      -- CN310110HAJ403CFD383
     */
    public static void createPersonalSeal(String drawStrName, String filePathAndFileName, String drawStrSpecial) {

        String drawStrSecondLine = "";
        String drawStrFirstLine = "";

        int length = drawStrName.length();
        Map<Integer, SignImage.Params> weidthHeightMap = getWeidthHeight(length);

        Params params = weidthHeightMap.get(length);
        int width = params.getWidth() * 2;
        int height = params.getHeight() * 2;

        int fontHeight = 12 * 2;
        int fontHeightSpecial = 12;

        int frameLineWhiteSize = 8;
        int newWidth = width;
        int newHeight = height + frameLineWhiteSize + fontHeightSpecial;
        int frameLineSize = 5;

        BufferedImage buffImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = buffImg.createGraphics();
        //设置透明  start
        buffImg = gd.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
        gd = buffImg.createGraphics();
        //设置透明  end

        //设置字体
        Font fontSong = new Font("宋体", Font.BOLD, fontHeight);
        gd.setFont(fontSong);
        //类对象,可以获得某个字体的高度,以及字符串的宽度
        FontMetrics fmSong = gd.getFontMetrics(fontSong);

        Stroke oldStroke = gd.getStroke();
        //设置颜色

        //设置线宽为5.0
        gd.setStroke(new BasicStroke(5.0f));
        //画边框
//        gd.drawRect(0, 0, width - 1 , height - fontHeightSpecial -8);
        // 画一个矩形
        gd.fillRect(0, 0, newWidth, newHeight);
        // 去除锯齿(当设置的字体过大的时候,会出现锯齿)
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gd.setColor(Color.black);
        gd.fillRect(0, 0, frameLineSize, height);
        gd.fillRect(0, 0, width, frameLineSize);
        gd.fillRect(0, height, width, frameLineSize);
        gd.fillRect(width - frameLineSize, 0, width, height);

        int songFontwidthFirst = 0;
        int songFontwidthSecond = 0;
        int arialFontheightFirst = 0;
        int arialFontheightSecond = 0;
        boolean largeFlag = length > 3;
        if (largeFlag) {
            int subStartLength = length % 2;
            if (subStartLength == 1) {
                subStartLength = (length - 1) / 2;
            } else {
                subStartLength = length / 2;
            }
            drawStrFirstLine = drawStrName.substring(0, subStartLength);
            drawStrSecondLine = drawStrName.substring(subStartLength, length);

            songFontwidthFirst = fmSong.stringWidth(drawStrFirstLine);
            songFontwidthSecond = fmSong.stringWidth(drawStrSecondLine);

            arialFontheightFirst = fmSong.getHeight();
            arialFontheightSecond = fmSong.getHeight();
        } else {
            drawStrFirstLine = drawStrName;
            songFontwidthFirst = fmSong.stringWidth(drawStrFirstLine);
            arialFontheightFirst = fmSong.getHeight();
        }

        if (largeFlag) {
            //先画第一行,输出文字（中文横向居中）
            gd.drawString(drawStrFirstLine, (width - songFontwidthFirst) / 2,
                    (newHeight - arialFontheightFirst * 2 - frameLineSize) / 2 + 10);
            //画第二行,输出文字（中文横向居中）
            gd.drawString(drawStrSecondLine, (width - songFontwidthSecond) / 2,
                    (newHeight - arialFontheightFirst * 2 - frameLineSize) / 2 + arialFontheightFirst + 20);
        } else {
            gd.drawString(drawStrFirstLine, (width - songFontwidthFirst) / 2, newHeight / 2);
        }

        Font arialFont = new Font("Arial", 2, fontHeightSpecial);
        gd.setFont(arialFont);
        //类对象,可以获得某个字体的高度,以及字符串的宽度
        FontMetrics fm = gd.getFontMetrics(arialFont);
        int arialFontwidth = fm.stringWidth(drawStrSpecial);

        gd.setColor(Color.BLACK);
        gd.drawString(drawStrSpecial, (width - arialFontwidth) / 2, newHeight);
        // 透明度设置结束
        gd.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        gd.dispose();
        try {
            ImageIO.write(buffImg, "png", new File(filePathAndFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffImg.flush();
    }

    public static Map<Integer, SignImage.Params> getWeidthHeight(int length) {
        int width = 0;
        int height = 0;

        switch (length) {
            case 1:
                width = 93;
                height = 44;
                break;
            case 2:
                width = 93;
                height = 44;
                break;
            case 3:
                width = 93;
                height = 44;
                break;
            case 4:
                width = 93;
                height = 44;
                break;
            case 5:
                width = 93;
                height = 44;
                break;
            case 6:
                width = 93;
                height = 44;
                break;
            case 7:
                width = 107;
                height = 50;
                break;
            case 8:
                width = 107;
                height = 50;
                break;
            case 9:
                width = 119;
                height = 50;
                break;
            case 10:
                width = 119;
                height = 50;
                break;
            case 11:
                width = 136;
                height = 58;
                break;
            case 12:
                width = 136;
                height = 58;
                break;
            case 13:
                width = 136;
                height = 58;
                break;
            case 14:
                width = 136;
                height = 58;
                break;
            case 15:
                width = 136;
                height = 58;
                break;
            case 16:
                width = 136;
                height = 58;
                break;
            case 17:
                width = 136;
                height = 58;
                break;
            case 18:
                width = 136;
                height = 58;
                break;
            default:
                throw new RuntimeException("姓名字数不符合要求");
        }
        Map<Integer, SignImage.Params> reParentMap = com.google.common.collect.Maps.newHashMap();
        SignImage.Params returnParams = new SignImage.Params();
        returnParams.setHeight(height);
        returnParams.setWidth(width);
        reParentMap.put(length, returnParams);

        return reParentMap;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    @Accessors(chain = true)
    @EqualsAndHashCode
    static class Params {

        int width;
        int height;
    }

    public static void main(String[] args) {
        SignImage.createPersonalSeal("张大牛", "E:\\test2\\grp.png", "CN310110HAJ403CFD383");
    }

}
