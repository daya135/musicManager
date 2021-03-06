package org.jzz.musicManger.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 根据本地音乐文件列表和虾米收藏列表, 找出虾米没有下载的歌曲
 */
public class LocalFile {
	
	private static Logger logger = LoggerFactory.getLogger(LocalFile.class);
	private static final String FILETYPE = ".mp3";
	
	//查找指定目录下的mp3文件列表
	public static List<Song> getLocalSongList(String dirName) {
		List<Song> list = new ArrayList<Song>();
		try {
			File direct = new File(dirName);
			if (direct.isDirectory()) {
				localFileList(dirName, list);
			} else {
				logger.info(String.format("指定目录不存在[%s]" , dirName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//递归查找并解析mp3文件，存入指定List
	private  static void localFileList(String dirName, List<Song> list) {
		try {
			File dir = new File(dirName);
			if (!dir.exists() || !dir.isDirectory()) 
				return;
			File files[] = dir.listFiles();
			for (File file:files) {
				if (file.isDirectory()) {
					localFileList(dirName + "/" + file.getName(),  list);
				} else if(file.getName().trim().endsWith(FILETYPE)) {	
					try {
						logger.info(String.format("开始解析mp3文件：[%s]" , file.getName().trim()));
						Song song = MP3Analysis2.mp3Info(dirName + "/" + file.getName().trim());
						if (song.getTitle() == null || song.getTitle().trim().length() == 0) {
							logger.info(String.format("文件[%s]歌曲名为空，跳过", file.getName()));
						} else {
							song.setLocalpath(dirName + "\\" + file.getName().trim());
							list.add(song);
							logger.info(String.format("文件[%s]解析成功，歌曲新信息[%s][%s][%s][%s][%s]", file.getName(), 
									song.getTitle(), song.getArtist(),song.getBand(),song.getAlbum(), song.getPublishyear()));
						}
					} catch (Exception e) {
						logger.info(String.format("解析文件[%s]失败，异常信息:[%s]", file.getName(), e.getMessage()));
					}		
				}
			}
		}	catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void writeList(List<Song> list) {
		String fileNameStr = "localList.txt";
		try {
			//从内码写出, 可随意指定编码, 只需要保证在文本编辑器或读文件时按照对应编码即可!!!
			BufferedWriter  out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(fileNameStr), false), "GBK"));
			for (Song song : list) {
				out.append(song.getTitle() + "\t" + song.getArtist() + "\n");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
