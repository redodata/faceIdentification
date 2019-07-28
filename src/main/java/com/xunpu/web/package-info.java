package com.xunpu.web;
/**
 * �ð���Ҫ���ڽ���ǰ�˵����󣬲�������˴������ú�˵ķ������õ���Ӧ�Ĺ��ܡ�
 * �ࣺ
 * ��AbstractBaseServlet  �̳�HttpServlet��
 *          ���ԣ�FaceClient�����ڵ��ú�˴���ҵ��
 *          ������responseJSON��������Ӧʱ ָ�������ݱ����ʽ�����������Լ�д����Ӧ���ݣ�
 *
 * ��ImageBaseServlet �̳�AbstractBaseServlet
 *          ���ԣ�storagePath���ļ��Ĵ洢·����ͨ��init()����Ϊ��ǰĿ¼��faceĿ¼��
 *          ������
 *              1��init()    ��Servlet���������������У��÷���ֻ�ᱻ����һ�Ρ�
 *                  �����ϴ��ļ��Ĵ洢·��Ϊ��ǰĿ¼��faceĿ¼.�����д�����ʧ�����׳��쳣��
 *              2��public String writeImage(HttpServletRequest request, String parameter)���ϴ����ļ����м��ܣ�����װΪ�������
 *              3�� public byte[] readImage(HttpServletRequest request,String parameter)ͨ��������ȡͼƬ������תΪ�������ֽ������ء�
 *
 * ��DetectFaceServlet �̳�ImageBaseServlet
 *          ������
 *          ��дdoGet()��doPost()
 *          doGet()�����ڶ�ͼƬ�����������ͼƬΪ�գ�����֯�������Ӧ��Ϣ��������֯��ȷ����Ӧ��Ϣ��
 *          doPost()���Եõ���ͼƬ���н��룬Ȼ�����detect()��������ʶ��õ�FaceInfo����ȡ�õĶ���Ϊ�գ�����֯��ȷ����Ӧ��Ϣ��������֯�������Ӧ��Ϣ��
 *
 * ��UploadFileServlet �̳�ImageBaseServlet
 *          ������
 *          ��дdoPost()����ȡwriteImage�õ����ַ������ж��ַ����Ƿ�Ϊ�գ����Ϊ�գ�����֯���ش������Ӧ��Ϣ�����򷵻���ȷ����Ӧ��Ϣ������url)��
 *
 * ��ShowFileServlet   �̳�ImageBaseServlet
 *          ������
 *          ��дdoGet()����ȡreadImage�õ����ֽ����顣���Ϊ�գ���������Ӧ״̬��Ϊ404.���򽫶���������д����ˢ�¡�
 *
 * ��MatchFaceServlet  �̳�ImageBaseServlet
 *          ������
 *          ��дdoGet()��ͨ��readImage��ȡ����ͼƬ������match���жԱȣ��õ����ƶȷ�������֯��ȷ����Ӧ��Ϣ��
 *
 */