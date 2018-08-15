package org.jzz.musicManger;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jzz.musicManger.dao.MybatisUtils;
import org.jzz.musicManger.dao.SongMapper;
import org.jzz.musicManger.utils.Song;

public class DaoTest {
	
	public static void main(String args[]) {
		SqlSessionFactory factory = MybatisUtils.getFactory();
		SqlSession sqlSession = factory.openSession();
		
		Song song = new Song();
		song.setSongid(3);
		song.setTitle("testDao1");
		song.setBand("test1");
		song.setCreatetime("2018-08-14 00:00:00");
		SongMapper songDao=sqlSession.getMapper(SongMapper.class);
		songDao.insert(song);
//		List<Song> songs = songDao.selectByTitle("testDao");
//		for (Song s : songs) {
//			System.out.println(s.getSongid() + " " + s.getTitle());
//		}
//		songDao.update(song);
       
        // 关闭session
        sqlSession.commit();
        sqlSession.close();
	}
}
