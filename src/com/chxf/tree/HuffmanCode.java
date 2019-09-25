package com.chxf.tree;

import java.io.*;
import java.util.*;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/16
 * @description ：哈夫曼编码
 * @version: 1.0
 */
public class HuffmanCode {
    public static void main(String[] args) {
        // 压缩文件
        // 获取文件的路径text.txt
        String srcFile = "d://text.txt";
        String dstFile = "d://dst.zip";
        zipFile(srcFile, dstFile);
        System.out.println("压缩成功···");
        String zipFile = "d://dst.zip";
        String desFile = "d://text2.txt";
        unZipFile(zipFile, desFile);
        System.out.println("解压缩成功···");

        /*
        // 压缩数据
        // 获取文本的字节数组
        String text = "i like like like like java do you like a java";
        byte[] countBytes = text.getBytes();
        System.out.println("加密前为："+Arrays.toString(countBytes));
        byte[] huffmanZipResult = huffmanZip(countBytes);
        System.out.println("加密后为："+Arrays.toString(huffmanZipResult));
        byte[] sourceByte = decode(huffmanCodeTable,huffmanZipResult);
        System.out.println("原文为："+new String(sourceByte));
*/

    }

    // 构造哈夫曼树
    public static CodeNode createHuffman(List<CodeNode> list) {

        while (list.size() > 1) {
            // 对数据进行排序
            Collections.sort(list);

            // 获取最小的两个节点
            CodeNode leftNode = list.get(0);
            CodeNode rightNode = list.get(1);
            // 创建新的节点
            CodeNode parent = new CodeNode(leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 删除最小的俩节点，将新产生的加入
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }

    // huffman压缩方法
    public static byte[] huffmanZip(byte[] bytes) {
        // 获取节点数据
        List<CodeNode> nodes = getCodeNode(bytes);
        // 创建哈夫曼树
        CodeNode root = createHuffman(nodes);
        // 输出测试
        perOrder(root);
        // 获取编码表
        //getCodes(root,"",stringBuilder);
        Map<Byte, String> codeTable = getCodeTable(root);
        System.out.println("编码表：" + codeTable);

        // 压缩后的数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodeTable);
        return huffmanCodeBytes;

    }

    // 获取节点的数据
    public static List<CodeNode> getCodeNode(byte[] bytes) {
        // c存放节点
        List<CodeNode> nodes = new ArrayList<CodeNode>();
        // 用于存放各个字符出现的次数以及相应的字符
        Map<Byte, Integer> map = new HashMap<Byte, Integer>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }
        // 遍历map数组进行节点的构建
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new CodeNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 前序遍历的方法
    public static void perOrder(CodeNode root) {
        if (root != null) {
            root.perOrder();
        } else {
            System.out.println("哈夫曼树为空！！！");
        }
    }

    // 重新构造一个函数，方便调用
    public static Map<Byte, String> getCodeTable(CodeNode root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodeTable;
    }

    // 用来存放Huffman编码
    static Map<Byte, String> huffmanCodeTable = new HashMap<Byte, String>();
    // 用来存储路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将所有的叶子节点的路径生成Huffman编码表
     *
     * @param node          传入的节点
     * @param code          路径，左子节点为0，右子节点为1
     * @param stringBuilder 将所有的路径保存
     */
    public static void getCodes(CodeNode node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        // 将路径添加到字符路径
        stringBuilder1.append(code);
        //
        if (node != null) {
            if (node.data == 0) {
                //递归处理
                getCodes(node.left, "0", stringBuilder1);
                getCodes(node.right, "1", stringBuilder1);
            } else {
                // 当节点为叶子节点时
                huffmanCodeTable.put(node.data, stringBuilder1.toString());
            }
        }
    }

    // 压缩方法
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder zipStrBuilder = new StringBuilder();
        // 获取压缩后的字节
        for (byte b : bytes) {
            zipStrBuilder.append(huffmanCodes.get(b));
        }

        // 统计字节数组的长度
        int len = (zipStrBuilder.length() + 7) / 8;
        int index = 0; // 记录数组的下标

        //创建一个临时的byte数组
        byte[] temp = new byte[len];
        for (int i = 0; i < zipStrBuilder.length(); i += 8) {
            String byteStr;
            // 将八位转成一个字
            if (i + 8 > zipStrBuilder.length()) {
                byteStr = zipStrBuilder.substring(i);
            } else {
                byteStr = zipStrBuilder.substring(i, i + 8);
            }
            // 将其转换成byte，放入byte数组
            temp[index] = (byte) Integer.parseInt(byteStr, 2);
            index++;
        }
        return temp;
    }


    /**
     * @param HuffmanCodes 哈夫曼编码
     * @param huffmanBytes 利用哈夫曼编码压缩得到的数组
     * @return 返回原来字符串中对应的数组
     */
    public static byte[] decode(Map<Byte, String> HuffmanCodes, byte[] huffmanBytes) {
        // 1. 获取byte数组对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        System.out.println("解码后的二进制字符串：" + stringBuilder);

        // 将哈夫曼编码表进行键--值交换
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodeTable.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        // 创建一个集合存放byte
        List<Byte> list = new ArrayList<Byte>();

        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            //
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        // 当for循环结束后，list中已经存放了所有的字符
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个 byte 转成一个二进制的字符串, 如果看不懂，可以参考我讲的 Java 基础 二进制的原码，反码，补码
     *
     * @param b    传入的 byte
     * @param flag 标志是否需要补高位如果是 true ，表示需要补高位，如果是 false 表示不补, 如果是最后一个字节，无需补高位
     * @return 是该 b 对应的二进制的字符串，（注意是按补码返回）
     */
    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        // flag是
        if (flag) {
            temp |= 256;
        }
        // b对应的二进制补码
        String str = Integer.toBinaryString(temp);
        // 获取补码的反码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 编写方法，将一个文件进行压缩
     *
     * @param srcFile 文件源目录
     * @param desFile 文件输出目录
     */
    public static void zipFile(String srcFile, String desFile) {

        FileInputStream is = null;
        OutputStream out = null;
        ObjectOutputStream obj = null;
        try {
            // 创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和原文件大小相同的大小节点数组
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b);
            // 获取文件对应的huffman表
            byte[] huffmanBytes = huffmanZip(b);

            // 创建文件输出流，将文件保存到外边
            out = new FileOutputStream(desFile);
            obj = new ObjectOutputStream(out);
            obj.writeObject(huffmanBytes);
            obj.writeObject(huffmanCodeTable);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭读取
            try {
                is.close();
                obj.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压文件
     *
     * @param zipFile 压缩文件所在的路径位置
     * @param desFile 文件解压到的路径位置
     */
    public static void unZipFile(String zipFile, String desFile) {
        InputStream is = null;
        ObjectInputStream obj = null;
        OutputStream out = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和 is 关联的对象输入流
            obj = new ObjectInputStream(is);
            //读取 byte 数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) obj.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) obj.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将 bytes 数组写入到目标文件
            out = new FileOutputStream(desFile);
            //写数据到 dstFile 文件
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭读取
            try {
                is.close();
                obj.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


// 节点类
class CodeNode implements Comparable<CodeNode> {
    public byte data;
    public int weight;
    public CodeNode left;
    public CodeNode right;

    public CodeNode(int weight) {
        this.weight = weight;
    }

    public CodeNode(byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CodeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(CodeNode o) {
        return this.weight - o.weight;
    }

    // 前序遍历
    public void perOrder() {
        System.out.println(this.toString());
        if (this.left != null) {
            this.left.perOrder();
        }
        if (this.right != null) {
            this.right.perOrder();
        }
    }
}