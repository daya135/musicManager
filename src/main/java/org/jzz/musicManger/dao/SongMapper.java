package org.jzz.musicManger.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.jzz.musicManger.utils.Song;

public interface SongMapper {
	/* 使用SelectKey返回插入生成的主键id, 在不同的数据库中有不同的写法，同时要考虑是自增id还是uuid */
	@Insert("INSERT INTO song "
			+ "(songid, title, artist, album, band, rate, len, publishyear, downsite,"
			+ " onsale, langtype, filetype, isdownload, localpath, createtime, updatetime)" 
			+ " VALUES(#{songid}, #{title}, #{artist}, #{album}, #{band}, #{rate}, #{len},"
			+ " #{publishyear}, #{downsite}, #{onsale}, #{langtype}, #{filetype}, #{isdownload},"
			+ " #{localpath}, #{createtime}, #{updatetime})")
	@SelectKey(statement="select last_insert_rowid()", keyProperty="songid", before=false, resultType=int.class)
    @Options(useGeneratedKeys = true, keyProperty = "songid")
    int insert(Song song);
	
	@Results(id = "songs", value = {
			  @Result(property = "songid", column = "songid", id = true), @Result(property = "title", column = "title"),
			  @Result(property = "artist", column = "artist"), @Result(property = "album", column = "album"),
			  @Result(property = "band", column = "band"), @Result(property = "rate", column = "rate"),
			  @Result(property = "len", column = "len"), @Result(property = "publishyear", column = "publishyear"),
			  @Result(property = "downsite", column = "downsite"), @Result(property = "onsale", column = "onsale"),
			  @Result(property = "langtype", column = "langtype"), @Result(property = "filetype", column = "filetype"),
			  @Result(property = "isdownload", column = "isdownload"), @Result(property = "localpath", column = "localpath"),
			  @Result(property = "createtime", column = "createtime"), @Result(property = "updatetime", column = "updatetime"),
	})
	@Select("select * from song where title = #{title}")
	List<Song> selectByTitle(String title);
	
	@Update("update song set title=#{title}, artist=#{artist}, album=#{album}, band=#{band},"
			+ " rate=#{rate}, len=#{len}, publishyear=#{publishyear}, downsite=#{downsite},"
			+ " onsale=#{onsale}, langtype=#{langtype}, filetype=#{filetype}, isdownload=#{isdownload},"
			+ " localpath=#{localpath}, createtime=#{createtime}, updatetime=#{updatetime} "
			+ " where songid=#{songid}")
	int update(Song song);
	
	
}
