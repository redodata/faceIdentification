package com.xunpu.web;
/**
 * 该包主要用于接收前端的请求，并交给后端处理。调用后端的方法，得到相应的功能。
 * 类：
 * ·AbstractBaseServlet  继承HttpServlet。
 *          属性：FaceClient（用于调用后端处理业务）
 *          方法：responseJSON（设置响应时 指定的数据编码格式和内容类型以及写回响应数据）
 *
 * ·ImageBaseServlet 继承AbstractBaseServlet
 *          属性：storagePath（文件的存储路径，通过init()设置为当前目录的face目录）
 *          方法：
 *              1）init()    在Servlet的整个生命周期中，该方法只会被调用一次。
 *                  设置上传文件的存储路径为当前目录的face目录.并进行创建，失败则抛出异常。
 *              2）public String writeImage(HttpServletRequest request, String parameter)对上传的文件进行加密，并包装为输出流。
 *              3） public byte[] readImage(HttpServletRequest request,String parameter)通过参数获取图片名，并转为二进制字节流返回。
 *
 * ·DetectFaceServlet 继承ImageBaseServlet
 *          方法：
 *          覆写doGet()和doPost()
 *          doGet()：用于读图片，如果读到的图片为空，则组织错误的响应信息；否则组织正确的响应信息。
 *          doPost()：对得到的图片进行解码，然后调用detect()进行人脸识别得到FaceInfo对象，取得的对象不为空，则组织正确的响应信息，否则组织错误的响应信息。
 *
 * ·UploadFileServlet 继承ImageBaseServlet
 *          方法：
 *          覆写doPost()：获取writeImage得到的字符串，判断字符串是否为空，如果为空，则组织返回错误的响应信息；否则返回正确的响应信息（包括url)。
 *
 * ·ShowFileServlet   继承ImageBaseServlet
 *          方法：
 *          覆写doGet()：获取readImage得到的字节数组。如果为空，则设置响应状态码为404.否则将读到的数据写出并刷新。
 *
 * ·MatchFaceServlet  继承ImageBaseServlet
 *          方法：
 *          覆写doGet()：通过readImage获取两张图片，传入match进行对比，得到相似度分数并组织正确的响应信息。
 *
 */