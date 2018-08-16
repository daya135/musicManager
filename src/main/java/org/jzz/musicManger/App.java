package org.jzz.musicManger;

import java.util.List;
import org.jzz.musicManger.utils.Song;
import org.jzz.musicManger.utils.XiamiCatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);
	
    public static void main(String[] args)
    {
    	if (args.length < 1) {
    		System.out.println("parms: xiami|local [pagenum|dirpath]");
    		return;
    	}
    	Integer pageNum = 1;
    	String dirPath = "./XMusic";
    	String opType = args[0];
    	switch(opType) {
    		case "xiami": {
    			try {
    				pageNum = Integer.parseInt(args[1]);
    			} catch (Exception e) {
    				pageNum = 1;
    			}
    			logger.info("从虾米抓取歌曲信息, 页数" + pageNum);
    			List<Song> songs = XiamiCatch.CatchSongInfo(pageNum);
    			if (songs != null && songs.size() > 0) {
    				XiamiCatch.CatchSongAlbumInfo(songs);
    			}
    			break;
    		} 
    		case "loacal": {
    			break;
    		}
    		default: {
    			System.out.println("parms: xiami|local [pagenum|dirpath]");
    			break;
    		}
    	}
    }
}
