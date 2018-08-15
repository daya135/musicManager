package org.jzz.musicManger.utils;


public class Song {
	
    private Integer songid;
    private String title;
    private String artist;
    private String album;
    private String band;
    private String rate;
    private String len;
    private String publishyear;
	private String downsite;
    private String onsale;
    private String langtype;
    private String filetype;
    private String isdownload;
    private String localpath;
    private String createtime;
    private String updatetime;

	public Song() {}
	
    public Integer getSongid() {
        return songid;
    }

    public void setSongid(Integer songid) {
        this.songid = songid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist == null ? null : artist.trim();
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album == null ? null : album.trim();
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band == null ? null : band.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len == null ? null : len.trim();
    }

    public String getPublishyear() {
        return publishyear;
    }

    public void setPublishyear(String publishyear) {
        this.publishyear = publishyear == null ? null : publishyear.trim();
    }

    public String getDownsite() {
        return downsite;
    }

    public void setDownsite(String downsite) {
        this.downsite = downsite == null ? null : downsite.trim();
    }

    public String getOnsale() {
        return onsale;
    }

    public void setOnsale(String onsale) {
        this.onsale = onsale == null ? null : onsale.trim();
    }

    public String getLangtype() {
        return langtype;
    }

    public void setLangtype(String langtype) {
        this.langtype = langtype == null ? null : langtype.trim();
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public String getIsdownload() {
        return isdownload;
    }

    public void setIsdownload(String isdownload) {
        this.isdownload = isdownload == null ? null : isdownload.trim();
    }

    public String getLocalpath() {
        return localpath;
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath == null ? null : localpath.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    
    @Override
    public String toString() {
        return String.format("title=[%s],artist=[%s],album=[%s],band=[%s],locakpath=[%s]", title, artist, album, band, localpath);
    }
}