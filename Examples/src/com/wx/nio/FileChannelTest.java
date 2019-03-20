package com.wx.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class FileChannelTest {

	public static void main(String[] args) throws IOException {
		test5();
	}
	public static void test() throws IOException {
		// TODO Auto-generated method stub
				RandomAccessFile raf=new RandomAccessFile("data/2.3.3集群性能.txt", "rw");
				FileChannel fc=raf.getChannel();
				//create buffer with capacity of 48 bytes
				ByteBuffer bf=ByteBuffer.allocate(48);
				int bytesRead=fc.read(bf); //read into buffer.
				while (bytesRead!=-1) {
					System.out.println("Read " + bytesRead);
					bf.flip();//make buffer ready for read
					while (bf.hasRemaining()) {
						System.out.println((char)bf.get()); // read 1 byte at a time
					}
					bf.clear(); //make buffer ready for writing
					bytesRead=fc.read(bf);
				}
				raf.close();
	}
	public static void test1() throws IOException {
		// TODO Auto-generated method stub
		RandomAccessFile raf=new RandomAccessFile("data/2.3.3集群性能.txt", "rw");
		FileChannel fc=raf.getChannel();
		ByteBuffer bf1=ByteBuffer.allocate(128);
		ByteBuffer bf2=ByteBuffer.allocate(1024);
		ByteBuffer[] bufferArray = { bf1, bf2 };
		long bytesRead=fc.read(bufferArray); //read into buffer.
		while (bytesRead!=-1) {
			System.out.println("Read " + bytesRead);
			bufferArray[0].flip();//make buffer ready for read
			while (bufferArray[0].hasRemaining()) {
				System.out.println((char)bufferArray[0].get()); // read 1 byte at a time
			}
			bufferArray[0].clear(); //make buffer ready for writing
			bufferArray[1].flip();//make buffer ready for read
			while (bufferArray[1].hasRemaining()) {
				System.out.println((char)bufferArray[1].get()); // read 1 byte at a time
			}
			bufferArray[1].clear(); //make buffer ready for writing
			bytesRead=fc.read(bufferArray);
		}
		raf.close();
	}
	public static void test2() throws IOException {
		// TODO Auto-generated method stub
		RandomAccessFile fromFile=new RandomAccessFile("data/2.3.3集群性能.txt", "rw");
		FileChannel fromChannel =fromFile.getChannel();
		RandomAccessFile toFile =new RandomAccessFile("data/2.3.3集群性能.txt.bak", "rw");
		FileChannel toChannel  =toFile.getChannel();
		long position = 0;
		long count =fromChannel.size();
		toChannel.transferFrom(fromChannel, position, count);
	}
	public static void test3() throws IOException {
		// TODO Auto-generated method stub
		RandomAccessFile fromFile=new RandomAccessFile("data/2.3.3集群性能.txt", "rw");
		FileChannel fromChannel =fromFile.getChannel();
		RandomAccessFile toFile =new RandomAccessFile("data/2.3.3集群性能.txt.bak", "rw");
		FileChannel toChannel  =toFile.getChannel();
		long position = 0;
		long count =fromChannel.size();
		fromChannel.transferTo(position, count, toChannel);
	}
	public static void test4() throws IOException {
		// TODO Auto-generated method stub
		RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel channel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		String newData = "New String to write to file..." + System.currentTimeMillis();
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
			channel.write(buf);
		}
		channel.close();

	}
	public static void test5() throws IOException {
		SocketChannel socketChannel =SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("150.95.172.163", 80));
		System.out.println(socketChannel.isConnected());
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = socketChannel .read(buf);
		System.out.println(0);
		while(buf.hasRemaining()) {
			System.out.println(1);
			System.out.println(buf.get());
		}
		socketChannel.close();
	}
}
